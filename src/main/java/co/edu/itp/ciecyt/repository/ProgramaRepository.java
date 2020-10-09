package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.Programa;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Programa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {
}
