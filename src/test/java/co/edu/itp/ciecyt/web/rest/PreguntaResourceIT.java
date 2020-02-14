package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Pregunta;
import co.edu.itp.ciecyt.repository.PreguntaRepository;
import co.edu.itp.ciecyt.service.PreguntaService;
import co.edu.itp.ciecyt.service.dto.PreguntaDTO;
import co.edu.itp.ciecyt.service.mapper.PreguntaMapper;
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
 * Integration tests for the {@link PreguntaResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class PreguntaResourceIT {

    private static final String DEFAULT_ENCABEZADO = "AAAAAAAAAA";
    private static final String UPDATED_ENCABEZADO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_PREGUNTA = "AAAAAAAAAA";
    private static final String UPDATED_PREGUNTA = "BBBBBBBBBB";

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private PreguntaMapper preguntaMapper;

    @Autowired
    private PreguntaService preguntaService;

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

    private MockMvc restPreguntaMockMvc;

    private Pregunta pregunta;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PreguntaResource preguntaResource = new PreguntaResource(preguntaService);
        this.restPreguntaMockMvc = MockMvcBuilders.standaloneSetup(preguntaResource)
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
    public static Pregunta createEntity(EntityManager em) {
        Pregunta pregunta = new Pregunta()
            .encabezado(DEFAULT_ENCABEZADO)
            .descripcion(DEFAULT_DESCRIPCION)
            .pregunta(DEFAULT_PREGUNTA);
        return pregunta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pregunta createUpdatedEntity(EntityManager em) {
        Pregunta pregunta = new Pregunta()
            .encabezado(UPDATED_ENCABEZADO)
            .descripcion(UPDATED_DESCRIPCION)
            .pregunta(UPDATED_PREGUNTA);
        return pregunta;
    }

    @BeforeEach
    public void initTest() {
        pregunta = createEntity(em);
    }

    @Test
    @Transactional
    public void createPregunta() throws Exception {
        int databaseSizeBeforeCreate = preguntaRepository.findAll().size();

        // Create the Pregunta
        PreguntaDTO preguntaDTO = preguntaMapper.toDto(pregunta);
        restPreguntaMockMvc.perform(post("/api/preguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preguntaDTO)))
            .andExpect(status().isCreated());

        // Validate the Pregunta in the database
        List<Pregunta> preguntaList = preguntaRepository.findAll();
        assertThat(preguntaList).hasSize(databaseSizeBeforeCreate + 1);
        Pregunta testPregunta = preguntaList.get(preguntaList.size() - 1);
        assertThat(testPregunta.getEncabezado()).isEqualTo(DEFAULT_ENCABEZADO);
        assertThat(testPregunta.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testPregunta.getPregunta()).isEqualTo(DEFAULT_PREGUNTA);
    }

    @Test
    @Transactional
    public void createPreguntaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = preguntaRepository.findAll().size();

        // Create the Pregunta with an existing ID
        pregunta.setId(1L);
        PreguntaDTO preguntaDTO = preguntaMapper.toDto(pregunta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPreguntaMockMvc.perform(post("/api/preguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preguntaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pregunta in the database
        List<Pregunta> preguntaList = preguntaRepository.findAll();
        assertThat(preguntaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPreguntas() throws Exception {
        // Initialize the database
        preguntaRepository.saveAndFlush(pregunta);

        // Get all the preguntaList
        restPreguntaMockMvc.perform(get("/api/preguntas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pregunta.getId().intValue())))
            .andExpect(jsonPath("$.[*].encabezado").value(hasItem(DEFAULT_ENCABEZADO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].pregunta").value(hasItem(DEFAULT_PREGUNTA)));
    }
    
    @Test
    @Transactional
    public void getPregunta() throws Exception {
        // Initialize the database
        preguntaRepository.saveAndFlush(pregunta);

        // Get the pregunta
        restPreguntaMockMvc.perform(get("/api/preguntas/{id}", pregunta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pregunta.getId().intValue()))
            .andExpect(jsonPath("$.encabezado").value(DEFAULT_ENCABEZADO))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.pregunta").value(DEFAULT_PREGUNTA));
    }

    @Test
    @Transactional
    public void getNonExistingPregunta() throws Exception {
        // Get the pregunta
        restPreguntaMockMvc.perform(get("/api/preguntas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePregunta() throws Exception {
        // Initialize the database
        preguntaRepository.saveAndFlush(pregunta);

        int databaseSizeBeforeUpdate = preguntaRepository.findAll().size();

        // Update the pregunta
        Pregunta updatedPregunta = preguntaRepository.findById(pregunta.getId()).get();
        // Disconnect from session so that the updates on updatedPregunta are not directly saved in db
        em.detach(updatedPregunta);
        updatedPregunta
            .encabezado(UPDATED_ENCABEZADO)
            .descripcion(UPDATED_DESCRIPCION)
            .pregunta(UPDATED_PREGUNTA);
        PreguntaDTO preguntaDTO = preguntaMapper.toDto(updatedPregunta);

        restPreguntaMockMvc.perform(put("/api/preguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preguntaDTO)))
            .andExpect(status().isOk());

        // Validate the Pregunta in the database
        List<Pregunta> preguntaList = preguntaRepository.findAll();
        assertThat(preguntaList).hasSize(databaseSizeBeforeUpdate);
        Pregunta testPregunta = preguntaList.get(preguntaList.size() - 1);
        assertThat(testPregunta.getEncabezado()).isEqualTo(UPDATED_ENCABEZADO);
        assertThat(testPregunta.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testPregunta.getPregunta()).isEqualTo(UPDATED_PREGUNTA);
    }

    @Test
    @Transactional
    public void updateNonExistingPregunta() throws Exception {
        int databaseSizeBeforeUpdate = preguntaRepository.findAll().size();

        // Create the Pregunta
        PreguntaDTO preguntaDTO = preguntaMapper.toDto(pregunta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPreguntaMockMvc.perform(put("/api/preguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preguntaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pregunta in the database
        List<Pregunta> preguntaList = preguntaRepository.findAll();
        assertThat(preguntaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePregunta() throws Exception {
        // Initialize the database
        preguntaRepository.saveAndFlush(pregunta);

        int databaseSizeBeforeDelete = preguntaRepository.findAll().size();

        // Delete the pregunta
        restPreguntaMockMvc.perform(delete("/api/preguntas/{id}", pregunta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pregunta> preguntaList = preguntaRepository.findAll();
        assertThat(preguntaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pregunta.class);
        Pregunta pregunta1 = new Pregunta();
        pregunta1.setId(1L);
        Pregunta pregunta2 = new Pregunta();
        pregunta2.setId(pregunta1.getId());
        assertThat(pregunta1).isEqualTo(pregunta2);
        pregunta2.setId(2L);
        assertThat(pregunta1).isNotEqualTo(pregunta2);
        pregunta1.setId(null);
        assertThat(pregunta1).isNotEqualTo(pregunta2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PreguntaDTO.class);
        PreguntaDTO preguntaDTO1 = new PreguntaDTO();
        preguntaDTO1.setId(1L);
        PreguntaDTO preguntaDTO2 = new PreguntaDTO();
        assertThat(preguntaDTO1).isNotEqualTo(preguntaDTO2);
        preguntaDTO2.setId(preguntaDTO1.getId());
        assertThat(preguntaDTO1).isEqualTo(preguntaDTO2);
        preguntaDTO2.setId(2L);
        assertThat(preguntaDTO1).isNotEqualTo(preguntaDTO2);
        preguntaDTO1.setId(null);
        assertThat(preguntaDTO1).isNotEqualTo(preguntaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(preguntaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(preguntaMapper.fromId(null)).isNull();
    }
}
