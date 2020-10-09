package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.AdjuntoProyectoFase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdjuntoProyectoFase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjuntoProyectoFaseRepository extends JpaRepository<AdjuntoProyectoFase, Long> {

}
