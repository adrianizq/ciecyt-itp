package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.ProyectoRespuestasService;
import co.edu.itp.ciecyt.domain.ProyectoRespuestas;
import co.edu.itp.ciecyt.repository.ProyectoRespuestasRepository;
import co.edu.itp.ciecyt.service.dto.ProyectoRespuestasDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoRespuestasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProyectoRespuestas}.
 */
@Service
@Transactional
public class ProyectoRespuestasServiceImpl implements ProyectoRespuestasService {

    private final Logger log = LoggerFactory.getLogger(ProyectoRespuestasServiceImpl.class);

    private final ProyectoRespuestasRepository proyectoRespuestasRepository;

    private final ProyectoRespuestasMapper proyectoRespuestasMapper;

    public ProyectoRespuestasServiceImpl(ProyectoRespuestasRepository proyectoRespuestasRepository, ProyectoRespuestasMapper proyectoRespuestasMapper) {
        this.proyectoRespuestasRepository = proyectoRespuestasRepository;
        this.proyectoRespuestasMapper = proyectoRespuestasMapper;
    }

    /**
     * Save a proyectoRespuestas.
     *
     * @param proyectoRespuestasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProyectoRespuestasDTO save(ProyectoRespuestasDTO proyectoRespuestasDTO) {
        log.debug("Request to save ProyectoRespuestas : {}", proyectoRespuestasDTO);
        ProyectoRespuestas proyectoRespuestas = proyectoRespuestasMapper.toEntity(proyectoRespuestasDTO);
        proyectoRespuestas = proyectoRespuestasRepository.save(proyectoRespuestas);
        return proyectoRespuestasMapper.toDto(proyectoRespuestas);
    }

    /**
     * Get all the proyectoRespuestas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProyectoRespuestasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProyectoRespuestas");
        return proyectoRespuestasRepository.findAll(pageable)
            .map(proyectoRespuestasMapper::toDto);
    }


    /**
     * Get one proyectoRespuestas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProyectoRespuestasDTO> findOne(Long id) {
        log.debug("Request to get ProyectoRespuestas : {}", id);
        return proyectoRespuestasRepository.findById(id)
            .map(proyectoRespuestasMapper::toDto);
    }

    /**
     * Delete the proyectoRespuestas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProyectoRespuestas : {}", id);
        proyectoRespuestasRepository.deleteById(id);
    }
}