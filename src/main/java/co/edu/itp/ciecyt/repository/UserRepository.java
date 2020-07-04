package co.edu.itp.ciecyt.repository;

import co.edu.itp.ciecyt.domain.User;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.Instant;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<User> findAllByLoginNot(Pageable pageable, String login);

//consulta para recuperar los datos de un usuario segun su authory

/*SELECT DISTINCT ju.id, ju.first_name, ju.last_name, ju.email, ju.image_url,
                ju.activated, ju.lang_key
    			 FROM jhi_user ju
    			 INNER JOIN jhi_user_authority jua ON jua.user_id = ju.id
    			 WHERE (jua.authority_name='ROLE_ASESOR')
    			 ORDER BY ju.id  */

    /*
    @Query(value=" SELECT DISTINCT ju.id, ju.first_name, ju.last_name, ju.email, ju.image_url,"+
                "ju.activated, ju.lang_key, "  +  //faltan otros campos
    			" FROM jhi_user ju  "+
    			" INNER JOIN jhi_user_authority jua ON jua.user_id = ju.id "+
    			" WHERE (jua.authority_name=?1)  " +
    			" ORDER BY jua.orden"
    			,nativeQuery = true)
    public List<User> buscarUsuariosPorAuthority(String authorityName);
    */

    //List<User> findAllByAuthority(String authorityName);
    //@EntityGraph(attributePaths = "authorities")
    //@PreAuthorize("hasRole('ROLE_ASESOR')")
    // List<User> findAllByCreatedDateBefore(String authority ,Instant dateTime, Predicate predicate);
    

    Page<User> findAll(Specification<User> where, Pageable pageable);

}
