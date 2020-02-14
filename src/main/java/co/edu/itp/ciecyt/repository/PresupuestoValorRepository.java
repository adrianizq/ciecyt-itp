package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.PresupuestoValor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PresupuestoValor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PresupuestoValorRepository extends JpaRepository<PresupuestoValor, Long> {

}
