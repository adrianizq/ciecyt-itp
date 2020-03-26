package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.User;
import co.edu.itp.ciecyt.repository.MenuUserRepository;
import co.edu.itp.ciecyt.repository.UserRepository;
import co.edu.itp.ciecyt.service.MenuUserService;
import co.edu.itp.ciecyt.service.UserService;
import co.edu.itp.ciecyt.service.dto.MenuDTO;
import co.edu.itp.ciecyt.service.mapper.MenuMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuUserServiceImpl implements MenuUserService {

    
    private final MenuUserRepository menuUserRepository;
    private final UserService userService;

    private final MenuMapper menuMapper;

    public MenuUserServiceImpl(MenuUserRepository menuUserRepository, MenuMapper menuMapper, UserService userService) {
      //public MenuUserServiceImpl(MenuUserRepository menuUserRepository, MenuMapper menuMapper) {
        this.menuUserRepository = menuUserRepository;
        this.menuMapper = menuMapper;
        this.userService = userService;

    }


    private final Logger log = LoggerFactory.getLogger(MenuUserServiceImpl.class);

    /**
     * Get all the menus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> buscarAllByUser( Pageable pageable) {
        Optional<User> user = userService.getUserWithAuthorities();
        if(!user.isPresent()) {
           log.error("User is not logged in");
           return null;
        }
        else{
            log.debug("El usuario en MenuService tiene id = " + userService.getUserWithAuthorities().get().getId());
        }
        

        
        //List<String> listAuth = userService.getAuthorities();
        
        
        
        
        //log.debug("Request to get all Menus of a User");
        return menuUserRepository.buscarMenusUsuario(userService.getUserWithAuthorities().get().getId(), pageable)
        //return menuUserRepository.buscarMenusUsuario(user, pageable)
            .map(menuMapper::toDto);
    }

 

}
