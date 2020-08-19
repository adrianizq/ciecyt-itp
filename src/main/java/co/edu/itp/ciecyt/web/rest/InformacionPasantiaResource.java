package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.domain.InformacionPasantia;
import co.edu.itp.ciecyt.repository.InformacionPasantiaRepository;
import co.edu.itp.ciecyt.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.itp.ciecyt.domain.InformacionPasantia}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InformacionPasantiaResource {

    private final Logger log = LoggerFactory.getLogger(InformacionPasantiaResource.class);

    private static final String ENTITY_NAME = "informacionPasantia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InformacionPasantiaRepository informacionPasantiaRepository;

    public InformacionPasantiaResource(InformacionPasantiaRepository informacionPasantiaRepository) {
        this.informacionPasantiaRepository = informacionPasantiaRepository;
    }

    /**
     * {@code POST  /informacion-pasantias} : Create a new informacionPasantia.
     *
     * @param informacionPasantia the informacionPasantia to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new informacionPasantia, or with status {@code 400 (Bad Request)} if the informacionPasantia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/informacion-pasantias")
    public ResponseEntity<InformacionPasantia> createInformacionPasantia(@RequestBody InformacionPasantia informacionPasantia) throws URISyntaxException {
        log.debug("REST request to save InformacionPasantia : {}", informacionPasantia);
        if (informacionPasantia.getId() != null) {
            throw new BadRequestAlertException("A new informacionPasantia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InformacionPasantia result = informacionPasantiaRepository.save(informacionPasantia);
        return ResponseEntity.created(new URI("/api/informacion-pasantias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /informacion-pasantias} : Updates an existing informacionPasantia.
     *
     * @param informacionPasantia the informacionPasantia to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacionPasantia,
     * or with status {@code 400 (Bad Request)} if the informacionPasantia is not valid,
     * or with status {@code 500 (Internal Server Error)} if the informacionPasantia couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/informacion-pasantias")
    public ResponseEntity<InformacionPasantia> updateInformacionPasantia(@RequestBody InformacionPasantia informacionPasantia) throws URISyntaxException {
        log.debug("REST request to update InformacionPasantia : {}", informacionPasantia);
        if (informacionPasantia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InformacionPasantia result = informacionPasantiaRepository.save(informacionPasantia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informacionPasantia.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /informacion-pasantias} : get all the informacionPasantias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informacionPasantias in body.
     */
    @GetMapping("/informacion-pasantias")
    public List<InformacionPasantia> getAllInformacionPasantias() {
        log.debug("REST request to get all InformacionPasantias");
        return informacionPasantiaRepository.findAll();
    }

    /**
     * {@code GET  /informacion-pasantias/:id} : get the "id" informacionPasantia.
     *
     * @param id the id of the informacionPasantia to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informacionPasantia, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/informacion-pasantias/{id}")
    public ResponseEntity<InformacionPasantia> getInformacionPasantia(@PathVariable Long id) {
        log.debug("REST request to get InformacionPasantia : {}", id);
        Optional<InformacionPasantia> informacionPasantia = informacionPasantiaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(informacionPasantia);
    }

    /**
     * {@code DELETE  /informacion-pasantias/:id} : delete the "id" informacionPasantia.
     *
     * @param id the id of the informacionPasantia to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/informacion-pasantias/{id}")
    public ResponseEntity<Void> deleteInformacionPasantia(@PathVariable Long id) {
        log.debug("REST request to delete InformacionPasantia : {}", id);
        informacionPasantiaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
