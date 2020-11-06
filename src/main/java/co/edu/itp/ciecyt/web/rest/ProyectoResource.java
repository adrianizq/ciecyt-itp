package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.service.ProyectoService;
import co.edu.itp.ciecyt.web.rest.errors.BadRequestAlertException;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;

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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.itp.ciecyt.domain.Proyecto}.
 */
@RestController
@RequestMapping("/api")
public class ProyectoResource {

    private final Logger log = LoggerFactory.getLogger(ProyectoResource.class);

    private static final String ENTITY_NAME = "proyecto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProyectoService proyectoService;
    //private final IntegranteProyectoService integranteProyectoService;



    public ProyectoResource(ProyectoService proyectoService) {
       this.proyectoService = proyectoService;


    }

    /**
     * {@code POST  /proyectos} : Create a new proyecto.
     *
     * @param proyectoDTO the proyectoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proyectoDTO, or with status {@code 400 (Bad Request)} if the proyecto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proyectos")
    //public ResponseEntity<ProyectoDTO> createProyecto(@RequestBody ProyectoDTO proyectoDTO, @RequestBody IntegranteProyectoDTO integranteProyectoDTO) throws URISyntaxException {
        public ResponseEntity<ProyectoDTO> createProyecto(@RequestBody ProyectoDTO proyectoDTO) throws URISyntaxException {
        //necesito
        //idIntegranteProyecto
        //idProyecto

        log.debug("REST request to save Proyecto : {}", proyectoDTO);
        if (proyectoDTO.getId() != null) {
            throw new BadRequestAlertException("A new proyecto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        //if(idIntegranteProyecto != null){
        //    throw new BadRequestAlertException("A new integranteProyecto cannot already have an ID", ENTITY_NAME, "idexists");
        //}

       //IntegranteProyectoDTO rI = IntegranteProyectoService.save
      //toca ver quien es ese usuario

        ProyectoDTO result = null;
        try {
            result = proyectoService.saveAsesorProyecto(proyectoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ProyectoDTO result = proyectoService.save(proyectoDTO);




         //integranteProyectoService.save(integDTO);


        return ResponseEntity.created(new URI("/api/proyectos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /proyectos} : Updates an existing proyecto.
     *
     * @param proyectoDTO the proyectoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proyectoDTO,
     * or with status {@code 400 (Bad Request)} if the proyectoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proyectoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proyectos")
    public ResponseEntity<ProyectoDTO> updateProyecto(@RequestBody ProyectoDTO proyectoDTO) throws URISyntaxException {
        log.debug("REST request to update Proyecto : {}", proyectoDTO);
        if (proyectoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProyectoDTO result = null;
        try {
             result = proyectoService.saveAsesorProyecto(proyectoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proyectoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /proyectos} : get all the proyectos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proyectos in body.
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<ProyectoDTO>> getAllProyectos(Pageable pageable) {
        log.debug("REST request to get a page of Proyectos");
        Page<ProyectoDTO> page = proyectoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    //ADR Retorna todos los protectos con una lista de los intgrantes
    @GetMapping("/proyectosIntegrantes")
    public ResponseEntity<List<ProyectoDTO>> getAllProyectosIntegrantes(Pageable pageable) throws Exception {
        log.debug("REST request to get a page of Proyectos");
        Page<ProyectoDTO> page = proyectoService.findAllProyectosIntegrantes(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/proyectos/{id}")
    public ResponseEntity<ProyectoDTO> getProyecto(@PathVariable Long id) {
        log.debug("REST request to get Proyecto : {}", id);
        Optional<ProyectoDTO> proyectoDTO = proyectoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proyectoDTO);
    }

    @GetMapping("/proyectoIntegrantes/{id}")
    public ResponseEntity<ProyectoDTO> getProyectoIntegrante(@PathVariable Long id) throws Exception {
        log.debug("REST request to get Proyecto : {}", id);
        Optional<ProyectoDTO>proyectoDTO = proyectoService.findOneIntegrantes(id);
        return  ResponseUtil.wrapOrNotFound(proyectoDTO);
    }





    /** ADR
     * {@code GET  /proyectos/:id} : get the "id" proyecto.
     *
     * @param idProyecto the id of the proyectoDTO to retrieve.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proyectoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proyectosWithAsesor/{idProyecto}")
    public ResponseEntity<?> getProyectoWithAsesor(@PathVariable Long idProyecto) {
        log.debug("REST request to get Proyecto : {}", idProyecto);

       //ProyectoDTO proyectoDTO = proyectoService.findOne(id);

        try {
           // proyectoDTO = proyectoService.findOneWithAsesor(id);

            final ProyectoDTO proyectoDTO = proyectoService.findOneWithAsesor(idProyecto);
            ResponseEntity<ProyectoDTO> responseEntity = new ResponseEntity(proyectoDTO, HttpStatus.OK);
            return responseEntity;



        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }





    /**
     * {@code DELETE  /proyectos/:id} : delete the "id" proyecto.
     *
     * @param id the id of the proyectoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proyectos/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Long id) {
        log.debug("REST request to delete Proyecto : {}", id);
        proyectoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    ///////////////////////////////////////////////////////////////7777777777777777777777777

    @GetMapping("/proyectos-integrante/{idUsuario}")
    public ResponseEntity<?> findByIntegranteProyecto(@PathVariable Long idUsuario) {
        log.debug("REST request to get Proyecto : {}", idUsuario);

        try {

            final List<ProyectoDTO> proyectoDTO = proyectoService.findByIntegranteProyecto(idUsuario);
            ResponseEntity<ProyectoDTO> responseEntity = new ResponseEntity(proyectoDTO, HttpStatus.OK);
            return responseEntity;



        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    //////////////////////////////////////////////////////////////////777777777777777777777



    @GetMapping("/proyectos-integrante/{idUsuario}/{authority}")
    public ResponseEntity<?> findByIntegranteProyectoAuthority(@PathVariable Long idUsuario, @PathVariable String authority) {
        log.debug("REST request to get Proyecto : {}", idUsuario, authority);

        try {

            final List<ProyectoDTO> proyectoDTO = proyectoService.findByIntegranteProyectoAuthority(idUsuario, authority);
            ResponseEntity<ProyectoDTO> responseEntity = new ResponseEntity(proyectoDTO, HttpStatus.OK);
            return responseEntity;



        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    //////////////////////////////////////////////////////////////////777777777777777777777

}
