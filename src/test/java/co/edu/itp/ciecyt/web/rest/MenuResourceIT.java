package co.edu.itp.ciecyt.web.rest;

import static co.edu.itp.ciecyt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Menu;
import co.edu.itp.ciecyt.repository.MenuRepository;
import co.edu.itp.ciecyt.service.MenuService;
import co.edu.itp.ciecyt.service.UserService;
import co.edu.itp.ciecyt.service.dto.MenuDTO;
import co.edu.itp.ciecyt.service.mapper.MenuMapper;
import co.edu.itp.ciecyt.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@link MenuResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class MenuResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_ICONO = "AAAAAAAAAA";
    private static final String UPDATED_ICONO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVO = false;
    private static final Boolean UPDATED_ACTIVO = true;

    private static final Boolean DEFAULT_ES_PUBLICO = false;
    private static final Boolean UPDATED_ES_PUBLICO = true;

    private static final Integer DEFAULT_ORDEN = 1;
    private static final Integer UPDATED_ORDEN = 2;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MessageSource messageSource;
    

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMenuMockMvc;

    private Menu menu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MenuResource menuResource = new MenuResource(menuService, messageSource, userService);
        this.restMenuMockMvc = MockMvcBuilders.standaloneSetup(menuResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Menu createEntity(EntityManager em) {
        Menu menu = new Menu()
            .nombre(DEFAULT_NOMBRE)
            .url(DEFAULT_URL)
            .icono(DEFAULT_ICONO)
            .activo(DEFAULT_ACTIVO)
            .esPublico(DEFAULT_ES_PUBLICO)
            .orden(DEFAULT_ORDEN);
        return menu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Menu createUpdatedEntity(EntityManager em) {
        Menu menu = new Menu()
            .nombre(UPDATED_NOMBRE)
            .url(UPDATED_URL)
            .icono(UPDATED_ICONO)
            .activo(UPDATED_ACTIVO)
            .esPublico(UPDATED_ES_PUBLICO)
            .orden(UPDATED_ORDEN);
        return menu;
    }

    @BeforeEach
    public void initTest() {
        menu = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenu() throws Exception {
        int databaseSizeBeforeCreate = menuRepository.findAll().size();

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);
        restMenuMockMvc.perform(post("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isCreated());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeCreate + 1);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testMenu.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testMenu.getIcono()).isEqualTo(DEFAULT_ICONO);
        assertThat(testMenu.isActivo()).isEqualTo(DEFAULT_ACTIVO);
        assertThat(testMenu.isEsPublico()).isEqualTo(DEFAULT_ES_PUBLICO);
        assertThat(testMenu.getOrden()).isEqualTo(DEFAULT_ORDEN);
    }

    @Test
    @Transactional
    public void createMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuRepository.findAll().size();

        // Create the Menu with an existing ID
        menu.setId(1L);
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuMockMvc.perform(post("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMenus() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        // Get all the menuList
        restMenuMockMvc.perform(get("/api/menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menu.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].icono").value(hasItem(DEFAULT_ICONO)))
            .andExpect(jsonPath("$.[*].activo").value(hasItem(DEFAULT_ACTIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].esPublico").value(hasItem(DEFAULT_ES_PUBLICO.booleanValue())))
            .andExpect(jsonPath("$.[*].orden").value(hasItem(DEFAULT_ORDEN)));
    }
    
    @Test
    @Transactional
    public void getMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        // Get the menu
        restMenuMockMvc.perform(get("/api/menus/{id}", menu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(menu.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.icono").value(DEFAULT_ICONO))
            .andExpect(jsonPath("$.activo").value(DEFAULT_ACTIVO.booleanValue()))
            .andExpect(jsonPath("$.esPublico").value(DEFAULT_ES_PUBLICO.booleanValue()))
            .andExpect(jsonPath("$.orden").value(DEFAULT_ORDEN));
    }

    @Test
    @Transactional
    public void getNonExistingMenu() throws Exception {
        // Get the menu
        restMenuMockMvc.perform(get("/api/menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        // Update the menu
        Menu updatedMenu = menuRepository.findById(menu.getId()).get();
        // Disconnect from session so that the updates on updatedMenu are not directly saved in db
        em.detach(updatedMenu);
        updatedMenu
            .nombre(UPDATED_NOMBRE)
            .url(UPDATED_URL)
            .icono(UPDATED_ICONO)
            .activo(UPDATED_ACTIVO)
            .esPublico(UPDATED_ES_PUBLICO)
            .orden(UPDATED_ORDEN);
        MenuDTO menuDTO = menuMapper.toDto(updatedMenu);

        restMenuMockMvc.perform(put("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isOk());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
        Menu testMenu = menuList.get(menuList.size() - 1);
        assertThat(testMenu.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testMenu.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testMenu.getIcono()).isEqualTo(UPDATED_ICONO);
        assertThat(testMenu.isActivo()).isEqualTo(UPDATED_ACTIVO);
        assertThat(testMenu.isEsPublico()).isEqualTo(UPDATED_ES_PUBLICO);
        assertThat(testMenu.getOrden()).isEqualTo(UPDATED_ORDEN);
    }

    @Test
    @Transactional
    public void updateNonExistingMenu() throws Exception {
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuMockMvc.perform(put("/api/menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenu() throws Exception {
        // Initialize the database
        menuRepository.saveAndFlush(menu);

        int databaseSizeBeforeDelete = menuRepository.findAll().size();

        // Delete the menu
        restMenuMockMvc.perform(delete("/api/menus/{id}", menu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Menu> menuList = menuRepository.findAll();
        assertThat(menuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Menu.class);
        Menu menu1 = new Menu();
        menu1.setId(1L);
        Menu menu2 = new Menu();
        menu2.setId(menu1.getId());
        assertThat(menu1).isEqualTo(menu2);
        menu2.setId(2L);
        assertThat(menu1).isNotEqualTo(menu2);
        menu1.setId(null);
        assertThat(menu1).isNotEqualTo(menu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuDTO.class);
        MenuDTO menuDTO1 = new MenuDTO();
        menuDTO1.setId(1L);
        MenuDTO menuDTO2 = new MenuDTO();
        assertThat(menuDTO1).isNotEqualTo(menuDTO2);
        menuDTO2.setId(menuDTO1.getId());
        assertThat(menuDTO1).isEqualTo(menuDTO2);
        menuDTO2.setId(2L);
        assertThat(menuDTO1).isNotEqualTo(menuDTO2);
        menuDTO1.setId(null);
        assertThat(menuDTO1).isNotEqualTo(menuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(menuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(menuMapper.fromId(null)).isNull();
    }
}
