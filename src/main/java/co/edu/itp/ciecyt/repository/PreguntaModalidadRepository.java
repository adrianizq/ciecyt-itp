package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.PreguntaModalidad;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PreguntaModalidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreguntaModalidadRepository extends JpaRepository<PreguntaModalidad, Long> {
}
