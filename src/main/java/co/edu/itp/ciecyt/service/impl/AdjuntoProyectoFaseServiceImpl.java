package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.AdjuntoProyectoFaseService;
import co.edu.itp.ciecyt.domain.AdjuntoProyectoFase;
import co.edu.itp.ciecyt.repository.AdjuntoProyectoFaseRepository;
import co.edu.itp.ciecyt.service.dto.AdjuntoProyectoFaseDTO;
import co.edu.itp.ciecyt.service.mapper.AdjuntoProyectoFaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdjuntoProyectoFase}.
 */
@Service
@Transactional
public class AdjuntoProyectoFaseServiceImpl implements AdjuntoProyectoFaseService {

    private final Logger log = LoggerFactory.getLogger(AdjuntoProyectoFaseServiceImpl.class);

    private final AdjuntoProyectoFaseRepository adjuntoProyectoFaseRepository;

    private final AdjuntoProyectoFaseMapper adjuntoProyectoFaseMapper;

    public AdjuntoProyectoFaseServiceImpl(AdjuntoProyectoFaseRepository adjuntoProyectoFaseRepository, AdjuntoProyectoFaseMapper adjuntoProyectoFaseMapper) {
        this.adjuntoProyectoFaseRepository = adjuntoProyectoFaseRepository;
        this.adjuntoProyectoFaseMapper = adjuntoProyectoFaseMapper;
    }

    @Override
    public AdjuntoProyectoFaseDTO save(AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO) {
        log.debug("Request to save AdjuntoProyectoFase : {}", adjuntoProyectoFaseDTO);
        AdjuntoProyectoFase adjuntoProyectoFase = adjuntoProyectoFaseMapper.toEntity(adjuntoProyectoFaseDTO);
        adjuntoProyectoFase = adjuntoProyectoFaseRepository.save(adjuntoProyectoFase);
        return adjuntoProyectoFaseMapper.toDto(adjuntoProyectoFase);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdjuntoProyectoFaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdjuntoProyectoFases");
        return adjuntoProyectoFaseRepository.findAll(pageable)
            .map(adjuntoProyectoFaseMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AdjuntoProyectoFaseDTO> findOne(Long id) {
        log.debug("Request to get AdjuntoProyectoFase : {}", id);
        return adjuntoProyectoFaseRepository.findById(id)
            .map(adjuntoProyectoFaseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdjuntoProyectoFase : {}", id);
        adjuntoProyectoFaseRepository.deleteById(id);
    }
}
