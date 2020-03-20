package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.RolMenu;
import co.edu.itp.ciecyt.repository.RolMenuRepository;
import co.edu.itp.ciecyt.service.RolMenuService;
import co.edu.itp.ciecyt.service.dto.RolMenuDTO;
import co.edu.itp.ciecyt.service.mapper.RolMenuMapper;
import co.edu.itp.ciecyt.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static co.edu.itp.ciecyt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RolMenuResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class RolMenuResourceIT {

    private static final Boolean DEFAULT_PERMITIR_ACCESO = false;
    private static final Boolean UPDATED_PERMITIR_ACCESO = true;

    private static final Boolean DEFAULT_PERMITIR_CREAR = false;
    private static final Boolean UPDATED_PERMITIR_CREAR = true;

    private static final Boolean DEFAULT_PERMITIR_EDITAR = false;
    private static final Boolean UPDATED_PERMITIR_EDITAR = true;

    private static final Boolean DEFAULT_PERMITIR_ELIMINAR = false;
    private static final Boolean UPDATED_PERMITIR_ELIMINAR = true;

    private static final String DEFAULT_AUTH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AUTH_NAME = "BBBBBBBBBB";

    @Autowired
    private RolMenuRepository rolMenuRepository;

    @Autowired
    private RolMenuMapper rolMenuMapper;

    @Autowired
    private RolMenuService rolMenuService;

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

    private MockMvc restRolMenuMockMvc;

    private RolMenu rolMenu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RolMenuResource rolMenuResource = new RolMenuResource(rolMenuService);
        this.restRolMenuMockMvc = MockMvcBuilders.standaloneSetup(rolMenuResource)
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
    public static RolMenu createEntity(EntityManager em) {
        RolMenu rolMenu = new RolMenu()
            .permitirAcceso(DEFAULT_PERMITIR_ACCESO)
            .permitirCrear(DEFAULT_PERMITIR_CREAR)
            .permitirEditar(DEFAULT_PERMITIR_EDITAR)
            .permitirEliminar(DEFAULT_PERMITIR_ELIMINAR)
            .auth_name(DEFAULT_AUTH_NAME);
        return rolMenu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RolMenu createUpdatedEntity(EntityManager em) {
        RolMenu rolMenu = new RolMenu()
            .permitirAcceso(UPDATED_PERMITIR_ACCESO)
            .permitirCrear(UPDATED_PERMITIR_CREAR)
            .permitirEditar(UPDATED_PERMITIR_EDITAR)
            .permitirEliminar(UPDATED_PERMITIR_ELIMINAR)
            .auth_name(UPDATED_AUTH_NAME);
        return rolMenu;
    }

    @BeforeEach
    public void initTest() {
        rolMenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createRolMenu() throws Exception {
        int databaseSizeBeforeCreate = rolMenuRepository.findAll().size();

        // Create the RolMenu
        RolMenuDTO rolMenuDTO = rolMenuMapper.toDto(rolMenu);
        restRolMenuMockMvc.perform(post("/api/rol-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the RolMenu in the database
        List<RolMenu> rolMenuList = rolMenuRepository.findAll();
        assertThat(rolMenuList).hasSize(databaseSizeBeforeCreate + 1);
        RolMenu testRolMenu = rolMenuList.get(rolMenuList.size() - 1);
        assertThat(testRolMenu.isPermitirAcceso()).isEqualTo(DEFAULT_PERMITIR_ACCESO);
        assertThat(testRolMenu.isPermitirCrear()).isEqualTo(DEFAULT_PERMITIR_CREAR);
        assertThat(testRolMenu.isPermitirEditar()).isEqualTo(DEFAULT_PERMITIR_EDITAR);
        assertThat(testRolMenu.isPermitirEliminar()).isEqualTo(DEFAULT_PERMITIR_ELIMINAR);
        assertThat(testRolMenu.getAuth_name()).isEqualTo(DEFAULT_AUTH_NAME);
    }

    @Test
    @Transactional
    public void createRolMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rolMenuRepository.findAll().size();

        // Create the RolMenu with an existing ID
        rolMenu.setId(1L);
        RolMenuDTO rolMenuDTO = rolMenuMapper.toDto(rolMenu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRolMenuMockMvc.perform(post("/api/rol-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RolMenu in the database
        List<RolMenu> rolMenuList = rolMenuRepository.findAll();
        assertThat(rolMenuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRolMenus() throws Exception {
        // Initialize the database
        rolMenuRepository.saveAndFlush(rolMenu);

        // Get all the rolMenuList
        restRolMenuMockMvc.perform(get("/api/rol-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rolMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].permitirAcceso").value(hasItem(DEFAULT_PERMITIR_ACCESO.booleanValue())))
            .andExpect(jsonPath("$.[*].permitirCrear").value(hasItem(DEFAULT_PERMITIR_CREAR.booleanValue())))
            .andExpect(jsonPath("$.[*].permitirEditar").value(hasItem(DEFAULT_PERMITIR_EDITAR.booleanValue())))
            .andExpect(jsonPath("$.[*].permitirEliminar").value(hasItem(DEFAULT_PERMITIR_ELIMINAR.booleanValue())))
            .andExpect(jsonPath("$.[*].auth_name").value(hasItem(DEFAULT_AUTH_NAME)));
    }
    
    @Test
    @Transactional
    public void getRolMenu() throws Exception {
        // Initialize the database
        rolMenuRepository.saveAndFlush(rolMenu);

        // Get the rolMenu
        restRolMenuMockMvc.perform(get("/api/rol-menus/{id}", rolMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rolMenu.getId().intValue()))
            .andExpect(jsonPath("$.permitirAcceso").value(DEFAULT_PERMITIR_ACCESO.booleanValue()))
            .andExpect(jsonPath("$.permitirCrear").value(DEFAULT_PERMITIR_CREAR.booleanValue()))
            .andExpect(jsonPath("$.permitirEditar").value(DEFAULT_PERMITIR_EDITAR.booleanValue()))
            .andExpect(jsonPath("$.permitirEliminar").value(DEFAULT_PERMITIR_ELIMINAR.booleanValue()))
            .andExpect(jsonPath("$.auth_name").value(DEFAULT_AUTH_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingRolMenu() throws Exception {
        // Get the rolMenu
        restRolMenuMockMvc.perform(get("/api/rol-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRolMenu() throws Exception {
        // Initialize the database
        rolMenuRepository.saveAndFlush(rolMenu);

        int databaseSizeBeforeUpdate = rolMenuRepository.findAll().size();

        // Update the rolMenu
        RolMenu updatedRolMenu = rolMenuRepository.findById(rolMenu.getId()).get();
        // Disconnect from session so that the updates on updatedRolMenu are not directly saved in db
        em.detach(updatedRolMenu);
        updatedRolMenu
            .permitirAcceso(UPDATED_PERMITIR_ACCESO)
            .permitirCrear(UPDATED_PERMITIR_CREAR)
            .permitirEditar(UPDATED_PERMITIR_EDITAR)
            .permitirEliminar(UPDATED_PERMITIR_ELIMINAR)
            .auth_name(UPDATED_AUTH_NAME);
        RolMenuDTO rolMenuDTO = rolMenuMapper.toDto(updatedRolMenu);

        restRolMenuMockMvc.perform(put("/api/rol-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolMenuDTO)))
            .andExpect(status().isOk());

        // Validate the RolMenu in the database
        List<RolMenu> rolMenuList = rolMenuRepository.findAll();
        assertThat(rolMenuList).hasSize(databaseSizeBeforeUpdate);
        RolMenu testRolMenu = rolMenuList.get(rolMenuList.size() - 1);
        assertThat(testRolMenu.isPermitirAcceso()).isEqualTo(UPDATED_PERMITIR_ACCESO);
        assertThat(testRolMenu.isPermitirCrear()).isEqualTo(UPDATED_PERMITIR_CREAR);
        assertThat(testRolMenu.isPermitirEditar()).isEqualTo(UPDATED_PERMITIR_EDITAR);
        assertThat(testRolMenu.isPermitirEliminar()).isEqualTo(UPDATED_PERMITIR_ELIMINAR);
        assertThat(testRolMenu.getAuth_name()).isEqualTo(UPDATED_AUTH_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingRolMenu() throws Exception {
        int databaseSizeBeforeUpdate = rolMenuRepository.findAll().size();

        // Create the RolMenu
        RolMenuDTO rolMenuDTO = rolMenuMapper.toDto(rolMenu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRolMenuMockMvc.perform(put("/api/rol-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RolMenu in the database
        List<RolMenu> rolMenuList = rolMenuRepository.findAll();
        assertThat(rolMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRolMenu() throws Exception {
        // Initialize the database
        rolMenuRepository.saveAndFlush(rolMenu);

        int databaseSizeBeforeDelete = rolMenuRepository.findAll().size();

        // Delete the rolMenu
        restRolMenuMockMvc.perform(delete("/api/rol-menus/{id}", rolMenu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RolMenu> rolMenuList = rolMenuRepository.findAll();
        assertThat(rolMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RolMenu.class);
        RolMenu rolMenu1 = new RolMenu();
        rolMenu1.setId(1L);
        RolMenu rolMenu2 = new RolMenu();
        rolMenu2.setId(rolMenu1.getId());
        assertThat(rolMenu1).isEqualTo(rolMenu2);
        rolMenu2.setId(2L);
        assertThat(rolMenu1).isNotEqualTo(rolMenu2);
        rolMenu1.setId(null);
        assertThat(rolMenu1).isNotEqualTo(rolMenu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RolMenuDTO.class);
        RolMenuDTO rolMenuDTO1 = new RolMenuDTO();
        rolMenuDTO1.setId(1L);
        RolMenuDTO rolMenuDTO2 = new RolMenuDTO();
        assertThat(rolMenuDTO1).isNotEqualTo(rolMenuDTO2);
        rolMenuDTO2.setId(rolMenuDTO1.getId());
        assertThat(rolMenuDTO1).isEqualTo(rolMenuDTO2);
        rolMenuDTO2.setId(2L);
        assertThat(rolMenuDTO1).isNotEqualTo(rolMenuDTO2);
        rolMenuDTO1.setId(null);
        assertThat(rolMenuDTO1).isNotEqualTo(rolMenuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rolMenuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rolMenuMapper.fromId(null)).isNull();
    }
}
