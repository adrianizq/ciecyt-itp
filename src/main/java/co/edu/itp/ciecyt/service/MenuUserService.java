package co.edu.itp.ciecyt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.itp.ciecyt.domain.Menu;
import co.edu.itp.ciecyt.service.dto.MenuDTO;

public interface MenuUserService {
    /**
     * Get all the modalidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    //Page<MenuDTO> findAllByUser(Long userId, Pageable pageable);

	Page<MenuDTO> buscarAllByUser(Long userId, Pageable pageable);

	List<MenuDTO> buscarAllByUserNoPage(Long userId);

}
