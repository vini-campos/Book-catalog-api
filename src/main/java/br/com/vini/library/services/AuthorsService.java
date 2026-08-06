package br.com.vini.library.services;

import br.com.vini.library.database.models.AuthorsEntity;
import br.com.vini.library.database.repositories.IAuthorsRepository;
import br.com.vini.library.dtos.requests.AuthorsDto;
import br.com.vini.library.dtos.responses.AuthorsResponse;
import br.com.vini.library.exceptions.BadRequestException;
import br.com.vini.library.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorsService {
    private final IAuthorsRepository authorsRepository;

    public List<AuthorsResponse> getAll() throws NotFoundException {
        List<AuthorsEntity> authors = authorsRepository.findAll();

        if (authors.isEmpty()) {
            throw new NotFoundException("There are no authors on the system");
        }

        return authors.stream()
                .map(AuthorsResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public AuthorsResponse getById(Integer id) throws NotFoundException {
        AuthorsEntity author = authorsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));

        return AuthorsResponse.fromEntity(author);
    }

    public void registerAuthor(AuthorsDto dto) throws BadRequestException {
        if (authorsRepository.existsByName(dto.getName())) {
            throw new BadRequestException("An author with this name already exists");
        }

        AuthorsEntity author = AuthorsEntity.builder()
                .name(dto.getName())
                .biography(dto.getBiography())
                .build();

        authorsRepository.save(author);
    }

    public AuthorsResponse update(Integer id, AuthorsDto dto) throws NotFoundException {
        AuthorsEntity author = authorsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("author not found to update"));

        author.setName(dto.getName());
        author.setBiography(dto.getBiography());

        return AuthorsResponse.fromEntity(authorsRepository.save(author));
    }

    public void deleteAuthor(Integer id) throws NotFoundException {
        if (authorsRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Author not found to delete");
        }

        authorsRepository.deleteById(id);
    }
}
