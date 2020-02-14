package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.ModalidadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.Modalidad}.
 */
public interface ModalidadService {

    /**
     * Save a modalidad.
     *
     * @param modalidadDTO the entity to save.
     * @return the persisted entity.
     */
    ModalidadDTO save(ModalidadDTO modalidadDTO);

    /**
     * Get all the modalidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ModalidadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" modalidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ModalidadDTO> findOne(Long id);

    /**
     * Delete the "id" modalidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
