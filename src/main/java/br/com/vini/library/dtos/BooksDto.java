package br.com.vini.library.dtos;

import br.com.vini.library.enums.AgeGroupEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BooksDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private AgeGroupEnum ageGroup;

    @NotNull
    private Boolean isBorrowed;

    @NotNull
    private String authorName;
}
