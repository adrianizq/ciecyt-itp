package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.ProyectoRespuestas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProyectoRespuestas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProyectoRespuestasRepository extends JpaRepository<ProyectoRespuestas, Long> {

}
