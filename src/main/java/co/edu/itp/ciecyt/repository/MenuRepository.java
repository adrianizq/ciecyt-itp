package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.Menu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.lang.Long;

/**
 * Spring Data repository for the Menu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    @Query(value=" select distinct m.id,m.nombre, m.url, m.icono, m.activo, "+
    " m.menu_padre_id, m.es_publico, m.orden "+
    " from menu m "+
    " INNER JOIN menu me ON me.menu_padre_id = m.id "
     ,nativeQuery = true)
      public List<Menu> buscarMenusPadre();  

}
