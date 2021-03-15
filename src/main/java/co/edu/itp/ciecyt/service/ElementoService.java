package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.service.dto.ElementoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.itp.ciecyt.domain.Elemento}.
 */
public interface ElementoService {

    /**
     * Save a elemento.
     *
     * @param elementoDTO the entity to save.
     * @return the persisted entity.
     */
    ElementoDTO save(ElementoDTO elementoDTO);

    /**
     * Get all the elementos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElementoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" elemento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElementoDTO> findOne(Long id);

    /**
     * Delete the "id" elemento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

   // public List<ElementoDTO> findByElementoModalidadId(Long idModalidad) throws Exception;
   public List<ElementoDTO> findByElementoFasesId(Long idFase) throws Exception;
}
