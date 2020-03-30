package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.service.MenuService;
import co.edu.itp.ciecyt.domain.Menu;
import co.edu.itp.ciecyt.repository.MenuRepository;
import co.edu.itp.ciecyt.service.dto.MenuDTO;
import co.edu.itp.ciecyt.service.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Menu}.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MenuDTO save(MenuDTO menuDTO) {
        log.debug("Request to save Menu : {}", menuDTO);
        Menu menu = menuMapper.toEntity(menuDTO);
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    /**
     * Get all the menus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Menus");
        return menuRepository.findAll(pageable).map(menuMapper::toDto);
    }

    /**
     * Get one menu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> findOne(Long id) {
        log.debug("Request to get Menu : {}", id);
        return menuRepository.findById(id).map(menuMapper::toDto);
    }

    /**
     * Delete the menu by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Menu : {}", id);
        menuRepository.deleteById(id);
    }

    /**
     * ADR Get all the menus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Menu> findAllFathers() {
        log.debug("Request to get all Menus ");
        List <Menu> lista_padres= menuRepository.buscarMenusPadre();
        List <Menu> lista_hijos=null;
        //return menuRepository.buscarMenusPadre();
        
        List <Menu> lm = new ArrayList<>();
        for (Menu mp :lista_padres){
            //agregar a cada item de lista menu primero el padre
            //despues cada hijo
           
            if (mp.getMenuPadre()!=null){
                lista_hijos = menuRepository.buscarMenusHijosDe(mp.getId());

            }
        }
        //return lista_menu;
    }

    

}
