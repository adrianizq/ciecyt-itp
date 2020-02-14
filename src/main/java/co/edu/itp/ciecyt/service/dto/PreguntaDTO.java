package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Pregunta} entity.
 */
public class PreguntaDTO implements Serializable {

    private Long id;

    private String encabezado;

    private String descripcion;

    private String pregunta;


    private Long preguntaTipoPreguntaId;

    private String preguntaTipoPreguntaTipoPregunta;

    private Long preguntaModalidadId;

    private String preguntaModalidadModalidad;

    private Long preguntaRolesModalidadId;

    private String preguntaRolesModalidadRol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Long getPreguntaTipoPreguntaId() {
        return preguntaTipoPreguntaId;
    }

    public void setPreguntaTipoPreguntaId(Long tipoPreguntaId) {
        this.preguntaTipoPreguntaId = tipoPreguntaId;
    }

    public String getPreguntaTipoPreguntaTipoPregunta() {
        return preguntaTipoPreguntaTipoPregunta;
    }

    public void setPreguntaTipoPreguntaTipoPregunta(String tipoPreguntaTipoPregunta) {
        this.preguntaTipoPreguntaTipoPregunta = tipoPreguntaTipoPregunta;
    }

    public Long getPreguntaModalidadId() {
        return preguntaModalidadId;
    }

    public void setPreguntaModalidadId(Long modalidadId) {
        this.preguntaModalidadId = modalidadId;
    }

    public String getPreguntaModalidadModalidad() {
        return preguntaModalidadModalidad;
    }

    public void setPreguntaModalidadModalidad(String modalidadModalidad) {
        this.preguntaModalidadModalidad = modalidadModalidad;
    }

    public Long getPreguntaRolesModalidadId() {
        return preguntaRolesModalidadId;
    }

    public void setPreguntaRolesModalidadId(Long rolesModalidadId) {
        this.preguntaRolesModalidadId = rolesModalidadId;
    }

    public String getPreguntaRolesModalidadRol() {
        return preguntaRolesModalidadRol;
    }

    public void setPreguntaRolesModalidadRol(String rolesModalidadRol) {
        this.preguntaRolesModalidadRol = rolesModalidadRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PreguntaDTO preguntaDTO = (PreguntaDTO) o;
        if (preguntaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), preguntaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PreguntaDTO{" +
            "id=" + getId() +
            ", encabezado='" + getEncabezado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", pregunta='" + getPregunta() + "'" +
            ", preguntaTipoPregunta=" + getPreguntaTipoPreguntaId() +
            ", preguntaTipoPregunta='" + getPreguntaTipoPreguntaTipoPregunta() + "'" +
            ", preguntaModalidad=" + getPreguntaModalidadId() +
            ", preguntaModalidad='" + getPreguntaModalidadModalidad() + "'" +
            ", preguntaRolesModalidad=" + getPreguntaRolesModalidadId() +
            ", preguntaRolesModalidad='" + getPreguntaRolesModalidadRol() + "'" +
            "}";
    }
}
