package br.com.vini.library.dtos.responses;

import br.com.vini.library.database.models.AuthorsEntity;
import br.com.vini.library.database.models.BooksEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorsResponse {
    private Integer id;
    private String name;
    private String biography;
    private List<String> books;

    public static AuthorsResponse fromEntity(AuthorsEntity entity) {
        return AuthorsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .biography(entity.getBiography())
                .books(entity.getBooks()
                        .stream()
                        .map(BooksEntity::getName)
                        .toList())
                .build();
    }
}
