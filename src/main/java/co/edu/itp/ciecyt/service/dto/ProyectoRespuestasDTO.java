package co.edu.itp.ciecyt.service.dto;

import java.io.Serializable;
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

    public String getProyectoRespuestasPreguntaPregunta() {
        return proyectoRespuestasPreguntaPregunta;
    }

    public void setProyectoRespuestasPreguntaPregunta(String proyectoRespuestasPreguntaPregunta) {
        this.proyectoRespuestasPreguntaPregunta = proyectoRespuestasPreguntaPregunta;
    }

    public Long getProyectoRespuestasPreguntaId() {
        return proyectoRespuestasPreguntaId;
    }

    public void setProyectoRespuestasPreguntaId(Long preguntaId) {
        this.proyectoRespuestasPreguntaId = preguntaId;
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
        if (!(o instanceof ProyectoRespuestasDTO)) {
            return false;
        }

        return id != null && id.equals(((ProyectoRespuestasDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProyectoRespuestasDTO{" +
            "id=" + getId() +
            ", respuesta='" + getRespuesta() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", viable='" + isViable() + "'" +
            ", proyectoRespuestasPreguntaPregunta='" + getProyectoRespuestasPreguntaPregunta() + "'" +
            ", proyectoRespuestasPreguntaId=" + getProyectoRespuestasPreguntaId() +
            ", proyectoRespuestasProyectoId=" + getProyectoRespuestasProyectoId() +
            ", proyectoRespuestasProyectoTitulo='" + getProyectoRespuestasProyectoTitulo() + "'" +
            "}";
    }
}
