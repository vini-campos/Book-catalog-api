package br.com.vini.library.controllers;

import br.com.vini.library.dtos.requests.AuthorsDto;
import br.com.vini.library.dtos.responses.AuthorsResponse;
import br.com.vini.library.services.AuthorsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping
    public ResponseEntity<Page<AuthorsResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(authorsService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorsResponse> getById(@Valid @PathVariable("id") Integer id) {
        return ResponseEntity.ok(authorsService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAuthor(@Valid @RequestBody AuthorsDto authorsDto) {
        authorsService.registerAuthor(authorsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorsResponse> updateAuthor(@Valid @PathVariable("id") Integer id, @RequestBody  AuthorsDto authorsDto) {
        return ResponseEntity.ok(authorsService.update(id, authorsDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("id") Integer id) {
        authorsService.deleteAuthor(id);
    }
}
