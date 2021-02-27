package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.PreguntaAuthority;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PreguntaAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreguntaAuthorityRepository extends JpaRepository<PreguntaAuthority, Long> {
}
