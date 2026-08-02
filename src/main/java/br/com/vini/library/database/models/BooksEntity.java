package br.com.vini.library.database.models;

import br.com.vini.library.enums.AgeGroupEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "age_group")
    private AgeGroupEnum ageGroup;

    @Column(nullable = false, name = "is_borrowed")
    private boolean isBorrowed;

    @ManyToOne
    @JoinColumn(name = "authors_id")
    private AuthorsEntity author;
}
