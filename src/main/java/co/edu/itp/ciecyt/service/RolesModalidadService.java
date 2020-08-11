package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.domain.RolesModalidad;
import co.edu.itp.ciecyt.service.dto.RolesModalidadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.RolesModalidad}.
 */
public interface RolesModalidadService {

    /**
     * Save a rolesModalidad.
     *
     * @param rolesModalidadDTO the entity to save.
     * @return the persisted entity.
     */
    RolesModalidadDTO save(RolesModalidadDTO rolesModalidadDTO);

    /**
     * Get all the rolesModalidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RolesModalidadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rolesModalidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RolesModalidadDTO> findOne(Long id);

    /**
     * Delete the "id" rolesModalidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);


    RolesModalidadDTO findByRolAndRolesModalidadModalidadId(String rol, Long rolModalidad) throws Exception;
}
