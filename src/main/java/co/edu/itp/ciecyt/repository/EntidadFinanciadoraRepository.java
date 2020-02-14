package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.EntidadFinanciadora;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntidadFinanciadora entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntidadFinanciadoraRepository extends JpaRepository<EntidadFinanciadora, Long> {

}
