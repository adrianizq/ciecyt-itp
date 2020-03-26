package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.service.MenuUserService;
import co.edu.itp.ciecyt.service.UserService;
import co.edu.itp.ciecyt.service.dto.MenuDTO;


import io.github.jhipster.web.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.itp.ciecyt.domain.Menu}.
 */
@RestController
@RequestMapping("/api")
public class MenuUserResource {

    private final Logger log = LoggerFactory.getLogger(MenuResource.class);

    private static final String ENTITY_NAME = "menu";
    private final UserService userService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

  

    private final MenuUserService menuUserService; //agregada ADR

    public MenuUserResource(MenuUserService menuUserService,  UserService userService) {
      
        this.menuUserService = menuUserService;
        this.userService = userService;
    }

    

    //ADR
    /**
     * {@code GET  /menus} : get all the menus.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menus in body.
     */
    @GetMapping("/menus-all-user")
    //public ResponseEntity<List<MenuDTO>> getAllMenusUser(@PathVariable Long id, Pageable pageable) {
    public ResponseEntity<List<MenuDTO>> getAllMenusUser(Pageable pageable) {
        log.debug("REST request to get a page of Menus of User");
        //Page<MenuDTO> page = menuService.findAll(pageable);
        Page <MenuDTO>  page = menuUserService.buscarAllByUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    
}
