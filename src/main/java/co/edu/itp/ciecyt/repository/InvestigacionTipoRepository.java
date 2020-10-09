package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.InvestigacionTipo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvestigacionTipo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestigacionTipoRepository extends JpaRepository<InvestigacionTipo, Long> {
}
