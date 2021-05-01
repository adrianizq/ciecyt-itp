package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.ElementoModalidad;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ElementoModalidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElementoModalidadRepository extends JpaRepository<ElementoModalidad, Long> {
}
