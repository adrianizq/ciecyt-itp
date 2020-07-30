package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.IntegranteProyecto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the IntegranteProyecto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntegranteProyectoRepository extends JpaRepository<IntegranteProyecto, Long> {

    @Query("select integranteProyecto from IntegranteProyecto integranteProyecto where integranteProyecto.integranteProyectoUser.login = ?#{principal.username}")
    List<IntegranteProyecto> findByIntegranteProyectoUserIsCurrentUser();
    List<IntegranteProyecto> findByIntegranteProyectoProyectoId(Long idProyecto);
    List<IntegranteProyecto> findByIntegranteProyectoProyectoIdAndIntegranteProyectoRolesModalidadIdIn(Long idProyecto, Long idRolModalidad);

}
