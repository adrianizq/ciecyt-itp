package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.service.ProyectoPredictService;
import io.github.jhipster.web.util.ResponseUtil;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ProyectoPredictResource(ProyectoPredictService proyectoPredictService) {
        this.proyectoPredictService = proyectoPredictService;
    }

    @GetMapping("/proyectopredict")
    public ResponseEntity<String> getPredictProyecto() throws Exception {
        log.debug("REST request to get Proyecto : {}");
        //Optional<String> estadisticas = proyectoPredictService.predicePlay(facultad,modalidad);
        Optional<String> estadisticas = proyectoPredictService.predicePlay();
        return ResponseUtil.wrapOrNotFound(estadisticas);
    }
}
