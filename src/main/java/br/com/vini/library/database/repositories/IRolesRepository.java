package br.com.vini.library.database.repositories;

import br.com.vini.library.database.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {
    Optional<RolesEntity> findByName(String role);
}
