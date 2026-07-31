package br.com.vini.library.controllers;

import br.com.vini.library.dtos.CustomersDto;
import br.com.vini.library.exceptions.BadRequestException;
import br.com.vini.library.services.CustomersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/customers")
public class CustomersController {
    private final CustomersService customersService;

    @GetMapping("/findAll")
    public ResponseEntity<List<CustomersDto>> getAll() {
        return ResponseEntity.ok(customersService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomersDto> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customersService.getById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@Valid @RequestBody CustomersDto customersDto) throws BadRequestException {
        customersService.registerCustomer(customersDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomersDto> updateCustomer(@Valid @PathVariable("id") Integer id, @RequestBody CustomersDto dto) {
        return ResponseEntity.ok(customersService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@Valid @PathVariable("id") Integer id) {
        customersService.deleteCustomer(id);
    }
}
