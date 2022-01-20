package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Proyecto}.
 */
public interface ProyectoPredictService {
    public Optional<ProyectoDTO> predicePlay(Long facultad, Long modalidad) throws Exception ;

    Page<ProyectoDTO> findAll(Pageable pageable);
}
