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
   //@Query(value="SELECT * FROM Menu", nativeQuery = true)
  // @Query(value="SELECT * FROM Menu m WHERE m.id = user.id", nativeQuery = true)

  @Query(value=" SELECT DISTINCT * " + 
" FROM menu m  "+
" INNER JOIN rol_menu rm ON rm.rol_menu_menu_id = m.id "+
" INNER JOIN jhi_user_authority jua ON rm.auth_name = jua.authority_name " +
" WHERE (((rm.rol_menu_menu_id)=m.id) "+ 
   " AND ((rm.auth_name)=jua.authority_name) " +
   " AND ((jua.user_id)=?1)) " 
 ,nativeQuery = true)
  public Page<Menu> buscarMenusUsuario(Long idUser, Pageable pageable);

 /*  @Query(value="SELECT DISTINCT " +
   "menu.id, menu.nombre FROM menu, rol_menu, jhi_user_authority " + 
   "WHERE (((rol_menu.rol_menu_menu_id)=menu.id) "+ 
   "AND ((rol_menu.auth_name)=jhi_user_authority.authority_name) " +
   "AND ((jhi_user_authority.user_id)=?1))"
*/
//Este de Access
/*@Query(value=" SELECT DISTINCT menu.id, menu.nombre " + 
"FROM (jhi_user_authority INNER JOIN rol_menu ON " + 
"jhi_user_authority.authority_name = rol_menu.auth_name) " +  
"INNER JOIN menu ON rol_menu.rol_menu_menu_id = menu.id " +
"WHERE (((rol_menu.rol_menu_menu_id)=menu.id) " + 
"AND ((rol_menu.auth_name)=jhi_user_authority.authority_name) " + 
"AND ((jhi_user_authority.user_id)=?1))"
, 
nativeQuery = true)
      
 public Page<Menu> buscarMenusUsuario(Long userId, Pageable pageable);
    
    
    Optional<Menu> findById(Long id);
    Optional<Menu> findByNombre(String nombre);

*/




   /*
   String query = "select distinct ex.idexamen, ex.titulo, ex.maximoevaluaciones, ex.porcentajeacierto, ex.creditos as creditosExamen, "
         + " cp.idcompetencia, cp.nombre as nombreCompentencia, "
         + " ev.idevaluacion, ev.fecha as fechaEvaluacion, ev.resultado, ev.aprobadosino, ev.creditos as creditosEvaluacion, "
         + " (select count(*) from evaluaciones ev where ev.idexamen = ex.idexamen AND ev.idusuario = :idUsuario ) as intentosRealizados, "
         + " act.idactor, act.nombre as nombreActor, "
         + " us.idusuario, us.nombres, us.apellidos, "
         //--p.idactor, p.IDHerramienta, p.IDNodo, p.IDSistema, 
         + " cu.idcertificacion, cu.fecha as fechaCertificacion, cu.metodo, cu.nivelobtenido,  cu.creditos as creditosCertificacion, cu.comentarios  "
         + " from Publicaciones p "
         + " inner join Examenes ex ON ex.idexamen = p.idexamen "
         + " inner join Competencias cp ON ex.idcompetencia = cp.idcompetencia "
         + " left join Evaluaciones ev ON ex.idexamen = ev.idexamen AND ev.aprobadosino = 'S' AND ev.idusuario = :idUsuario "
         + " left join Usuarios us ON ev.idusuario = us.idusuario "
         + " left join Actores act ON ev.idactor = act.idactor "
         + " left join CertificacionesPorUsuario cu ON cu.idcertificacion = ev.idcertificacion "
         + " WHERE us.estadousuario = "+UserState.USER_ACTIVE.getId()+" and (p.idpublicacion in ( SELECT p.idpublicacion from Publicaciones p " 
         + " 	WHERE p.idnodo IN (SELECT an.idnodo FROM ActoresPorNodo an " 
         + "			WHERE an.idactor = :idActor ) ) "
         + " OR p.idpublicacion IN (SELECT p.idpublicacion from Publicaciones p "
         + " 	WHERE p.idactor = :idActor ) "
         + " OR p.idpublicacion IN (SELECT p.idpublicacion from Publicaciones p " 
         + "		WHERE p.idherramienta IN (SELECT hn.idherramienta from HerramientasPorNodo hn " 
         + "			WHERE hn.idnodo IN (SELECT an.idnodo FROM ActoresPorNodo an " 
         + "				WHERE an.idactor = :idActor)) ) "
         + " OR p.idpublicacion IN (SELECT p.idpublicacion from Publicaciones p " 
         + "		WHERE p.idsistema IN (SELECT ns.idsistema from NodosPorSistema ns " 
         + "			WHERE ns.idnodo IN (SELECT an.idnodo FROM ActoresPorNodo an " 
         + "				WHERE an.idactor = :idActor) )  ) "
         + " OR p.idpublicacion IN (SELECT p.idpublicacion from Publicaciones p " 
         + "		WHERE p.idsistema IN (SELECT acs.idsistema from ActoresPorSistema acs "
         + "			WHERE acs.idactor  = :idActor ) ) "  
         + "	) "
         + " order by ex.Titulo";
   
  */




}
