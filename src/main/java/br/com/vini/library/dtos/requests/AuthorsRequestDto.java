package br.com.vini.library.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AuthorsRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String biography;
}
