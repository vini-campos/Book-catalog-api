package br.com.vini.library.dtos;

import br.com.vini.library.database.models.AuthorsEntity;
import br.com.vini.library.database.models.BooksEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AuthorsDto {
    @NotBlank
    private String name;

    @NotBlank
    private String biography;

    @NotNull
    private List<BooksEntity> books;

    public static AuthorsDto fromEntity(AuthorsEntity entity) {
        return AuthorsDto.builder()
                .name(entity.getName())
                .biography(entity.getBiography())
                .books(entity.getBooks())
                .build();
    }
}
