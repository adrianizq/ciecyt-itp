package co.edu.itp.ciecyt.repository;
import co.edu.itp.ciecyt.domain.RolMenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RolMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RolMenuRepository extends JpaRepository<RolMenu, Long> {

}
