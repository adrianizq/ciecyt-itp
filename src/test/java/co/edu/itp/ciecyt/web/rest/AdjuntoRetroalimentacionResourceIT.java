package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.AdjuntoRetroalimentacion;
import co.edu.itp.ciecyt.repository.AdjuntoRetroalimentacionRepository;
import co.edu.itp.ciecyt.service.AdjuntoRetroalimentacionService;
import co.edu.itp.ciecyt.service.dto.AdjuntoRetroalimentacionDTO;
import co.edu.itp.ciecyt.service.mapper.AdjuntoRetroalimentacionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdjuntoRetroalimentacionResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjuntoRetroalimentacionResourceIT {

    private static final String DEFAULT_NOMBRE_ADJUNTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ADJUNTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ESTADO_ADJUNTO = 1;
    private static final Integer UPDATED_ESTADO_ADJUNTO = 2;

    private static final String DEFAULT_ADJUNTO_RETROALIMENTACION = "AAAAAAAAAA";
    private static final String UPDATED_ADJUNTO_RETROALIMENTACION = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_ARCHIVO_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ARCHIVO_ORIGINAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdjuntoRetroalimentacionRepository adjuntoRetroalimentacionRepository;

    @Autowired
    private AdjuntoRetroalimentacionMapper adjuntoRetroalimentacionMapper;

    @Autowired
    private AdjuntoRetroalimentacionService adjuntoRetroalimentacionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjuntoRetroalimentacionMockMvc;

    private AdjuntoRetroalimentacion adjuntoRetroalimentacion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjuntoRetroalimentacion createEntity(EntityManager em) {
        AdjuntoRetroalimentacion adjuntoRetroalimentacion = new AdjuntoRetroalimentacion()
            .nombreAdjunto(DEFAULT_NOMBRE_ADJUNTO)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .fechaModificacion(DEFAULT_FECHA_MODIFICACION)
            .estadoAdjunto(DEFAULT_ESTADO_ADJUNTO)
            .adjuntoRetroalimentacion(DEFAULT_ADJUNTO_RETROALIMENTACION)
            .nombreArchivoOriginal(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN);
        return adjuntoRetroalimentacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjuntoRetroalimentacion createUpdatedEntity(EntityManager em) {
        AdjuntoRetroalimentacion adjuntoRetroalimentacion = new AdjuntoRetroalimentacion()
            .nombreAdjunto(UPDATED_NOMBRE_ADJUNTO)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .estadoAdjunto(UPDATED_ESTADO_ADJUNTO)
            .adjuntoRetroalimentacion(UPDATED_ADJUNTO_RETROALIMENTACION)
            .nombreArchivoOriginal(UPDATED_NOMBRE_ARCHIVO_ORIGINAL)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);
        return adjuntoRetroalimentacion;
    }

    @BeforeEach
    public void initTest() {
        adjuntoRetroalimentacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjuntoRetroalimentacion() throws Exception {
        int databaseSizeBeforeCreate = adjuntoRetroalimentacionRepository.findAll().size();
        // Create the AdjuntoRetroalimentacion
        AdjuntoRetroalimentacionDTO adjuntoRetroalimentacionDTO = adjuntoRetroalimentacionMapper.toDto(adjuntoRetroalimentacion);
        restAdjuntoRetroalimentacionMockMvc.perform(post("/api/adjunto-retroalimentacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoRetroalimentacionDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjuntoRetroalimentacion in the database
        List<AdjuntoRetroalimentacion> adjuntoRetroalimentacionList = adjuntoRetroalimentacionRepository.findAll();
        assertThat(adjuntoRetroalimentacionList).hasSize(databaseSizeBeforeCreate + 1);
        AdjuntoRetroalimentacion testAdjuntoRetroalimentacion = adjuntoRetroalimentacionList.get(adjuntoRetroalimentacionList.size() - 1);
        assertThat(testAdjuntoRetroalimentacion.getNombreAdjunto()).isEqualTo(DEFAULT_NOMBRE_ADJUNTO);
        assertThat(testAdjuntoRetroalimentacion.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testAdjuntoRetroalimentacion.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
        assertThat(testAdjuntoRetroalimentacion.getEstadoAdjunto()).isEqualTo(DEFAULT_ESTADO_ADJUNTO);
        assertThat(testAdjuntoRetroalimentacion.getAdjuntoRetroalimentacion()).isEqualTo(DEFAULT_ADJUNTO_RETROALIMENTACION);
        assertThat(testAdjuntoRetroalimentacion.getNombreArchivoOriginal()).isEqualTo(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL);
        assertThat(testAdjuntoRetroalimentacion.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testAdjuntoRetroalimentacion.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
    }

    @Test
    @Transactional
    public void createAdjuntoRetroalimentacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjuntoRetroalimentacionRepository.findAll().size();

        // Create the AdjuntoRetroalimentacion with an existing ID
        adjuntoRetroalimentacion.setId(1L);
        AdjuntoRetroalimentacionDTO adjuntoRetroalimentacionDTO = adjuntoRetroalimentacionMapper.toDto(adjuntoRetroalimentacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjuntoRetroalimentacionMockMvc.perform(post("/api/adjunto-retroalimentacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoRetroalimentacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjuntoRetroalimentacion in the database
        List<AdjuntoRetroalimentacion> adjuntoRetroalimentacionList = adjuntoRetroalimentacionRepository.findAll();
        assertThat(adjuntoRetroalimentacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjuntoRetroalimentacions() throws Exception {
        // Initialize the database
        adjuntoRetroalimentacionRepository.saveAndFlush(adjuntoRetroalimentacion);

        // Get all the adjuntoRetroalimentacionList
        restAdjuntoRetroalimentacionMockMvc.perform(get("/api/adjunto-retroalimentacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjuntoRetroalimentacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreAdjunto").value(hasItem(DEFAULT_NOMBRE_ADJUNTO)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].estadoAdjunto").value(hasItem(DEFAULT_ESTADO_ADJUNTO)))
            .andExpect(jsonPath("$.[*].adjuntoRetroalimentacion").value(hasItem(DEFAULT_ADJUNTO_RETROALIMENTACION)))
            .andExpect(jsonPath("$.[*].nombreArchivoOriginal").value(hasItem(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())));
    }
    
    @Test
    @Transactional
    public void getAdjuntoRetroalimentacion() throws Exception {
        // Initialize the database
        adjuntoRetroalimentacionRepository.saveAndFlush(adjuntoRetroalimentacion);

        // Get the adjuntoRetroalimentacion
        restAdjuntoRetroalimentacionMockMvc.perform(get("/api/adjunto-retroalimentacions/{id}", adjuntoRetroalimentacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjuntoRetroalimentacion.getId().intValue()))
            .andExpect(jsonPath("$.nombreAdjunto").value(DEFAULT_NOMBRE_ADJUNTO))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.estadoAdjunto").value(DEFAULT_ESTADO_ADJUNTO))
            .andExpect(jsonPath("$.adjuntoRetroalimentacion").value(DEFAULT_ADJUNTO_RETROALIMENTACION))
            .andExpect(jsonPath("$.nombreArchivoOriginal").value(DEFAULT_NOMBRE_ARCHIVO_ORIGINAL))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAdjuntoRetroalimentacion() throws Exception {
        // Get the adjuntoRetroalimentacion
        restAdjuntoRetroalimentacionMockMvc.perform(get("/api/adjunto-retroalimentacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjuntoRetroalimentacion() throws Exception {
        // Initialize the database
        adjuntoRetroalimentacionRepository.saveAndFlush(adjuntoRetroalimentacion);

        int databaseSizeBeforeUpdate = adjuntoRetroalimentacionRepository.findAll().size();

        // Update the adjuntoRetroalimentacion
        AdjuntoRetroalimentacion updatedAdjuntoRetroalimentacion = adjuntoRetroalimentacionRepository.findById(adjuntoRetroalimentacion.getId()).get();
        // Disconnect from session so that the updates on updatedAdjuntoRetroalimentacion are not directly saved in db
        em.detach(updatedAdjuntoRetroalimentacion);
        updatedAdjuntoRetroalimentacion
            .nombreAdjunto(UPDATED_NOMBRE_ADJUNTO)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .estadoAdjunto(UPDATED_ESTADO_ADJUNTO)
            .adjuntoRetroalimentacion(UPDATED_ADJUNTO_RETROALIMENTACION)
            .nombreArchivoOriginal(UPDATED_NOMBRE_ARCHIVO_ORIGINAL)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);
        AdjuntoRetroalimentacionDTO adjuntoRetroalimentacionDTO = adjuntoRetroalimentacionMapper.toDto(updatedAdjuntoRetroalimentacion);

        restAdjuntoRetroalimentacionMockMvc.perform(put("/api/adjunto-retroalimentacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoRetroalimentacionDTO)))
            .andExpect(status().isOk());

        // Validate the AdjuntoRetroalimentacion in the database
        List<AdjuntoRetroalimentacion> adjuntoRetroalimentacionList = adjuntoRetroalimentacionRepository.findAll();
        assertThat(adjuntoRetroalimentacionList).hasSize(databaseSizeBeforeUpdate);
        AdjuntoRetroalimentacion testAdjuntoRetroalimentacion = adjuntoRetroalimentacionList.get(adjuntoRetroalimentacionList.size() - 1);
        assertThat(testAdjuntoRetroalimentacion.getNombreAdjunto()).isEqualTo(UPDATED_NOMBRE_ADJUNTO);
        assertThat(testAdjuntoRetroalimentacion.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testAdjuntoRetroalimentacion.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
        assertThat(testAdjuntoRetroalimentacion.getEstadoAdjunto()).isEqualTo(UPDATED_ESTADO_ADJUNTO);
        assertThat(testAdjuntoRetroalimentacion.getAdjuntoRetroalimentacion()).isEqualTo(UPDATED_ADJUNTO_RETROALIMENTACION);
        assertThat(testAdjuntoRetroalimentacion.getNombreArchivoOriginal()).isEqualTo(UPDATED_NOMBRE_ARCHIVO_ORIGINAL);
        assertThat(testAdjuntoRetroalimentacion.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testAdjuntoRetroalimentacion.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjuntoRetroalimentacion() throws Exception {
        int databaseSizeBeforeUpdate = adjuntoRetroalimentacionRepository.findAll().size();

        // Create the AdjuntoRetroalimentacion
        AdjuntoRetroalimentacionDTO adjuntoRetroalimentacionDTO = adjuntoRetroalimentacionMapper.toDto(adjuntoRetroalimentacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjuntoRetroalimentacionMockMvc.perform(put("/api/adjunto-retroalimentacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjuntoRetroalimentacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjuntoRetroalimentacion in the database
        List<AdjuntoRetroalimentacion> adjuntoRetroalimentacionList = adjuntoRetroalimentacionRepository.findAll();
        assertThat(adjuntoRetroalimentacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjuntoRetroalimentacion() throws Exception {
        // Initialize the database
        adjuntoRetroalimentacionRepository.saveAndFlush(adjuntoRetroalimentacion);

        int databaseSizeBeforeDelete = adjuntoRetroalimentacionRepository.findAll().size();

        // Delete the adjuntoRetroalimentacion
        restAdjuntoRetroalimentacionMockMvc.perform(delete("/api/adjunto-retroalimentacions/{id}", adjuntoRetroalimentacion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjuntoRetroalimentacion> adjuntoRetroalimentacionList = adjuntoRetroalimentacionRepository.findAll();
        assertThat(adjuntoRetroalimentacionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
