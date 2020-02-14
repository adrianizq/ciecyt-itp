package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.ProyectoRespuestas;
import co.edu.itp.ciecyt.repository.ProyectoRespuestasRepository;
import co.edu.itp.ciecyt.service.ProyectoRespuestasService;
import co.edu.itp.ciecyt.service.dto.ProyectoRespuestasDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoRespuestasMapper;
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

import co.edu.itp.ciecyt.domain.enumeration.EnumRespuestas;
/**
 * Integration tests for the {@link ProyectoRespuestasResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class ProyectoRespuestasResourceIT {

    private static final EnumRespuestas DEFAULT_RESPUESTA = EnumRespuestas.CUMPLE;
    private static final EnumRespuestas UPDATED_RESPUESTA = EnumRespuestas.NO_CUMPLE;

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VIABLE = false;
    private static final Boolean UPDATED_VIABLE = true;

    @Autowired
    private ProyectoRespuestasRepository proyectoRespuestasRepository;

    @Autowired
    private ProyectoRespuestasMapper proyectoRespuestasMapper;

    @Autowired
    private ProyectoRespuestasService proyectoRespuestasService;

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

    private MockMvc restProyectoRespuestasMockMvc;

    private ProyectoRespuestas proyectoRespuestas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProyectoRespuestasResource proyectoRespuestasResource = new ProyectoRespuestasResource(proyectoRespuestasService);
        this.restProyectoRespuestasMockMvc = MockMvcBuilders.standaloneSetup(proyectoRespuestasResource)
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
    public static ProyectoRespuestas createEntity(EntityManager em) {
        ProyectoRespuestas proyectoRespuestas = new ProyectoRespuestas()
            .respuesta(DEFAULT_RESPUESTA)
            .observaciones(DEFAULT_OBSERVACIONES)
            .viable(DEFAULT_VIABLE);
        return proyectoRespuestas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProyectoRespuestas createUpdatedEntity(EntityManager em) {
        ProyectoRespuestas proyectoRespuestas = new ProyectoRespuestas()
            .respuesta(UPDATED_RESPUESTA)
            .observaciones(UPDATED_OBSERVACIONES)
            .viable(UPDATED_VIABLE);
        return proyectoRespuestas;
    }

    @BeforeEach
    public void initTest() {
        proyectoRespuestas = createEntity(em);
    }

    @Test
    @Transactional
    public void createProyectoRespuestas() throws Exception {
        int databaseSizeBeforeCreate = proyectoRespuestasRepository.findAll().size();

        // Create the ProyectoRespuestas
        ProyectoRespuestasDTO proyectoRespuestasDTO = proyectoRespuestasMapper.toDto(proyectoRespuestas);
        restProyectoRespuestasMockMvc.perform(post("/api/proyecto-respuestas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoRespuestasDTO)))
            .andExpect(status().isCreated());

        // Validate the ProyectoRespuestas in the database
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeCreate + 1);
        ProyectoRespuestas testProyectoRespuestas = proyectoRespuestasList.get(proyectoRespuestasList.size() - 1);
        assertThat(testProyectoRespuestas.getRespuesta()).isEqualTo(DEFAULT_RESPUESTA);
        assertThat(testProyectoRespuestas.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testProyectoRespuestas.isViable()).isEqualTo(DEFAULT_VIABLE);
    }

    @Test
    @Transactional
    public void createProyectoRespuestasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proyectoRespuestasRepository.findAll().size();

        // Create the ProyectoRespuestas with an existing ID
        proyectoRespuestas.setId(1L);
        ProyectoRespuestasDTO proyectoRespuestasDTO = proyectoRespuestasMapper.toDto(proyectoRespuestas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProyectoRespuestasMockMvc.perform(post("/api/proyecto-respuestas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoRespuestasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProyectoRespuestas in the database
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProyectoRespuestas() throws Exception {
        // Initialize the database
        proyectoRespuestasRepository.saveAndFlush(proyectoRespuestas);

        // Get all the proyectoRespuestasList
        restProyectoRespuestasMockMvc.perform(get("/api/proyecto-respuestas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proyectoRespuestas.getId().intValue())))
            .andExpect(jsonPath("$.[*].respuesta").value(hasItem(DEFAULT_RESPUESTA.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].viable").value(hasItem(DEFAULT_VIABLE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProyectoRespuestas() throws Exception {
        // Initialize the database
        proyectoRespuestasRepository.saveAndFlush(proyectoRespuestas);

        // Get the proyectoRespuestas
        restProyectoRespuestasMockMvc.perform(get("/api/proyecto-respuestas/{id}", proyectoRespuestas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proyectoRespuestas.getId().intValue()))
            .andExpect(jsonPath("$.respuesta").value(DEFAULT_RESPUESTA.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES))
            .andExpect(jsonPath("$.viable").value(DEFAULT_VIABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProyectoRespuestas() throws Exception {
        // Get the proyectoRespuestas
        restProyectoRespuestasMockMvc.perform(get("/api/proyecto-respuestas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProyectoRespuestas() throws Exception {
        // Initialize the database
        proyectoRespuestasRepository.saveAndFlush(proyectoRespuestas);

        int databaseSizeBeforeUpdate = proyectoRespuestasRepository.findAll().size();

        // Update the proyectoRespuestas
        ProyectoRespuestas updatedProyectoRespuestas = proyectoRespuestasRepository.findById(proyectoRespuestas.getId()).get();
        // Disconnect from session so that the updates on updatedProyectoRespuestas are not directly saved in db
        em.detach(updatedProyectoRespuestas);
        updatedProyectoRespuestas
            .respuesta(UPDATED_RESPUESTA)
            .observaciones(UPDATED_OBSERVACIONES)
            .viable(UPDATED_VIABLE);
        ProyectoRespuestasDTO proyectoRespuestasDTO = proyectoRespuestasMapper.toDto(updatedProyectoRespuestas);

        restProyectoRespuestasMockMvc.perform(put("/api/proyecto-respuestas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoRespuestasDTO)))
            .andExpect(status().isOk());

        // Validate the ProyectoRespuestas in the database
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeUpdate);
        ProyectoRespuestas testProyectoRespuestas = proyectoRespuestasList.get(proyectoRespuestasList.size() - 1);
        assertThat(testProyectoRespuestas.getRespuesta()).isEqualTo(UPDATED_RESPUESTA);
        assertThat(testProyectoRespuestas.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testProyectoRespuestas.isViable()).isEqualTo(UPDATED_VIABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingProyectoRespuestas() throws Exception {
        int databaseSizeBeforeUpdate = proyectoRespuestasRepository.findAll().size();

        // Create the ProyectoRespuestas
        ProyectoRespuestasDTO proyectoRespuestasDTO = proyectoRespuestasMapper.toDto(proyectoRespuestas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProyectoRespuestasMockMvc.perform(put("/api/proyecto-respuestas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proyectoRespuestasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProyectoRespuestas in the database
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProyectoRespuestas() throws Exception {
        // Initialize the database
        proyectoRespuestasRepository.saveAndFlush(proyectoRespuestas);

        int databaseSizeBeforeDelete = proyectoRespuestasRepository.findAll().size();

        // Delete the proyectoRespuestas
        restProyectoRespuestasMockMvc.perform(delete("/api/proyecto-respuestas/{id}", proyectoRespuestas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProyectoRespuestas.class);
        ProyectoRespuestas proyectoRespuestas1 = new ProyectoRespuestas();
        proyectoRespuestas1.setId(1L);
        ProyectoRespuestas proyectoRespuestas2 = new ProyectoRespuestas();
        proyectoRespuestas2.setId(proyectoRespuestas1.getId());
        assertThat(proyectoRespuestas1).isEqualTo(proyectoRespuestas2);
        proyectoRespuestas2.setId(2L);
        assertThat(proyectoRespuestas1).isNotEqualTo(proyectoRespuestas2);
        proyectoRespuestas1.setId(null);
        assertThat(proyectoRespuestas1).isNotEqualTo(proyectoRespuestas2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProyectoRespuestasDTO.class);
        ProyectoRespuestasDTO proyectoRespuestasDTO1 = new ProyectoRespuestasDTO();
        proyectoRespuestasDTO1.setId(1L);
        ProyectoRespuestasDTO proyectoRespuestasDTO2 = new ProyectoRespuestasDTO();
        assertThat(proyectoRespuestasDTO1).isNotEqualTo(proyectoRespuestasDTO2);
        proyectoRespuestasDTO2.setId(proyectoRespuestasDTO1.getId());
        assertThat(proyectoRespuestasDTO1).isEqualTo(proyectoRespuestasDTO2);
        proyectoRespuestasDTO2.setId(2L);
        assertThat(proyectoRespuestasDTO1).isNotEqualTo(proyectoRespuestasDTO2);
        proyectoRespuestasDTO1.setId(null);
        assertThat(proyectoRespuestasDTO1).isNotEqualTo(proyectoRespuestasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(proyectoRespuestasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(proyectoRespuestasMapper.fromId(null)).isNull();
    }
}
