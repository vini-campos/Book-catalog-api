package br.com.vini.library.services;

import br.com.vini.library.database.models.CustomersEntity;
import br.com.vini.library.database.repositories.ICustomersRepository;
import br.com.vini.library.dtos.CustomersDto;
import br.com.vini.library.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersService {
    private final ICustomersRepository customersRepository;

    public void registerCustomer(CustomersDto customersDto) throws BadRequestException {
        if (customersRepository.existsByEmail(customersDto.getEmail())) {
            throw new BadRequestException("A customer with this email already exists");
        }

        CustomersEntity customer = CustomersEntity.builder()
                .name(customersDto.getName())
                .email(customersDto.getEmail())
                .birthDate(customersDto.getBirthDate())
                .build();

        customersRepository.save(customer);
    }
}
