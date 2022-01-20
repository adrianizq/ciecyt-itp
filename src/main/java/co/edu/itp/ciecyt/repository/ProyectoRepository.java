package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Proyecto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

   Proyecto findByIdOrderById(Long idProyecto);

    List<Proyecto> findByFacultadIdAndProyectoModalidadId(Long facultad, Long modalidad);
}
