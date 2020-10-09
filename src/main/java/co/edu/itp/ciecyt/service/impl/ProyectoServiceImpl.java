package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.*;
import co.edu.itp.ciecyt.service.IntegranteProyectoService;
import co.edu.itp.ciecyt.service.ProyectoService;
import co.edu.itp.ciecyt.repository.ProyectoRepository;
import co.edu.itp.ciecyt.service.RolesModalidadService;
import co.edu.itp.ciecyt.service.dto.ElementoDTO;
import co.edu.itp.ciecyt.service.dto.IntegranteProyectoDTO;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import co.edu.itp.ciecyt.service.dto.RolesModalidadDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.ArrayList;
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
    private final RolesModalidadService rolesModalidadService;

    private final ProyectoMapper proyectoMapper;



    public ProyectoServiceImpl(ProyectoRepository proyectoRepository,
                               ProyectoMapper proyectoMapper,
                               IntegranteProyectoService integranteProyectoService,
                               RolesModalidadService rolesModalidadService) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.integranteProyectoService = integranteProyectoService;
        this.rolesModalidadService = rolesModalidadService;
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
    public ProyectoDTO saveAsesorProyecto (ProyectoDTO proyectoDTO) throws Exception {
        log.debug("Request to save Proyecto : {}", proyectoDTO);
        Proyecto proyecto = proyectoMapper.toEntity(proyectoDTO);
        proyecto = proyectoRepository.save(proyecto);
        //guadar integrante
        IntegranteProyectoDTO asesorDTO = new IntegranteProyectoDTO();
        asesorDTO.setIntegranteProyectoProyectoId(proyecto.getId());
        asesorDTO.setIntegranteProyectoUserId(proyectoDTO.getAsesorId());
        //arreglado

        Modalidad modalidad = proyecto.getProyectoModalidad();
        Long modalidadId= modalidad.getId(); //eje 1551

        RolesModalidadDTO  rolesModalidad;
        rolesModalidad = rolesModalidadService.findByRolAndRolesModalidadModalidadId("Asesor", modalidadId);
        Long rolesModalidadId= rolesModalidad.getId();
        //asesorDTO.setIntegranteProyectoRolesModalidadId(4451L);
        asesorDTO.setIntegranteProyectoRolesModalidadId(rolesModalidadId);

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
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
   // public ProyectoDTO findOneWithAsesor(Long idProyecto, Long idRolModalkidad) throws Exception {
    public ProyectoDTO findOneWithAsesor(Long idProyecto) throws Exception {
        log.debug("Request to get Proyecto : {}", idProyecto);
        Proyecto p = new Proyecto();
        p = proyectoRepository.findByIdOrderById(idProyecto);

        Modalidad modalidad = p.getProyectoModalidad();
        Long modalidadId= modalidad.getId(); //eje 1551

        RolesModalidadDTO  rolesModalidad;
        rolesModalidad = rolesModalidadService.findByRolAndRolesModalidadModalidadId("Asesor", modalidadId);
        Long rolesModalidadId= rolesModalidad.getId();

        IntegranteProyectoDTO integranteProyectoDTO;
        final List<IntegranteProyectoDTO> lIntegranteProyectoDTO = integranteProyectoService.findByIntegranteProyectoProyectoIdAndIntegranteProyectoRolesModalidadId(idProyecto,rolesModalidadId);
        integranteProyectoDTO = lIntegranteProyectoDTO.get(0);
        Long integranteProyectoId = integranteProyectoDTO.getIntegranteProyectoUserId();

        ProyectoDTO proyectoDTO;

        proyectoDTO = proyectoMapper.toDto(p);

        proyectoDTO.setAsesorId(integranteProyectoId);

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


    @Transactional(readOnly = true)
    public List<ProyectoDTO> findByIntegranteProyecto(Long idUsuaro) throws Exception {
        log.debug("Request to get all Elementos de una modalidad con una idModalidad");
        List<Proyecto> listaProyecto = new ArrayList<>();
        List <IntegranteProyecto> listaIntegranteProyecto= integranteProyectoService.findByIntegranteProyectoUserId(idUsuaro);
        if(listaIntegranteProyecto!=null){
            for(IntegranteProyecto integranteProyecto: listaIntegranteProyecto){
                Proyecto proyecto = new Proyecto();
                proyecto = integranteProyecto.getIntegranteProyectoProyecto();
                listaProyecto.add(proyecto);
            }

            List<ProyectoDTO> listDTO = new ArrayList<>();

            for (Proyecto p : listaProyecto) {
                listDTO.add(proyectoMapper.toDto(p));
            }
            return listDTO;
        }
        else{
            return null;
        }

    }


    ///////////////////////////////77777777777777777777777
    @Transactional(readOnly = true)
    public List<ProyectoDTO> findByIntegranteProyectoAuthority(Long idUsuario,  String authority) throws Exception {
        log.debug("Request to get all Proyectos de una modalidad con una idModalidad");
        //List<RolesModalidad> lRolesModalidad = rolesModalidadService.findByRolesModalidadAuthorityName(authority);

        List<Proyecto> listaProyecto = new ArrayList<>();
        List <IntegranteProyecto> listaIntegranteProyecto= integranteProyectoService.findByIntegranteProyectoAuthority(idUsuario,authority);
        if(listaIntegranteProyecto!=null){


            for(IntegranteProyecto integranteProyecto: listaIntegranteProyecto){
                Proyecto proyecto = new Proyecto();
                proyecto = integranteProyecto.getIntegranteProyectoProyecto();
                listaProyecto.add(proyecto);
            }

            List<ProyectoDTO> listDTO = new ArrayList<>();

            for (Proyecto p : listaProyecto) {
                listDTO.add(proyectoMapper.toDto(p));
            }
            return listDTO;
        }
        else{
            return null;
        }

    }
    //////////////////////////////////////////77777777777777

}
