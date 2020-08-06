package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.PresupuestoValor;
import co.edu.itp.ciecyt.repository.PresupuestoValorRepository;
import co.edu.itp.ciecyt.service.PresupuestoValorService;
import co.edu.itp.ciecyt.service.dto.PresupuestoValorDTO;
import co.edu.itp.ciecyt.service.mapper.PresupuestoValorMapper;
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
 * Integration tests for the {@link PresupuestoValorResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class PresupuestoValorResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_JUSTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICACION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

    private static final Double DEFAULT_VALOR_UNITARIO = 1D;
    private static final Double UPDATED_VALOR_UNITARIO = 2D;

    private static final Double DEFAULT_ESPECIE = 1D;
    private static final Double UPDATED_ESPECIE = 2D;

    private static final Double DEFAULT_DINERO = 1D;
    private static final Double UPDATED_DINERO = 2D;

    private static final Integer DEFAULT_ORDEN_VISTA = 1;
    private static final Integer UPDATED_ORDEN_VISTA = 2;

    @Autowired
    private PresupuestoValorRepository presupuestoValorRepository;

    @Autowired
    private PresupuestoValorMapper presupuestoValorMapper;

    @Autowired
    private PresupuestoValorService presupuestoValorService;

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

    private MockMvc restPresupuestoValorMockMvc;

    private PresupuestoValor presupuestoValor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PresupuestoValorResource presupuestoValorResource = new PresupuestoValorResource(presupuestoValorService);
        this.restPresupuestoValorMockMvc = MockMvcBuilders.standaloneSetup(presupuestoValorResource)
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
    public static PresupuestoValor createEntity(EntityManager em) {
        PresupuestoValor presupuestoValor = new PresupuestoValor()
            .descripcion(DEFAULT_DESCRIPCION)
            .justificacion(DEFAULT_JUSTIFICACION)
            .cantidad(DEFAULT_CANTIDAD)
            .valorUnitario(DEFAULT_VALOR_UNITARIO)
            .especie(DEFAULT_ESPECIE)
            .dinero(DEFAULT_DINERO)
            .ordenVista(DEFAULT_ORDEN_VISTA);
        return presupuestoValor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PresupuestoValor createUpdatedEntity(EntityManager em) {
        PresupuestoValor presupuestoValor = new PresupuestoValor()
            .descripcion(UPDATED_DESCRIPCION)
            .justificacion(UPDATED_JUSTIFICACION)
            .cantidad(UPDATED_CANTIDAD)
            .valorUnitario(UPDATED_VALOR_UNITARIO)
            .especie(UPDATED_ESPECIE)
            .dinero(UPDATED_DINERO)
            .ordenVista(UPDATED_ORDEN_VISTA);
        return presupuestoValor;
    }

    @BeforeEach
    public void initTest() {
        presupuestoValor = createEntity(em);
    }

    @Test
    @Transactional
    public void createPresupuestoValor() throws Exception {
        int databaseSizeBeforeCreate = presupuestoValorRepository.findAll().size();

        // Create the PresupuestoValor
        PresupuestoValorDTO presupuestoValorDTO = presupuestoValorMapper.toDto(presupuestoValor);
        restPresupuestoValorMockMvc.perform(post("/api/presupuesto-valors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(presupuestoValorDTO)))
            .andExpect(status().isCreated());

        // Validate the PresupuestoValor in the database
        List<PresupuestoValor> presupuestoValorList = presupuestoValorRepository.findAll();
        assertThat(presupuestoValorList).hasSize(databaseSizeBeforeCreate + 1);
        PresupuestoValor testPresupuestoValor = presupuestoValorList.get(presupuestoValorList.size() - 1);
        assertThat(testPresupuestoValor.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testPresupuestoValor.getJustificacion()).isEqualTo(DEFAULT_JUSTIFICACION);
        assertThat(testPresupuestoValor.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
        assertThat(testPresupuestoValor.getValorUnitario()).isEqualTo(DEFAULT_VALOR_UNITARIO);
        assertThat(testPresupuestoValor.getEspecie()).isEqualTo(DEFAULT_ESPECIE);
        assertThat(testPresupuestoValor.getDinero()).isEqualTo(DEFAULT_DINERO);
        assertThat(testPresupuestoValor.getOrdenVista()).isEqualTo(DEFAULT_ORDEN_VISTA);
    }

    @Test
    @Transactional
    public void createPresupuestoValorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = presupuestoValorRepository.findAll().size();

        // Create the PresupuestoValor with an existing ID
        presupuestoValor.setId(1L);
        PresupuestoValorDTO presupuestoValorDTO = presupuestoValorMapper.toDto(presupuestoValor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPresupuestoValorMockMvc.perform(post("/api/presupuesto-valors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(presupuestoValorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PresupuestoValor in the database
        List<PresupuestoValor> presupuestoValorList = presupuestoValorRepository.findAll();
        assertThat(presupuestoValorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPresupuestoValors() throws Exception {
        // Initialize the database
        presupuestoValorRepository.saveAndFlush(presupuestoValor);

        // Get all the presupuestoValorList
        restPresupuestoValorMockMvc.perform(get("/api/presupuesto-valors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(presupuestoValor.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].justificacion").value(hasItem(DEFAULT_JUSTIFICACION)))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].valorUnitario").value(hasItem(DEFAULT_VALOR_UNITARIO.doubleValue())))
            .andExpect(jsonPath("$.[*].especie").value(hasItem(DEFAULT_ESPECIE.doubleValue())))
            .andExpect(jsonPath("$.[*].dinero").value(hasItem(DEFAULT_DINERO.doubleValue())))
            .andExpect(jsonPath("$.[*].ordenVista").value(hasItem(DEFAULT_ORDEN_VISTA)));
    }
    
    @Test
    @Transactional
    public void getPresupuestoValor() throws Exception {
        // Initialize the database
        presupuestoValorRepository.saveAndFlush(presupuestoValor);

        // Get the presupuestoValor
        restPresupuestoValorMockMvc.perform(get("/api/presupuesto-valors/{id}", presupuestoValor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(presupuestoValor.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.justificacion").value(DEFAULT_JUSTIFICACION))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD))
            .andExpect(jsonPath("$.valorUnitario").value(DEFAULT_VALOR_UNITARIO.doubleValue()))
            .andExpect(jsonPath("$.especie").value(DEFAULT_ESPECIE.doubleValue()))
            .andExpect(jsonPath("$.dinero").value(DEFAULT_DINERO.doubleValue()))
            .andExpect(jsonPath("$.ordenVista").value(DEFAULT_ORDEN_VISTA));
    }

    @Test
    @Transactional
    public void getNonExistingPresupuestoValor() throws Exception {
        // Get the presupuestoValor
        restPresupuestoValorMockMvc.perform(get("/api/presupuesto-valors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePresupuestoValor() throws Exception {
        // Initialize the database
        presupuestoValorRepository.saveAndFlush(presupuestoValor);

        int databaseSizeBeforeUpdate = presupuestoValorRepository.findAll().size();

        // Update the presupuestoValor
        PresupuestoValor updatedPresupuestoValor = presupuestoValorRepository.findById(presupuestoValor.getId()).get();
        // Disconnect from session so that the updates on updatedPresupuestoValor are not directly saved in db
        em.detach(updatedPresupuestoValor);
        updatedPresupuestoValor
            .descripcion(UPDATED_DESCRIPCION)
            .justificacion(UPDATED_JUSTIFICACION)
            .cantidad(UPDATED_CANTIDAD)
            .valorUnitario(UPDATED_VALOR_UNITARIO)
            .especie(UPDATED_ESPECIE)
            .dinero(UPDATED_DINERO)
            .ordenVista(UPDATED_ORDEN_VISTA);
        PresupuestoValorDTO presupuestoValorDTO = presupuestoValorMapper.toDto(updatedPresupuestoValor);

        restPresupuestoValorMockMvc.perform(put("/api/presupuesto-valors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(presupuestoValorDTO)))
            .andExpect(status().isOk());

        // Validate the PresupuestoValor in the database
        List<PresupuestoValor> presupuestoValorList = presupuestoValorRepository.findAll();
        assertThat(presupuestoValorList).hasSize(databaseSizeBeforeUpdate);
        PresupuestoValor testPresupuestoValor = presupuestoValorList.get(presupuestoValorList.size() - 1);
        assertThat(testPresupuestoValor.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testPresupuestoValor.getJustificacion()).isEqualTo(UPDATED_JUSTIFICACION);
        assertThat(testPresupuestoValor.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testPresupuestoValor.getValorUnitario()).isEqualTo(UPDATED_VALOR_UNITARIO);
        assertThat(testPresupuestoValor.getEspecie()).isEqualTo(UPDATED_ESPECIE);
        assertThat(testPresupuestoValor.getDinero()).isEqualTo(UPDATED_DINERO);
        assertThat(testPresupuestoValor.getOrdenVista()).isEqualTo(UPDATED_ORDEN_VISTA);
    }

    @Test
    @Transactional
    public void updateNonExistingPresupuestoValor() throws Exception {
        int databaseSizeBeforeUpdate = presupuestoValorRepository.findAll().size();

        // Create the PresupuestoValor
        PresupuestoValorDTO presupuestoValorDTO = presupuestoValorMapper.toDto(presupuestoValor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPresupuestoValorMockMvc.perform(put("/api/presupuesto-valors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(presupuestoValorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PresupuestoValor in the database
        List<PresupuestoValor> presupuestoValorList = presupuestoValorRepository.findAll();
        assertThat(presupuestoValorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePresupuestoValor() throws Exception {
        // Initialize the database
        presupuestoValorRepository.saveAndFlush(presupuestoValor);

        int databaseSizeBeforeDelete = presupuestoValorRepository.findAll().size();

        // Delete the presupuestoValor
        restPresupuestoValorMockMvc.perform(delete("/api/presupuesto-valors/{id}", presupuestoValor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PresupuestoValor> presupuestoValorList = presupuestoValorRepository.findAll();
        assertThat(presupuestoValorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PresupuestoValor.class);
        PresupuestoValor presupuestoValor1 = new PresupuestoValor();
        presupuestoValor1.setId(1L);
        PresupuestoValor presupuestoValor2 = new PresupuestoValor();
        presupuestoValor2.setId(presupuestoValor1.getId());
        assertThat(presupuestoValor1).isEqualTo(presupuestoValor2);
        presupuestoValor2.setId(2L);
        assertThat(presupuestoValor1).isNotEqualTo(presupuestoValor2);
        presupuestoValor1.setId(null);
        assertThat(presupuestoValor1).isNotEqualTo(presupuestoValor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PresupuestoValorDTO.class);
        PresupuestoValorDTO presupuestoValorDTO1 = new PresupuestoValorDTO();
        presupuestoValorDTO1.setId(1L);
        PresupuestoValorDTO presupuestoValorDTO2 = new PresupuestoValorDTO();
        assertThat(presupuestoValorDTO1).isNotEqualTo(presupuestoValorDTO2);
        presupuestoValorDTO2.setId(presupuestoValorDTO1.getId());
        assertThat(presupuestoValorDTO1).isEqualTo(presupuestoValorDTO2);
        presupuestoValorDTO2.setId(2L);
        assertThat(presupuestoValorDTO1).isNotEqualTo(presupuestoValorDTO2);
        presupuestoValorDTO1.setId(null);
        assertThat(presupuestoValorDTO1).isNotEqualTo(presupuestoValorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(presupuestoValorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(presupuestoValorMapper.fromId(null)).isNull();
    }
}
