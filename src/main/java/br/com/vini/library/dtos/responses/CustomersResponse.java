package br.com.vini.library.dtos.responses;

import br.com.vini.library.database.models.CustomersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersResponse {
    private Integer id;
    private String name;
    private String email;
    private LocalDate birthDate;

    public static CustomersResponse fromEntity(CustomersEntity entity) {
        return CustomersResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
