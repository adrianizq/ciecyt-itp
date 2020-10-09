package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AdjuntoProyectoFase.
 */
@Entity
@Table(name = "adjunto_proyecto_fase")
public class AdjuntoProyectoFase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre_adjunto")
    private String nombreAdjunto;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @Column(name = "estado_adjunto")
    private Integer estadoAdjunto;

    @Column(name = "adjunto_proyecto_fase")
    private String adjuntoProyectoFase;

    @Column(name = "nombre_archivo_original")
    private String nombreArchivoOriginal;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @ManyToOne
    @JsonIgnoreProperties("adjuntoProyectoFases")
    private ProyectoFase adjuntoProyectoFaseProyectoFase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public AdjuntoProyectoFase nombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
        return this;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public AdjuntoProyectoFase fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public AdjuntoProyectoFase fechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getEstadoAdjunto() {
        return estadoAdjunto;
    }

    public AdjuntoProyectoFase estadoAdjunto(Integer estadoAdjunto) {
        this.estadoAdjunto = estadoAdjunto;
        return this;
    }

    public void setEstadoAdjunto(Integer estadoAdjunto) {
        this.estadoAdjunto = estadoAdjunto;
    }

    public String getAdjuntoProyectoFase() {
        return adjuntoProyectoFase;
    }

    public AdjuntoProyectoFase adjuntoProyectoFase(String adjuntoProyectoFase) {
        this.adjuntoProyectoFase = adjuntoProyectoFase;
        return this;
    }

    public void setAdjuntoProyectoFase(String adjuntoProyectoFase) {
        this.adjuntoProyectoFase = adjuntoProyectoFase;
    }

    public String getNombreArchivoOriginal() {
        return nombreArchivoOriginal;
    }

    public AdjuntoProyectoFase nombreArchivoOriginal(String nombreArchivoOriginal) {
        this.nombreArchivoOriginal = nombreArchivoOriginal;
        return this;
    }

    public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
        this.nombreArchivoOriginal = nombreArchivoOriginal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public AdjuntoProyectoFase fechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public AdjuntoProyectoFase fechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ProyectoFase getAdjuntoProyectoFaseProyectoFase() {
        return adjuntoProyectoFaseProyectoFase;
    }

    public AdjuntoProyectoFase adjuntoProyectoFaseProyectoFase(ProyectoFase proyectoFase) {
        this.adjuntoProyectoFaseProyectoFase = proyectoFase;
        return this;
    }

    public void setAdjuntoProyectoFaseProyectoFase(ProyectoFase proyectoFase) {
        this.adjuntoProyectoFaseProyectoFase = proyectoFase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjuntoProyectoFase)) {
            return false;
        }
        return id != null && id.equals(((AdjuntoProyectoFase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdjuntoProyectoFase{" +
            "id=" + getId() +
            ", nombreAdjunto='" + getNombreAdjunto() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", estadoAdjunto=" + getEstadoAdjunto() +
            ", adjuntoProyectoFase='" + getAdjuntoProyectoFase() + "'" +
            ", nombreArchivoOriginal='" + getNombreArchivoOriginal() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            "}";
    }
}
