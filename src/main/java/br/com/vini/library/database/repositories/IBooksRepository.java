package br.com.vini.library.database.repositories;

import br.com.vini.library.database.models.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBooksRepository extends JpaRepository<BooksEntity, Integer> {
    boolean existsByName(String name);
}
