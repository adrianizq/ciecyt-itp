package co.edu.itp.ciecyt.service;
import co.edu.itp.ciecyt.domain.IntegranteProyecto;

import co.edu.itp.ciecyt.service.dto.IntegranteProyectoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.IntegranteProyecto}.
 */
public interface IntegranteProyectoService {

    /**
     * Save a integranteProyecto.
     *
     * @param integranteProyectoDTO the entity to save.
     * @return the persisted entity.
     */
    IntegranteProyectoDTO save(IntegranteProyectoDTO integranteProyectoDTO);

    /**
     * Get all the integranteProyectos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IntegranteProyectoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" integranteProyecto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IntegranteProyectoDTO> findOne(Long id);

    /**
     * Delete the "id" integranteProyecto.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<IntegranteProyectoDTO> findByIntegranteProyectoProyectoId(Long idProyecto) throws Exception ;
    List<IntegranteProyectoDTO> findByIntegranteProyectoProyectoIdAndIntegranteProyectoRolesModalidadIdIn(Long idProyecto, Long idRolModalidad) throws Exception ;

}
