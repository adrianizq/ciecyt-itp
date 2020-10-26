package co.edu.itp.ciecyt.web.rest;

import co.edu.itp.ciecyt.CiecytApp;
import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.repository.ProyectoRepository;
import co.edu.itp.ciecyt.service.ProyectoService;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoMapper;

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
 * Integration tests for the {@link ProyectoResource} REST controller.
 */
@SpringBootTest(classes = CiecytApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProyectoResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_LUGAR_EJECUCION = "AAAAAAAAAA";
    private static final String UPDATED_LUGAR_EJECUCION = "BBBBBBBBBB";

    private static final String DEFAULT_DURACION = "AAAAAAAAAA";
    private static final String UPDATED_DURACION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INI = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_CONTRAPARTIDA_PESOS = 1D;
    private static final Double UPDATED_CONTRAPARTIDA_PESOS = 2D;

    private static final Double DEFAULT_CONTRAPARTIDA_ESPECIE = 1D;
    private static final Double UPDATED_CONTRAPARTIDA_ESPECIE = 2D;

    private static final String DEFAULT_PALABRAS_CLAVE = "AAAAAAAAAA";
    private static final String UPDATED_PALABRAS_CLAVE = "BBBBBBBBBB";

    private static final String DEFAULT_CONVOCATORIA = "AAAAAAAAAA";
    private static final String UPDATED_CONVOCATORIA = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCIAS = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIAS = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRAMA = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_MUNICIPIO = "BBBBBBBBBB";

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ProyectoMapper proyectoMapper;

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProyectoMockMvc;

    private Proyecto proyecto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proyecto createEntity(EntityManager em) {
        Proyecto proyecto = new Proyecto()
            .titulo(DEFAULT_TITULO)
            .url(DEFAULT_URL)
            .lugarEjecucion(DEFAULT_LUGAR_EJECUCION)
            .duracion(DEFAULT_DURACION)
            .fechaIni(DEFAULT_FECHA_INI)
            .fechaFin(DEFAULT_FECHA_FIN)
            .contrapartidaPesos(DEFAULT_CONTRAPARTIDA_PESOS)
            .contrapartidaEspecie(DEFAULT_CONTRAPARTIDA_ESPECIE)
            .palabrasClave(DEFAULT_PALABRAS_CLAVE)
            .convocatoria(DEFAULT_CONVOCATORIA)
            .tipo(DEFAULT_TIPO)
            .referencias(DEFAULT_REFERENCIAS)
            .programa(DEFAULT_PROGRAMA)
            .departamento(DEFAULT_DEPARTAMENTO)
            .municipio(DEFAULT_MUNICIPIO);
        return proyecto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proyecto createUpdatedEntity(EntityManager em) {
        Proyecto proyecto = new Proyecto()
            .titulo(UPDATED_TITULO)
            .url(UPDATED_URL)
            .lugarEjecucion(UPDATED_LUGAR_EJECUCION)
            .duracion(UPDATED_DURACION)
            .fechaIni(UPDATED_FECHA_INI)
            .fechaFin(UPDATED_FECHA_FIN)
            .contrapartidaPesos(UPDATED_CONTRAPARTIDA_PESOS)
            .contrapartidaEspecie(UPDATED_CONTRAPARTIDA_ESPECIE)
            .palabrasClave(UPDATED_PALABRAS_CLAVE)
            .convocatoria(UPDATED_CONVOCATORIA)
            .tipo(UPDATED_TIPO)
            .referencias(UPDATED_REFERENCIAS)
            .programa(UPDATED_PROGRAMA)
            .departamento(UPDATED_DEPARTAMENTO)
            .municipio(UPDATED_MUNICIPIO);
        return proyecto;
    }

    @BeforeEach
    public void initTest() {
        proyecto = createEntity(em);
    }

    @Test
    @Transactional
    public void createProyecto() throws Exception {
        int databaseSizeBeforeCreate = proyectoRepository.findAll().size();
        // Create the Proyecto
        ProyectoDTO proyectoDTO = proyectoMapper.toDto(proyecto);
        restProyectoMockMvc.perform(post("/api/proyectos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proyectoDTO)))
            .andExpect(status().isCreated());

        // Validate the Proyecto in the database
        List<Proyecto> proyectoList = proyectoRepository.findAll();
        assertThat(proyectoList).hasSize(databaseSizeBeforeCreate + 1);
        Proyecto testProyecto = proyectoList.get(proyectoList.size() - 1);
        assertThat(testProyecto.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testProyecto.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testProyecto.getLugarEjecucion()).isEqualTo(DEFAULT_LUGAR_EJECUCION);
        assertThat(testProyecto.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testProyecto.getFechaIni()).isEqualTo(DEFAULT_FECHA_INI);
        assertThat(testProyecto.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testProyecto.getContrapartidaPesos()).isEqualTo(DEFAULT_CONTRAPARTIDA_PESOS);
        assertThat(testProyecto.getContrapartidaEspecie()).isEqualTo(DEFAULT_CONTRAPARTIDA_ESPECIE);
        assertThat(testProyecto.getPalabrasClave()).isEqualTo(DEFAULT_PALABRAS_CLAVE);
        assertThat(testProyecto.getConvocatoria()).isEqualTo(DEFAULT_CONVOCATORIA);
        assertThat(testProyecto.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testProyecto.getReferencias()).isEqualTo(DEFAULT_REFERENCIAS);
        assertThat(testProyecto.getPrograma()).isEqualTo(DEFAULT_PROGRAMA);
        assertThat(testProyecto.getDepartamento()).isEqualTo(DEFAULT_DEPARTAMENTO);
        assertThat(testProyecto.getMunicipio()).isEqualTo(DEFAULT_MUNICIPIO);
    }

    @Test
    @Transactional
    public void createProyectoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proyectoRepository.findAll().size();

        // Create the Proyecto with an existing ID
        proyecto.setId(1L);
        ProyectoDTO proyectoDTO = proyectoMapper.toDto(proyecto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProyectoMockMvc.perform(post("/api/proyectos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proyectoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proyecto in the database
        List<Proyecto> proyectoList = proyectoRepository.findAll();
        assertThat(proyectoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProyectos() throws Exception {
        // Initialize the database
        proyectoRepository.saveAndFlush(proyecto);

        // Get all the proyectoList
        restProyectoMockMvc.perform(get("/api/proyectos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proyecto.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].lugarEjecucion").value(hasItem(DEFAULT_LUGAR_EJECUCION)))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION)))
            .andExpect(jsonPath("$.[*].fechaIni").value(hasItem(DEFAULT_FECHA_INI.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].contrapartidaPesos").value(hasItem(DEFAULT_CONTRAPARTIDA_PESOS.doubleValue())))
            .andExpect(jsonPath("$.[*].contrapartidaEspecie").value(hasItem(DEFAULT_CONTRAPARTIDA_ESPECIE.doubleValue())))
            .andExpect(jsonPath("$.[*].palabrasClave").value(hasItem(DEFAULT_PALABRAS_CLAVE)))
            .andExpect(jsonPath("$.[*].convocatoria").value(hasItem(DEFAULT_CONVOCATORIA)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)))
            .andExpect(jsonPath("$.[*].programa").value(hasItem(DEFAULT_PROGRAMA)))
            .andExpect(jsonPath("$.[*].departamento").value(hasItem(DEFAULT_DEPARTAMENTO)))
            .andExpect(jsonPath("$.[*].municipio").value(hasItem(DEFAULT_MUNICIPIO)));
    }
    
    @Test
    @Transactional
    public void getProyecto() throws Exception {
        // Initialize the database
        proyectoRepository.saveAndFlush(proyecto);

        // Get the proyecto
        restProyectoMockMvc.perform(get("/api/proyectos/{id}", proyecto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proyecto.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.lugarEjecucion").value(DEFAULT_LUGAR_EJECUCION))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION))
            .andExpect(jsonPath("$.fechaIni").value(DEFAULT_FECHA_INI.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.contrapartidaPesos").value(DEFAULT_CONTRAPARTIDA_PESOS.doubleValue()))
            .andExpect(jsonPath("$.contrapartidaEspecie").value(DEFAULT_CONTRAPARTIDA_ESPECIE.doubleValue()))
            .andExpect(jsonPath("$.palabrasClave").value(DEFAULT_PALABRAS_CLAVE))
            .andExpect(jsonPath("$.convocatoria").value(DEFAULT_CONVOCATORIA))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.referencias").value(DEFAULT_REFERENCIAS))
            .andExpect(jsonPath("$.programa").value(DEFAULT_PROGRAMA))
            .andExpect(jsonPath("$.departamento").value(DEFAULT_DEPARTAMENTO))
            .andExpect(jsonPath("$.municipio").value(DEFAULT_MUNICIPIO));
    }
    @Test
    @Transactional
    public void getNonExistingProyecto() throws Exception {
        // Get the proyecto
        restProyectoMockMvc.perform(get("/api/proyectos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProyecto() throws Exception {
        // Initialize the database
        proyectoRepository.saveAndFlush(proyecto);

        int databaseSizeBeforeUpdate = proyectoRepository.findAll().size();

        // Update the proyecto
        Proyecto updatedProyecto = proyectoRepository.findById(proyecto.getId()).get();
        // Disconnect from session so that the updates on updatedProyecto are not directly saved in db
        em.detach(updatedProyecto);
        updatedProyecto
            .titulo(UPDATED_TITULO)
            .url(UPDATED_URL)
            .lugarEjecucion(UPDATED_LUGAR_EJECUCION)
            .duracion(UPDATED_DURACION)
            .fechaIni(UPDATED_FECHA_INI)
            .fechaFin(UPDATED_FECHA_FIN)
            .contrapartidaPesos(UPDATED_CONTRAPARTIDA_PESOS)
            .contrapartidaEspecie(UPDATED_CONTRAPARTIDA_ESPECIE)
            .palabrasClave(UPDATED_PALABRAS_CLAVE)
            .convocatoria(UPDATED_CONVOCATORIA)
            .tipo(UPDATED_TIPO)
            .referencias(UPDATED_REFERENCIAS)
            .programa(UPDATED_PROGRAMA)
            .departamento(UPDATED_DEPARTAMENTO)
            .municipio(UPDATED_MUNICIPIO);
        ProyectoDTO proyectoDTO = proyectoMapper.toDto(updatedProyecto);

        restProyectoMockMvc.perform(put("/api/proyectos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proyectoDTO)))
            .andExpect(status().isOk());

        // Validate the Proyecto in the database
        List<Proyecto> proyectoList = proyectoRepository.findAll();
        assertThat(proyectoList).hasSize(databaseSizeBeforeUpdate);
        Proyecto testProyecto = proyectoList.get(proyectoList.size() - 1);
        assertThat(testProyecto.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testProyecto.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testProyecto.getLugarEjecucion()).isEqualTo(UPDATED_LUGAR_EJECUCION);
        assertThat(testProyecto.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testProyecto.getFechaIni()).isEqualTo(UPDATED_FECHA_INI);
        assertThat(testProyecto.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testProyecto.getContrapartidaPesos()).isEqualTo(UPDATED_CONTRAPARTIDA_PESOS);
        assertThat(testProyecto.getContrapartidaEspecie()).isEqualTo(UPDATED_CONTRAPARTIDA_ESPECIE);
        assertThat(testProyecto.getPalabrasClave()).isEqualTo(UPDATED_PALABRAS_CLAVE);
        assertThat(testProyecto.getConvocatoria()).isEqualTo(UPDATED_CONVOCATORIA);
        assertThat(testProyecto.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testProyecto.getReferencias()).isEqualTo(UPDATED_REFERENCIAS);
        assertThat(testProyecto.getPrograma()).isEqualTo(UPDATED_PROGRAMA);
        assertThat(testProyecto.getDepartamento()).isEqualTo(UPDATED_DEPARTAMENTO);
        assertThat(testProyecto.getMunicipio()).isEqualTo(UPDATED_MUNICIPIO);
    }

    @Test
    @Transactional
    public void updateNonExistingProyecto() throws Exception {
        int databaseSizeBeforeUpdate = proyectoRepository.findAll().size();

        // Create the Proyecto
        ProyectoDTO proyectoDTO = proyectoMapper.toDto(proyecto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProyectoMockMvc.perform(put("/api/proyectos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proyectoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proyecto in the database
        List<Proyecto> proyectoList = proyectoRepository.findAll();
        assertThat(proyectoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProyecto() throws Exception {
        // Initialize the database
        proyectoRepository.saveAndFlush(proyecto);

        int databaseSizeBeforeDelete = proyectoRepository.findAll().size();

        // Delete the proyecto
        restProyectoMockMvc.perform(delete("/api/proyectos/{id}", proyecto.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Proyecto> proyectoList = proyectoRepository.findAll();
        assertThat(proyectoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
