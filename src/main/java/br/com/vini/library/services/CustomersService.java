package br.com.vini.library.services;

import br.com.vini.library.database.models.CustomersEntity;
import br.com.vini.library.database.repositories.ICustomersRepository;
import br.com.vini.library.dtos.requests.CustomersDto;
import br.com.vini.library.dtos.responses.CustomersResponse;
import br.com.vini.library.exceptions.BadRequestException;
import br.com.vini.library.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomersService {
    private final ICustomersRepository customersRepository;

    public List<CustomersResponse> getAll() {
        List<CustomersEntity> customers = customersRepository.findAll();

        if (customers.isEmpty()) {
            throw new NotFoundException("There are no customers on the system");
        }

        return customers.stream()
                .map(CustomersResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CustomersResponse getById(Integer id) {
        CustomersEntity customer = customersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        return CustomersResponse.fromEntity(customer);
    }

    public void registerCustomer(CustomersDto dto) throws BadRequestException {
        if (customersRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("A customer with this email already exists");
        }

        CustomersEntity customer = CustomersEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .build();

        customersRepository.save(customer);
    }

    public CustomersResponse update(Integer id, CustomersDto dto) {
        CustomersEntity customer = customersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setBirthDate(dto.getBirthDate());

        return CustomersResponse.fromEntity(customersRepository.save(customer));
    }

    public void deleteCustomer(Integer id) {
        if (!customersRepository.existsById(id)) {
            throw new NotFoundException("Customer not found");
        }

        customersRepository.deleteById(id);
    }
}
