package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.service.ProyectoPredictService;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.itp.ciecyt.domain.Proyecto}.
 */
@RestController
@RequestMapping("/api")
public class ProyectoPredictResource {

    private final Logger log = LoggerFactory.getLogger(ProyectoPredictResource.class);

    private static final String ENTITY_NAME = "proyecto";


    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProyectoPredictService proyectoPredictService;

    //private final IntegranteProyectoService integranteProyectoService;

    public ProyectoPredictResource(ProyectoPredictService proyectoPredictService) {
        this.proyectoPredictService = proyectoPredictService;
    }

    @GetMapping("/proyectospredict")
    public ResponseEntity<List<ProyectoDTO>> getPredictProyectos(Pageable pageable) {
        log.debug("REST request to get a page of Proyectos");
        Page<ProyectoDTO> page = proyectoPredictService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /*@GetMapping("/proyectopredict/{facultad}/{modalidad}")
    public ResponseEntity<ProyectoDTO> getPredictProyecto(@PathVariable Long facultad, @PathVariable Long modalidad) throws Exception  {
        log.debug("REST request to get Proyecto : {}", facultad, modalidad);
        Optional<ProyectoDTO> proyectoDTO = proyectoPredictService.predicePlay(facultad,modalidad);
        return ResponseUtil.wrapOrNotFound(proyectoDTO);

    }*/
    @GetMapping("/proyectopredict/{facultad}/{modalidad}")
    public ResponseEntity<String> getPredictProyecto(@PathVariable Long facultad, @PathVariable Long modalidad) throws Exception  {
        log.debug("REST request to get Proyecto : {}", facultad, modalidad);
        Optional<String> estadisticas = proyectoPredictService.predicePlay(facultad,modalidad);
        return ResponseUtil.wrapOrNotFound(estadisticas);

    }

}
