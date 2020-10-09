package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.Elemento;
import co.edu.itp.ciecyt.service.PreguntaService;
import co.edu.itp.ciecyt.domain.Pregunta;
import co.edu.itp.ciecyt.repository.PreguntaRepository;
import co.edu.itp.ciecyt.service.dto.ElementoDTO;
import co.edu.itp.ciecyt.service.dto.PreguntaDTO;
import co.edu.itp.ciecyt.service.mapper.PreguntaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Pregunta}.
 */
@Service
@Transactional
public class PreguntaServiceImpl implements PreguntaService {

    private final Logger log = LoggerFactory.getLogger(PreguntaServiceImpl.class);

    private final PreguntaRepository preguntaRepository;

    private final PreguntaMapper preguntaMapper;

    public PreguntaServiceImpl(PreguntaRepository preguntaRepository, PreguntaMapper preguntaMapper) {
        this.preguntaRepository = preguntaRepository;
        this.preguntaMapper = preguntaMapper;
    }

    @Override
    public PreguntaDTO save(PreguntaDTO preguntaDTO) {
        log.debug("Request to save Pregunta : {}", preguntaDTO);
        Pregunta pregunta = preguntaMapper.toEntity(preguntaDTO);
        pregunta = preguntaRepository.save(pregunta);
        return preguntaMapper.toDto(pregunta);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PreguntaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Preguntas");
        return preguntaRepository.findAll(pageable)
            .map(preguntaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PreguntaDTO> findOne(Long id) {
        log.debug("Request to get Pregunta : {}", id);
        return preguntaRepository.findById(id)
            .map(preguntaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pregunta : {}", id);
        preguntaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreguntaDTO> findByPreguntaModalidadId(Long idModalidad) throws Exception {
        log.debug("Request to get all Preguntas de una modalidad con una idModalidad");
        List<PreguntaDTO> listDTO = new ArrayList<>();
        List<Pregunta> list = preguntaRepository.findByPreguntaModalidadId(idModalidad);

        for (Pregunta pregunta : list) {
            listDTO.add(preguntaMapper.toDto(pregunta));
        }
        return listDTO;
    }
}
