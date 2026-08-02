package br.com.vini.library.dtos.responses;

import br.com.vini.library.database.models.BooksEntity;
import br.com.vini.library.enums.AgeGroupEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BooksResponse {
    private Integer id;
    private String name;
    private String description;
    private String isbn;
    private AgeGroupEnum ageGroup;
    private boolean isBorrowed;
    private String authorName;

    public static BooksResponse fromEntity(BooksEntity entity) {
        return BooksResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isbn(entity.getIsbn())
                .ageGroup(entity.getAgeGroup())
                .isBorrowed(entity.isBorrowed())
                .authorName(entity.getAuthor().getName())
                .build();
    }
}
