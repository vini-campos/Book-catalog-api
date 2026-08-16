package br.com.vini.library.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomersRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private LocalDate birthDate;
}
