package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.RolesModalidad;
import co.edu.itp.ciecyt.repository.RolesModalidadRepository;
import co.edu.itp.ciecyt.service.RolesModalidadService;
import co.edu.itp.ciecyt.service.dto.RolesModalidadDTO;
import co.edu.itp.ciecyt.service.mapper.RolesModalidadMapper;
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
 * Integration tests for the {@link RolesModalidadResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class RolesModalidadResourceIT {

    private static final String DEFAULT_ROL = "AAAAAAAAAA";
    private static final String UPDATED_ROL = "BBBBBBBBBB";

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

    private static final Boolean DEFAULT_CALIFICADOR = false;
    private static final Boolean UPDATED_CALIFICADOR = true;

    @Autowired
    private RolesModalidadRepository rolesModalidadRepository;

    @Autowired
    private RolesModalidadMapper rolesModalidadMapper;

    @Autowired
    private RolesModalidadService rolesModalidadService;

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

    private MockMvc restRolesModalidadMockMvc;

    private RolesModalidad rolesModalidad;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RolesModalidadResource rolesModalidadResource = new RolesModalidadResource(rolesModalidadService);
        this.restRolesModalidadMockMvc = MockMvcBuilders.standaloneSetup(rolesModalidadResource)
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
    public static RolesModalidad createEntity(EntityManager em) {
        RolesModalidad rolesModalidad = new RolesModalidad()
            .rol(DEFAULT_ROL)
            .cantidad(DEFAULT_CANTIDAD)
            .calificador(DEFAULT_CALIFICADOR);
        return rolesModalidad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RolesModalidad createUpdatedEntity(EntityManager em) {
        RolesModalidad rolesModalidad = new RolesModalidad()
            .rol(UPDATED_ROL)
            .cantidad(UPDATED_CANTIDAD)
            .calificador(UPDATED_CALIFICADOR);
        return rolesModalidad;
    }

    @BeforeEach
    public void initTest() {
        rolesModalidad = createEntity(em);
    }

    @Test
    @Transactional
    public void createRolesModalidad() throws Exception {
        int databaseSizeBeforeCreate = rolesModalidadRepository.findAll().size();

        // Create the RolesModalidad
        RolesModalidadDTO rolesModalidadDTO = rolesModalidadMapper.toDto(rolesModalidad);
        restRolesModalidadMockMvc.perform(post("/api/roles-modalidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesModalidadDTO)))
            .andExpect(status().isCreated());

        // Validate the RolesModalidad in the database
        List<RolesModalidad> rolesModalidadList = rolesModalidadRepository.findAll();
        assertThat(rolesModalidadList).hasSize(databaseSizeBeforeCreate + 1);
        RolesModalidad testRolesModalidad = rolesModalidadList.get(rolesModalidadList.size() - 1);
        assertThat(testRolesModalidad.getRol()).isEqualTo(DEFAULT_ROL);
        assertThat(testRolesModalidad.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
        assertThat(testRolesModalidad.isCalificador()).isEqualTo(DEFAULT_CALIFICADOR);
    }

    @Test
    @Transactional
    public void createRolesModalidadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rolesModalidadRepository.findAll().size();

        // Create the RolesModalidad with an existing ID
        rolesModalidad.setId(1L);
        RolesModalidadDTO rolesModalidadDTO = rolesModalidadMapper.toDto(rolesModalidad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRolesModalidadMockMvc.perform(post("/api/roles-modalidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesModalidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RolesModalidad in the database
        List<RolesModalidad> rolesModalidadList = rolesModalidadRepository.findAll();
        assertThat(rolesModalidadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRolesModalidads() throws Exception {
        // Initialize the database
        rolesModalidadRepository.saveAndFlush(rolesModalidad);

        // Get all the rolesModalidadList
        restRolesModalidadMockMvc.perform(get("/api/roles-modalidads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rolesModalidad.getId().intValue())))
            .andExpect(jsonPath("$.[*].rol").value(hasItem(DEFAULT_ROL)))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].calificador").value(hasItem(DEFAULT_CALIFICADOR.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getRolesModalidad() throws Exception {
        // Initialize the database
        rolesModalidadRepository.saveAndFlush(rolesModalidad);

        // Get the rolesModalidad
        restRolesModalidadMockMvc.perform(get("/api/roles-modalidads/{id}", rolesModalidad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rolesModalidad.getId().intValue()))
            .andExpect(jsonPath("$.rol").value(DEFAULT_ROL))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD))
            .andExpect(jsonPath("$.calificador").value(DEFAULT_CALIFICADOR.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRolesModalidad() throws Exception {
        // Get the rolesModalidad
        restRolesModalidadMockMvc.perform(get("/api/roles-modalidads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRolesModalidad() throws Exception {
        // Initialize the database
        rolesModalidadRepository.saveAndFlush(rolesModalidad);

        int databaseSizeBeforeUpdate = rolesModalidadRepository.findAll().size();

        // Update the rolesModalidad
        RolesModalidad updatedRolesModalidad = rolesModalidadRepository.findById(rolesModalidad.getId()).get();
        // Disconnect from session so that the updates on updatedRolesModalidad are not directly saved in db
        em.detach(updatedRolesModalidad);
        updatedRolesModalidad
            .rol(UPDATED_ROL)
            .cantidad(UPDATED_CANTIDAD)
            .calificador(UPDATED_CALIFICADOR);
        RolesModalidadDTO rolesModalidadDTO = rolesModalidadMapper.toDto(updatedRolesModalidad);

        restRolesModalidadMockMvc.perform(put("/api/roles-modalidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesModalidadDTO)))
            .andExpect(status().isOk());

        // Validate the RolesModalidad in the database
        List<RolesModalidad> rolesModalidadList = rolesModalidadRepository.findAll();
        assertThat(rolesModalidadList).hasSize(databaseSizeBeforeUpdate);
        RolesModalidad testRolesModalidad = rolesModalidadList.get(rolesModalidadList.size() - 1);
        assertThat(testRolesModalidad.getRol()).isEqualTo(UPDATED_ROL);
        assertThat(testRolesModalidad.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testRolesModalidad.isCalificador()).isEqualTo(UPDATED_CALIFICADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingRolesModalidad() throws Exception {
        int databaseSizeBeforeUpdate = rolesModalidadRepository.findAll().size();

        // Create the RolesModalidad
        RolesModalidadDTO rolesModalidadDTO = rolesModalidadMapper.toDto(rolesModalidad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRolesModalidadMockMvc.perform(put("/api/roles-modalidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesModalidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RolesModalidad in the database
        List<RolesModalidad> rolesModalidadList = rolesModalidadRepository.findAll();
        assertThat(rolesModalidadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRolesModalidad() throws Exception {
        // Initialize the database
        rolesModalidadRepository.saveAndFlush(rolesModalidad);

        int databaseSizeBeforeDelete = rolesModalidadRepository.findAll().size();

        // Delete the rolesModalidad
        restRolesModalidadMockMvc.perform(delete("/api/roles-modalidads/{id}", rolesModalidad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RolesModalidad> rolesModalidadList = rolesModalidadRepository.findAll();
        assertThat(rolesModalidadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RolesModalidad.class);
        RolesModalidad rolesModalidad1 = new RolesModalidad();
        rolesModalidad1.setId(1L);
        RolesModalidad rolesModalidad2 = new RolesModalidad();
        rolesModalidad2.setId(rolesModalidad1.getId());
        assertThat(rolesModalidad1).isEqualTo(rolesModalidad2);
        rolesModalidad2.setId(2L);
        assertThat(rolesModalidad1).isNotEqualTo(rolesModalidad2);
        rolesModalidad1.setId(null);
        assertThat(rolesModalidad1).isNotEqualTo(rolesModalidad2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RolesModalidadDTO.class);
        RolesModalidadDTO rolesModalidadDTO1 = new RolesModalidadDTO();
        rolesModalidadDTO1.setId(1L);
        RolesModalidadDTO rolesModalidadDTO2 = new RolesModalidadDTO();
        assertThat(rolesModalidadDTO1).isNotEqualTo(rolesModalidadDTO2);
        rolesModalidadDTO2.setId(rolesModalidadDTO1.getId());
        assertThat(rolesModalidadDTO1).isEqualTo(rolesModalidadDTO2);
        rolesModalidadDTO2.setId(2L);
        assertThat(rolesModalidadDTO1).isNotEqualTo(rolesModalidadDTO2);
        rolesModalidadDTO1.setId(null);
        assertThat(rolesModalidadDTO1).isNotEqualTo(rolesModalidadDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rolesModalidadMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rolesModalidadMapper.fromId(null)).isNull();
    }
}
