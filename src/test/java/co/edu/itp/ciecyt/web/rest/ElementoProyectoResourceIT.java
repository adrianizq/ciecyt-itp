package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.ElementoProyecto;
import co.edu.itp.ciecyt.repository.ElementoProyectoRepository;
import co.edu.itp.ciecyt.service.ElementoProyectoService;
import co.edu.itp.ciecyt.service.dto.ElementoProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.ElementoProyectoMapper;
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
 * Integration tests for the {@link ElementoProyectoResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class ElementoProyectoResourceIT {

    private static final String DEFAULT_DATO = "AAAAAAAAAA";
    private static final String UPDATED_DATO = "BBBBBBBBBB";

    @Autowired
    private ElementoProyectoRepository elementoProyectoRepository;

    @Autowired
    private ElementoProyectoMapper elementoProyectoMapper;

    @Autowired
    private ElementoProyectoService elementoProyectoService;

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

    private MockMvc restElementoProyectoMockMvc;

    private ElementoProyecto elementoProyecto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElementoProyectoResource elementoProyectoResource = new ElementoProyectoResource(elementoProyectoService);
        this.restElementoProyectoMockMvc = MockMvcBuilders.standaloneSetup(elementoProyectoResource)
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
    public static ElementoProyecto createEntity(EntityManager em) {
        ElementoProyecto elementoProyecto = new ElementoProyecto()
            .dato(DEFAULT_DATO);
        return elementoProyecto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElementoProyecto createUpdatedEntity(EntityManager em) {
        ElementoProyecto elementoProyecto = new ElementoProyecto()
            .dato(UPDATED_DATO);
        return elementoProyecto;
    }

    @BeforeEach
    public void initTest() {
        elementoProyecto = createEntity(em);
    }

    @Test
    @Transactional
    public void createElementoProyecto() throws Exception {
        int databaseSizeBeforeCreate = elementoProyectoRepository.findAll().size();

        // Create the ElementoProyecto
        ElementoProyectoDTO elementoProyectoDTO = elementoProyectoMapper.toDto(elementoProyecto);
        restElementoProyectoMockMvc.perform(post("/api/elemento-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoProyectoDTO)))
            .andExpect(status().isCreated());

        // Validate the ElementoProyecto in the database
        List<ElementoProyecto> elementoProyectoList = elementoProyectoRepository.findAll();
        assertThat(elementoProyectoList).hasSize(databaseSizeBeforeCreate + 1);
        ElementoProyecto testElementoProyecto = elementoProyectoList.get(elementoProyectoList.size() - 1);
        assertThat(testElementoProyecto.getDato()).isEqualTo(DEFAULT_DATO);
    }

    @Test
    @Transactional
    public void createElementoProyectoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elementoProyectoRepository.findAll().size();

        // Create the ElementoProyecto with an existing ID
        elementoProyecto.setId(1L);
        ElementoProyectoDTO elementoProyectoDTO = elementoProyectoMapper.toDto(elementoProyecto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElementoProyectoMockMvc.perform(post("/api/elemento-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoProyectoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElementoProyecto in the database
        List<ElementoProyecto> elementoProyectoList = elementoProyectoRepository.findAll();
        assertThat(elementoProyectoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElementoProyectos() throws Exception {
        // Initialize the database
        elementoProyectoRepository.saveAndFlush(elementoProyecto);

        // Get all the elementoProyectoList
        restElementoProyectoMockMvc.perform(get("/api/elemento-proyectos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elementoProyecto.getId().intValue())))
            .andExpect(jsonPath("$.[*].dato").value(hasItem(DEFAULT_DATO)));
    }
    
    @Test
    @Transactional
    public void getElementoProyecto() throws Exception {
        // Initialize the database
        elementoProyectoRepository.saveAndFlush(elementoProyecto);

        // Get the elementoProyecto
        restElementoProyectoMockMvc.perform(get("/api/elemento-proyectos/{id}", elementoProyecto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(elementoProyecto.getId().intValue()))
            .andExpect(jsonPath("$.dato").value(DEFAULT_DATO));
    }

    @Test
    @Transactional
    public void getNonExistingElementoProyecto() throws Exception {
        // Get the elementoProyecto
        restElementoProyectoMockMvc.perform(get("/api/elemento-proyectos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElementoProyecto() throws Exception {
        // Initialize the database
        elementoProyectoRepository.saveAndFlush(elementoProyecto);

        int databaseSizeBeforeUpdate = elementoProyectoRepository.findAll().size();

        // Update the elementoProyecto
        ElementoProyecto updatedElementoProyecto = elementoProyectoRepository.findById(elementoProyecto.getId()).get();
        // Disconnect from session so that the updates on updatedElementoProyecto are not directly saved in db
        em.detach(updatedElementoProyecto);
        updatedElementoProyecto
            .dato(UPDATED_DATO);
        ElementoProyectoDTO elementoProyectoDTO = elementoProyectoMapper.toDto(updatedElementoProyecto);

        restElementoProyectoMockMvc.perform(put("/api/elemento-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoProyectoDTO)))
            .andExpect(status().isOk());

        // Validate the ElementoProyecto in the database
        List<ElementoProyecto> elementoProyectoList = elementoProyectoRepository.findAll();
        assertThat(elementoProyectoList).hasSize(databaseSizeBeforeUpdate);
        ElementoProyecto testElementoProyecto = elementoProyectoList.get(elementoProyectoList.size() - 1);
        assertThat(testElementoProyecto.getDato()).isEqualTo(UPDATED_DATO);
    }

    @Test
    @Transactional
    public void updateNonExistingElementoProyecto() throws Exception {
        int databaseSizeBeforeUpdate = elementoProyectoRepository.findAll().size();

        // Create the ElementoProyecto
        ElementoProyectoDTO elementoProyectoDTO = elementoProyectoMapper.toDto(elementoProyecto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElementoProyectoMockMvc.perform(put("/api/elemento-proyectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoProyectoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElementoProyecto in the database
        List<ElementoProyecto> elementoProyectoList = elementoProyectoRepository.findAll();
        assertThat(elementoProyectoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElementoProyecto() throws Exception {
        // Initialize the database
        elementoProyectoRepository.saveAndFlush(elementoProyecto);

        int databaseSizeBeforeDelete = elementoProyectoRepository.findAll().size();

        // Delete the elementoProyecto
        restElementoProyectoMockMvc.perform(delete("/api/elemento-proyectos/{id}", elementoProyecto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElementoProyecto> elementoProyectoList = elementoProyectoRepository.findAll();
        assertThat(elementoProyectoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElementoProyecto.class);
        ElementoProyecto elementoProyecto1 = new ElementoProyecto();
        elementoProyecto1.setId(1L);
        ElementoProyecto elementoProyecto2 = new ElementoProyecto();
        elementoProyecto2.setId(elementoProyecto1.getId());
        assertThat(elementoProyecto1).isEqualTo(elementoProyecto2);
        elementoProyecto2.setId(2L);
        assertThat(elementoProyecto1).isNotEqualTo(elementoProyecto2);
        elementoProyecto1.setId(null);
        assertThat(elementoProyecto1).isNotEqualTo(elementoProyecto2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElementoProyectoDTO.class);
        ElementoProyectoDTO elementoProyectoDTO1 = new ElementoProyectoDTO();
        elementoProyectoDTO1.setId(1L);
        ElementoProyectoDTO elementoProyectoDTO2 = new ElementoProyectoDTO();
        assertThat(elementoProyectoDTO1).isNotEqualTo(elementoProyectoDTO2);
        elementoProyectoDTO2.setId(elementoProyectoDTO1.getId());
        assertThat(elementoProyectoDTO1).isEqualTo(elementoProyectoDTO2);
        elementoProyectoDTO2.setId(2L);
        assertThat(elementoProyectoDTO1).isNotEqualTo(elementoProyectoDTO2);
        elementoProyectoDTO1.setId(null);
        assertThat(elementoProyectoDTO1).isNotEqualTo(elementoProyectoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(elementoProyectoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(elementoProyectoMapper.fromId(null)).isNull();
    }
}
