package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Retroalimentacion;
import co.edu.itp.ciecyt.repository.RetroalimentacionRepository;
import co.edu.itp.ciecyt.service.RetroalimentacionService;
import co.edu.itp.ciecyt.service.dto.RetroalimentacionDTO;
import co.edu.itp.ciecyt.service.mapper.RetroalimentacionMapper;
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
 * Integration tests for the {@link RetroalimentacionResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class RetroalimentacionResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_RETROALIMENTACION = "AAAAAAAAAA";
    private static final String UPDATED_RETROALIMENTACION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_RETROALIMENTACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_RETROALIMENTACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ESTADO_RETROALIMENTACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ESTADO_RETROALIMENTACION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ESTADO_PROYECTO_FASE = 1;
    private static final Integer UPDATED_ESTADO_PROYECTO_FASE = 2;

    @Autowired
    private RetroalimentacionRepository retroalimentacionRepository;

    @Autowired
    private RetroalimentacionMapper retroalimentacionMapper;

    @Autowired
    private RetroalimentacionService retroalimentacionService;

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

    private MockMvc restRetroalimentacionMockMvc;

    private Retroalimentacion retroalimentacion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RetroalimentacionResource retroalimentacionResource = new RetroalimentacionResource(retroalimentacionService);
        this.restRetroalimentacionMockMvc = MockMvcBuilders.standaloneSetup(retroalimentacionResource)
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
    public static Retroalimentacion createEntity(EntityManager em) {
        Retroalimentacion retroalimentacion = new Retroalimentacion()
            .titulo(DEFAULT_TITULO)
            .retroalimentacion(DEFAULT_RETROALIMENTACION)
            .fechaRetroalimentacion(DEFAULT_FECHA_RETROALIMENTACION)
            .estadoRetroalimentacion(DEFAULT_ESTADO_RETROALIMENTACION)
            .estadoProyectoFase(DEFAULT_ESTADO_PROYECTO_FASE);
        return retroalimentacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Retroalimentacion createUpdatedEntity(EntityManager em) {
        Retroalimentacion retroalimentacion = new Retroalimentacion()
            .titulo(UPDATED_TITULO)
            .retroalimentacion(UPDATED_RETROALIMENTACION)
            .fechaRetroalimentacion(UPDATED_FECHA_RETROALIMENTACION)
            .estadoRetroalimentacion(UPDATED_ESTADO_RETROALIMENTACION)
            .estadoProyectoFase(UPDATED_ESTADO_PROYECTO_FASE);
        return retroalimentacion;
    }

    @BeforeEach
    public void initTest() {
        retroalimentacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createRetroalimentacion() throws Exception {
        int databaseSizeBeforeCreate = retroalimentacionRepository.findAll().size();

        // Create the Retroalimentacion
        RetroalimentacionDTO retroalimentacionDTO = retroalimentacionMapper.toDto(retroalimentacion);
        restRetroalimentacionMockMvc.perform(post("/api/retroalimentacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retroalimentacionDTO)))
            .andExpect(status().isCreated());

        // Validate the Retroalimentacion in the database
        List<Retroalimentacion> retroalimentacionList = retroalimentacionRepository.findAll();
        assertThat(retroalimentacionList).hasSize(databaseSizeBeforeCreate + 1);
        Retroalimentacion testRetroalimentacion = retroalimentacionList.get(retroalimentacionList.size() - 1);
        assertThat(testRetroalimentacion.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testRetroalimentacion.getRetroalimentacion()).isEqualTo(DEFAULT_RETROALIMENTACION);
        assertThat(testRetroalimentacion.getFechaRetroalimentacion()).isEqualTo(DEFAULT_FECHA_RETROALIMENTACION);
        assertThat(testRetroalimentacion.getEstadoRetroalimentacion()).isEqualTo(DEFAULT_ESTADO_RETROALIMENTACION);
        assertThat(testRetroalimentacion.getEstadoProyectoFase()).isEqualTo(DEFAULT_ESTADO_PROYECTO_FASE);
    }

    @Test
    @Transactional
    public void createRetroalimentacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = retroalimentacionRepository.findAll().size();

        // Create the Retroalimentacion with an existing ID
        retroalimentacion.setId(1L);
        RetroalimentacionDTO retroalimentacionDTO = retroalimentacionMapper.toDto(retroalimentacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRetroalimentacionMockMvc.perform(post("/api/retroalimentacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retroalimentacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Retroalimentacion in the database
        List<Retroalimentacion> retroalimentacionList = retroalimentacionRepository.findAll();
        assertThat(retroalimentacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRetroalimentacions() throws Exception {
        // Initialize the database
        retroalimentacionRepository.saveAndFlush(retroalimentacion);

        // Get all the retroalimentacionList
        restRetroalimentacionMockMvc.perform(get("/api/retroalimentacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(retroalimentacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].retroalimentacion").value(hasItem(DEFAULT_RETROALIMENTACION)))
            .andExpect(jsonPath("$.[*].fechaRetroalimentacion").value(hasItem(DEFAULT_FECHA_RETROALIMENTACION.toString())))
            .andExpect(jsonPath("$.[*].estadoRetroalimentacion").value(hasItem(DEFAULT_ESTADO_RETROALIMENTACION.toString())))
            .andExpect(jsonPath("$.[*].estadoProyectoFase").value(hasItem(DEFAULT_ESTADO_PROYECTO_FASE)));
    }
    
    @Test
    @Transactional
    public void getRetroalimentacion() throws Exception {
        // Initialize the database
        retroalimentacionRepository.saveAndFlush(retroalimentacion);

        // Get the retroalimentacion
        restRetroalimentacionMockMvc.perform(get("/api/retroalimentacions/{id}", retroalimentacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(retroalimentacion.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.retroalimentacion").value(DEFAULT_RETROALIMENTACION))
            .andExpect(jsonPath("$.fechaRetroalimentacion").value(DEFAULT_FECHA_RETROALIMENTACION.toString()))
            .andExpect(jsonPath("$.estadoRetroalimentacion").value(DEFAULT_ESTADO_RETROALIMENTACION.toString()))
            .andExpect(jsonPath("$.estadoProyectoFase").value(DEFAULT_ESTADO_PROYECTO_FASE));
    }

    @Test
    @Transactional
    public void getNonExistingRetroalimentacion() throws Exception {
        // Get the retroalimentacion
        restRetroalimentacionMockMvc.perform(get("/api/retroalimentacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRetroalimentacion() throws Exception {
        // Initialize the database
        retroalimentacionRepository.saveAndFlush(retroalimentacion);

        int databaseSizeBeforeUpdate = retroalimentacionRepository.findAll().size();

        // Update the retroalimentacion
        Retroalimentacion updatedRetroalimentacion = retroalimentacionRepository.findById(retroalimentacion.getId()).get();
        // Disconnect from session so that the updates on updatedRetroalimentacion are not directly saved in db
        em.detach(updatedRetroalimentacion);
        updatedRetroalimentacion
            .titulo(UPDATED_TITULO)
            .retroalimentacion(UPDATED_RETROALIMENTACION)
            .fechaRetroalimentacion(UPDATED_FECHA_RETROALIMENTACION)
            .estadoRetroalimentacion(UPDATED_ESTADO_RETROALIMENTACION)
            .estadoProyectoFase(UPDATED_ESTADO_PROYECTO_FASE);
        RetroalimentacionDTO retroalimentacionDTO = retroalimentacionMapper.toDto(updatedRetroalimentacion);

        restRetroalimentacionMockMvc.perform(put("/api/retroalimentacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retroalimentacionDTO)))
            .andExpect(status().isOk());

        // Validate the Retroalimentacion in the database
        List<Retroalimentacion> retroalimentacionList = retroalimentacionRepository.findAll();
        assertThat(retroalimentacionList).hasSize(databaseSizeBeforeUpdate);
        Retroalimentacion testRetroalimentacion = retroalimentacionList.get(retroalimentacionList.size() - 1);
        assertThat(testRetroalimentacion.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testRetroalimentacion.getRetroalimentacion()).isEqualTo(UPDATED_RETROALIMENTACION);
        assertThat(testRetroalimentacion.getFechaRetroalimentacion()).isEqualTo(UPDATED_FECHA_RETROALIMENTACION);
        assertThat(testRetroalimentacion.getEstadoRetroalimentacion()).isEqualTo(UPDATED_ESTADO_RETROALIMENTACION);
        assertThat(testRetroalimentacion.getEstadoProyectoFase()).isEqualTo(UPDATED_ESTADO_PROYECTO_FASE);
    }

    @Test
    @Transactional
    public void updateNonExistingRetroalimentacion() throws Exception {
        int databaseSizeBeforeUpdate = retroalimentacionRepository.findAll().size();

        // Create the Retroalimentacion
        RetroalimentacionDTO retroalimentacionDTO = retroalimentacionMapper.toDto(retroalimentacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRetroalimentacionMockMvc.perform(put("/api/retroalimentacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retroalimentacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Retroalimentacion in the database
        List<Retroalimentacion> retroalimentacionList = retroalimentacionRepository.findAll();
        assertThat(retroalimentacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRetroalimentacion() throws Exception {
        // Initialize the database
        retroalimentacionRepository.saveAndFlush(retroalimentacion);

        int databaseSizeBeforeDelete = retroalimentacionRepository.findAll().size();

        // Delete the retroalimentacion
        restRetroalimentacionMockMvc.perform(delete("/api/retroalimentacions/{id}", retroalimentacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Retroalimentacion> retroalimentacionList = retroalimentacionRepository.findAll();
        assertThat(retroalimentacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Retroalimentacion.class);
        Retroalimentacion retroalimentacion1 = new Retroalimentacion();
        retroalimentacion1.setId(1L);
        Retroalimentacion retroalimentacion2 = new Retroalimentacion();
        retroalimentacion2.setId(retroalimentacion1.getId());
        assertThat(retroalimentacion1).isEqualTo(retroalimentacion2);
        retroalimentacion2.setId(2L);
        assertThat(retroalimentacion1).isNotEqualTo(retroalimentacion2);
        retroalimentacion1.setId(null);
        assertThat(retroalimentacion1).isNotEqualTo(retroalimentacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetroalimentacionDTO.class);
        RetroalimentacionDTO retroalimentacionDTO1 = new RetroalimentacionDTO();
        retroalimentacionDTO1.setId(1L);
        RetroalimentacionDTO retroalimentacionDTO2 = new RetroalimentacionDTO();
        assertThat(retroalimentacionDTO1).isNotEqualTo(retroalimentacionDTO2);
        retroalimentacionDTO2.setId(retroalimentacionDTO1.getId());
        assertThat(retroalimentacionDTO1).isEqualTo(retroalimentacionDTO2);
        retroalimentacionDTO2.setId(2L);
        assertThat(retroalimentacionDTO1).isNotEqualTo(retroalimentacionDTO2);
        retroalimentacionDTO1.setId(null);
        assertThat(retroalimentacionDTO1).isNotEqualTo(retroalimentacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(retroalimentacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(retroalimentacionMapper.fromId(null)).isNull();
    }
}
