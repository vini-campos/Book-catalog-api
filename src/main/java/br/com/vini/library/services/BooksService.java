package br.com.vini.library.services;

import br.com.vini.library.database.models.AuthorsEntity;
import br.com.vini.library.database.models.BooksEntity;
import br.com.vini.library.database.repositories.IAuthorsRepository;
import br.com.vini.library.database.repositories.IBooksRepository;
import br.com.vini.library.dtos.requests.BooksDto;
import br.com.vini.library.dtos.responses.BooksResponse;
import br.com.vini.library.exceptions.BadRequestException;
import br.com.vini.library.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BooksService {
    private final IBooksRepository booksRepository;
    private final IAuthorsRepository authorsRepository;

    public List<BooksResponse> getAll() {
        List<BooksEntity> books = booksRepository.findAll();

        if (books.isEmpty()) {
            throw new NotFoundException("There are no books on the system");
        }

        return books.stream()
                .map(BooksResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public BooksResponse getById(Integer id) {
        BooksEntity book = booksRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        return BooksResponse.fromEntity(book);
    }

    public void registerBook(BooksDto dto) throws BadRequestException {
        if (booksRepository.existsByIsbn(dto.getIsbn())) {
            throw new BadRequestException("A book with this ISBN already exists");
        }

        AuthorsEntity author = authorsRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));

        BooksEntity book = BooksEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .isbn(dto.getIsbn())
                .ageGroup(dto.getAgeGroup())
                .isBorrowed(dto.getIsBorrowed())
                .author(author)
                .build();

        booksRepository.save(book);
    }

    public BooksResponse update(String isbn, BooksDto dto) throws NotFoundException {
        BooksEntity book = booksRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book not found to update"));

        book.setName(dto.getName());
        book.setDescription(dto.getDescription());
        book.setIsbn(dto.getIsbn());
        book.setAgeGroup(dto.getAgeGroup());
        book.setBorrowed(dto.getIsBorrowed());

        AuthorsEntity author = authorsRepository.findById(dto.getAuthorId())
                        .orElseThrow(() -> new NotFoundException("Author not found"));

        book.setAuthor(author);

        return BooksResponse.fromEntity(booksRepository.save(book));
    }

    public void deleteBook(String isbn) {
        if (booksRepository.findByIsbn(isbn).isEmpty()) {
            throw new NotFoundException("Book not found to delete");
        }

        booksRepository.deleteByIsbn(isbn);
    }
}