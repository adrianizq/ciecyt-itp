package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.Proyecto}.
 */
public interface ProyectoService {

    /**
     * Save a proyecto.
     *
     * @param proyectoDTO the entity to save.
     * @return the persisted entity.
     */
    ProyectoDTO save(ProyectoDTO proyectoDTO);

    ProyectoDTO saveAsesorProyecto (ProyectoDTO proyectoDTO);

    /**
     * Get all the proyectos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProyectoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proyecto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProyectoDTO> findOne(Long id);

    @Transactional(readOnly = true)
    ProyectoDTO findOneWithAsesor(Long idProyecto, Long idRolModalidad) throws Exception;
    //Proyecto findOneWithAsesor(Long id);

    /**
     * Delete the "id" proyecto.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
