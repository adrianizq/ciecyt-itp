package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.PreguntaAuthorityService;
import co.edu.itp.ciecyt.domain.PreguntaAuthority;
import co.edu.itp.ciecyt.repository.PreguntaAuthorityRepository;
import co.edu.itp.ciecyt.service.dto.PreguntaAuthorityDTO;
import co.edu.itp.ciecyt.service.mapper.PreguntaAuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PreguntaAuthority}.
 */
@Service
@Transactional
public class PreguntaAuthorityServiceImpl implements PreguntaAuthorityService {

    private final Logger log = LoggerFactory.getLogger(PreguntaAuthorityServiceImpl.class);

    private final PreguntaAuthorityRepository preguntaAuthorityRepository;

    private final PreguntaAuthorityMapper preguntaAuthorityMapper;

    public PreguntaAuthorityServiceImpl(PreguntaAuthorityRepository preguntaAuthorityRepository, PreguntaAuthorityMapper preguntaAuthorityMapper) {
        this.preguntaAuthorityRepository = preguntaAuthorityRepository;
        this.preguntaAuthorityMapper = preguntaAuthorityMapper;
    }

    @Override
    public PreguntaAuthorityDTO save(PreguntaAuthorityDTO preguntaAuthorityDTO) {
        log.debug("Request to save PreguntaAuthority : {}", preguntaAuthorityDTO);
        PreguntaAuthority preguntaAuthority = preguntaAuthorityMapper.toEntity(preguntaAuthorityDTO);
        preguntaAuthority = preguntaAuthorityRepository.save(preguntaAuthority);
        return preguntaAuthorityMapper.toDto(preguntaAuthority);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreguntaAuthorityDTO> findAll() {
        log.debug("Request to get all PreguntaAuthorities");
        return preguntaAuthorityRepository.findAll().stream()
            .map(preguntaAuthorityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PreguntaAuthorityDTO> findOne(Long id) {
        log.debug("Request to get PreguntaAuthority : {}", id);
        return preguntaAuthorityRepository.findById(id)
            .map(preguntaAuthorityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PreguntaAuthority : {}", id);
        preguntaAuthorityRepository.deleteById(id);
    }
}
