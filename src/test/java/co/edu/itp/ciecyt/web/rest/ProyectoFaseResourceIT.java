package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.ProyectoFase;
import co.edu.itp.ciecyt.repository.ProyectoFaseRepository;
import co.edu.itp.ciecyt.service.ProyectoFaseService;
import co.edu.itp.ciecyt.service.dto.ProyectoFaseDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoFaseMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static co.edu.itp.ciecyt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProyectoFaseResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class ProyectoFaseResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CUMPLIDA = false;
    private static final Boolean UPDATED_CUMPLIDA = true;

    private static final LocalDate DEFAULT_FECHA_CUMPLIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CUMPLIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    @Autowired
    private ProyectoFaseRepository proyectoFaseRepository;

    @Autowired
    private ProyectoFaseMapper proyectoFaseMapper;

    @Autowired
    private ProyectoFaseService proyectoFaseService;

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

    private MockMvc restProyectoFaseMockMvc;

    private ProyectoFase proyectoFase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProyectoFaseResource proyectoFaseResource = new ProyectoFaseResource(proyectoFaseService);
        this.restProyectoFaseMockMvc = MockMvcBuilders.standaloneSetup(proyectoFaseResource)
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
    public static ProyectoFase createEntity(EntityManager em) {
        ProyectoFase proyectoFase = new ProyectoFase()
            .titulo(DEFAULT_TITULO)
            .cumplida(DEFAULT_CUMPLIDA)
            .fechaCumplimiento(DEFAULT_FECHA_CUMPLIMIENTO)
            .observaciones(DEFAULT_OBSERVACIONES);
        return proyectoFase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProyectoFase createUpdatedEntity(EntityManager em) {
        ProyectoFase proyectoFase = new ProyectoFase()
            .titulo(UPDATED_TITULO)
            .cumplida(UPDATED_CUMPLIDA)
            .fechaCumplimiento(UPDATED_FECHA_CUMPLIMIENTO)
            .observaciones(UPDATED_OBSERVACIONES);
        return proyectoFase;
    }

    @BeforeEach
    public void initTest() {
        proyectoFase = createEntity(em);
    }

    @Test
    @Transactional
    public void createProyectoFase() throws Exception {
        int databaseSizeBeforeCreate = proyectoFaseRepository.findAll().size();

        // Create the ProyectoFase
        ProyectoFaseDTO proyectoFaseDTO = proyectoFaseMapper.toDto(proyectoFase);
        restProyectoFaseMockMvc.perform(post("/api/proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoFaseDTO)))
            .andExpect(status().isCreated());

        // Validate the ProyectoFase in the database
        List<ProyectoFase> proyectoFaseList = proyectoFaseRepository.findAll();
        assertThat(proyectoFaseList).hasSize(databaseSizeBeforeCreate + 1);
        ProyectoFase testProyectoFase = proyectoFaseList.get(proyectoFaseList.size() - 1);
        assertThat(testProyectoFase.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testProyectoFase.isCumplida()).isEqualTo(DEFAULT_CUMPLIDA);
        assertThat(testProyectoFase.getFechaCumplimiento()).isEqualTo(DEFAULT_FECHA_CUMPLIMIENTO);
        assertThat(testProyectoFase.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void createProyectoFaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proyectoFaseRepository.findAll().size();

        // Create the ProyectoFase with an existing ID
        proyectoFase.setId(1L);
        ProyectoFaseDTO proyectoFaseDTO = proyectoFaseMapper.toDto(proyectoFase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProyectoFaseMockMvc.perform(post("/api/proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoFaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProyectoFase in the database
        List<ProyectoFase> proyectoFaseList = proyectoFaseRepository.findAll();
        assertThat(proyectoFaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProyectoFases() throws Exception {
        // Initialize the database
        proyectoFaseRepository.saveAndFlush(proyectoFase);

        // Get all the proyectoFaseList
        restProyectoFaseMockMvc.perform(get("/api/proyecto-fases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proyectoFase.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].cumplida").value(hasItem(DEFAULT_CUMPLIDA.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaCumplimiento").value(hasItem(DEFAULT_FECHA_CUMPLIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)));
    }
    
    @Test
    @Transactional
    public void getProyectoFase() throws Exception {
        // Initialize the database
        proyectoFaseRepository.saveAndFlush(proyectoFase);

        // Get the proyectoFase
        restProyectoFaseMockMvc.perform(get("/api/proyecto-fases/{id}", proyectoFase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proyectoFase.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.cumplida").value(DEFAULT_CUMPLIDA.booleanValue()))
            .andExpect(jsonPath("$.fechaCumplimiento").value(DEFAULT_FECHA_CUMPLIMIENTO.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES));
    }

    @Test
    @Transactional
    public void getNonExistingProyectoFase() throws Exception {
        // Get the proyectoFase
        restProyectoFaseMockMvc.perform(get("/api/proyecto-fases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProyectoFase() throws Exception {
        // Initialize the database
        proyectoFaseRepository.saveAndFlush(proyectoFase);

        int databaseSizeBeforeUpdate = proyectoFaseRepository.findAll().size();

        // Update the proyectoFase
        ProyectoFase updatedProyectoFase = proyectoFaseRepository.findById(proyectoFase.getId()).get();
        // Disconnect from session so that the updates on updatedProyectoFase are not directly saved in db
        em.detach(updatedProyectoFase);
        updatedProyectoFase
            .titulo(UPDATED_TITULO)
            .cumplida(UPDATED_CUMPLIDA)
            .fechaCumplimiento(UPDATED_FECHA_CUMPLIMIENTO)
            .observaciones(UPDATED_OBSERVACIONES);
        ProyectoFaseDTO proyectoFaseDTO = proyectoFaseMapper.toDto(updatedProyectoFase);

        restProyectoFaseMockMvc.perform(put("/api/proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoFaseDTO)))
            .andExpect(status().isOk());

        // Validate the ProyectoFase in the database
        List<ProyectoFase> proyectoFaseList = proyectoFaseRepository.findAll();
        assertThat(proyectoFaseList).hasSize(databaseSizeBeforeUpdate);
        ProyectoFase testProyectoFase = proyectoFaseList.get(proyectoFaseList.size() - 1);
        assertThat(testProyectoFase.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testProyectoFase.isCumplida()).isEqualTo(UPDATED_CUMPLIDA);
        assertThat(testProyectoFase.getFechaCumplimiento()).isEqualTo(UPDATED_FECHA_CUMPLIMIENTO);
        assertThat(testProyectoFase.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void updateNonExistingProyectoFase() throws Exception {
        int databaseSizeBeforeUpdate = proyectoFaseRepository.findAll().size();

        // Create the ProyectoFase
        ProyectoFaseDTO proyectoFaseDTO = proyectoFaseMapper.toDto(proyectoFase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProyectoFaseMockMvc.perform(put("/api/proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoFaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProyectoFase in the database
        List<ProyectoFase> proyectoFaseList = proyectoFaseRepository.findAll();
        assertThat(proyectoFaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProyectoFase() throws Exception {
        // Initialize the database
        proyectoFaseRepository.saveAndFlush(proyectoFase);

        int databaseSizeBeforeDelete = proyectoFaseRepository.findAll().size();

        // Delete the proyectoFase
        restProyectoFaseMockMvc.perform(delete("/api/proyecto-fases/{id}", proyectoFase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProyectoFase> proyectoFaseList = proyectoFaseRepository.findAll();
        assertThat(proyectoFaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProyectoFase.class);
        ProyectoFase proyectoFase1 = new ProyectoFase();
        proyectoFase1.setId(1L);
        ProyectoFase proyectoFase2 = new ProyectoFase();
        proyectoFase2.setId(proyectoFase1.getId());
        assertThat(proyectoFase1).isEqualTo(proyectoFase2);
        proyectoFase2.setId(2L);
        assertThat(proyectoFase1).isNotEqualTo(proyectoFase2);
        proyectoFase1.setId(null);
        assertThat(proyectoFase1).isNotEqualTo(proyectoFase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProyectoFaseDTO.class);
        ProyectoFaseDTO proyectoFaseDTO1 = new ProyectoFaseDTO();
        proyectoFaseDTO1.setId(1L);
        ProyectoFaseDTO proyectoFaseDTO2 = new ProyectoFaseDTO();
        assertThat(proyectoFaseDTO1).isNotEqualTo(proyectoFaseDTO2);
        proyectoFaseDTO2.setId(proyectoFaseDTO1.getId());
        assertThat(proyectoFaseDTO1).isEqualTo(proyectoFaseDTO2);
        proyectoFaseDTO2.setId(2L);
        assertThat(proyectoFaseDTO1).isNotEqualTo(proyectoFaseDTO2);
        proyectoFaseDTO1.setId(null);
        assertThat(proyectoFaseDTO1).isNotEqualTo(proyectoFaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(proyectoFaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(proyectoFaseMapper.fromId(null)).isNull();
    }
}
