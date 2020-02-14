package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.ElementoProyecto} entity.
 */
public class ElementoProyectoDTO implements Serializable {

    private Long id;

    private String dato;


    private Long elementoProyectoElementoId;

    private String elementoProyectoElementoElemento;

    private Long elementoProyectoProyectoId;

    private String elementoProyectoProyectoTitulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Long getElementoProyectoElementoId() {
        return elementoProyectoElementoId;
    }

    public void setElementoProyectoElementoId(Long elementoId) {
        this.elementoProyectoElementoId = elementoId;
    }

    public String getElementoProyectoElementoElemento() {
        return elementoProyectoElementoElemento;
    }

    public void setElementoProyectoElementoElemento(String elementoElemento) {
        this.elementoProyectoElementoElemento = elementoElemento;
    }

    public Long getElementoProyectoProyectoId() {
        return elementoProyectoProyectoId;
    }

    public void setElementoProyectoProyectoId(Long proyectoId) {
        this.elementoProyectoProyectoId = proyectoId;
    }

    public String getElementoProyectoProyectoTitulo() {
        return elementoProyectoProyectoTitulo;
    }

    public void setElementoProyectoProyectoTitulo(String proyectoTitulo) {
        this.elementoProyectoProyectoTitulo = proyectoTitulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ElementoProyectoDTO elementoProyectoDTO = (ElementoProyectoDTO) o;
        if (elementoProyectoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), elementoProyectoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ElementoProyectoDTO{" +
            "id=" + getId() +
            ", dato='" + getDato() + "'" +
            ", elementoProyectoElemento=" + getElementoProyectoElementoId() +
            ", elementoProyectoElemento='" + getElementoProyectoElementoElemento() + "'" +
            ", elementoProyectoProyecto=" + getElementoProyectoProyectoId() +
            ", elementoProyectoProyecto='" + getElementoProyectoProyectoTitulo() + "'" +
            "}";
    }
}
