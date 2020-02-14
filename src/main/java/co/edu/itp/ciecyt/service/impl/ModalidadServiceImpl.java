package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.ModalidadService;
import co.edu.itp.ciecyt.domain.Modalidad;
import co.edu.itp.ciecyt.repository.ModalidadRepository;
import co.edu.itp.ciecyt.service.dto.ModalidadDTO;
import co.edu.itp.ciecyt.service.mapper.ModalidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Modalidad}.
 */
@Service
@Transactional
public class ModalidadServiceImpl implements ModalidadService {

    private final Logger log = LoggerFactory.getLogger(ModalidadServiceImpl.class);

    private final ModalidadRepository modalidadRepository;

    private final ModalidadMapper modalidadMapper;

    public ModalidadServiceImpl(ModalidadRepository modalidadRepository, ModalidadMapper modalidadMapper) {
        this.modalidadRepository = modalidadRepository;
        this.modalidadMapper = modalidadMapper;
    }

    /**
     * Save a modalidad.
     *
     * @param modalidadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ModalidadDTO save(ModalidadDTO modalidadDTO) {
        log.debug("Request to save Modalidad : {}", modalidadDTO);
        Modalidad modalidad = modalidadMapper.toEntity(modalidadDTO);
        modalidad = modalidadRepository.save(modalidad);
        return modalidadMapper.toDto(modalidad);
    }

    /**
     * Get all the modalidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ModalidadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Modalidads");
        return modalidadRepository.findAll(pageable)
            .map(modalidadMapper::toDto);
    }


    /**
     * Get one modalidad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ModalidadDTO> findOne(Long id) {
        log.debug("Request to get Modalidad : {}", id);
        return modalidadRepository.findById(id)
            .map(modalidadMapper::toDto);
    }

    /**
     * Delete the modalidad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modalidad : {}", id);
        modalidadRepository.deleteById(id);
    }
}
