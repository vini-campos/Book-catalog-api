package br.com.vini.library.controllers;

import br.com.vini.library.dtos.requests.BooksRequestDto;
import br.com.vini.library.dtos.responses.BooksResponse;
import br.com.vini.library.services.BooksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/books")
public class BooksController {
    private final BooksService booksService;

    @GetMapping
    public ResponseEntity<Page<BooksResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(booksService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(booksService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerBook(@Valid @RequestBody BooksRequestDto booksDto) {
        booksService.registerBook(booksDto);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BooksResponse> updateBook(@Valid @PathVariable("isbn") String isbn, @RequestBody BooksRequestDto booksDto) {
        return ResponseEntity.ok(booksService.update(isbn, booksDto));
    }

    @PatchMapping("/{id}/borrow/{isbn}")
    public ResponseEntity<BooksResponse> borrowBook(@Valid @PathVariable("id") Integer id, @PathVariable String isbn) {
        return ResponseEntity.ok(booksService.borrowBook(id, isbn));
    }

    @PatchMapping("/{id}/return/{isbn}")
    public ResponseEntity<BooksResponse> returnBook(@Valid @PathVariable("id") Integer id, @PathVariable("isbn") String isbn) {
        return ResponseEntity.ok(booksService.returnBook(id, isbn));
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("isbn") String isbn) {
        booksService.deleteBook(isbn);
    }
}
