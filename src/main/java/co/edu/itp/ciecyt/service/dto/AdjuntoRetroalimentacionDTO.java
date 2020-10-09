package co.edu.itp.ciecyt.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.AdjuntoRetroalimentacion} entity.
 */
public class AdjuntoRetroalimentacionDTO implements Serializable {

    private Long id;

    private String nombreAdjunto;

    private LocalDate fechaCreacion;

    private LocalDate fechaModificacion;

    private Integer estadoAdjunto;

    private String adjuntoRetroalimentacion;

    private String nombreArchivoOriginal;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;


    private Long adjuntoRetroalimentacionRetroalimentacionId;

    private String adjuntoRetroalimentacionRetroalimentacionTitulo;

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

    public String getAdjuntoRetroalimentacion() {
        return adjuntoRetroalimentacion;
    }

    public void setAdjuntoRetroalimentacion(String adjuntoRetroalimentacion) {
        this.adjuntoRetroalimentacion = adjuntoRetroalimentacion;
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

    public Long getAdjuntoRetroalimentacionRetroalimentacionId() {
        return adjuntoRetroalimentacionRetroalimentacionId;
    }

    public void setAdjuntoRetroalimentacionRetroalimentacionId(Long retroalimentacionId) {
        this.adjuntoRetroalimentacionRetroalimentacionId = retroalimentacionId;
    }

    public String getAdjuntoRetroalimentacionRetroalimentacionTitulo() {
        return adjuntoRetroalimentacionRetroalimentacionTitulo;
    }

    public void setAdjuntoRetroalimentacionRetroalimentacionTitulo(String retroalimentacionTitulo) {
        this.adjuntoRetroalimentacionRetroalimentacionTitulo = retroalimentacionTitulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdjuntoRetroalimentacionDTO adjuntoRetroalimentacionDTO = (AdjuntoRetroalimentacionDTO) o;
        if (adjuntoRetroalimentacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adjuntoRetroalimentacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdjuntoRetroalimentacionDTO{" +
            "id=" + getId() +
            ", nombreAdjunto='" + getNombreAdjunto() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", estadoAdjunto=" + getEstadoAdjunto() +
            ", adjuntoRetroalimentacion='" + getAdjuntoRetroalimentacion() + "'" +
            ", nombreArchivoOriginal='" + getNombreArchivoOriginal() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", adjuntoRetroalimentacionRetroalimentacion=" + getAdjuntoRetroalimentacionRetroalimentacionId() +
            ", adjuntoRetroalimentacionRetroalimentacion='" + getAdjuntoRetroalimentacionRetroalimentacionTitulo() + "'" +
            "}";
    }
}
