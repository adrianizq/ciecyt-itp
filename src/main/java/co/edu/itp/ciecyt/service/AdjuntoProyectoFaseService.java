package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.AdjuntoProyectoFaseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.AdjuntoProyectoFase}.
 */
public interface AdjuntoProyectoFaseService {
    /**
     * Save a adjuntoProyectoFase.
     *
     * @param adjuntoProyectoFaseDTO the entity to save.
     * @return the persisted entity.
     */
    AdjuntoProyectoFaseDTO save(AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO);

    /**
     * Get all the adjuntoProyectoFases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdjuntoProyectoFaseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" adjuntoProyectoFase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdjuntoProyectoFaseDTO> findOne(Long id);

    /**
     * Delete the "id" adjuntoProyectoFase.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
