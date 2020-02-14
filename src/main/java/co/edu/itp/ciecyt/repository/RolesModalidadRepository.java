package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.RolesModalidad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RolesModalidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RolesModalidadRepository extends JpaRepository<RolesModalidad, Long> {

}
