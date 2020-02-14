package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Solicitud;
import co.edu.itp.ciecyt.repository.SolicitudRepository;
import co.edu.itp.ciecyt.service.SolicitudService;
import co.edu.itp.ciecyt.service.dto.SolicitudDTO;
import co.edu.itp.ciecyt.service.mapper.SolicitudMapper;
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
 * Integration tests for the {@link SolicitudResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
public class SolicitudResourceIT {

    private static final Boolean DEFAULT_ESTADO = false;
    private static final Boolean UPDATED_ESTADO = true;

    private static final String DEFAULT_ASUNTO = "AAAAAAAAAA";
    private static final String UPDATED_ASUNTO = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTO_SOLICITUD = "AAAAAAAAAA";
    private static final String UPDATED_TEXTO_SOLICITUD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_SOLICITUD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_SOLICITUD = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private SolicitudMapper solicitudMapper;

    @Autowired
    private SolicitudService solicitudService;

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

    private MockMvc restSolicitudMockMvc;

    private Solicitud solicitud;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SolicitudResource solicitudResource = new SolicitudResource(solicitudService);
        this.restSolicitudMockMvc = MockMvcBuilders.standaloneSetup(solicitudResource)
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
    public static Solicitud createEntity(EntityManager em) {
        Solicitud solicitud = new Solicitud()
            .estado(DEFAULT_ESTADO)
            .asunto(DEFAULT_ASUNTO)
            .textoSolicitud(DEFAULT_TEXTO_SOLICITUD)
            .fechaSolicitud(DEFAULT_FECHA_SOLICITUD);
        return solicitud;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Solicitud createUpdatedEntity(EntityManager em) {
        Solicitud solicitud = new Solicitud()
            .estado(UPDATED_ESTADO)
            .asunto(UPDATED_ASUNTO)
            .textoSolicitud(UPDATED_TEXTO_SOLICITUD)
            .fechaSolicitud(UPDATED_FECHA_SOLICITUD);
        return solicitud;
    }

    @BeforeEach
    public void initTest() {
        solicitud = createEntity(em);
    }

    @Test
    @Transactional
    public void createSolicitud() throws Exception {
        int databaseSizeBeforeCreate = solicitudRepository.findAll().size();

        // Create the Solicitud
        SolicitudDTO solicitudDTO = solicitudMapper.toDto(solicitud);
        restSolicitudMockMvc.perform(post("/api/solicituds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudDTO)))
            .andExpect(status().isCreated());

        // Validate the Solicitud in the database
        List<Solicitud> solicitudList = solicitudRepository.findAll();
        assertThat(solicitudList).hasSize(databaseSizeBeforeCreate + 1);
        Solicitud testSolicitud = solicitudList.get(solicitudList.size() - 1);
        assertThat(testSolicitud.isEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testSolicitud.getAsunto()).isEqualTo(DEFAULT_ASUNTO);
        assertThat(testSolicitud.getTextoSolicitud()).isEqualTo(DEFAULT_TEXTO_SOLICITUD);
        assertThat(testSolicitud.getFechaSolicitud()).isEqualTo(DEFAULT_FECHA_SOLICITUD);
    }

    @Test
    @Transactional
    public void createSolicitudWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = solicitudRepository.findAll().size();

        // Create the Solicitud with an existing ID
        solicitud.setId(1L);
        SolicitudDTO solicitudDTO = solicitudMapper.toDto(solicitud);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSolicitudMockMvc.perform(post("/api/solicituds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitud in the database
        List<Solicitud> solicitudList = solicitudRepository.findAll();
        assertThat(solicitudList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSolicituds() throws Exception {
        // Initialize the database
        solicitudRepository.saveAndFlush(solicitud);

        // Get all the solicitudList
        restSolicitudMockMvc.perform(get("/api/solicituds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(solicitud.getId().intValue())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.booleanValue())))
            .andExpect(jsonPath("$.[*].asunto").value(hasItem(DEFAULT_ASUNTO)))
            .andExpect(jsonPath("$.[*].textoSolicitud").value(hasItem(DEFAULT_TEXTO_SOLICITUD)))
            .andExpect(jsonPath("$.[*].fechaSolicitud").value(hasItem(DEFAULT_FECHA_SOLICITUD.toString())));
    }
    
    @Test
    @Transactional
    public void getSolicitud() throws Exception {
        // Initialize the database
        solicitudRepository.saveAndFlush(solicitud);

        // Get the solicitud
        restSolicitudMockMvc.perform(get("/api/solicituds/{id}", solicitud.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(solicitud.getId().intValue()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.booleanValue()))
            .andExpect(jsonPath("$.asunto").value(DEFAULT_ASUNTO))
            .andExpect(jsonPath("$.textoSolicitud").value(DEFAULT_TEXTO_SOLICITUD))
            .andExpect(jsonPath("$.fechaSolicitud").value(DEFAULT_FECHA_SOLICITUD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSolicitud() throws Exception {
        // Get the solicitud
        restSolicitudMockMvc.perform(get("/api/solicituds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSolicitud() throws Exception {
        // Initialize the database
        solicitudRepository.saveAndFlush(solicitud);

        int databaseSizeBeforeUpdate = solicitudRepository.findAll().size();

        // Update the solicitud
        Solicitud updatedSolicitud = solicitudRepository.findById(solicitud.getId()).get();
        // Disconnect from session so that the updates on updatedSolicitud are not directly saved in db
        em.detach(updatedSolicitud);
        updatedSolicitud
            .estado(UPDATED_ESTADO)
            .asunto(UPDATED_ASUNTO)
            .textoSolicitud(UPDATED_TEXTO_SOLICITUD)
            .fechaSolicitud(UPDATED_FECHA_SOLICITUD);
        SolicitudDTO solicitudDTO = solicitudMapper.toDto(updatedSolicitud);

        restSolicitudMockMvc.perform(put("/api/solicituds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudDTO)))
            .andExpect(status().isOk());

        // Validate the Solicitud in the database
        List<Solicitud> solicitudList = solicitudRepository.findAll();
        assertThat(solicitudList).hasSize(databaseSizeBeforeUpdate);
        Solicitud testSolicitud = solicitudList.get(solicitudList.size() - 1);
        assertThat(testSolicitud.isEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testSolicitud.getAsunto()).isEqualTo(UPDATED_ASUNTO);
        assertThat(testSolicitud.getTextoSolicitud()).isEqualTo(UPDATED_TEXTO_SOLICITUD);
        assertThat(testSolicitud.getFechaSolicitud()).isEqualTo(UPDATED_FECHA_SOLICITUD);
    }

    @Test
    @Transactional
    public void updateNonExistingSolicitud() throws Exception {
        int databaseSizeBeforeUpdate = solicitudRepository.findAll().size();

        // Create the Solicitud
        SolicitudDTO solicitudDTO = solicitudMapper.toDto(solicitud);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSolicitudMockMvc.perform(put("/api/solicituds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitud in the database
        List<Solicitud> solicitudList = solicitudRepository.findAll();
        assertThat(solicitudList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSolicitud() throws Exception {
        // Initialize the database
        solicitudRepository.saveAndFlush(solicitud);

        int databaseSizeBeforeDelete = solicitudRepository.findAll().size();

        // Delete the solicitud
        restSolicitudMockMvc.perform(delete("/api/solicituds/{id}", solicitud.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Solicitud> solicitudList = solicitudRepository.findAll();
        assertThat(solicitudList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Solicitud.class);
        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(1L);
        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(solicitud1.getId());
        assertThat(solicitud1).isEqualTo(solicitud2);
        solicitud2.setId(2L);
        assertThat(solicitud1).isNotEqualTo(solicitud2);
        solicitud1.setId(null);
        assertThat(solicitud1).isNotEqualTo(solicitud2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SolicitudDTO.class);
        SolicitudDTO solicitudDTO1 = new SolicitudDTO();
        solicitudDTO1.setId(1L);
        SolicitudDTO solicitudDTO2 = new SolicitudDTO();
        assertThat(solicitudDTO1).isNotEqualTo(solicitudDTO2);
        solicitudDTO2.setId(solicitudDTO1.getId());
        assertThat(solicitudDTO1).isEqualTo(solicitudDTO2);
        solicitudDTO2.setId(2L);
        assertThat(solicitudDTO1).isNotEqualTo(solicitudDTO2);
        solicitudDTO1.setId(null);
        assertThat(solicitudDTO1).isNotEqualTo(solicitudDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(solicitudMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(solicitudMapper.fromId(null)).isNull();
    }
}
