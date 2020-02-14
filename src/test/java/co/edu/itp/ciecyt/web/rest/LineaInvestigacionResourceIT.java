package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.LineaInvestigacion;
import co.edu.itp.ciecyt.repository.LineaInvestigacionRepository;
import co.edu.itp.ciecyt.service.LineaInvestigacionService;
import co.edu.itp.ciecyt.service.dto.LineaInvestigacionDTO;
import co.edu.itp.ciecyt.service.mapper.LineaInvestigacionMapper;
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
 * Integration tests for the {@link LineaInvestigacionResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class LineaInvestigacionResourceIT {

    private static final String DEFAULT_LINEA = "AAAAAAAAAA";
    private static final String UPDATED_LINEA = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_LINEA = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_LINEA = "BBBBBBBBBB";

    @Autowired
    private LineaInvestigacionRepository lineaInvestigacionRepository;

    @Autowired
    private LineaInvestigacionMapper lineaInvestigacionMapper;

    @Autowired
    private LineaInvestigacionService lineaInvestigacionService;

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

    private MockMvc restLineaInvestigacionMockMvc;

    private LineaInvestigacion lineaInvestigacion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LineaInvestigacionResource lineaInvestigacionResource = new LineaInvestigacionResource(lineaInvestigacionService);
        this.restLineaInvestigacionMockMvc = MockMvcBuilders.standaloneSetup(lineaInvestigacionResource)
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
    public static LineaInvestigacion createEntity(EntityManager em) {
        LineaInvestigacion lineaInvestigacion = new LineaInvestigacion()
            .linea(DEFAULT_LINEA)
            .codigoLinea(DEFAULT_CODIGO_LINEA);
        return lineaInvestigacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineaInvestigacion createUpdatedEntity(EntityManager em) {
        LineaInvestigacion lineaInvestigacion = new LineaInvestigacion()
            .linea(UPDATED_LINEA)
            .codigoLinea(UPDATED_CODIGO_LINEA);
        return lineaInvestigacion;
    }

    @BeforeEach
    public void initTest() {
        lineaInvestigacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createLineaInvestigacion() throws Exception {
        int databaseSizeBeforeCreate = lineaInvestigacionRepository.findAll().size();

        // Create the LineaInvestigacion
        LineaInvestigacionDTO lineaInvestigacionDTO = lineaInvestigacionMapper.toDto(lineaInvestigacion);
        restLineaInvestigacionMockMvc.perform(post("/api/linea-investigacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineaInvestigacionDTO)))
            .andExpect(status().isCreated());

        // Validate the LineaInvestigacion in the database
        List<LineaInvestigacion> lineaInvestigacionList = lineaInvestigacionRepository.findAll();
        assertThat(lineaInvestigacionList).hasSize(databaseSizeBeforeCreate + 1);
        LineaInvestigacion testLineaInvestigacion = lineaInvestigacionList.get(lineaInvestigacionList.size() - 1);
        assertThat(testLineaInvestigacion.getLinea()).isEqualTo(DEFAULT_LINEA);
        assertThat(testLineaInvestigacion.getCodigoLinea()).isEqualTo(DEFAULT_CODIGO_LINEA);
    }

    @Test
    @Transactional
    public void createLineaInvestigacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lineaInvestigacionRepository.findAll().size();

        // Create the LineaInvestigacion with an existing ID
        lineaInvestigacion.setId(1L);
        LineaInvestigacionDTO lineaInvestigacionDTO = lineaInvestigacionMapper.toDto(lineaInvestigacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLineaInvestigacionMockMvc.perform(post("/api/linea-investigacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineaInvestigacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LineaInvestigacion in the database
        List<LineaInvestigacion> lineaInvestigacionList = lineaInvestigacionRepository.findAll();
        assertThat(lineaInvestigacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLineaInvestigacions() throws Exception {
        // Initialize the database
        lineaInvestigacionRepository.saveAndFlush(lineaInvestigacion);

        // Get all the lineaInvestigacionList
        restLineaInvestigacionMockMvc.perform(get("/api/linea-investigacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lineaInvestigacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].linea").value(hasItem(DEFAULT_LINEA)))
            .andExpect(jsonPath("$.[*].codigoLinea").value(hasItem(DEFAULT_CODIGO_LINEA)));
    }
    
    @Test
    @Transactional
    public void getLineaInvestigacion() throws Exception {
        // Initialize the database
        lineaInvestigacionRepository.saveAndFlush(lineaInvestigacion);

        // Get the lineaInvestigacion
        restLineaInvestigacionMockMvc.perform(get("/api/linea-investigacions/{id}", lineaInvestigacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lineaInvestigacion.getId().intValue()))
            .andExpect(jsonPath("$.linea").value(DEFAULT_LINEA))
            .andExpect(jsonPath("$.codigoLinea").value(DEFAULT_CODIGO_LINEA));
    }

    @Test
    @Transactional
    public void getNonExistingLineaInvestigacion() throws Exception {
        // Get the lineaInvestigacion
        restLineaInvestigacionMockMvc.perform(get("/api/linea-investigacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLineaInvestigacion() throws Exception {
        // Initialize the database
        lineaInvestigacionRepository.saveAndFlush(lineaInvestigacion);

        int databaseSizeBeforeUpdate = lineaInvestigacionRepository.findAll().size();

        // Update the lineaInvestigacion
        LineaInvestigacion updatedLineaInvestigacion = lineaInvestigacionRepository.findById(lineaInvestigacion.getId()).get();
        // Disconnect from session so that the updates on updatedLineaInvestigacion are not directly saved in db
        em.detach(updatedLineaInvestigacion);
        updatedLineaInvestigacion
            .linea(UPDATED_LINEA)
            .codigoLinea(UPDATED_CODIGO_LINEA);
        LineaInvestigacionDTO lineaInvestigacionDTO = lineaInvestigacionMapper.toDto(updatedLineaInvestigacion);

        restLineaInvestigacionMockMvc.perform(put("/api/linea-investigacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineaInvestigacionDTO)))
            .andExpect(status().isOk());

        // Validate the LineaInvestigacion in the database
        List<LineaInvestigacion> lineaInvestigacionList = lineaInvestigacionRepository.findAll();
        assertThat(lineaInvestigacionList).hasSize(databaseSizeBeforeUpdate);
        LineaInvestigacion testLineaInvestigacion = lineaInvestigacionList.get(lineaInvestigacionList.size() - 1);
        assertThat(testLineaInvestigacion.getLinea()).isEqualTo(UPDATED_LINEA);
        assertThat(testLineaInvestigacion.getCodigoLinea()).isEqualTo(UPDATED_CODIGO_LINEA);
    }

    @Test
    @Transactional
    public void updateNonExistingLineaInvestigacion() throws Exception {
        int databaseSizeBeforeUpdate = lineaInvestigacionRepository.findAll().size();

        // Create the LineaInvestigacion
        LineaInvestigacionDTO lineaInvestigacionDTO = lineaInvestigacionMapper.toDto(lineaInvestigacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLineaInvestigacionMockMvc.perform(put("/api/linea-investigacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lineaInvestigacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LineaInvestigacion in the database
        List<LineaInvestigacion> lineaInvestigacionList = lineaInvestigacionRepository.findAll();
        assertThat(lineaInvestigacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLineaInvestigacion() throws Exception {
        // Initialize the database
        lineaInvestigacionRepository.saveAndFlush(lineaInvestigacion);

        int databaseSizeBeforeDelete = lineaInvestigacionRepository.findAll().size();

        // Delete the lineaInvestigacion
        restLineaInvestigacionMockMvc.perform(delete("/api/linea-investigacions/{id}", lineaInvestigacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LineaInvestigacion> lineaInvestigacionList = lineaInvestigacionRepository.findAll();
        assertThat(lineaInvestigacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineaInvestigacion.class);
        LineaInvestigacion lineaInvestigacion1 = new LineaInvestigacion();
        lineaInvestigacion1.setId(1L);
        LineaInvestigacion lineaInvestigacion2 = new LineaInvestigacion();
        lineaInvestigacion2.setId(lineaInvestigacion1.getId());
        assertThat(lineaInvestigacion1).isEqualTo(lineaInvestigacion2);
        lineaInvestigacion2.setId(2L);
        assertThat(lineaInvestigacion1).isNotEqualTo(lineaInvestigacion2);
        lineaInvestigacion1.setId(null);
        assertThat(lineaInvestigacion1).isNotEqualTo(lineaInvestigacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineaInvestigacionDTO.class);
        LineaInvestigacionDTO lineaInvestigacionDTO1 = new LineaInvestigacionDTO();
        lineaInvestigacionDTO1.setId(1L);
        LineaInvestigacionDTO lineaInvestigacionDTO2 = new LineaInvestigacionDTO();
        assertThat(lineaInvestigacionDTO1).isNotEqualTo(lineaInvestigacionDTO2);
        lineaInvestigacionDTO2.setId(lineaInvestigacionDTO1.getId());
        assertThat(lineaInvestigacionDTO1).isEqualTo(lineaInvestigacionDTO2);
        lineaInvestigacionDTO2.setId(2L);
        assertThat(lineaInvestigacionDTO1).isNotEqualTo(lineaInvestigacionDTO2);
        lineaInvestigacionDTO1.setId(null);
        assertThat(lineaInvestigacionDTO1).isNotEqualTo(lineaInvestigacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(lineaInvestigacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(lineaInvestigacionMapper.fromId(null)).isNull();
    }
}
