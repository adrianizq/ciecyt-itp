package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.AdjuntoProyectoFase;
import co.edu.itp.ciecyt.repository.AdjuntoProyectoFaseRepository;
import co.edu.itp.ciecyt.service.AdjuntoProyectoFaseService;
import co.edu.itp.ciecyt.service.dto.AdjuntoProyectoFaseDTO;
import co.edu.itp.ciecyt.service.mapper.AdjuntoProyectoFaseMapper;
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
 * Integration tests for the {@link AdjuntoProyectoFaseResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class AdjuntoProyectoFaseResourceIT {

    private static final String DEFAULT_NOMBRE_ADJUNTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ADJUNTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ESTADO_ADJUNTO = 1;
    private static final Integer UPDATED_ESTADO_ADJUNTO = 2;

    private static final String DEFAULT_ADJUNTO_PROYECTO_FASE = "AAAAAAAAAA";
    private static final String UPDATED_ADJUNTO_PROYECTO_FASE = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_ARCHIVO_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ARCHIVO_ORIGINAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdjuntoProyectoFaseRepository adjuntoProyectoFaseRepository;

    @Autowired
    private AdjuntoProyectoFaseMapper adjuntoProyectoFaseMapper;

    @Autowired
    private AdjuntoProyectoFaseService adjuntoProyectoFaseService;

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

    private MockMvc restAdjuntoProyectoFaseMockMvc;

    private AdjuntoProyectoFase adjuntoProyectoFase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdjuntoProyectoFaseResource adjuntoProyectoFaseResource = new AdjuntoProyectoFaseResource(adjuntoProyectoFaseService);
        this.restAdjuntoProyectoFaseMockMvc = MockMvcBuilders.standaloneSetup(adjuntoProyectoFaseResource)
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
    public static AdjuntoProyectoFase createEntity(EntityManager em) {
        AdjuntoProyectoFase adjuntoProyectoFase = new AdjuntoProyectoFase()
            .nombreAdjunto(DEFAULT_NOMBRE_ADJUNTO)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .fechaModificacion(DEFAULT_FECHA_MODIFICACION)
            .estadoAdjunto(DEFAULT_ESTADO_ADJUNTO)
            .adjuntoProyectoFase(DEFAULT_ADJUNTO_PROYECTO_FASE)
            .nombreArchivoOriginal(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN);
        return adjuntoProyectoFase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjuntoProyectoFase createUpdatedEntity(EntityManager em) {
        AdjuntoProyectoFase adjuntoProyectoFase = new AdjuntoProyectoFase()
            .nombreAdjunto(UPDATED_NOMBRE_ADJUNTO)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .estadoAdjunto(UPDATED_ESTADO_ADJUNTO)
            .adjuntoProyectoFase(UPDATED_ADJUNTO_PROYECTO_FASE)
            .nombreArchivoOriginal(UPDATED_NOMBRE_ARCHIVO_ORIGINAL)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);
        return adjuntoProyectoFase;
    }

    @BeforeEach
    public void initTest() {
        adjuntoProyectoFase = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjuntoProyectoFase() throws Exception {
        int databaseSizeBeforeCreate = adjuntoProyectoFaseRepository.findAll().size();

        // Create the AdjuntoProyectoFase
        AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO = adjuntoProyectoFaseMapper.toDto(adjuntoProyectoFase);
        restAdjuntoProyectoFaseMockMvc.perform(post("/api/adjunto-proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoProyectoFaseDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjuntoProyectoFase in the database
        List<AdjuntoProyectoFase> adjuntoProyectoFaseList = adjuntoProyectoFaseRepository.findAll();
        assertThat(adjuntoProyectoFaseList).hasSize(databaseSizeBeforeCreate + 1);
        AdjuntoProyectoFase testAdjuntoProyectoFase = adjuntoProyectoFaseList.get(adjuntoProyectoFaseList.size() - 1);
        assertThat(testAdjuntoProyectoFase.getNombreAdjunto()).isEqualTo(DEFAULT_NOMBRE_ADJUNTO);
        assertThat(testAdjuntoProyectoFase.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testAdjuntoProyectoFase.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
        assertThat(testAdjuntoProyectoFase.getEstadoAdjunto()).isEqualTo(DEFAULT_ESTADO_ADJUNTO);
        assertThat(testAdjuntoProyectoFase.getAdjuntoProyectoFase()).isEqualTo(DEFAULT_ADJUNTO_PROYECTO_FASE);
        assertThat(testAdjuntoProyectoFase.getNombreArchivoOriginal()).isEqualTo(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL);
        assertThat(testAdjuntoProyectoFase.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testAdjuntoProyectoFase.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
    }

    @Test
    @Transactional
    public void createAdjuntoProyectoFaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjuntoProyectoFaseRepository.findAll().size();

        // Create the AdjuntoProyectoFase with an existing ID
        adjuntoProyectoFase.setId(1L);
        AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO = adjuntoProyectoFaseMapper.toDto(adjuntoProyectoFase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjuntoProyectoFaseMockMvc.perform(post("/api/adjunto-proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoProyectoFaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjuntoProyectoFase in the database
        List<AdjuntoProyectoFase> adjuntoProyectoFaseList = adjuntoProyectoFaseRepository.findAll();
        assertThat(adjuntoProyectoFaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjuntoProyectoFases() throws Exception {
        // Initialize the database
        adjuntoProyectoFaseRepository.saveAndFlush(adjuntoProyectoFase);

        // Get all the adjuntoProyectoFaseList
        restAdjuntoProyectoFaseMockMvc.perform(get("/api/adjunto-proyecto-fases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjuntoProyectoFase.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreAdjunto").value(hasItem(DEFAULT_NOMBRE_ADJUNTO)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].estadoAdjunto").value(hasItem(DEFAULT_ESTADO_ADJUNTO)))
            .andExpect(jsonPath("$.[*].adjuntoProyectoFase").value(hasItem(DEFAULT_ADJUNTO_PROYECTO_FASE)))
            .andExpect(jsonPath("$.[*].nombreArchivoOriginal").value(hasItem(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())));
    }
    
    @Test
    @Transactional
    public void getAdjuntoProyectoFase() throws Exception {
        // Initialize the database
        adjuntoProyectoFaseRepository.saveAndFlush(adjuntoProyectoFase);

        // Get the adjuntoProyectoFase
        restAdjuntoProyectoFaseMockMvc.perform(get("/api/adjunto-proyecto-fases/{id}", adjuntoProyectoFase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adjuntoProyectoFase.getId().intValue()))
            .andExpect(jsonPath("$.nombreAdjunto").value(DEFAULT_NOMBRE_ADJUNTO))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.estadoAdjunto").value(DEFAULT_ESTADO_ADJUNTO))
            .andExpect(jsonPath("$.adjuntoProyectoFase").value(DEFAULT_ADJUNTO_PROYECTO_FASE))
            .andExpect(jsonPath("$.nombreArchivoOriginal").value(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdjuntoProyectoFase() throws Exception {
        // Get the adjuntoProyectoFase
        restAdjuntoProyectoFaseMockMvc.perform(get("/api/adjunto-proyecto-fases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjuntoProyectoFase() throws Exception {
        // Initialize the database
        adjuntoProyectoFaseRepository.saveAndFlush(adjuntoProyectoFase);

        int databaseSizeBeforeUpdate = adjuntoProyectoFaseRepository.findAll().size();

        // Update the adjuntoProyectoFase
        AdjuntoProyectoFase updatedAdjuntoProyectoFase = adjuntoProyectoFaseRepository.findById(adjuntoProyectoFase.getId()).get();
        // Disconnect from session so that the updates on updatedAdjuntoProyectoFase are not directly saved in db
        em.detach(updatedAdjuntoProyectoFase);
        updatedAdjuntoProyectoFase
            .nombreAdjunto(UPDATED_NOMBRE_ADJUNTO)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .estadoAdjunto(UPDATED_ESTADO_ADJUNTO)
            .adjuntoProyectoFase(UPDATED_ADJUNTO_PROYECTO_FASE)
            .nombreArchivoOriginal(UPDATED_NOMBRE_ARCHIVO_ORIGINAL)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);
        AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO = adjuntoProyectoFaseMapper.toDto(updatedAdjuntoProyectoFase);

        restAdjuntoProyectoFaseMockMvc.perform(put("/api/adjunto-proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoProyectoFaseDTO)))
            .andExpect(status().isOk());

        // Validate the AdjuntoProyectoFase in the database
        List<AdjuntoProyectoFase> adjuntoProyectoFaseList = adjuntoProyectoFaseRepository.findAll();
        assertThat(adjuntoProyectoFaseList).hasSize(databaseSizeBeforeUpdate);
        AdjuntoProyectoFase testAdjuntoProyectoFase = adjuntoProyectoFaseList.get(adjuntoProyectoFaseList.size() - 1);
        assertThat(testAdjuntoProyectoFase.getNombreAdjunto()).isEqualTo(UPDATED_NOMBRE_ADJUNTO);
        assertThat(testAdjuntoProyectoFase.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testAdjuntoProyectoFase.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
        assertThat(testAdjuntoProyectoFase.getEstadoAdjunto()).isEqualTo(UPDATED_ESTADO_ADJUNTO);
        assertThat(testAdjuntoProyectoFase.getAdjuntoProyectoFase()).isEqualTo(UPDATED_ADJUNTO_PROYECTO_FASE);
        assertThat(testAdjuntoProyectoFase.getNombreArchivoOriginal()).isEqualTo(UPDATED_NOMBRE_ARCHIVO_ORIGINAL);
        assertThat(testAdjuntoProyectoFase.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testAdjuntoProyectoFase.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjuntoProyectoFase() throws Exception {
        int databaseSizeBeforeUpdate = adjuntoProyectoFaseRepository.findAll().size();

        // Create the AdjuntoProyectoFase
        AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO = adjuntoProyectoFaseMapper.toDto(adjuntoProyectoFase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjuntoProyectoFaseMockMvc.perform(put("/api/adjunto-proyecto-fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoProyectoFaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjuntoProyectoFase in the database
        List<AdjuntoProyectoFase> adjuntoProyectoFaseList = adjuntoProyectoFaseRepository.findAll();
        assertThat(adjuntoProyectoFaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjuntoProyectoFase() throws Exception {
        // Initialize the database
        adjuntoProyectoFaseRepository.saveAndFlush(adjuntoProyectoFase);

        int databaseSizeBeforeDelete = adjuntoProyectoFaseRepository.findAll().size();

        // Delete the adjuntoProyectoFase
        restAdjuntoProyectoFaseMockMvc.perform(delete("/api/adjunto-proyecto-fases/{id}", adjuntoProyectoFase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjuntoProyectoFase> adjuntoProyectoFaseList = adjuntoProyectoFaseRepository.findAll();
        assertThat(adjuntoProyectoFaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjuntoProyectoFase.class);
        AdjuntoProyectoFase adjuntoProyectoFase1 = new AdjuntoProyectoFase();
        adjuntoProyectoFase1.setId(1L);
        AdjuntoProyectoFase adjuntoProyectoFase2 = new AdjuntoProyectoFase();
        adjuntoProyectoFase2.setId(adjuntoProyectoFase1.getId());
        assertThat(adjuntoProyectoFase1).isEqualTo(adjuntoProyectoFase2);
        adjuntoProyectoFase2.setId(2L);
        assertThat(adjuntoProyectoFase1).isNotEqualTo(adjuntoProyectoFase2);
        adjuntoProyectoFase1.setId(null);
        assertThat(adjuntoProyectoFase1).isNotEqualTo(adjuntoProyectoFase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjuntoProyectoFaseDTO.class);
        AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO1 = new AdjuntoProyectoFaseDTO();
        adjuntoProyectoFaseDTO1.setId(1L);
        AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO2 = new AdjuntoProyectoFaseDTO();
        assertThat(adjuntoProyectoFaseDTO1).isNotEqualTo(adjuntoProyectoFaseDTO2);
        adjuntoProyectoFaseDTO2.setId(adjuntoProyectoFaseDTO1.getId());
        assertThat(adjuntoProyectoFaseDTO1).isEqualTo(adjuntoProyectoFaseDTO2);
        adjuntoProyectoFaseDTO2.setId(2L);
        assertThat(adjuntoProyectoFaseDTO1).isNotEqualTo(adjuntoProyectoFaseDTO2);
        adjuntoProyectoFaseDTO1.setId(null);
        assertThat(adjuntoProyectoFaseDTO1).isNotEqualTo(adjuntoProyectoFaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adjuntoProyectoFaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adjuntoProyectoFaseMapper.fromId(null)).isNull();
    }
}
