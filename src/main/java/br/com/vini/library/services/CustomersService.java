package br.com.vini.library.services;

import br.com.vini.library.database.models.CustomersEntity;
import br.com.vini.library.database.repositories.ICustomersRepository;
import br.com.vini.library.dtos.requests.CustomersRequestDto;
import br.com.vini.library.dtos.responses.CustomersResponse;
import br.com.vini.library.exceptions.BadRequestException;
import br.com.vini.library.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersService {
    private final ICustomersRepository customersRepository;

    public Page<CustomersResponse> getAll(Pageable pageable) {
        Page<CustomersEntity> customers = customersRepository.findAll(pageable);

        if (customers.isEmpty()) {
            throw new NotFoundException("There are no customers on the system");
        }

        return customers.map(CustomersResponse::fromEntity);
    }

    public CustomersResponse getById(Integer id) {
        CustomersEntity customer = customersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        return CustomersResponse.fromEntity(customer);
    }

    public void registerCustomer(CustomersRequestDto dto) throws BadRequestException {
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

    public CustomersResponse update(Integer id, CustomersRequestDto dto) {
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