package br.com.vini.library.dtos;

import br.com.vini.library.database.models.CustomersEntity;
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
public class CustomersDto {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private LocalDate birthDate;

    public static CustomersDto fromEntity(CustomersEntity entity) {
        return CustomersDto.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
