package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.ElementoModalidadDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.ElementoModalidad}.
 */
public interface ElementoModalidadService {

    /**
     * Save a elementoModalidad.
     *
     * @param elementoModalidadDTO the entity to save.
     * @return the persisted entity.
     */
    ElementoModalidadDTO save(ElementoModalidadDTO elementoModalidadDTO);

    /**
     * Get all the elementoModalidads.
     *
     * @return the list of entities.
     */
    List<ElementoModalidadDTO> findAll();


    /**
     * Get the "id" elementoModalidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElementoModalidadDTO> findOne(Long id);

    /**
     * Delete the "id" elementoModalidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
