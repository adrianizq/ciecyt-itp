package co.edu.itp.ciecyt.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.AdjuntoProyectoFase} entity.
 */
public class AdjuntoProyectoFaseDTO implements Serializable {

    private Long id;

    private String nombreAdjunto;

    private LocalDate fechaCreacion;

    private LocalDate fechaModificacion;

    private Integer estadoAdjunto;

    private String adjuntoProyectoFase;

    private String nombreArchivoOriginal;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;


    private Long adjuntoProyectoFaseProyectoFaseId;

    private String adjuntoProyectoFaseProyectoFaseTitulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getEstadoAdjunto() {
        return estadoAdjunto;
    }

    public void setEstadoAdjunto(Integer estadoAdjunto) {
        this.estadoAdjunto = estadoAdjunto;
    }

    public String getAdjuntoProyectoFase() {
        return adjuntoProyectoFase;
    }

    public void setAdjuntoProyectoFase(String adjuntoProyectoFase) {
        this.adjuntoProyectoFase = adjuntoProyectoFase;
    }

    public String getNombreArchivoOriginal() {
        return nombreArchivoOriginal;
    }

    public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
        this.nombreArchivoOriginal = nombreArchivoOriginal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getAdjuntoProyectoFaseProyectoFaseId() {
        return adjuntoProyectoFaseProyectoFaseId;
    }

    public void setAdjuntoProyectoFaseProyectoFaseId(Long proyectoFaseId) {
        this.adjuntoProyectoFaseProyectoFaseId = proyectoFaseId;
    }

    public String getAdjuntoProyectoFaseProyectoFaseTitulo() {
        return adjuntoProyectoFaseProyectoFaseTitulo;
    }

    public void setAdjuntoProyectoFaseProyectoFaseTitulo(String proyectoFaseTitulo) {
        this.adjuntoProyectoFaseProyectoFaseTitulo = proyectoFaseTitulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdjuntoProyectoFaseDTO adjuntoProyectoFaseDTO = (AdjuntoProyectoFaseDTO) o;
        if (adjuntoProyectoFaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adjuntoProyectoFaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdjuntoProyectoFaseDTO{" +
            "id=" + getId() +
            ", nombreAdjunto='" + getNombreAdjunto() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", estadoAdjunto=" + getEstadoAdjunto() +
            ", adjuntoProyectoFase='" + getAdjuntoProyectoFase() + "'" +
            ", nombreArchivoOriginal='" + getNombreArchivoOriginal() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", adjuntoProyectoFaseProyectoFase=" + getAdjuntoProyectoFaseProyectoFaseId() +
            ", adjuntoProyectoFaseProyectoFase='" + getAdjuntoProyectoFaseProyectoFaseTitulo() + "'" +
            "}";
    }
}
