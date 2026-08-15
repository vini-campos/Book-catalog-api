package br.com.vini.library.database.repositories;

import br.com.vini.library.database.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {

}
