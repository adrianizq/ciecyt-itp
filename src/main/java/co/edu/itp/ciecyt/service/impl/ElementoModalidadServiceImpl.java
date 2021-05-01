package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.ElementoModalidadService;
import co.edu.itp.ciecyt.domain.ElementoModalidad;
import co.edu.itp.ciecyt.repository.ElementoModalidadRepository;
import co.edu.itp.ciecyt.service.dto.ElementoModalidadDTO;
import co.edu.itp.ciecyt.service.mapper.ElementoModalidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ElementoModalidad}.
 */
@Service
@Transactional
public class ElementoModalidadServiceImpl implements ElementoModalidadService {

    private final Logger log = LoggerFactory.getLogger(ElementoModalidadServiceImpl.class);

    private final ElementoModalidadRepository elementoModalidadRepository;

    private final ElementoModalidadMapper elementoModalidadMapper;

    public ElementoModalidadServiceImpl(ElementoModalidadRepository elementoModalidadRepository, ElementoModalidadMapper elementoModalidadMapper) {
        this.elementoModalidadRepository = elementoModalidadRepository;
        this.elementoModalidadMapper = elementoModalidadMapper;
    }

    @Override
    public ElementoModalidadDTO save(ElementoModalidadDTO elementoModalidadDTO) {
        log.debug("Request to save ElementoModalidad : {}", elementoModalidadDTO);
        ElementoModalidad elementoModalidad = elementoModalidadMapper.toEntity(elementoModalidadDTO);
        elementoModalidad = elementoModalidadRepository.save(elementoModalidad);
        return elementoModalidadMapper.toDto(elementoModalidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElementoModalidadDTO> findAll() {
        log.debug("Request to get all ElementoModalidads");
        return elementoModalidadRepository.findAll().stream()
            .map(elementoModalidadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ElementoModalidadDTO> findOne(Long id) {
        log.debug("Request to get ElementoModalidad : {}", id);
        return elementoModalidadRepository.findById(id)
            .map(elementoModalidadMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElementoModalidad : {}", id);
        elementoModalidadRepository.deleteById(id);
    }
}
