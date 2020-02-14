package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.FichaTecnica;
import co.edu.itp.ciecyt.repository.FichaTecnicaRepository;
import co.edu.itp.ciecyt.service.FichaTecnicaService;
import co.edu.itp.ciecyt.service.dto.FichaTecnicaDTO;
import co.edu.itp.ciecyt.service.mapper.FichaTecnicaMapper;
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
 * Integration tests for the {@link FichaTecnicaResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class FichaTecnicaResourceIT {

    private static final String DEFAULT_TITULO_PROFESIONAL = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_PROFESIONAL = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO_POSTGRADO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_POSTGRADO = "BBBBBBBBBB";

    private static final String DEFAULT_EXPERIENCIA = "AAAAAAAAAA";
    private static final String UPDATED_EXPERIENCIA = "BBBBBBBBBB";

    @Autowired
    private FichaTecnicaRepository fichaTecnicaRepository;

    @Autowired
    private FichaTecnicaMapper fichaTecnicaMapper;

    @Autowired
    private FichaTecnicaService fichaTecnicaService;

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

    private MockMvc restFichaTecnicaMockMvc;

    private FichaTecnica fichaTecnica;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FichaTecnicaResource fichaTecnicaResource = new FichaTecnicaResource(fichaTecnicaService);
        this.restFichaTecnicaMockMvc = MockMvcBuilders.standaloneSetup(fichaTecnicaResource)
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
    public static FichaTecnica createEntity(EntityManager em) {
        FichaTecnica fichaTecnica = new FichaTecnica()
            .tituloProfesional(DEFAULT_TITULO_PROFESIONAL)
            .tituloPostgrado(DEFAULT_TITULO_POSTGRADO)
            .experiencia(DEFAULT_EXPERIENCIA);
        return fichaTecnica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FichaTecnica createUpdatedEntity(EntityManager em) {
        FichaTecnica fichaTecnica = new FichaTecnica()
            .tituloProfesional(UPDATED_TITULO_PROFESIONAL)
            .tituloPostgrado(UPDATED_TITULO_POSTGRADO)
            .experiencia(UPDATED_EXPERIENCIA);
        return fichaTecnica;
    }

    @BeforeEach
    public void initTest() {
        fichaTecnica = createEntity(em);
    }

    @Test
    @Transactional
    public void createFichaTecnica() throws Exception {
        int databaseSizeBeforeCreate = fichaTecnicaRepository.findAll().size();

        // Create the FichaTecnica
        FichaTecnicaDTO fichaTecnicaDTO = fichaTecnicaMapper.toDto(fichaTecnica);
        restFichaTecnicaMockMvc.perform(post("/api/ficha-tecnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaTecnicaDTO)))
            .andExpect(status().isCreated());

        // Validate the FichaTecnica in the database
        List<FichaTecnica> fichaTecnicaList = fichaTecnicaRepository.findAll();
        assertThat(fichaTecnicaList).hasSize(databaseSizeBeforeCreate + 1);
        FichaTecnica testFichaTecnica = fichaTecnicaList.get(fichaTecnicaList.size() - 1);
        assertThat(testFichaTecnica.getTituloProfesional()).isEqualTo(DEFAULT_TITULO_PROFESIONAL);
        assertThat(testFichaTecnica.getTituloPostgrado()).isEqualTo(DEFAULT_TITULO_POSTGRADO);
        assertThat(testFichaTecnica.getExperiencia()).isEqualTo(DEFAULT_EXPERIENCIA);
    }

    @Test
    @Transactional
    public void createFichaTecnicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fichaTecnicaRepository.findAll().size();

        // Create the FichaTecnica with an existing ID
        fichaTecnica.setId(1L);
        FichaTecnicaDTO fichaTecnicaDTO = fichaTecnicaMapper.toDto(fichaTecnica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFichaTecnicaMockMvc.perform(post("/api/ficha-tecnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaTecnicaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichaTecnica in the database
        List<FichaTecnica> fichaTecnicaList = fichaTecnicaRepository.findAll();
        assertThat(fichaTecnicaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFichaTecnicas() throws Exception {
        // Initialize the database
        fichaTecnicaRepository.saveAndFlush(fichaTecnica);

        // Get all the fichaTecnicaList
        restFichaTecnicaMockMvc.perform(get("/api/ficha-tecnicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fichaTecnica.getId().intValue())))
            .andExpect(jsonPath("$.[*].tituloProfesional").value(hasItem(DEFAULT_TITULO_PROFESIONAL)))
            .andExpect(jsonPath("$.[*].tituloPostgrado").value(hasItem(DEFAULT_TITULO_POSTGRADO)))
            .andExpect(jsonPath("$.[*].experiencia").value(hasItem(DEFAULT_EXPERIENCIA)));
    }
    
    @Test
    @Transactional
    public void getFichaTecnica() throws Exception {
        // Initialize the database
        fichaTecnicaRepository.saveAndFlush(fichaTecnica);

        // Get the fichaTecnica
        restFichaTecnicaMockMvc.perform(get("/api/ficha-tecnicas/{id}", fichaTecnica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fichaTecnica.getId().intValue()))
            .andExpect(jsonPath("$.tituloProfesional").value(DEFAULT_TITULO_PROFESIONAL))
            .andExpect(jsonPath("$.tituloPostgrado").value(DEFAULT_TITULO_POSTGRADO))
            .andExpect(jsonPath("$.experiencia").value(DEFAULT_EXPERIENCIA));
    }

    @Test
    @Transactional
    public void getNonExistingFichaTecnica() throws Exception {
        // Get the fichaTecnica
        restFichaTecnicaMockMvc.perform(get("/api/ficha-tecnicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFichaTecnica() throws Exception {
        // Initialize the database
        fichaTecnicaRepository.saveAndFlush(fichaTecnica);

        int databaseSizeBeforeUpdate = fichaTecnicaRepository.findAll().size();

        // Update the fichaTecnica
        FichaTecnica updatedFichaTecnica = fichaTecnicaRepository.findById(fichaTecnica.getId()).get();
        // Disconnect from session so that the updates on updatedFichaTecnica are not directly saved in db
        em.detach(updatedFichaTecnica);
        updatedFichaTecnica
            .tituloProfesional(UPDATED_TITULO_PROFESIONAL)
            .tituloPostgrado(UPDATED_TITULO_POSTGRADO)
            .experiencia(UPDATED_EXPERIENCIA);
        FichaTecnicaDTO fichaTecnicaDTO = fichaTecnicaMapper.toDto(updatedFichaTecnica);

        restFichaTecnicaMockMvc.perform(put("/api/ficha-tecnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaTecnicaDTO)))
            .andExpect(status().isOk());

        // Validate the FichaTecnica in the database
        List<FichaTecnica> fichaTecnicaList = fichaTecnicaRepository.findAll();
        assertThat(fichaTecnicaList).hasSize(databaseSizeBeforeUpdate);
        FichaTecnica testFichaTecnica = fichaTecnicaList.get(fichaTecnicaList.size() - 1);
        assertThat(testFichaTecnica.getTituloProfesional()).isEqualTo(UPDATED_TITULO_PROFESIONAL);
        assertThat(testFichaTecnica.getTituloPostgrado()).isEqualTo(UPDATED_TITULO_POSTGRADO);
        assertThat(testFichaTecnica.getExperiencia()).isEqualTo(UPDATED_EXPERIENCIA);
    }

    @Test
    @Transactional
    public void updateNonExistingFichaTecnica() throws Exception {
        int databaseSizeBeforeUpdate = fichaTecnicaRepository.findAll().size();

        // Create the FichaTecnica
        FichaTecnicaDTO fichaTecnicaDTO = fichaTecnicaMapper.toDto(fichaTecnica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFichaTecnicaMockMvc.perform(put("/api/ficha-tecnicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaTecnicaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichaTecnica in the database
        List<FichaTecnica> fichaTecnicaList = fichaTecnicaRepository.findAll();
        assertThat(fichaTecnicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFichaTecnica() throws Exception {
        // Initialize the database
        fichaTecnicaRepository.saveAndFlush(fichaTecnica);

        int databaseSizeBeforeDelete = fichaTecnicaRepository.findAll().size();

        // Delete the fichaTecnica
        restFichaTecnicaMockMvc.perform(delete("/api/ficha-tecnicas/{id}", fichaTecnica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FichaTecnica> fichaTecnicaList = fichaTecnicaRepository.findAll();
        assertThat(fichaTecnicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichaTecnica.class);
        FichaTecnica fichaTecnica1 = new FichaTecnica();
        fichaTecnica1.setId(1L);
        FichaTecnica fichaTecnica2 = new FichaTecnica();
        fichaTecnica2.setId(fichaTecnica1.getId());
        assertThat(fichaTecnica1).isEqualTo(fichaTecnica2);
        fichaTecnica2.setId(2L);
        assertThat(fichaTecnica1).isNotEqualTo(fichaTecnica2);
        fichaTecnica1.setId(null);
        assertThat(fichaTecnica1).isNotEqualTo(fichaTecnica2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichaTecnicaDTO.class);
        FichaTecnicaDTO fichaTecnicaDTO1 = new FichaTecnicaDTO();
        fichaTecnicaDTO1.setId(1L);
        FichaTecnicaDTO fichaTecnicaDTO2 = new FichaTecnicaDTO();
        assertThat(fichaTecnicaDTO1).isNotEqualTo(fichaTecnicaDTO2);
        fichaTecnicaDTO2.setId(fichaTecnicaDTO1.getId());
        assertThat(fichaTecnicaDTO1).isEqualTo(fichaTecnicaDTO2);
        fichaTecnicaDTO2.setId(2L);
        assertThat(fichaTecnicaDTO1).isNotEqualTo(fichaTecnicaDTO2);
        fichaTecnicaDTO1.setId(null);
        assertThat(fichaTecnicaDTO1).isNotEqualTo(fichaTecnicaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fichaTecnicaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fichaTecnicaMapper.fromId(null)).isNull();
    }
}
