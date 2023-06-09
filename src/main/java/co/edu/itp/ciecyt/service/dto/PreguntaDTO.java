package co.edu.itp.ciecyt.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Pregunta} entity.
 */
public class PreguntaDTO implements Serializable {
    
    private Long id;

    private String encabezado;

    private String descripcion;

    private String elemento;

    private Long elementoId;

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

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Long getElementoId() {
        return elementoId;
    }

    public void setElementoId(Long elementoId) {
        this.elementoId = elementoId;
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
        if (!(o instanceof PreguntaDTO)) {
            return false;
        }

        return id != null && id.equals(((PreguntaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreguntaDTO{" +
            "id=" + getId() +
            ", encabezado='" + getEncabezado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", elemento='" + getElemento() + "'" +
            ", elementoId=" + getElementoId() +
            ", pregunta='" + getPregunta() + "'" +
            ", preguntaTipoPreguntaId=" + getPreguntaTipoPreguntaId() +
            ", preguntaTipoPreguntaTipoPregunta='" + getPreguntaTipoPreguntaTipoPregunta() + "'" +
            ", preguntaModalidadId=" + getPreguntaModalidadId() +
            ", preguntaModalidadModalidad='" + getPreguntaModalidadModalidad() + "'" +
            ", preguntaRolesModalidadId=" + getPreguntaRolesModalidadId() +
            ", preguntaRolesModalidadRol='" + getPreguntaRolesModalidadRol() + "'" +
            "}";
    }
}
