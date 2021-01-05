package co.edu.itp.ciecyt.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Pregunta} entity.
 */
public class PreguntaDTO implements Serializable {

    private Long id;

    private String encabezado;

    private String descripcion;

    private String pregunta;

    private Float puntaje;

    private Float puntajeMaximo;

    private Long preguntaTipoPreguntaId;

    private String preguntaTipoPreguntaTipoPregunta;

    private Long preguntaModalidadId;

    private String preguntaModalidadModalidad;

    private Long preguntaRolesModalidadId;

    private String preguntaRolesModalidadRol;

    private Long preguntaElementoId;

    private String preguntaElementoElemento;

    private Long preguntaFaseId;

    private String preguntaFaseFase;

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

    public Float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Float puntaje) {
        this.puntaje = puntaje;
    }

    public Float getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(Float puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
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

    public Long getPreguntaElementoId() {
        return preguntaElementoId;
    }

    public void setPreguntaElementoId(Long elementoId) {
        this.preguntaElementoId = elementoId;
    }

    public String getPreguntaElementoElemento() {
        return preguntaElementoElemento;
    }

    public void setPreguntaElementoElemento(String elementoElemento) {
        this.preguntaElementoElemento = elementoElemento;
    }

    public Long getPreguntaFaseId() {
        return preguntaFaseId;
    }

    public void setPreguntaFaseId(Long fasesId) {
        this.preguntaFaseId = fasesId;
    }

    public String getPreguntaFaseFase() {
        return preguntaFaseFase;
    }

    public void setPreguntaFaseFase(String fasesFase) {
        this.preguntaFaseFase = fasesFase;
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
            ", pregunta='" + getPregunta() + "'" +
            ", puntaje=" + getPuntaje() +
            ", puntajeMaximo=" + getPuntajeMaximo() +
            ", preguntaTipoPreguntaId=" + getPreguntaTipoPreguntaId() +
            ", preguntaTipoPreguntaTipoPregunta='" + getPreguntaTipoPreguntaTipoPregunta() + "'" +
            ", preguntaModalidadId=" + getPreguntaModalidadId() +
            ", preguntaModalidadModalidad='" + getPreguntaModalidadModalidad() + "'" +
            ", preguntaRolesModalidadId=" + getPreguntaRolesModalidadId() +
            ", preguntaRolesModalidadRol='" + getPreguntaRolesModalidadRol() + "'" +
            ", preguntaElementoId=" + getPreguntaElementoId() +
            ", preguntaElementoElemento='" + getPreguntaElementoElemento() + "'" +
            ", preguntaFaseId=" + getPreguntaFaseId() +
            ", preguntaFaseFase='" + getPreguntaFaseFase() + "'" +
            "}";
    }
}
