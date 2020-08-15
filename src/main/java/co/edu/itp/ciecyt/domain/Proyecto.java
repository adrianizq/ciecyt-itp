package co.edu.itp.ciecyt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Proyecto.
 */
@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "url")
    private String url;

    @Column(name = "lugar_ejecucion")
    private String lugarEjecucion;

    @Column(name = "duracion")
    private String duracion;

    @Column(name = "fecha_ini")
    private LocalDate fechaIni;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "contrapartida_pesos")
    private Double contrapartidaPesos;

    @Column(name = "contrapartida_especie")
    private Double contrapartidaEspecie;

    @Column(name = "palabras_clave")
    private String palabrasClave;

    @Column(name = "convocatoria")
    private String convocatoria;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "referencias")
    private String referencias;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private LineaInvestigacion proyectoLineaInvestigacion;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private GrupoSemillero proyectoGrupoSemillero;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private Modalidad proyectoModalidad;

    @ManyToOne
    @JsonIgnoreProperties(value = "facultadProyectos", allowSetters = true)
    private Facultad facultad;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private LineaInvestigacion subLineaLineaInvestigacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Proyecto titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public Proyecto url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLugarEjecucion() {
        return lugarEjecucion;
    }

    public Proyecto lugarEjecucion(String lugarEjecucion) {
        this.lugarEjecucion = lugarEjecucion;
        return this;
    }

    public void setLugarEjecucion(String lugarEjecucion) {
        this.lugarEjecucion = lugarEjecucion;
    }

    public String getDuracion() {
        return duracion;
    }

    public Proyecto duracion(String duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public Proyecto fechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
        return this;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Proyecto fechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getContrapartidaPesos() {
        return contrapartidaPesos;
    }

    public Proyecto contrapartidaPesos(Double contrapartidaPesos) {
        this.contrapartidaPesos = contrapartidaPesos;
        return this;
    }

    public void setContrapartidaPesos(Double contrapartidaPesos) {
        this.contrapartidaPesos = contrapartidaPesos;
    }

    public Double getContrapartidaEspecie() {
        return contrapartidaEspecie;
    }

    public Proyecto contrapartidaEspecie(Double contrapartidaEspecie) {
        this.contrapartidaEspecie = contrapartidaEspecie;
        return this;
    }

    public void setContrapartidaEspecie(Double contrapartidaEspecie) {
        this.contrapartidaEspecie = contrapartidaEspecie;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public Proyecto palabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
        return this;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public Proyecto convocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
        return this;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getTipo() {
        return tipo;
    }

    public Proyecto tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReferencias() {
        return referencias;
    }

    public Proyecto referencias(String referencias) {
        this.referencias = referencias;
        return this;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public LineaInvestigacion getProyectoLineaInvestigacion() {
        return proyectoLineaInvestigacion;
    }

    public Proyecto proyectoLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.proyectoLineaInvestigacion = lineaInvestigacion;
        return this;
    }

    public void setProyectoLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.proyectoLineaInvestigacion = lineaInvestigacion;
    }

    public GrupoSemillero getProyectoGrupoSemillero() {
        return proyectoGrupoSemillero;
    }

    public Proyecto proyectoGrupoSemillero(GrupoSemillero grupoSemillero) {
        this.proyectoGrupoSemillero = grupoSemillero;
        return this;
    }

    public void setProyectoGrupoSemillero(GrupoSemillero grupoSemillero) {
        this.proyectoGrupoSemillero = grupoSemillero;
    }

    public Modalidad getProyectoModalidad() {
        return proyectoModalidad;
    }

    public Proyecto proyectoModalidad(Modalidad modalidad) {
        this.proyectoModalidad = modalidad;
        return this;
    }

    public void setProyectoModalidad(Modalidad modalidad) {
        this.proyectoModalidad = modalidad;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public Proyecto facultad(Facultad facultad) {
        this.facultad = facultad;
        return this;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public LineaInvestigacion getSubLineaLineaInvestigacion() {
        return subLineaLineaInvestigacion;
    }

    public Proyecto subLineaLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.subLineaLineaInvestigacion = lineaInvestigacion;
        return this;
    }

    public void setSubLineaLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.subLineaLineaInvestigacion = lineaInvestigacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proyecto)) {
            return false;
        }
        return id != null && id.equals(((Proyecto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proyecto{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", url='" + getUrl() + "'" +
            ", lugarEjecucion='" + getLugarEjecucion() + "'" +
            ", duracion='" + getDuracion() + "'" +
            ", fechaIni='" + getFechaIni() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", contrapartidaPesos=" + getContrapartidaPesos() +
            ", contrapartidaEspecie=" + getContrapartidaEspecie() +
            ", palabrasClave='" + getPalabrasClave() + "'" +
            ", convocatoria='" + getConvocatoria() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", referencias='" + getReferencias() + "'" +
            "}";
    }
}
