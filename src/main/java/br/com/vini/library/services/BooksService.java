package br.com.vini.library.services;

import br.com.vini.library.database.models.AuthorsEntity;
import br.com.vini.library.database.models.BooksEntity;
import br.com.vini.library.database.models.CustomersEntity;
import br.com.vini.library.database.repositories.IAuthorsRepository;
import br.com.vini.library.database.repositories.IBooksRepository;
import br.com.vini.library.database.repositories.ICustomersRepository;
import br.com.vini.library.dtos.requests.BooksDto;
import br.com.vini.library.dtos.responses.BooksResponse;
import br.com.vini.library.exceptions.BadRequestException;
import br.com.vini.library.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BooksService {
    private final IBooksRepository booksRepository;
    private final IAuthorsRepository authorsRepository;
    private final ICustomersRepository customersRepository;

    public Page<BooksResponse> getAll(Pageable pageable) throws NotFoundException {
        Page<BooksEntity> books = booksRepository.findAll(pageable);

        if (books.isEmpty()) {
            throw new NotFoundException("There are no books on the system");
        }

        return books.map(BooksResponse::fromEntity);
    }

    public BooksResponse getById(Integer id) throws NotFoundException {
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
                .borrowed(dto.getIsBorrowed())
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

    public BooksResponse borrowBook(Integer customerId, String isbn) throws NotFoundException {
        CustomersEntity customer = customersRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("customer not found"));

        boolean alreadyHasBook = customer.getBooksBorrowed() != null
                && !customer.getBooksBorrowed().isEmpty();

        if (alreadyHasBook) throw new BadRequestException("Customer already has a borrowed book");

        BooksEntity book = booksRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        if (book.isBorrowed()) throw new BadRequestException("Book is already borrowed");

        book.setBorrowed(true);
        book.setCurrentOwner(customer);
        booksRepository.save(book);

        return BooksResponse.fromEntity(book);
    }

    public BooksResponse returnBook(Integer customerId, String isbn) throws NotFoundException {
        CustomersEntity customer = customersRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        boolean hasNoBook = customer.getBooksBorrowed() == null
                || customer.getBooksBorrowed().isEmpty();

        if (hasNoBook) throw new BadRequestException("Customer has no book borrowed");

        BooksEntity book = booksRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        if (!book.isBorrowed()) throw new BadRequestException("Book is not borrowed");

        book.setBorrowed(false);
        book.setCurrentOwner(null);
        booksRepository.save(book);

        return BooksResponse.fromEntity(book);
    }

    public void deleteBook(String isbn) throws NotFoundException {
        if (booksRepository.findByIsbn(isbn).isEmpty()) {
            throw new NotFoundException("Book not found to delete");
        }

        booksRepository.deleteByIsbn(isbn);
    }
}