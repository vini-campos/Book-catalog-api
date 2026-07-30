package br.com.vini.library.dtos;

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
}
