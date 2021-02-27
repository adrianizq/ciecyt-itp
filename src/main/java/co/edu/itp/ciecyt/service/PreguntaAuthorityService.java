package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.PreguntaAuthorityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.PreguntaAuthority}.
 */
public interface PreguntaAuthorityService {

    /**
     * Save a preguntaAuthority.
     *
     * @param preguntaAuthorityDTO the entity to save.
     * @return the persisted entity.
     */
    PreguntaAuthorityDTO save(PreguntaAuthorityDTO preguntaAuthorityDTO);

    /**
     * Get all the preguntaAuthorities.
     *
     * @return the list of entities.
     */
    List<PreguntaAuthorityDTO> findAll();


    /**
     * Get the "id" preguntaAuthority.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PreguntaAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" preguntaAuthority.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
