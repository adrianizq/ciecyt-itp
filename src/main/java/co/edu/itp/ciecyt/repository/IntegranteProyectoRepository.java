package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.IntegranteProyecto;
import co.edu.itp.ciecyt.service.dto.IntegranteProyectoDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    IntegranteProyecto findByIdOrderById(Long id);
    //devuelve los IntegranteProyecto de un idUsuario
    List<IntegranteProyecto> findByIntegranteProyectoUserId(Long idUsuario);
    //List<IntegranteProyecto> findByIntegranteProyectoUserIdOrderById(Long idUsuario, String authority);
    //No
    //List<IntegranteProyecto> findByIntegranteProyectoUserIdAndIntegranteProyectoRolesModalidadIdIn(Long idUsuario, Long idRolModalidad);


}
