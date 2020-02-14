package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.AdjuntoRetroalimentacionService;
import co.edu.itp.ciecyt.domain.AdjuntoRetroalimentacion;
import co.edu.itp.ciecyt.repository.AdjuntoRetroalimentacionRepository;
import co.edu.itp.ciecyt.service.dto.AdjuntoRetroalimentacionDTO;
import co.edu.itp.ciecyt.service.mapper.AdjuntoRetroalimentacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdjuntoRetroalimentacion}.
 */
@Service
@Transactional
public class AdjuntoRetroalimentacionServiceImpl implements AdjuntoRetroalimentacionService {

    private final Logger log = LoggerFactory.getLogger(AdjuntoRetroalimentacionServiceImpl.class);

    private final AdjuntoRetroalimentacionRepository adjuntoRetroalimentacionRepository;

    private final AdjuntoRetroalimentacionMapper adjuntoRetroalimentacionMapper;

    public AdjuntoRetroalimentacionServiceImpl(AdjuntoRetroalimentacionRepository adjuntoRetroalimentacionRepository, AdjuntoRetroalimentacionMapper adjuntoRetroalimentacionMapper) {
        this.adjuntoRetroalimentacionRepository = adjuntoRetroalimentacionRepository;
        this.adjuntoRetroalimentacionMapper = adjuntoRetroalimentacionMapper;
    }

    /**
     * Save a adjuntoRetroalimentacion.
     *
     * @param adjuntoRetroalimentacionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdjuntoRetroalimentacionDTO save(AdjuntoRetroalimentacionDTO adjuntoRetroalimentacionDTO) {
        log.debug("Request to save AdjuntoRetroalimentacion : {}", adjuntoRetroalimentacionDTO);
        AdjuntoRetroalimentacion adjuntoRetroalimentacion = adjuntoRetroalimentacionMapper.toEntity(adjuntoRetroalimentacionDTO);
        adjuntoRetroalimentacion = adjuntoRetroalimentacionRepository.save(adjuntoRetroalimentacion);
        return adjuntoRetroalimentacionMapper.toDto(adjuntoRetroalimentacion);
    }

    /**
     * Get all the adjuntoRetroalimentacions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdjuntoRetroalimentacionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdjuntoRetroalimentacions");
        return adjuntoRetroalimentacionRepository.findAll(pageable)
            .map(adjuntoRetroalimentacionMapper::toDto);
    }


    /**
     * Get one adjuntoRetroalimentacion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdjuntoRetroalimentacionDTO> findOne(Long id) {
        log.debug("Request to get AdjuntoRetroalimentacion : {}", id);
        return adjuntoRetroalimentacionRepository.findById(id)
            .map(adjuntoRetroalimentacionMapper::toDto);
    }

    /**
     * Delete the adjuntoRetroalimentacion by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdjuntoRetroalimentacion : {}", id);
        adjuntoRetroalimentacionRepository.deleteById(id);
    }
}
