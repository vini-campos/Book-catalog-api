package br.com.vini.library.database.repositories;

import br.com.vini.library.database.models.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomersRepository extends JpaRepository<CustomersEntity, Integer> {
    boolean existsByEmail(String email);
    Optional<CustomersEntity> findByEmail(String email);
}
