package co.edu.itp.ciecyt.service;

import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Proyecto}.
 */
public interface ProyectoPredictService {
    //public Optional<String> predicePlay(Long facultad, Long modalidad) throws Exception ;
    //public List<String> predicePlay() throws Exception;
    public List<String> predicePlay(Long tipo) throws Exception;

    Page<ProyectoDTO> findAll(Pageable pageable);
}
