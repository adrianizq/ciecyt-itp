package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Formato;
import co.edu.itp.ciecyt.repository.FormatoRepository;
import co.edu.itp.ciecyt.service.FormatoService;
import co.edu.itp.ciecyt.service.dto.FormatoDTO;
import co.edu.itp.ciecyt.service.mapper.FormatoMapper;
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
 * Integration tests for the {@link FormatoResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class FormatoResourceIT {

    private static final String DEFAULT_FORMATO = "AAAAAAAAAA";
    private static final String UPDATED_FORMATO = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FormatoRepository formatoRepository;

    @Autowired
    private FormatoMapper formatoMapper;

    @Autowired
    private FormatoService formatoService;

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

    private MockMvc restFormatoMockMvc;

    private Formato formato;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormatoResource formatoResource = new FormatoResource(formatoService);
        this.restFormatoMockMvc = MockMvcBuilders.standaloneSetup(formatoResource)
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
    public static Formato createEntity(EntityManager em) {
        Formato formato = new Formato()
            .formato(DEFAULT_FORMATO)
            .version(DEFAULT_VERSION)
            .codigo(DEFAULT_CODIGO)
            .fecha(DEFAULT_FECHA);
        return formato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formato createUpdatedEntity(EntityManager em) {
        Formato formato = new Formato()
            .formato(UPDATED_FORMATO)
            .version(UPDATED_VERSION)
            .codigo(UPDATED_CODIGO)
            .fecha(UPDATED_FECHA);
        return formato;
    }

    @BeforeEach
    public void initTest() {
        formato = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormato() throws Exception {
        int databaseSizeBeforeCreate = formatoRepository.findAll().size();

        // Create the Formato
        FormatoDTO formatoDTO = formatoMapper.toDto(formato);
        restFormatoMockMvc.perform(post("/api/formatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formatoDTO)))
            .andExpect(status().isCreated());

        // Validate the Formato in the database
        List<Formato> formatoList = formatoRepository.findAll();
        assertThat(formatoList).hasSize(databaseSizeBeforeCreate + 1);
        Formato testFormato = formatoList.get(formatoList.size() - 1);
        assertThat(testFormato.getFormato()).isEqualTo(DEFAULT_FORMATO);
        assertThat(testFormato.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testFormato.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testFormato.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    public void createFormatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formatoRepository.findAll().size();

        // Create the Formato with an existing ID
        formato.setId(1L);
        FormatoDTO formatoDTO = formatoMapper.toDto(formato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormatoMockMvc.perform(post("/api/formatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Formato in the database
        List<Formato> formatoList = formatoRepository.findAll();
        assertThat(formatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormatoes() throws Exception {
        // Initialize the database
        formatoRepository.saveAndFlush(formato);

        // Get all the formatoList
        restFormatoMockMvc.perform(get("/api/formatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formato.getId().intValue())))
            .andExpect(jsonPath("$.[*].formato").value(hasItem(DEFAULT_FORMATO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }
    
    @Test
    @Transactional
    public void getFormato() throws Exception {
        // Initialize the database
        formatoRepository.saveAndFlush(formato);

        // Get the formato
        restFormatoMockMvc.perform(get("/api/formatoes/{id}", formato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formato.getId().intValue()))
            .andExpect(jsonPath("$.formato").value(DEFAULT_FORMATO))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFormato() throws Exception {
        // Get the formato
        restFormatoMockMvc.perform(get("/api/formatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormato() throws Exception {
        // Initialize the database
        formatoRepository.saveAndFlush(formato);

        int databaseSizeBeforeUpdate = formatoRepository.findAll().size();

        // Update the formato
        Formato updatedFormato = formatoRepository.findById(formato.getId()).get();
        // Disconnect from session so that the updates on updatedFormato are not directly saved in db
        em.detach(updatedFormato);
        updatedFormato
            .formato(UPDATED_FORMATO)
            .version(UPDATED_VERSION)
            .codigo(UPDATED_CODIGO)
            .fecha(UPDATED_FECHA);
        FormatoDTO formatoDTO = formatoMapper.toDto(updatedFormato);

        restFormatoMockMvc.perform(put("/api/formatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formatoDTO)))
            .andExpect(status().isOk());

        // Validate the Formato in the database
        List<Formato> formatoList = formatoRepository.findAll();
        assertThat(formatoList).hasSize(databaseSizeBeforeUpdate);
        Formato testFormato = formatoList.get(formatoList.size() - 1);
        assertThat(testFormato.getFormato()).isEqualTo(UPDATED_FORMATO);
        assertThat(testFormato.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testFormato.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testFormato.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void updateNonExistingFormato() throws Exception {
        int databaseSizeBeforeUpdate = formatoRepository.findAll().size();

        // Create the Formato
        FormatoDTO formatoDTO = formatoMapper.toDto(formato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormatoMockMvc.perform(put("/api/formatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Formato in the database
        List<Formato> formatoList = formatoRepository.findAll();
        assertThat(formatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormato() throws Exception {
        // Initialize the database
        formatoRepository.saveAndFlush(formato);

        int databaseSizeBeforeDelete = formatoRepository.findAll().size();

        // Delete the formato
        restFormatoMockMvc.perform(delete("/api/formatoes/{id}", formato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formato> formatoList = formatoRepository.findAll();
        assertThat(formatoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Formato.class);
        Formato formato1 = new Formato();
        formato1.setId(1L);
        Formato formato2 = new Formato();
        formato2.setId(formato1.getId());
        assertThat(formato1).isEqualTo(formato2);
        formato2.setId(2L);
        assertThat(formato1).isNotEqualTo(formato2);
        formato1.setId(null);
        assertThat(formato1).isNotEqualTo(formato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormatoDTO.class);
        FormatoDTO formatoDTO1 = new FormatoDTO();
        formatoDTO1.setId(1L);
        FormatoDTO formatoDTO2 = new FormatoDTO();
        assertThat(formatoDTO1).isNotEqualTo(formatoDTO2);
        formatoDTO2.setId(formatoDTO1.getId());
        assertThat(formatoDTO1).isEqualTo(formatoDTO2);
        formatoDTO2.setId(2L);
        assertThat(formatoDTO1).isNotEqualTo(formatoDTO2);
        formatoDTO1.setId(null);
        assertThat(formatoDTO1).isNotEqualTo(formatoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(formatoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(formatoMapper.fromId(null)).isNull();
    }
}
