package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.ProyectoRespuestas;
import co.edu.itp.ciecyt.repository.ProyectoRespuestasRepository;
import co.edu.itp.ciecyt.service.ProyectoRespuestasService;
import co.edu.itp.ciecyt.service.dto.ProyectoRespuestasDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoRespuestasMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.itp.ciecyt.domain.enumeration.EnumRespuestas;
/**
 * Integration tests for the {@link ProyectoRespuestasResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProyectoRespuestasResourceIT {

    private static final EnumRespuestas DEFAULT_RESPUESTA = EnumRespuestas.CUMPLE;
    private static final EnumRespuestas UPDATED_RESPUESTA = EnumRespuestas.NO_CUMPLE;

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VIABLE = false;
    private static final Boolean UPDATED_VIABLE = true;

    private static final Float DEFAULT_PUNTAJE = 1F;
    private static final Float UPDATED_PUNTAJE = 2F;

    private static final Boolean DEFAULT_SI_NO = false;
    private static final Boolean UPDATED_SI_NO = true;

    @Autowired
    private ProyectoRespuestasRepository proyectoRespuestasRepository;

    @Autowired
    private ProyectoRespuestasMapper proyectoRespuestasMapper;

    @Autowired
    private ProyectoRespuestasService proyectoRespuestasService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProyectoRespuestasMockMvc;

    private ProyectoRespuestas proyectoRespuestas;

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
            .viable(DEFAULT_VIABLE)
            .puntaje(DEFAULT_PUNTAJE)
            .siNo(DEFAULT_SI_NO);
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
            .viable(UPDATED_VIABLE)
            .puntaje(UPDATED_PUNTAJE)
            .siNo(UPDATED_SI_NO);
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
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proyectoRespuestasDTO)))
            .andExpect(status().isCreated());

        // Validate the ProyectoRespuestas in the database
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeCreate + 1);
        ProyectoRespuestas testProyectoRespuestas = proyectoRespuestasList.get(proyectoRespuestasList.size() - 1);
        assertThat(testProyectoRespuestas.getRespuesta()).isEqualTo(DEFAULT_RESPUESTA);
        assertThat(testProyectoRespuestas.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testProyectoRespuestas.isViable()).isEqualTo(DEFAULT_VIABLE);
        assertThat(testProyectoRespuestas.getPuntaje()).isEqualTo(DEFAULT_PUNTAJE);
        assertThat(testProyectoRespuestas.isSiNo()).isEqualTo(DEFAULT_SI_NO);
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
            .contentType(MediaType.APPLICATION_JSON)
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
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proyectoRespuestas.getId().intValue())))
            .andExpect(jsonPath("$.[*].respuesta").value(hasItem(DEFAULT_RESPUESTA.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].viable").value(hasItem(DEFAULT_VIABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].puntaje").value(hasItem(DEFAULT_PUNTAJE.doubleValue())))
            .andExpect(jsonPath("$.[*].siNo").value(hasItem(DEFAULT_SI_NO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProyectoRespuestas() throws Exception {
        // Initialize the database
        proyectoRespuestasRepository.saveAndFlush(proyectoRespuestas);

        // Get the proyectoRespuestas
        restProyectoRespuestasMockMvc.perform(get("/api/proyecto-respuestas/{id}", proyectoRespuestas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proyectoRespuestas.getId().intValue()))
            .andExpect(jsonPath("$.respuesta").value(DEFAULT_RESPUESTA.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES))
            .andExpect(jsonPath("$.viable").value(DEFAULT_VIABLE.booleanValue()))
            .andExpect(jsonPath("$.puntaje").value(DEFAULT_PUNTAJE.doubleValue()))
            .andExpect(jsonPath("$.siNo").value(DEFAULT_SI_NO.booleanValue()));
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
            .viable(UPDATED_VIABLE)
            .puntaje(UPDATED_PUNTAJE)
            .siNo(UPDATED_SI_NO);
        ProyectoRespuestasDTO proyectoRespuestasDTO = proyectoRespuestasMapper.toDto(updatedProyectoRespuestas);

        restProyectoRespuestasMockMvc.perform(put("/api/proyecto-respuestas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proyectoRespuestasDTO)))
            .andExpect(status().isOk());

        // Validate the ProyectoRespuestas in the database
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeUpdate);
        ProyectoRespuestas testProyectoRespuestas = proyectoRespuestasList.get(proyectoRespuestasList.size() - 1);
        assertThat(testProyectoRespuestas.getRespuesta()).isEqualTo(UPDATED_RESPUESTA);
        assertThat(testProyectoRespuestas.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testProyectoRespuestas.isViable()).isEqualTo(UPDATED_VIABLE);
        assertThat(testProyectoRespuestas.getPuntaje()).isEqualTo(UPDATED_PUNTAJE);
        assertThat(testProyectoRespuestas.isSiNo()).isEqualTo(UPDATED_SI_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingProyectoRespuestas() throws Exception {
        int databaseSizeBeforeUpdate = proyectoRespuestasRepository.findAll().size();

        // Create the ProyectoRespuestas
        ProyectoRespuestasDTO proyectoRespuestasDTO = proyectoRespuestasMapper.toDto(proyectoRespuestas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProyectoRespuestasMockMvc.perform(put("/api/proyecto-respuestas")
            .contentType(MediaType.APPLICATION_JSON)
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
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProyectoRespuestas> proyectoRespuestasList = proyectoRespuestasRepository.findAll();
        assertThat(proyectoRespuestasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
