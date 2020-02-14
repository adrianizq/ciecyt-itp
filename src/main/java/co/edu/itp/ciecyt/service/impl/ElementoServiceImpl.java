package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.ElementoService;
import co.edu.itp.ciecyt.domain.Elemento;
import co.edu.itp.ciecyt.repository.ElementoRepository;
import co.edu.itp.ciecyt.service.dto.ElementoDTO;
import co.edu.itp.ciecyt.service.mapper.ElementoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Elemento}.
 */
@Service
@Transactional
public class ElementoServiceImpl implements ElementoService {

    private final Logger log = LoggerFactory.getLogger(ElementoServiceImpl.class);

    private final ElementoRepository elementoRepository;

    private final ElementoMapper elementoMapper;

    public ElementoServiceImpl(ElementoRepository elementoRepository, ElementoMapper elementoMapper) {
        this.elementoRepository = elementoRepository;
        this.elementoMapper = elementoMapper;
    }

    /**
     * Save a elemento.
     *
     * @param elementoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ElementoDTO save(ElementoDTO elementoDTO) {
        log.debug("Request to save Elemento : {}", elementoDTO);
        Elemento elemento = elementoMapper.toEntity(elementoDTO);
        elemento = elementoRepository.save(elemento);
        return elementoMapper.toDto(elemento);
    }

    /**
     * Get all the elementos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ElementoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Elementos");
        return elementoRepository.findAll(pageable)
            .map(elementoMapper::toDto);
    }


    /**
     * Get one elemento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElementoDTO> findOne(Long id) {
        log.debug("Request to get Elemento : {}", id);
        return elementoRepository.findById(id)
            .map(elementoMapper::toDto);
    }

    /**
     * Delete the elemento by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Elemento : {}", id);
        elementoRepository.deleteById(id);
    }
}
