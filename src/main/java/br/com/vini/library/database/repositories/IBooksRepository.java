package br.com.vini.library.database.repositories;

import br.com.vini.library.database.models.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IBooksRepository extends JpaRepository<BooksEntity, Integer> {
    Optional<BooksEntity> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    @Transactional
    void deleteByIsbn(String isbn);
}
