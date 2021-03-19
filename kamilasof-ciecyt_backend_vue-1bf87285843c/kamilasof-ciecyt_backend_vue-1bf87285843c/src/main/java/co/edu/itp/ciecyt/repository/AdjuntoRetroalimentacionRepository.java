package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.AdjuntoRetroalimentacion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjuntoRetroalimentacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjuntoRetroalimentacionRepository extends JpaRepository<AdjuntoRetroalimentacion, Long> {
}
