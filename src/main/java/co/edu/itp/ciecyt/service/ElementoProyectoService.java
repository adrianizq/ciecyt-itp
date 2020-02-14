package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.ElementoProyectoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.ElementoProyecto}.
 */
public interface ElementoProyectoService {

    /**
     * Save a elementoProyecto.
     *
     * @param elementoProyectoDTO the entity to save.
     * @return the persisted entity.
     */
    ElementoProyectoDTO save(ElementoProyectoDTO elementoProyectoDTO);

    /**
     * Get all the elementoProyectos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElementoProyectoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" elementoProyecto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElementoProyectoDTO> findOne(Long id);

    /**
     * Delete the "id" elementoProyecto.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
