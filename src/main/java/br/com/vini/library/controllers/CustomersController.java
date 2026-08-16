package br.com.vini.library.controllers;

import br.com.vini.library.dtos.requests.CustomersRequestDto;
import br.com.vini.library.dtos.responses.CustomersResponse;
import br.com.vini.library.services.CustomersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomersController {
    private final CustomersService customersService;

    @GetMapping
    public ResponseEntity<Page<CustomersResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(customersService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomersResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(customersService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@Valid @RequestBody CustomersRequestDto customersDto) {
        customersService.registerCustomer(customersDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomersResponse> updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomersRequestDto customersDto) {
        return ResponseEntity.ok(customersService.update(id, customersDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        customersService.deleteCustomer(id);
    }
}
