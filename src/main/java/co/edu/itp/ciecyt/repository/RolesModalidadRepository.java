package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.ImpactosEsperados;
import co.edu.itp.ciecyt.domain.RolesModalidad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RolesModalidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RolesModalidadRepository extends JpaRepository<RolesModalidad, Long> {
    RolesModalidad findByRolAndRolesModalidadModalidadId(String rol, Long rolModalidad);


}
