package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.GrupoSemillero;
import co.edu.itp.ciecyt.repository.GrupoSemilleroRepository;
import co.edu.itp.ciecyt.service.GrupoSemilleroService;
import co.edu.itp.ciecyt.service.dto.GrupoSemilleroDTO;
import co.edu.itp.ciecyt.service.mapper.GrupoSemilleroMapper;
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
 * Integration tests for the {@link GrupoSemilleroResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class GrupoSemilleroResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TIPO = false;
    private static final Boolean UPDATED_TIPO = true;

    @Autowired
    private GrupoSemilleroRepository grupoSemilleroRepository;

    @Autowired
    private GrupoSemilleroMapper grupoSemilleroMapper;

    @Autowired
    private GrupoSemilleroService grupoSemilleroService;

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

    private MockMvc restGrupoSemilleroMockMvc;

    private GrupoSemillero grupoSemillero;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrupoSemilleroResource grupoSemilleroResource = new GrupoSemilleroResource(grupoSemilleroService);
        this.restGrupoSemilleroMockMvc = MockMvcBuilders.standaloneSetup(grupoSemilleroResource)
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
    public static GrupoSemillero createEntity(EntityManager em) {
        GrupoSemillero grupoSemillero = new GrupoSemillero()
            .nombre(DEFAULT_NOMBRE)
            .tipo(DEFAULT_TIPO);
        return grupoSemillero;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrupoSemillero createUpdatedEntity(EntityManager em) {
        GrupoSemillero grupoSemillero = new GrupoSemillero()
            .nombre(UPDATED_NOMBRE)
            .tipo(UPDATED_TIPO);
        return grupoSemillero;
    }

    @BeforeEach
    public void initTest() {
        grupoSemillero = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrupoSemillero() throws Exception {
        int databaseSizeBeforeCreate = grupoSemilleroRepository.findAll().size();

        // Create the GrupoSemillero
        GrupoSemilleroDTO grupoSemilleroDTO = grupoSemilleroMapper.toDto(grupoSemillero);
        restGrupoSemilleroMockMvc.perform(post("/api/grupo-semilleros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSemilleroDTO)))
            .andExpect(status().isCreated());

        // Validate the GrupoSemillero in the database
        List<GrupoSemillero> grupoSemilleroList = grupoSemilleroRepository.findAll();
        assertThat(grupoSemilleroList).hasSize(databaseSizeBeforeCreate + 1);
        GrupoSemillero testGrupoSemillero = grupoSemilleroList.get(grupoSemilleroList.size() - 1);
        assertThat(testGrupoSemillero.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testGrupoSemillero.isTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createGrupoSemilleroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grupoSemilleroRepository.findAll().size();

        // Create the GrupoSemillero with an existing ID
        grupoSemillero.setId(1L);
        GrupoSemilleroDTO grupoSemilleroDTO = grupoSemilleroMapper.toDto(grupoSemillero);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrupoSemilleroMockMvc.perform(post("/api/grupo-semilleros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSemilleroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoSemillero in the database
        List<GrupoSemillero> grupoSemilleroList = grupoSemilleroRepository.findAll();
        assertThat(grupoSemilleroList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGrupoSemilleros() throws Exception {
        // Initialize the database
        grupoSemilleroRepository.saveAndFlush(grupoSemillero);

        // Get all the grupoSemilleroList
        restGrupoSemilleroMockMvc.perform(get("/api/grupo-semilleros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupoSemillero.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getGrupoSemillero() throws Exception {
        // Initialize the database
        grupoSemilleroRepository.saveAndFlush(grupoSemillero);

        // Get the grupoSemillero
        restGrupoSemilleroMockMvc.perform(get("/api/grupo-semilleros/{id}", grupoSemillero.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grupoSemillero.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGrupoSemillero() throws Exception {
        // Get the grupoSemillero
        restGrupoSemilleroMockMvc.perform(get("/api/grupo-semilleros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrupoSemillero() throws Exception {
        // Initialize the database
        grupoSemilleroRepository.saveAndFlush(grupoSemillero);

        int databaseSizeBeforeUpdate = grupoSemilleroRepository.findAll().size();

        // Update the grupoSemillero
        GrupoSemillero updatedGrupoSemillero = grupoSemilleroRepository.findById(grupoSemillero.getId()).get();
        // Disconnect from session so that the updates on updatedGrupoSemillero are not directly saved in db
        em.detach(updatedGrupoSemillero);
        updatedGrupoSemillero
            .nombre(UPDATED_NOMBRE)
            .tipo(UPDATED_TIPO);
        GrupoSemilleroDTO grupoSemilleroDTO = grupoSemilleroMapper.toDto(updatedGrupoSemillero);

        restGrupoSemilleroMockMvc.perform(put("/api/grupo-semilleros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSemilleroDTO)))
            .andExpect(status().isOk());

        // Validate the GrupoSemillero in the database
        List<GrupoSemillero> grupoSemilleroList = grupoSemilleroRepository.findAll();
        assertThat(grupoSemilleroList).hasSize(databaseSizeBeforeUpdate);
        GrupoSemillero testGrupoSemillero = grupoSemilleroList.get(grupoSemilleroList.size() - 1);
        assertThat(testGrupoSemillero.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testGrupoSemillero.isTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingGrupoSemillero() throws Exception {
        int databaseSizeBeforeUpdate = grupoSemilleroRepository.findAll().size();

        // Create the GrupoSemillero
        GrupoSemilleroDTO grupoSemilleroDTO = grupoSemilleroMapper.toDto(grupoSemillero);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrupoSemilleroMockMvc.perform(put("/api/grupo-semilleros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSemilleroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoSemillero in the database
        List<GrupoSemillero> grupoSemilleroList = grupoSemilleroRepository.findAll();
        assertThat(grupoSemilleroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGrupoSemillero() throws Exception {
        // Initialize the database
        grupoSemilleroRepository.saveAndFlush(grupoSemillero);

        int databaseSizeBeforeDelete = grupoSemilleroRepository.findAll().size();

        // Delete the grupoSemillero
        restGrupoSemilleroMockMvc.perform(delete("/api/grupo-semilleros/{id}", grupoSemillero.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrupoSemillero> grupoSemilleroList = grupoSemilleroRepository.findAll();
        assertThat(grupoSemilleroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoSemillero.class);
        GrupoSemillero grupoSemillero1 = new GrupoSemillero();
        grupoSemillero1.setId(1L);
        GrupoSemillero grupoSemillero2 = new GrupoSemillero();
        grupoSemillero2.setId(grupoSemillero1.getId());
        assertThat(grupoSemillero1).isEqualTo(grupoSemillero2);
        grupoSemillero2.setId(2L);
        assertThat(grupoSemillero1).isNotEqualTo(grupoSemillero2);
        grupoSemillero1.setId(null);
        assertThat(grupoSemillero1).isNotEqualTo(grupoSemillero2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoSemilleroDTO.class);
        GrupoSemilleroDTO grupoSemilleroDTO1 = new GrupoSemilleroDTO();
        grupoSemilleroDTO1.setId(1L);
        GrupoSemilleroDTO grupoSemilleroDTO2 = new GrupoSemilleroDTO();
        assertThat(grupoSemilleroDTO1).isNotEqualTo(grupoSemilleroDTO2);
        grupoSemilleroDTO2.setId(grupoSemilleroDTO1.getId());
        assertThat(grupoSemilleroDTO1).isEqualTo(grupoSemilleroDTO2);
        grupoSemilleroDTO2.setId(2L);
        assertThat(grupoSemilleroDTO1).isNotEqualTo(grupoSemilleroDTO2);
        grupoSemilleroDTO1.setId(null);
        assertThat(grupoSemilleroDTO1).isNotEqualTo(grupoSemilleroDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(grupoSemilleroMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(grupoSemilleroMapper.fromId(null)).isNull();
    }
}
