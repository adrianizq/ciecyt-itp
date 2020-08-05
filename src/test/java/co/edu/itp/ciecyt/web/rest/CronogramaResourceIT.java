package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Cronograma;
import co.edu.itp.ciecyt.repository.CronogramaRepository;
import co.edu.itp.ciecyt.service.CronogramaService;
import co.edu.itp.ciecyt.service.dto.CronogramaDTO;
import co.edu.itp.ciecyt.service.mapper.CronogramaMapper;
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
 * Integration tests for the {@link CronogramaResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class CronogramaResourceIT {

    private static final String DEFAULT_ACTIVIDAD = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVIDAD = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURACION = 1;
    private static final Integer UPDATED_DURACION = 2;

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ORDEN_VISTA = 1;
    private static final Integer UPDATED_ORDEN_VISTA = 2;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Autowired
    private CronogramaMapper cronogramaMapper;

    @Autowired
    private CronogramaService cronogramaService;

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

    private MockMvc restCronogramaMockMvc;

    private Cronograma cronograma;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CronogramaResource cronogramaResource = new CronogramaResource(cronogramaService);
        this.restCronogramaMockMvc = MockMvcBuilders.standaloneSetup(cronogramaResource)
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
    public static Cronograma createEntity(EntityManager em) {
        Cronograma cronograma = new Cronograma()
            .actividad(DEFAULT_ACTIVIDAD)
            .duracion(DEFAULT_DURACION)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN)
            .ordenVista(DEFAULT_ORDEN_VISTA);
        return cronograma;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cronograma createUpdatedEntity(EntityManager em) {
        Cronograma cronograma = new Cronograma()
            .actividad(UPDATED_ACTIVIDAD)
            .duracion(UPDATED_DURACION)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .ordenVista(UPDATED_ORDEN_VISTA);
        return cronograma;
    }

    @BeforeEach
    public void initTest() {
        cronograma = createEntity(em);
    }

    @Test
    @Transactional
    public void createCronograma() throws Exception {
        int databaseSizeBeforeCreate = cronogramaRepository.findAll().size();

        // Create the Cronograma
        CronogramaDTO cronogramaDTO = cronogramaMapper.toDto(cronograma);
        restCronogramaMockMvc.perform(post("/api/cronogramas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaDTO)))
            .andExpect(status().isCreated());

        // Validate the Cronograma in the database
        List<Cronograma> cronogramaList = cronogramaRepository.findAll();
        assertThat(cronogramaList).hasSize(databaseSizeBeforeCreate + 1);
        Cronograma testCronograma = cronogramaList.get(cronogramaList.size() - 1);
        assertThat(testCronograma.getActividad()).isEqualTo(DEFAULT_ACTIVIDAD);
        assertThat(testCronograma.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testCronograma.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testCronograma.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testCronograma.getOrdenVista()).isEqualTo(DEFAULT_ORDEN_VISTA);
    }

    @Test
    @Transactional
    public void createCronogramaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cronogramaRepository.findAll().size();

        // Create the Cronograma with an existing ID
        cronograma.setId(1L);
        CronogramaDTO cronogramaDTO = cronogramaMapper.toDto(cronograma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCronogramaMockMvc.perform(post("/api/cronogramas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cronograma in the database
        List<Cronograma> cronogramaList = cronogramaRepository.findAll();
        assertThat(cronogramaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCronogramas() throws Exception {
        // Initialize the database
        cronogramaRepository.saveAndFlush(cronograma);

        // Get all the cronogramaList
        restCronogramaMockMvc.perform(get("/api/cronogramas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cronograma.getId().intValue())))
            .andExpect(jsonPath("$.[*].actividad").value(hasItem(DEFAULT_ACTIVIDAD)))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].ordenVista").value(hasItem(DEFAULT_ORDEN_VISTA)));
    }
    
    @Test
    @Transactional
    public void getCronograma() throws Exception {
        // Initialize the database
        cronogramaRepository.saveAndFlush(cronograma);

        // Get the cronograma
        restCronogramaMockMvc.perform(get("/api/cronogramas/{id}", cronograma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cronograma.getId().intValue()))
            .andExpect(jsonPath("$.actividad").value(DEFAULT_ACTIVIDAD))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.ordenVista").value(DEFAULT_ORDEN_VISTA));
    }

    @Test
    @Transactional
    public void getNonExistingCronograma() throws Exception {
        // Get the cronograma
        restCronogramaMockMvc.perform(get("/api/cronogramas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCronograma() throws Exception {
        // Initialize the database
        cronogramaRepository.saveAndFlush(cronograma);

        int databaseSizeBeforeUpdate = cronogramaRepository.findAll().size();

        // Update the cronograma
        Cronograma updatedCronograma = cronogramaRepository.findById(cronograma.getId()).get();
        // Disconnect from session so that the updates on updatedCronograma are not directly saved in db
        em.detach(updatedCronograma);
        updatedCronograma
            .actividad(UPDATED_ACTIVIDAD)
            .duracion(UPDATED_DURACION)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .ordenVista(UPDATED_ORDEN_VISTA);
        CronogramaDTO cronogramaDTO = cronogramaMapper.toDto(updatedCronograma);

        restCronogramaMockMvc.perform(put("/api/cronogramas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaDTO)))
            .andExpect(status().isOk());

        // Validate the Cronograma in the database
        List<Cronograma> cronogramaList = cronogramaRepository.findAll();
        assertThat(cronogramaList).hasSize(databaseSizeBeforeUpdate);
        Cronograma testCronograma = cronogramaList.get(cronogramaList.size() - 1);
        assertThat(testCronograma.getActividad()).isEqualTo(UPDATED_ACTIVIDAD);
        assertThat(testCronograma.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testCronograma.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testCronograma.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testCronograma.getOrdenVista()).isEqualTo(UPDATED_ORDEN_VISTA);
    }

    @Test
    @Transactional
    public void updateNonExistingCronograma() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaRepository.findAll().size();

        // Create the Cronograma
        CronogramaDTO cronogramaDTO = cronogramaMapper.toDto(cronograma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCronogramaMockMvc.perform(put("/api/cronogramas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cronogramaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cronograma in the database
        List<Cronograma> cronogramaList = cronogramaRepository.findAll();
        assertThat(cronogramaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCronograma() throws Exception {
        // Initialize the database
        cronogramaRepository.saveAndFlush(cronograma);

        int databaseSizeBeforeDelete = cronogramaRepository.findAll().size();

        // Delete the cronograma
        restCronogramaMockMvc.perform(delete("/api/cronogramas/{id}", cronograma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cronograma> cronogramaList = cronogramaRepository.findAll();
        assertThat(cronogramaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cronograma.class);
        Cronograma cronograma1 = new Cronograma();
        cronograma1.setId(1L);
        Cronograma cronograma2 = new Cronograma();
        cronograma2.setId(cronograma1.getId());
        assertThat(cronograma1).isEqualTo(cronograma2);
        cronograma2.setId(2L);
        assertThat(cronograma1).isNotEqualTo(cronograma2);
        cronograma1.setId(null);
        assertThat(cronograma1).isNotEqualTo(cronograma2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CronogramaDTO.class);
        CronogramaDTO cronogramaDTO1 = new CronogramaDTO();
        cronogramaDTO1.setId(1L);
        CronogramaDTO cronogramaDTO2 = new CronogramaDTO();
        assertThat(cronogramaDTO1).isNotEqualTo(cronogramaDTO2);
        cronogramaDTO2.setId(cronogramaDTO1.getId());
        assertThat(cronogramaDTO1).isEqualTo(cronogramaDTO2);
        cronogramaDTO2.setId(2L);
        assertThat(cronogramaDTO1).isNotEqualTo(cronogramaDTO2);
        cronogramaDTO1.setId(null);
        assertThat(cronogramaDTO1).isNotEqualTo(cronogramaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cronogramaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cronogramaMapper.fromId(null)).isNull();
    }
}
