package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.CronogramaCiecytFases;
import co.edu.itp.ciecyt.repository.CronogramaCiecytFasesRepository;
import co.edu.itp.ciecyt.service.CronogramaCiecytFasesService;
import co.edu.itp.ciecyt.service.dto.CronogramaCiecytFasesDTO;
import co.edu.itp.ciecyt.service.mapper.CronogramaCiecytFasesMapper;
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
 * Integration tests for the {@link CronogramaCiecytFasesResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class CronogramaCiecytFasesResourceIT {

    private static final LocalDate DEFAULT_INICIO_FASE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INICIO_FASE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FIN_FASE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FIN_FASE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TEXTO_EXPLICATIVO = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_EXPLICATIVO = "BBBBBBBBBB";

    @Autowired
    private CronogramaCiecytFasesRepository cronogramaCiecytFasesRepository;

    @Autowired
    private CronogramaCiecytFasesMapper cronogramaCiecytFasesMapper;

    @Autowired
    private CronogramaCiecytFasesService cronogramaCiecytFasesService;

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

    private MockMvc restCronogramaCiecytFasesMockMvc;

    private CronogramaCiecytFases cronogramaCiecytFases;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CronogramaCiecytFasesResource cronogramaCiecytFasesResource = new CronogramaCiecytFasesResource(cronogramaCiecytFasesService);
        this.restCronogramaCiecytFasesMockMvc = MockMvcBuilders.standaloneSetup(cronogramaCiecytFasesResource)
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
    public static CronogramaCiecytFases createEntity(EntityManager em) {
        CronogramaCiecytFases cronogramaCiecytFases = new CronogramaCiecytFases()
            .inicioFase(DEFAULT_INICIO_FASE)
            .finFase(DEFAULT_FIN_FASE)
            .textoExplicativo(DEFAULT_TEXTO_EXPLICATIVO);
        return cronogramaCiecytFases;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CronogramaCiecytFases createUpdatedEntity(EntityManager em) {
        CronogramaCiecytFases cronogramaCiecytFases = new CronogramaCiecytFases()
            .inicioFase(UPDATED_INICIO_FASE)
            .finFase(UPDATED_FIN_FASE)
            .textoExplicativo(UPDATED_TEXTO_EXPLICATIVO);
        return cronogramaCiecytFases;
    }

    @BeforeEach
    public void initTest() {
        cronogramaCiecytFases = createEntity(em);
    }

    @Test
    @Transactional
    public void createCronogramaCiecytFases() throws Exception {
        int databaseSizeBeforeCreate = cronogramaCiecytFasesRepository.findAll().size();

        // Create the CronogramaCiecytFases
        CronogramaCiecytFasesDTO cronogramaCiecytFasesDTO = cronogramaCiecytFasesMapper.toDto(cronogramaCiecytFases);
        restCronogramaCiecytFasesMockMvc.perform(post("/api/cronograma-ciecyt-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaCiecytFasesDTO)))
            .andExpect(status().isCreated());

        // Validate the CronogramaCiecytFases in the database
        List<CronogramaCiecytFases> cronogramaCiecytFasesList = cronogramaCiecytFasesRepository.findAll();
        assertThat(cronogramaCiecytFasesList).hasSize(databaseSizeBeforeCreate + 1);
        CronogramaCiecytFases testCronogramaCiecytFases = cronogramaCiecytFasesList.get(cronogramaCiecytFasesList.size() - 1);
        assertThat(testCronogramaCiecytFases.getInicioFase()).isEqualTo(DEFAULT_INICIO_FASE);
        assertThat(testCronogramaCiecytFases.getFinFase()).isEqualTo(DEFAULT_FIN_FASE);
        assertThat(testCronogramaCiecytFases.getTextoExplicativo()).isEqualTo(DEFAULT_TEXTO_EXPLICATIVO);
    }

    @Test
    @Transactional
    public void createCronogramaCiecytFasesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cronogramaCiecytFasesRepository.findAll().size();

        // Create the CronogramaCiecytFases with an existing ID
        cronogramaCiecytFases.setId(1L);
        CronogramaCiecytFasesDTO cronogramaCiecytFasesDTO = cronogramaCiecytFasesMapper.toDto(cronogramaCiecytFases);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCronogramaCiecytFasesMockMvc.perform(post("/api/cronograma-ciecyt-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaCiecytFasesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CronogramaCiecytFases in the database
        List<CronogramaCiecytFases> cronogramaCiecytFasesList = cronogramaCiecytFasesRepository.findAll();
        assertThat(cronogramaCiecytFasesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCronogramaCiecytFases() throws Exception {
        // Initialize the database
        cronogramaCiecytFasesRepository.saveAndFlush(cronogramaCiecytFases);

        // Get all the cronogramaCiecytFasesList
        restCronogramaCiecytFasesMockMvc.perform(get("/api/cronograma-ciecyt-fases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cronogramaCiecytFases.getId().intValue())))
            .andExpect(jsonPath("$.[*].inicioFase").value(hasItem(DEFAULT_INICIO_FASE.toString())))
            .andExpect(jsonPath("$.[*].finFase").value(hasItem(DEFAULT_FIN_FASE.toString())))
            .andExpect(jsonPath("$.[*].textoExplicativo").value(hasItem(DEFAULT_TEXTO_EXPLICATIVO)));
    }
    
    @Test
    @Transactional
    public void getCronogramaCiecytFases() throws Exception {
        // Initialize the database
        cronogramaCiecytFasesRepository.saveAndFlush(cronogramaCiecytFases);

        // Get the cronogramaCiecytFases
        restCronogramaCiecytFasesMockMvc.perform(get("/api/cronograma-ciecyt-fases/{id}", cronogramaCiecytFases.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cronogramaCiecytFases.getId().intValue()))
            .andExpect(jsonPath("$.inicioFase").value(DEFAULT_INICIO_FASE.toString()))
            .andExpect(jsonPath("$.finFase").value(DEFAULT_FIN_FASE.toString()))
            .andExpect(jsonPath("$.textoExplicativo").value(DEFAULT_TEXTO_EXPLICATIVO));
    }

    @Test
    @Transactional
    public void getNonExistingCronogramaCiecytFases() throws Exception {
        // Get the cronogramaCiecytFases
        restCronogramaCiecytFasesMockMvc.perform(get("/api/cronograma-ciecyt-fases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCronogramaCiecytFases() throws Exception {
        // Initialize the database
        cronogramaCiecytFasesRepository.saveAndFlush(cronogramaCiecytFases);

        int databaseSizeBeforeUpdate = cronogramaCiecytFasesRepository.findAll().size();

        // Update the cronogramaCiecytFases
        CronogramaCiecytFases updatedCronogramaCiecytFases = cronogramaCiecytFasesRepository.findById(cronogramaCiecytFases.getId()).get();
        // Disconnect from session so that the updates on updatedCronogramaCiecytFases are not directly saved in db
        em.detach(updatedCronogramaCiecytFases);
        updatedCronogramaCiecytFases
            .inicioFase(UPDATED_INICIO_FASE)
            .finFase(UPDATED_FIN_FASE)
            .textoExplicativo(UPDATED_TEXTO_EXPLICATIVO);
        CronogramaCiecytFasesDTO cronogramaCiecytFasesDTO = cronogramaCiecytFasesMapper.toDto(updatedCronogramaCiecytFases);

        restCronogramaCiecytFasesMockMvc.perform(put("/api/cronograma-ciecyt-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaCiecytFasesDTO)))
            .andExpect(status().isOk());

        // Validate the CronogramaCiecytFases in the database
        List<CronogramaCiecytFases> cronogramaCiecytFasesList = cronogramaCiecytFasesRepository.findAll();
        assertThat(cronogramaCiecytFasesList).hasSize(databaseSizeBeforeUpdate);
        CronogramaCiecytFases testCronogramaCiecytFases = cronogramaCiecytFasesList.get(cronogramaCiecytFasesList.size() - 1);
        assertThat(testCronogramaCiecytFases.getInicioFase()).isEqualTo(UPDATED_INICIO_FASE);
        assertThat(testCronogramaCiecytFases.getFinFase()).isEqualTo(UPDATED_FIN_FASE);
        assertThat(testCronogramaCiecytFases.getTextoExplicativo()).isEqualTo(UPDATED_TEXTO_EXPLICATIVO);
    }

    @Test
    @Transactional
    public void updateNonExistingCronogramaCiecytFases() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaCiecytFasesRepository.findAll().size();

        // Create the CronogramaCiecytFases
        CronogramaCiecytFasesDTO cronogramaCiecytFasesDTO = cronogramaCiecytFasesMapper.toDto(cronogramaCiecytFases);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCronogramaCiecytFasesMockMvc.perform(put("/api/cronograma-ciecyt-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaCiecytFasesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CronogramaCiecytFases in the database
        List<CronogramaCiecytFases> cronogramaCiecytFasesList = cronogramaCiecytFasesRepository.findAll();
        assertThat(cronogramaCiecytFasesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCronogramaCiecytFases() throws Exception {
        // Initialize the database
        cronogramaCiecytFasesRepository.saveAndFlush(cronogramaCiecytFases);

        int databaseSizeBeforeDelete = cronogramaCiecytFasesRepository.findAll().size();

        // Delete the cronogramaCiecytFases
        restCronogramaCiecytFasesMockMvc.perform(delete("/api/cronograma-ciecyt-fases/{id}", cronogramaCiecytFases.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CronogramaCiecytFases> cronogramaCiecytFasesList = cronogramaCiecytFasesRepository.findAll();
        assertThat(cronogramaCiecytFasesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CronogramaCiecytFases.class);
        CronogramaCiecytFases cronogramaCiecytFases1 = new CronogramaCiecytFases();
        cronogramaCiecytFases1.setId(1L);
        CronogramaCiecytFases cronogramaCiecytFases2 = new CronogramaCiecytFases();
        cronogramaCiecytFases2.setId(cronogramaCiecytFases1.getId());
        assertThat(cronogramaCiecytFases1).isEqualTo(cronogramaCiecytFases2);
        cronogramaCiecytFases2.setId(2L);
        assertThat(cronogramaCiecytFases1).isNotEqualTo(cronogramaCiecytFases2);
        cronogramaCiecytFases1.setId(null);
        assertThat(cronogramaCiecytFases1).isNotEqualTo(cronogramaCiecytFases2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CronogramaCiecytFasesDTO.class);
        CronogramaCiecytFasesDTO cronogramaCiecytFasesDTO1 = new CronogramaCiecytFasesDTO();
        cronogramaCiecytFasesDTO1.setId(1L);
        CronogramaCiecytFasesDTO cronogramaCiecytFasesDTO2 = new CronogramaCiecytFasesDTO();
        assertThat(cronogramaCiecytFasesDTO1).isNotEqualTo(cronogramaCiecytFasesDTO2);
        cronogramaCiecytFasesDTO2.setId(cronogramaCiecytFasesDTO1.getId());
        assertThat(cronogramaCiecytFasesDTO1).isEqualTo(cronogramaCiecytFasesDTO2);
        cronogramaCiecytFasesDTO2.setId(2L);
        assertThat(cronogramaCiecytFasesDTO1).isNotEqualTo(cronogramaCiecytFasesDTO2);
        cronogramaCiecytFasesDTO1.setId(null);
        assertThat(cronogramaCiecytFasesDTO1).isNotEqualTo(cronogramaCiecytFasesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cronogramaCiecytFasesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cronogramaCiecytFasesMapper.fromId(null)).isNull();
    }
}
