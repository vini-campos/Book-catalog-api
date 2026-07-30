package br.com.vini.library.database.repositories;

import br.com.vini.library.database.models.AuthorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorsRepository extends JpaRepository<AuthorsEntity, Integer> {
    boolean existsByName(String name);
}
