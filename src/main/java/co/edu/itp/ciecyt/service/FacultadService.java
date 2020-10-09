package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.FacultadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.Facultad}.
 */
public interface FacultadService {

    /**
     * Save a facultad.
     *
     * @param facultadDTO the entity to save.
     * @return the persisted entity.
     */
    FacultadDTO save(FacultadDTO facultadDTO);

    /**
     * Get all the facultads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FacultadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" facultad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FacultadDTO> findOne(Long id);

    /**
     * Delete the "id" facultad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
