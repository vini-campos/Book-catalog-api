package br.com.vini.library.services;

import br.com.vini.library.database.models.BooksEntity;
import br.com.vini.library.database.repositories.IBooksRepository;
import br.com.vini.library.dtos.BooksDto;
import br.com.vini.library.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BooksService {
    private final IBooksRepository booksRepository;

    public List<BooksDto> getAll() {
        List<BooksEntity> books = booksRepository.findAll();

        if (books.isEmpty()) {
            throw new NotFoundException("There are no books on the system");
        }

        return books.stream()
                .map(BooksDto::fromEntity)
                .collect(Collectors.toList());
    }
}
