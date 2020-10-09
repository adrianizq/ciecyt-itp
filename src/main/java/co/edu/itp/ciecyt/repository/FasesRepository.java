package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.Fases;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Fases entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FasesRepository extends JpaRepository<Fases, Long> {

}
