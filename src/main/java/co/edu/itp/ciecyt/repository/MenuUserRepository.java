package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.Menu;
import co.edu.itp.ciecyt.domain.User;
import co.edu.itp.ciecyt.service.dto.MenuDTO;

import java.util.List;
import java.util.Optional;

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
public interface MenuUserRepository extends JpaRepository<Menu, Long> {

@Query(value=" SELECT DISTINCT m.id, m.nombre, m.url, m.icono, m.activo,"+ 
"m.menu_padre_id, m.es_publico, m.orden   " + 
" FROM menu m  "+
" INNER JOIN rol_menu rm ON rm.rol_menu_menu_id = m.id "+
" INNER JOIN jhi_user_authority jua ON rm.auth_name = jua.authority_name " +
" WHERE (jua.user_id=?1)  " +
" ORDER BY m.orden" 
 ,nativeQuery = true)
  public Page<Menu> buscarMenusUsuario(Long idUser, Pageable pageable);
/*

@Query(value="SELECT DISTINCT rm.id, rm.permitir_acceso, rm.permitir_crear, "+
" rm.permitir_editar, rm.permitir_eliminar,rm.auth_name, "+
" rm.rol_menu_menu_id " +
" FROM rol_menu rm  "+
" WHERE ( rm.auth_name=?1 )  "+
" ORDER BY rm.id "
,nativeQuery = true)
public Page<Menu> buscarRolMenuAuthority(Long idUser, Pageable pageable);
*/
}