package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.IntegranteProyectoService;
import co.edu.itp.ciecyt.domain.IntegranteProyecto;
import co.edu.itp.ciecyt.repository.IntegranteProyectoRepository;
import co.edu.itp.ciecyt.service.dto.IntegranteProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.IntegranteProyectoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link IntegranteProyecto}.
 */
@Service
@Transactional
public class IntegranteProyectoServiceImpl implements IntegranteProyectoService {

    private final Logger log = LoggerFactory.getLogger(IntegranteProyectoServiceImpl.class);

    private final IntegranteProyectoRepository integranteProyectoRepository;

    private final IntegranteProyectoMapper integranteProyectoMapper;

    public IntegranteProyectoServiceImpl(IntegranteProyectoRepository integranteProyectoRepository, IntegranteProyectoMapper integranteProyectoMapper) {
        this.integranteProyectoRepository = integranteProyectoRepository;
        this.integranteProyectoMapper = integranteProyectoMapper;
    }

    /**
     * Save a integranteProyecto.
     *
     * @param integranteProyectoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public IntegranteProyectoDTO save(IntegranteProyectoDTO integranteProyectoDTO) {
        log.debug("Request to save IntegranteProyecto : {}", integranteProyectoDTO);
        IntegranteProyecto integranteProyecto = integranteProyectoMapper.toEntity(integranteProyectoDTO);
        integranteProyecto = integranteProyectoRepository.save(integranteProyecto);
        return integranteProyectoMapper.toDto(integranteProyecto);
    }

    /**
     * Get all the integranteProyectos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IntegranteProyectoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IntegranteProyectos");
        return integranteProyectoRepository.findAll(pageable)
            .map(integranteProyectoMapper::toDto);
    }


    /**
     * Get one integranteProyecto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IntegranteProyectoDTO> findOne(Long id) {
        log.debug("Request to get IntegranteProyecto : {}", id);
        return integranteProyectoRepository.findById(id)
            .map(integranteProyectoMapper::toDto);
    }

    /**
     * Delete the integranteProyecto by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IntegranteProyecto : {}", id);
        integranteProyectoRepository.deleteById(id);
    }
}
