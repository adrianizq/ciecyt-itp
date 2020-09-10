package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.Elemento;
import co.edu.itp.ciecyt.domain.Pregunta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Pregunta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByPreguntaModalidadId(Long idModalidad);
}
