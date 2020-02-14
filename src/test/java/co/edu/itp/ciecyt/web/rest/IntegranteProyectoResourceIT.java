package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.IntegranteProyecto;
import co.edu.itp.ciecyt.repository.IntegranteProyectoRepository;
import co.edu.itp.ciecyt.service.IntegranteProyectoService;
import co.edu.itp.ciecyt.service.dto.IntegranteProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.IntegranteProyectoMapper;
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
 * Integration tests for the {@link IntegranteProyectoResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class IntegranteProyectoResourceIT {

    private static final String DEFAULT_INTEGRANTE = "AAAAAAAAAA";
    private static final String UPDATED_INTEGRANTE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private IntegranteProyectoRepository integranteProyectoRepository;

    @Autowired
    private IntegranteProyectoMapper integranteProyectoMapper;

    @Autowired
    private IntegranteProyectoService integranteProyectoService;

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

    private MockMvc restIntegranteProyectoMockMvc;

    private IntegranteProyecto integranteProyecto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IntegranteProyectoResource integranteProyectoResource = new IntegranteProyectoResource(integranteProyectoService);
        this.restIntegranteProyectoMockMvc = MockMvcBuilders.standaloneSetup(integranteProyectoResource)
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
    public static IntegranteProyecto createEntity(EntityManager em) {
        IntegranteProyecto integranteProyecto = new IntegranteProyecto()
            .integrante(DEFAULT_INTEGRANTE)
            .descripcion(DEFAULT_DESCRIPCION);
        return integranteProyecto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IntegranteProyecto createUpdatedEntity(EntityManager em) {
        IntegranteProyecto integranteProyecto = new IntegranteProyecto()
            .integrante(UPDATED_INTEGRANTE)
            .descripcion(UPDATED_DESCRIPCION);
        return integranteProyecto;
    }

    @BeforeEach
    public void initTest() {
        integranteProyecto = createEntity(em);
    }

    @Test
    @Transactional
    public void createIntegranteProyecto() throws Exception {
        int databaseSizeBeforeCreate = integranteProyectoRepository.findAll().size();

        // Create the IntegranteProyecto
        IntegranteProyectoDTO integranteProyectoDTO = integranteProyectoMapper.toDto(integranteProyecto);
        restIntegranteProyectoMockMvc.perform(post("/api/integrante-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(integranteProyectoDTO)))
            .andExpect(status().isCreated());

        // Validate the IntegranteProyecto in the database
        List<IntegranteProyecto> integranteProyectoList = integranteProyectoRepository.findAll();
        assertThat(integranteProyectoList).hasSize(databaseSizeBeforeCreate + 1);
        IntegranteProyecto testIntegranteProyecto = integranteProyectoList.get(integranteProyectoList.size() - 1);
        assertThat(testIntegranteProyecto.getIntegrante()).isEqualTo(DEFAULT_INTEGRANTE);
        assertThat(testIntegranteProyecto.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createIntegranteProyectoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = integranteProyectoRepository.findAll().size();

        // Create the IntegranteProyecto with an existing ID
        integranteProyecto.setId(1L);
        IntegranteProyectoDTO integranteProyectoDTO = integranteProyectoMapper.toDto(integranteProyecto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIntegranteProyectoMockMvc.perform(post("/api/integrante-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(integranteProyectoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IntegranteProyecto in the database
        List<IntegranteProyecto> integranteProyectoList = integranteProyectoRepository.findAll();
        assertThat(integranteProyectoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllIntegranteProyectos() throws Exception {
        // Initialize the database
        integranteProyectoRepository.saveAndFlush(integranteProyecto);

        // Get all the integranteProyectoList
        restIntegranteProyectoMockMvc.perform(get("/api/integrante-proyectos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(integranteProyecto.getId().intValue())))
            .andExpect(jsonPath("$.[*].integrante").value(hasItem(DEFAULT_INTEGRANTE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getIntegranteProyecto() throws Exception {
        // Initialize the database
        integranteProyectoRepository.saveAndFlush(integranteProyecto);

        // Get the integranteProyecto
        restIntegranteProyectoMockMvc.perform(get("/api/integrante-proyectos/{id}", integranteProyecto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(integranteProyecto.getId().intValue()))
            .andExpect(jsonPath("$.integrante").value(DEFAULT_INTEGRANTE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }

    @Test
    @Transactional
    public void getNonExistingIntegranteProyecto() throws Exception {
        // Get the integranteProyecto
        restIntegranteProyectoMockMvc.perform(get("/api/integrante-proyectos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIntegranteProyecto() throws Exception {
        // Initialize the database
        integranteProyectoRepository.saveAndFlush(integranteProyecto);

        int databaseSizeBeforeUpdate = integranteProyectoRepository.findAll().size();

        // Update the integranteProyecto
        IntegranteProyecto updatedIntegranteProyecto = integranteProyectoRepository.findById(integranteProyecto.getId()).get();
        // Disconnect from session so that the updates on updatedIntegranteProyecto are not directly saved in db
        em.detach(updatedIntegranteProyecto);
        updatedIntegranteProyecto
            .integrante(UPDATED_INTEGRANTE)
            .descripcion(UPDATED_DESCRIPCION);
        IntegranteProyectoDTO integranteProyectoDTO = integranteProyectoMapper.toDto(updatedIntegranteProyecto);

        restIntegranteProyectoMockMvc.perform(put("/api/integrante-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(integranteProyectoDTO)))
            .andExpect(status().isOk());

        // Validate the IntegranteProyecto in the database
        List<IntegranteProyecto> integranteProyectoList = integranteProyectoRepository.findAll();
        assertThat(integranteProyectoList).hasSize(databaseSizeBeforeUpdate);
        IntegranteProyecto testIntegranteProyecto = integranteProyectoList.get(integranteProyectoList.size() - 1);
        assertThat(testIntegranteProyecto.getIntegrante()).isEqualTo(UPDATED_INTEGRANTE);
        assertThat(testIntegranteProyecto.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingIntegranteProyecto() throws Exception {
        int databaseSizeBeforeUpdate = integranteProyectoRepository.findAll().size();

        // Create the IntegranteProyecto
        IntegranteProyectoDTO integranteProyectoDTO = integranteProyectoMapper.toDto(integranteProyecto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIntegranteProyectoMockMvc.perform(put("/api/integrante-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(integranteProyectoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IntegranteProyecto in the database
        List<IntegranteProyecto> integranteProyectoList = integranteProyectoRepository.findAll();
        assertThat(integranteProyectoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIntegranteProyecto() throws Exception {
        // Initialize the database
        integranteProyectoRepository.saveAndFlush(integranteProyecto);

        int databaseSizeBeforeDelete = integranteProyectoRepository.findAll().size();

        // Delete the integranteProyecto
        restIntegranteProyectoMockMvc.perform(delete("/api/integrante-proyectos/{id}", integranteProyecto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IntegranteProyecto> integranteProyectoList = integranteProyectoRepository.findAll();
        assertThat(integranteProyectoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IntegranteProyecto.class);
        IntegranteProyecto integranteProyecto1 = new IntegranteProyecto();
        integranteProyecto1.setId(1L);
        IntegranteProyecto integranteProyecto2 = new IntegranteProyecto();
        integranteProyecto2.setId(integranteProyecto1.getId());
        assertThat(integranteProyecto1).isEqualTo(integranteProyecto2);
        integranteProyecto2.setId(2L);
        assertThat(integranteProyecto1).isNotEqualTo(integranteProyecto2);
        integranteProyecto1.setId(null);
        assertThat(integranteProyecto1).isNotEqualTo(integranteProyecto2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IntegranteProyectoDTO.class);
        IntegranteProyectoDTO integranteProyectoDTO1 = new IntegranteProyectoDTO();
        integranteProyectoDTO1.setId(1L);
        IntegranteProyectoDTO integranteProyectoDTO2 = new IntegranteProyectoDTO();
        assertThat(integranteProyectoDTO1).isNotEqualTo(integranteProyectoDTO2);
        integranteProyectoDTO2.setId(integranteProyectoDTO1.getId());
        assertThat(integranteProyectoDTO1).isEqualTo(integranteProyectoDTO2);
        integranteProyectoDTO2.setId(2L);
        assertThat(integranteProyectoDTO1).isNotEqualTo(integranteProyectoDTO2);
        integranteProyectoDTO1.setId(null);
        assertThat(integranteProyectoDTO1).isNotEqualTo(integranteProyectoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(integranteProyectoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(integranteProyectoMapper.fromId(null)).isNull();
    }
}
