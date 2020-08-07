package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.IntegranteProyectoService;
import co.edu.itp.ciecyt.service.ProyectoService;
import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.repository.ProyectoRepository;
import co.edu.itp.ciecyt.service.dto.IntegranteProyectoDTO;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Proyecto}.
 */
@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {

    private final Logger log = LoggerFactory.getLogger(ProyectoServiceImpl.class);

    private final ProyectoRepository proyectoRepository;
    private final IntegranteProyectoService integranteProyectoService;

    private final ProyectoMapper proyectoMapper;



    public ProyectoServiceImpl(ProyectoRepository proyectoRepository,
                               ProyectoMapper proyectoMapper,
                               IntegranteProyectoService integranteProyectoService
                               ) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.integranteProyectoService = integranteProyectoService;



    }

    /**
     * Save a proyecto.
     *
     * @param proyectoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProyectoDTO save(ProyectoDTO proyectoDTO) {
        log.debug("Request to save Proyecto : {}", proyectoDTO);
        Proyecto proyecto = proyectoMapper.toEntity(proyectoDTO);
        proyecto = proyectoRepository.save(proyecto);
        return proyectoMapper.toDto(proyecto);
    }


      /**
     * Save a proyecto.
     *
     * @param proyectoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProyectoDTO saveAsesorProyecto (ProyectoDTO proyectoDTO) {
        log.debug("Request to save Proyecto : {}", proyectoDTO);
        Proyecto proyecto = proyectoMapper.toEntity(proyectoDTO);
        proyecto = proyectoRepository.save(proyecto);
        //guadar integrante
        IntegranteProyectoDTO asesorDTO = new IntegranteProyectoDTO();
        asesorDTO.setIntegranteProyectoProyectoId(proyecto.getId());
        asesorDTO.setIntegranteProyectoUserId(proyectoDTO.getAsesorId());
        //arreglar ...
        asesorDTO.setIntegranteProyectoRolesModalidadId(4451L);

        integranteProyectoService.save(asesorDTO);
        return proyectoMapper.toDto(proyecto);
    }


    /**
     * Get all the proyectos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProyectoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Proyectos");
        return proyectoRepository.findAll(pageable)
            .map(proyectoMapper::toDto);
    }


    /**
     * Get one proyecto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProyectoDTO> findOne(Long id) {
        log.debug("Request to get Proyecto : {}", id);
        return proyectoRepository.findById(id)
            .map(proyectoMapper::toDto);
    }



    /**
     * Get one proyecto con asesor by id.
     *
     * @param idProyecto  the id of the entity.
     * @param idRolModalidad  the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public ProyectoDTO findOneWithAsesor(Long idProyecto, Long idRolModalidad) throws Exception {
        log.debug("Request to get Proyecto : {}", idProyecto);
        Proyecto p = new Proyecto();
        p = proyectoRepository.findByIdOrderById(idProyecto);

        IntegranteProyectoDTO integranteProyectoDTO;
        //integranteProyectoDTO.setIntegranteProyectoProyectoId(id);

        ProyectoDTO proyectoDTO;

        final List<IntegranteProyectoDTO> lIntegranteProyectoDTO = integranteProyectoService.findByIntegranteProyectoProyectoIdAndIntegranteProyectoRolesModalidadIdIn(idProyecto,idRolModalidad);
        integranteProyectoDTO = lIntegranteProyectoDTO.get(0);


        proyectoDTO = proyectoMapper.toDto(p);
        //proyectoDTO.setAsesorId(asesorDTO.getIntegranteProyectoUserId());

        proyectoDTO.setAsesorId(integranteProyectoDTO.getIntegranteProyectoUserId());

        return proyectoDTO;
    }

    /**
     * Delete the proyecto by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Proyecto : {}", id);
        proyectoRepository.deleteById(id);
    }
}
