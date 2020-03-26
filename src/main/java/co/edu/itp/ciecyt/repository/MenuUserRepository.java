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
 
  @Query(value=" SELECT DISTINCT * " + 
" FROM menu m  "+
" INNER JOIN rol_menu rm ON rm.rol_menu_menu_id = m.id "+
" INNER JOIN jhi_user_authority jua ON rm.auth_name = jua.authority_name " +
" WHERE (((rm.rol_menu_menu_id)=m.id) "+ 
   " AND ((rm.auth_name)=jua.authority_name) " +
   " AND ((jua.user_id)=?1)) " 
 ,nativeQuery = true)
  public Page<Menu> buscarMenusUsuario(Long idUser, Pageable pageable);
}
