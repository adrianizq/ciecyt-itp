package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.FasesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.Fases}.
 */
public interface FasesService {

    /**
     * Save a fases.
     *
     * @param fasesDTO the entity to save.
     * @return the persisted entity.
     */
    FasesDTO save(FasesDTO fasesDTO);

    /**
     * Get all the fases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FasesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fases.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FasesDTO> findOne(Long id);

    /**
     * Delete the "id" fases.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
