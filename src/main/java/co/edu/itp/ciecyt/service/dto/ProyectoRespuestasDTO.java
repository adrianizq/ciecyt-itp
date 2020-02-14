package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;
import co.edu.itp.ciecyt.domain.enumeration.EnumRespuestas;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.ProyectoRespuestas} entity.
 */
public class ProyectoRespuestasDTO implements Serializable {

    private Long id;

    private EnumRespuestas respuesta;

    private String observaciones;

    private Boolean viable;


    private Long proyectoRespuestasPreguntaId;

    private String proyectoRespuestasPreguntaPregunta;

    private Long proyectoRespuestasProyectoId;

    private String proyectoRespuestasProyectoTitulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumRespuestas getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(EnumRespuestas respuesta) {
        this.respuesta = respuesta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean isViable() {
        return viable;
    }

    public void setViable(Boolean viable) {
        this.viable = viable;
    }

    public Long getProyectoRespuestasPreguntaId() {
        return proyectoRespuestasPreguntaId;
    }

    public void setProyectoRespuestasPreguntaId(Long preguntaId) {
        this.proyectoRespuestasPreguntaId = preguntaId;
    }

    public String getProyectoRespuestasPreguntaPregunta() {
        return proyectoRespuestasPreguntaPregunta;
    }

    public void setProyectoRespuestasPreguntaPregunta(String preguntaPregunta) {
        this.proyectoRespuestasPreguntaPregunta = preguntaPregunta;
    }

    public Long getProyectoRespuestasProyectoId() {
        return proyectoRespuestasProyectoId;
    }

    public void setProyectoRespuestasProyectoId(Long proyectoId) {
        this.proyectoRespuestasProyectoId = proyectoId;
    }

    public String getProyectoRespuestasProyectoTitulo() {
        return proyectoRespuestasProyectoTitulo;
    }

    public void setProyectoRespuestasProyectoTitulo(String proyectoTitulo) {
        this.proyectoRespuestasProyectoTitulo = proyectoTitulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProyectoRespuestasDTO proyectoRespuestasDTO = (ProyectoRespuestasDTO) o;
        if (proyectoRespuestasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proyectoRespuestasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProyectoRespuestasDTO{" +
            "id=" + getId() +
            ", respuesta='" + getRespuesta() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", viable='" + isViable() + "'" +
            ", proyectoRespuestasPregunta=" + getProyectoRespuestasPreguntaId() +
            ", proyectoRespuestasPregunta='" + getProyectoRespuestasPreguntaPregunta() + "'" +
            ", proyectoRespuestasProyecto=" + getProyectoRespuestasProyectoId() +
            ", proyectoRespuestasProyecto='" + getProyectoRespuestasProyectoTitulo() + "'" +
            "}";
    }
}
