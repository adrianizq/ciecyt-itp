package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.service.PreguntaModalidadService;
import co.edu.itp.ciecyt.service.PreguntaService;
import co.edu.itp.ciecyt.service.dto.ElementoDTO;
import co.edu.itp.ciecyt.service.dto.PreguntaModalidadDTO;
import co.edu.itp.ciecyt.web.rest.errors.BadRequestAlertException;
import co.edu.itp.ciecyt.service.dto.PreguntaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.itp.ciecyt.domain.Pregunta}.
 */
@RestController
@RequestMapping("/api")
public class PreguntaResource {

    private final Logger log = LoggerFactory.getLogger(PreguntaResource.class);

    private static final String ENTITY_NAME = "pregunta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PreguntaService preguntaService;
    private final PreguntaModalidadService preguntaModalidadService;




    public PreguntaResource(PreguntaService preguntaService, PreguntaModalidadService preguntaModalidadService) {

        this.preguntaService = preguntaService;
        this.preguntaModalidadService = preguntaModalidadService;
    }

    /**
     * {@code POST  /preguntas} : Create a new pregunta.
     *
     * @param preguntaDTO the preguntaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new preguntaDTO, or with status {@code 400 (Bad Request)} if the pregunta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/preguntas")
    public ResponseEntity<PreguntaDTO> createPregunta(@RequestBody PreguntaDTO preguntaDTO) throws URISyntaxException {
        log.debug("REST request to save Pregunta : {}", preguntaDTO);
        if (preguntaDTO.getId() != null) {
            throw new BadRequestAlertException("A new pregunta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        //PreguntaDTO result = preguntaService.save(preguntaDTO);
        PreguntaDTO result = preguntaService.saveModalidadAuthority(preguntaDTO);

        return ResponseEntity.created(new URI("/api/preguntas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /preguntas} : Updates an existing pregunta.
     *
     * @param preguntaDTO the preguntaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated preguntaDTO,
     * or with status {@code 400 (Bad Request)} if the preguntaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the preguntaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/preguntas")
    public ResponseEntity<PreguntaDTO> updatePregunta(@RequestBody PreguntaDTO preguntaDTO) throws URISyntaxException {
        log.debug("REST request to update Pregunta : {}", preguntaDTO);
        if (preguntaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        //PreguntaDTO result = preguntaService.save(preguntaDTO);
        PreguntaDTO result = preguntaService.saveModalidadAuthority(preguntaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, preguntaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /preguntas} : get all the preguntas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of preguntas in body.
     */
    @GetMapping("/preguntas")
    public ResponseEntity<List<PreguntaDTO>> getAllPreguntas(Pageable pageable) {
        log.debug("REST request to get a page of Preguntas");
        Page<PreguntaDTO> page = preguntaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /preguntas/:id} : get the "id" pregunta.
     *
     * @param id the id of the preguntaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the preguntaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/preguntas/{id}")
    public ResponseEntity<PreguntaDTO> getPregunta(@PathVariable Long id) {
        log.debug("REST request to get Pregunta : {}", id);
        Optional<PreguntaDTO> preguntaDTO = preguntaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preguntaDTO);
    }

    /**
     * {@code DELETE  /preguntas/:id} : delete the "id" pregunta.
     *
     * @param id the id of the preguntaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/preguntas/{id}")
    public ResponseEntity<Void> deletePregunta(@PathVariable Long id) {
        log.debug("REST request to delete Pregunta : {}", id);
        preguntaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //////////////////////////////////////////////////////////77777777777777777777
    /////////////777777777777777777777777777777777777777777777
    /**
     *      *
     * @param idModalidad the id of the preguntaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the preguntaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pregunta-modalidad/{idModalidad}")
    public ResponseEntity<?> getPreguntaModalidad(@PathVariable Long idModalidad) {
        log.debug("REST request to get Pregunta Modalidad : {}", idModalidad);
        try{
            final List<PreguntaDTO> DTO = preguntaService.findByPreguntaModalidadId(idModalidad);

            return new ResponseEntity<>(DTO, HttpStatus.OK);

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }



    //////////////////////////////////////////////////////////77777777777777777777
    /////////////777777777777777777777777777777777777777777777
    /**
     *      *
     * @param idModalidad the id of the preguntaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the preguntaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pregunta-modalidad-fase/{idModalidad}/{idFase}")
    public ResponseEntity<?> getPreguntaModalidadFase(@PathVariable Long idModalidad, @PathVariable Long idFase) {
        log.debug("REST request to get Pregunta Modalidad Fase : {}", idModalidad, idFase);
        try{
            final List<PreguntaDTO> DTO = preguntaService.findByPreguntaModalidadIdAndPreguntaFaseId(idModalidad, idFase);
            return new ResponseEntity<>(DTO, HttpStatus.OK);

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }
}
