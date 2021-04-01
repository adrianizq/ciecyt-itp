package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.service.ElementoService;
import co.edu.itp.ciecyt.service.dto.IntegranteProyectoDTO;
import co.edu.itp.ciecyt.web.rest.errors.BadRequestAlertException;
import co.edu.itp.ciecyt.service.dto.ElementoDTO;

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
 * REST controller for managing {@link co.edu.itp.ciecyt.domain.Elemento}.
 */
@RestController
@RequestMapping("/api")
public class ElementoResource {

    private final Logger log = LoggerFactory.getLogger(ElementoResource.class);

    private static final String ENTITY_NAME = "elemento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElementoService elementoService;

    public ElementoResource(ElementoService elementoService) {
        this.elementoService = elementoService;
    }

    /**
     * {@code POST  /elementos} : Create a new elemento.
     *
     * @param elementoDTO the elementoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elementoDTO, or with status {@code 400 (Bad Request)} if the elemento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elementos")
    public ResponseEntity<ElementoDTO> createElemento(@RequestBody ElementoDTO elementoDTO) throws URISyntaxException {
        log.debug("REST request to save Elemento : {}", elementoDTO);
        if (elementoDTO.getId() != null) {
            throw new BadRequestAlertException("A new elemento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElementoDTO result = elementoService.save(elementoDTO);
        return ResponseEntity.created(new URI("/api/elementos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elementos} : Updates an existing elemento.
     *
     * @param elementoDTO the elementoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elementoDTO,
     * or with status {@code 400 (Bad Request)} if the elementoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elementoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elementos")
    public ResponseEntity<ElementoDTO> updateElemento(@RequestBody ElementoDTO elementoDTO) throws URISyntaxException {
        log.debug("REST request to update Elemento : {}", elementoDTO);
        if (elementoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElementoDTO result = elementoService.save(elementoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, elementoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /elementos} : get all the elementos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elementos in body.
     */
    @GetMapping("/elementos")
    public ResponseEntity<List<ElementoDTO>> getAllElementos(Pageable pageable) {
        log.debug("REST request to get a page of Elementos");
        Page<ElementoDTO> page = elementoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    //findAllByOrderByIdAsc

    /**
     * {@code GET  /elementos/:id} : get the "id" elemento.
     *
     * @param id the id of the elementoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elementoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elementos/{id}")
    public ResponseEntity<ElementoDTO> getElemento(@PathVariable Long id) {
        log.debug("REST request to get Elemento : {}", id);
        Optional<ElementoDTO> elementoDTO = elementoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elementoDTO);
    }

    /**
     * {@code DELETE  /elementos/:id} : delete the "id" elemento.
     *
     * @param id the id of the elementoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elementos/{id}")
    public ResponseEntity<Void> deleteElemento(@PathVariable Long id) {
        log.debug("REST request to delete Elemento : {}", id);
        elementoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    //////////////////////////////////////////////////////////77777777777777777777
    /////////////777777777777777777777777777777777777777777777
    /**
     * {@code GET  /integrante-proyectos/:id} : get the "id" idProyecto.
     *
     * @param id the id of the elementoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elementooDTO, or with status {@code 404 (Not Found)}.
     */
   /* @GetMapping("/elemento-modalidad/{idModalidad}")
    public ResponseEntity<?> getElementoModalidad(@PathVariable Long idModalidad) {
        log.debug("REST request to get Elemento Modalidad : {}", idModalidad);
        try{
            //final List<ElementoDTO> elementoDTO = elementoService.findByElementoModalidadId(idModalidad);
            final List<ElementoDTO> elementoDTO = elementoService.findByElementoModalidadId(idModalidad);
            return new ResponseEntity<>(elementoDTO, HttpStatus.OK);

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }*/
    //se reemplazara por getElementoFaseFormato
    @GetMapping("/elemento-fases/{idFase}")
    public ResponseEntity<?> getElementoFase(@PathVariable Long idFase) {
        log.debug("REST request to get Elemento Modalidad : {}", idFase);
        try{
            final List<ElementoDTO> elementoDTO = elementoService.findByElementoFasesId(idFase);
            return new ResponseEntity<>(elementoDTO, HttpStatus.OK);

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }


    @GetMapping("/elemento-fase-formato/{idFase}/{idFormato}")
    public ResponseEntity<?> getElementoFaseFormato(@PathVariable Long idFase, @PathVariable Long idFormato) {
        log.debug("REST request to get Elemento Modalidad : {}", idFase);
        try{
            final List<ElementoDTO> elementoDTO = elementoService.findByElementoFasesIdAndElementoFormatoId(idFase,idFormato);
            return new ResponseEntity<>(elementoDTO, HttpStatus.OK);

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }

    /**
     * {@code GET  /elementos} : get all the elementos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elementos in body.
     */
    @GetMapping("/elementos-nopage")
    public ResponseEntity<?> getAllElementosNoPage() throws Exception {
        log.debug("REST request to get a page of Elementos");

        try {
        List <ElementoDTO> elementoDTOS = elementoService.findAllByOrderByIdAsc();

            return new ResponseEntity<>(elementoDTOS, HttpStatus.OK);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }



}
