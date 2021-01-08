package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.Elemento;
import co.edu.itp.ciecyt.domain.Pregunta;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Pregunta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByPreguntaModalidadId(Long idModalidad);
    List<Pregunta> findByPreguntaModalidadIdAndPreguntaFaseId(Long idModalidad, Long idFase);
}
