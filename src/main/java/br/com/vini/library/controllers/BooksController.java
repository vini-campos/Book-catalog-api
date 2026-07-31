package br.com.vini.library.controllers;

import br.com.vini.library.dtos.BooksDto;
import br.com.vini.library.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/books")
public class BooksController {
    private final BooksService booksService;

    @GetMapping("/findALl")
    public ResponseEntity<List<BooksDto>> getAll() {
        return ResponseEntity.ok(booksService.getAll());
    }
}
