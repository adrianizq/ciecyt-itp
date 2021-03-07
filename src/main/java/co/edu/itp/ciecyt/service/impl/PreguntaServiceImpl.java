package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.Elemento;
import co.edu.itp.ciecyt.domain.PreguntaAuthority;
import co.edu.itp.ciecyt.domain.PreguntaModalidad;
import co.edu.itp.ciecyt.repository.PreguntaAuthorityRepository;
import co.edu.itp.ciecyt.repository.PreguntaModalidadRepository;
import co.edu.itp.ciecyt.service.PreguntaAuthorityService;
import co.edu.itp.ciecyt.service.PreguntaModalidadService;
import co.edu.itp.ciecyt.service.PreguntaService;
import co.edu.itp.ciecyt.domain.Pregunta;
import co.edu.itp.ciecyt.repository.PreguntaRepository;
import co.edu.itp.ciecyt.service.dto.ElementoDTO;
import co.edu.itp.ciecyt.service.dto.PreguntaAuthorityDTO;
import co.edu.itp.ciecyt.service.dto.PreguntaDTO;
import co.edu.itp.ciecyt.service.dto.PreguntaModalidadDTO;
import co.edu.itp.ciecyt.service.mapper.PreguntaAuthorityMapper;
import co.edu.itp.ciecyt.service.mapper.PreguntaMapper;
import co.edu.itp.ciecyt.service.mapper.PreguntaModalidadMapper;
import org.ehcache.xml.model.TimeUnit;
import org.hibernate.annotations.Synchronize;
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
    private final PreguntaModalidadRepository preguntaModalidadRepository;
    private final PreguntaAuthorityRepository preguntaAuthorityRepository;



    private final PreguntaAuthorityService preguntaAuthorityService;
    private final PreguntaModalidadService preguntaModalidadService;


    private final PreguntaMapper preguntaMapper;
    private final PreguntaModalidadMapper preguntaModalidadMapper;
    private final PreguntaAuthorityMapper preguntaAuthorityMapper;


    public PreguntaServiceImpl(PreguntaRepository preguntaRepository,
                               PreguntaModalidadRepository preguntaModalidadRepository,
                               PreguntaAuthorityRepository preguntaAuthorityRepository,
                               PreguntaMapper preguntaMapper,
                               PreguntaModalidadMapper preguntaModalidadMapper,
                               PreguntaAuthorityService preguntaAuthorityService,
                               PreguntaModalidadService preguntaModalidadService,
                               PreguntaAuthorityMapper preguntaAuthorityMapper) {
        this.preguntaRepository = preguntaRepository;
        this.preguntaMapper = preguntaMapper;
        this.preguntaAuthorityService = preguntaAuthorityService;
        this.preguntaModalidadService = preguntaModalidadService;
        this.preguntaModalidadMapper = preguntaModalidadMapper;
        this.preguntaModalidadRepository = preguntaModalidadRepository;
        this.preguntaAuthorityRepository = preguntaAuthorityRepository;
        this.preguntaAuthorityMapper = preguntaAuthorityMapper;
    }

    @Override
    public PreguntaDTO save(PreguntaDTO preguntaDTO) {
        log.debug("Request to save Pregunta : {}", preguntaDTO);
        Pregunta pregunta = preguntaMapper.toEntity(preguntaDTO);
        pregunta = preguntaRepository.save(pregunta);
        return preguntaMapper.toDto(pregunta);
    }
    @Override
    @Transactional(readOnly = false)
    public PreguntaDTO saveModalidadAuthority(PreguntaDTO preguntaDTO) {
        log.debug("Request to save Pregunta : {}", preguntaDTO);
        Pregunta pregunta = preguntaMapper.toEntity(preguntaDTO);
        preguntaRepository.save(pregunta);

        List <PreguntaModalidad> pmL = preguntaModalidadRepository.findByPreguntaId(pregunta.getId());
        List <PreguntaModalidadDTO> lpmDto= new ArrayList<>();
        lpmDto = preguntaDTO.getPreguntaModalidads();


        for(PreguntaModalidad pm: pmL){
            preguntaModalidadRepository.delete(pm);


        }
        pmL = preguntaModalidadRepository.findByPreguntaId(pregunta.getId());
        

        //PreguntaModalidadDTO anterior = null;
        for (PreguntaModalidadDTO pmDto: lpmDto){
                pmDto.setPreguntaId(pregunta.getId());
                PreguntaModalidad pm = preguntaModalidadMapper.toEntity(pmDto);
               preguntaModalidadRepository.save(pm);
              // anterior=pmDto;

        }
        //ciclo para insertar
        /*
        boolean existe;
        for (PreguntaModalidadDTO pmDto: lpmDto
        ) { existe=false;
            for (PreguntaModalidad pm: pmL){
                if (pm.equals(pmDto)){
                    existe=true;
                    break;
                }
            }
            if(existe==false){
                pmDto.setPreguntaId(pregunta.getId());
                PreguntaModalidad pm = preguntaModalidadMapper.toEntity(pmDto);
                //preguntasModalidadAGrabar[]
                preguntaModalidadRepository.save(pm);
            }
        }
        */
        /*

        ///ciclo para borrar
        for(PreguntaModalidad pm: pmL){
            existe=false;
            for (PreguntaModalidadDTO pmDto: lpmDto
            ) {
                if (pm.getModalidad2().getId() == pmDto.getModalidad2Id()){
                    existe=true;
                }
            }
            if(!existe) {
                //preguntasModalidadABorrar[]
                //preguntaModalidadRepository.delete(pm);
            }
        }
*/
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
//OK
    @Override
    @Transactional(readOnly = true)
    public List<PreguntaDTO> findByPreguntaModalidadId(Long idModalidad) throws Exception {
        log.debug("Request to get all Preguntas de una modalidad con una idModalidad");
        List<PreguntaDTO> listDTO = new ArrayList<>();
        List<Pregunta> list = new ArrayList<>();


        List<PreguntaModalidad> lpm = preguntaModalidadRepository.findByModalidad2Id(idModalidad);

        //tengo que recorrer la lista PreguntaModalidad buscando cuales
        //preguntas tienen el idModalidad
        for (PreguntaModalidad pm: lpm
             ) {
            //PreguntaDTO pDto = new PreguntaDTO();

            Optional<Pregunta> p = preguntaRepository.findById(pm.getPregunta().getId());
            Pregunta pr = new Pregunta();
            if(p.isPresent()){
                pr = p.get();
            }
           list.add(pr);


        }

        for (Pregunta pregunta : list) {
            listDTO.add(preguntaMapper.toDto(pregunta));
        }
        return listDTO;
    }

    public List<PreguntaDTO> findByPreguntaModalidadIdAndPreguntaFaseId(Long idModalidad, Long idFase) throws Exception{
        log.debug("Request to get all Preguntas de una modalidad con una idModalidad");
        List<PreguntaDTO> listDTO = new ArrayList<>();
        List<Pregunta> list = new ArrayList<>();

        //List<PreguntaDTO> listPreguntaDTO = new ArrayList<>();
        List<Pregunta> listPregunta = preguntaRepository.findByPreguntaFaseId(idFase);

        List<PreguntaModalidad> listPreguntaModalidad= preguntaModalidadRepository.findByModalidad2Id(idModalidad);

        if(listPregunta!=null) {
            for (Pregunta pregunta : listPregunta
            ) {
                if(listPreguntaModalidad!=null) {
                    for (PreguntaModalidad preguntaModalidad : listPreguntaModalidad
                    ) {
                        if (pregunta.getId() == preguntaModalidad.getPregunta().getId()) {
                            list.add(pregunta);
                        }

                    }
                }
            }
        }

        for (Pregunta pregunta : list) {
            listDTO.add(preguntaMapper.toDto(pregunta));
        }
        return listDTO;
    }

}
