package br.com.vini.library.controllers;

import br.com.vini.library.dtos.requests.BooksDto;
import br.com.vini.library.dtos.responses.BooksResponse;
import br.com.vini.library.services.BooksService;
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
@RequestMapping("/v1/books")
public class BooksController {
    private final BooksService booksService;

    @GetMapping
    public ResponseEntity<List<BooksResponse>> getAll() {
        return ResponseEntity.ok(booksService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(booksService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerBook(@Valid @RequestBody BooksDto booksDto) {
        booksService.registerBook(booksDto);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BooksResponse> updateBook(@Valid @PathVariable("isbn") String isbn, @RequestBody BooksDto booksDto) {
        return ResponseEntity.ok(booksService.update(isbn, booksDto));
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(String isbn) {
        booksService.deleteBook(isbn);
    }
}
