package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Elemento;
import co.edu.itp.ciecyt.repository.ElementoRepository;
import co.edu.itp.ciecyt.service.ElementoService;
import co.edu.itp.ciecyt.service.dto.ElementoDTO;
import co.edu.itp.ciecyt.service.mapper.ElementoMapper;
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
 * Integration tests for the {@link ElementoResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class ElementoResourceIT {

    private static final String DEFAULT_ELEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ELEMENTO = "BBBBBBBBBB";

    @Autowired
    private ElementoRepository elementoRepository;

    @Autowired
    private ElementoMapper elementoMapper;

    @Autowired
    private ElementoService elementoService;

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

    private MockMvc restElementoMockMvc;

    private Elemento elemento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ElementoResource elementoResource = new ElementoResource(elementoService);
        this.restElementoMockMvc = MockMvcBuilders.standaloneSetup(elementoResource)
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
    public static Elemento createEntity(EntityManager em) {
        Elemento elemento = new Elemento()
            .elemento(DEFAULT_ELEMENTO);
        return elemento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Elemento createUpdatedEntity(EntityManager em) {
        Elemento elemento = new Elemento()
            .elemento(UPDATED_ELEMENTO);
        return elemento;
    }

    @BeforeEach
    public void initTest() {
        elemento = createEntity(em);
    }

    @Test
    @Transactional
    public void createElemento() throws Exception {
        int databaseSizeBeforeCreate = elementoRepository.findAll().size();

        // Create the Elemento
        ElementoDTO elementoDTO = elementoMapper.toDto(elemento);
        restElementoMockMvc.perform(post("/api/elementos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoDTO)))
            .andExpect(status().isCreated());

        // Validate the Elemento in the database
        List<Elemento> elementoList = elementoRepository.findAll();
        assertThat(elementoList).hasSize(databaseSizeBeforeCreate + 1);
        Elemento testElemento = elementoList.get(elementoList.size() - 1);
        assertThat(testElemento.getElemento()).isEqualTo(DEFAULT_ELEMENTO);
    }

    @Test
    @Transactional
    public void createElementoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elementoRepository.findAll().size();

        // Create the Elemento with an existing ID
        elemento.setId(1L);
        ElementoDTO elementoDTO = elementoMapper.toDto(elemento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElementoMockMvc.perform(post("/api/elementos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Elemento in the database
        List<Elemento> elementoList = elementoRepository.findAll();
        assertThat(elementoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElementos() throws Exception {
        // Initialize the database
        elementoRepository.saveAndFlush(elemento);

        // Get all the elementoList
        restElementoMockMvc.perform(get("/api/elementos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elemento.getId().intValue())))
            .andExpect(jsonPath("$.[*].elemento").value(hasItem(DEFAULT_ELEMENTO)));
    }
    
    @Test
    @Transactional
    public void getElemento() throws Exception {
        // Initialize the database
        elementoRepository.saveAndFlush(elemento);

        // Get the elemento
        restElementoMockMvc.perform(get("/api/elementos/{id}", elemento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(elemento.getId().intValue()))
            .andExpect(jsonPath("$.elemento").value(DEFAULT_ELEMENTO));
    }

    @Test
    @Transactional
    public void getNonExistingElemento() throws Exception {
        // Get the elemento
        restElementoMockMvc.perform(get("/api/elementos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElemento() throws Exception {
        // Initialize the database
        elementoRepository.saveAndFlush(elemento);

        int databaseSizeBeforeUpdate = elementoRepository.findAll().size();

        // Update the elemento
        Elemento updatedElemento = elementoRepository.findById(elemento.getId()).get();
        // Disconnect from session so that the updates on updatedElemento are not directly saved in db
        em.detach(updatedElemento);
        updatedElemento
            .elemento(UPDATED_ELEMENTO);
        ElementoDTO elementoDTO = elementoMapper.toDto(updatedElemento);

        restElementoMockMvc.perform(put("/api/elementos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoDTO)))
            .andExpect(status().isOk());

        // Validate the Elemento in the database
        List<Elemento> elementoList = elementoRepository.findAll();
        assertThat(elementoList).hasSize(databaseSizeBeforeUpdate);
        Elemento testElemento = elementoList.get(elementoList.size() - 1);
        assertThat(testElemento.getElemento()).isEqualTo(UPDATED_ELEMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingElemento() throws Exception {
        int databaseSizeBeforeUpdate = elementoRepository.findAll().size();

        // Create the Elemento
        ElementoDTO elementoDTO = elementoMapper.toDto(elemento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElementoMockMvc.perform(put("/api/elementos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(elementoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Elemento in the database
        List<Elemento> elementoList = elementoRepository.findAll();
        assertThat(elementoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElemento() throws Exception {
        // Initialize the database
        elementoRepository.saveAndFlush(elemento);

        int databaseSizeBeforeDelete = elementoRepository.findAll().size();

        // Delete the elemento
        restElementoMockMvc.perform(delete("/api/elementos/{id}", elemento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Elemento> elementoList = elementoRepository.findAll();
        assertThat(elementoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Elemento.class);
        Elemento elemento1 = new Elemento();
        elemento1.setId(1L);
        Elemento elemento2 = new Elemento();
        elemento2.setId(elemento1.getId());
        assertThat(elemento1).isEqualTo(elemento2);
        elemento2.setId(2L);
        assertThat(elemento1).isNotEqualTo(elemento2);
        elemento1.setId(null);
        assertThat(elemento1).isNotEqualTo(elemento2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElementoDTO.class);
        ElementoDTO elementoDTO1 = new ElementoDTO();
        elementoDTO1.setId(1L);
        ElementoDTO elementoDTO2 = new ElementoDTO();
        assertThat(elementoDTO1).isNotEqualTo(elementoDTO2);
        elementoDTO2.setId(elementoDTO1.getId());
        assertThat(elementoDTO1).isEqualTo(elementoDTO2);
        elementoDTO2.setId(2L);
        assertThat(elementoDTO1).isNotEqualTo(elementoDTO2);
        elementoDTO1.setId(null);
        assertThat(elementoDTO1).isNotEqualTo(elementoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(elementoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(elementoMapper.fromId(null)).isNull();
    }
}
