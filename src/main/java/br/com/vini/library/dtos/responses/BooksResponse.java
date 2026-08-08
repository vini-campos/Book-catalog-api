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
    private boolean borrowed;
    private String authorName;
    private  String currentOwnerName;

    public static BooksResponse fromEntity(BooksEntity entity) {
        return BooksResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isbn(entity.getIsbn())
                .ageGroup(entity.getAgeGroup())
                .borrowed(entity.isBorrowed())
                .authorName(entity.getAuthor().getName())
                .currentOwnerName(entity.getCurrentOwner() == null ? null : entity.getCurrentOwner().getName())
                .build();
    }
}
