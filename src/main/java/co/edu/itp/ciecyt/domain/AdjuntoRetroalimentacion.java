package co.edu.itp.ciecyt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AdjuntoRetroalimentacion.
 */
@Entity
@Table(name = "adjunto_retroalimentacion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdjuntoRetroalimentacion implements Serializable {

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

    @Column(name = "adjunto_retroalimentacion")
    private String adjuntoRetroalimentacion;

    @Column(name = "nombre_archivo_original")
    private String nombreArchivoOriginal;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Lob
    @Column(name = "archivo")
    private byte[] archivo;

    @Column(name = "archivo_content_type")
    private String archivoContentType;

    @ManyToOne
    @JsonIgnoreProperties(value = "adjuntoRetroalimentacions", allowSetters = true)
    private Retroalimentacion adjuntoRetroalimentacionRetroalimentacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public AdjuntoRetroalimentacion nombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
        return this;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public AdjuntoRetroalimentacion fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public AdjuntoRetroalimentacion fechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getEstadoAdjunto() {
        return estadoAdjunto;
    }

    public AdjuntoRetroalimentacion estadoAdjunto(Integer estadoAdjunto) {
        this.estadoAdjunto = estadoAdjunto;
        return this;
    }

    public void setEstadoAdjunto(Integer estadoAdjunto) {
        this.estadoAdjunto = estadoAdjunto;
    }

    public String getAdjuntoRetroalimentacion() {
        return adjuntoRetroalimentacion;
    }

    public AdjuntoRetroalimentacion adjuntoRetroalimentacion(String adjuntoRetroalimentacion) {
        this.adjuntoRetroalimentacion = adjuntoRetroalimentacion;
        return this;
    }

    public void setAdjuntoRetroalimentacion(String adjuntoRetroalimentacion) {
        this.adjuntoRetroalimentacion = adjuntoRetroalimentacion;
    }

    public String getNombreArchivoOriginal() {
        return nombreArchivoOriginal;
    }

    public AdjuntoRetroalimentacion nombreArchivoOriginal(String nombreArchivoOriginal) {
        this.nombreArchivoOriginal = nombreArchivoOriginal;
        return this;
    }

    public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
        this.nombreArchivoOriginal = nombreArchivoOriginal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public AdjuntoRetroalimentacion fechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public AdjuntoRetroalimentacion fechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public AdjuntoRetroalimentacion archivo(byte[] archivo) {
        this.archivo = archivo;
        return this;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getArchivoContentType() {
        return archivoContentType;
    }

    public AdjuntoRetroalimentacion archivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
        return this;
    }

    public void setArchivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
    }

    public Retroalimentacion getAdjuntoRetroalimentacionRetroalimentacion() {
        return adjuntoRetroalimentacionRetroalimentacion;
    }

    public AdjuntoRetroalimentacion adjuntoRetroalimentacionRetroalimentacion(Retroalimentacion retroalimentacion) {
        this.adjuntoRetroalimentacionRetroalimentacion = retroalimentacion;
        return this;
    }

    public void setAdjuntoRetroalimentacionRetroalimentacion(Retroalimentacion retroalimentacion) {
        this.adjuntoRetroalimentacionRetroalimentacion = retroalimentacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjuntoRetroalimentacion)) {
            return false;
        }
        return id != null && id.equals(((AdjuntoRetroalimentacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjuntoRetroalimentacion{" +
            "id=" + getId() +
            ", nombreAdjunto='" + getNombreAdjunto() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", estadoAdjunto=" + getEstadoAdjunto() +
            ", adjuntoRetroalimentacion='" + getAdjuntoRetroalimentacion() + "'" +
            ", nombreArchivoOriginal='" + getNombreArchivoOriginal() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", archivo='" + getArchivo() + "'" +
            ", archivoContentType='" + getArchivoContentType() + "'" +
            ", archivoContentType='" + getArchivoContentType() + "'" +
            "}";
    }
}
