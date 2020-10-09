package co.edu.itp.ciecyt.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Elemento} entity.
 */
public class ElementoDTO implements Serializable {

    private Long id;

    private String elemento;

    private String descripcion;


    private Long elementoFormatoId;

    private String elementoFormatoFormato;

    private Long elementoModalidadId;

    private String elementoModalidadModalidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getElementoFormatoId() {
        return elementoFormatoId;
    }

    public void setElementoFormatoId(Long formatoId) {
        this.elementoFormatoId = formatoId;
    }

    public String getElementoFormatoFormato() {
        return elementoFormatoFormato;
    }

    public void setElementoFormatoFormato(String formatoFormato) {
        this.elementoFormatoFormato = formatoFormato;
    }

    public Long getElementoModalidadId() {
        return elementoModalidadId;
    }

    public void setElementoModalidadId(Long modalidadId) {
        this.elementoModalidadId = modalidadId;
    }

    public String getElementoModalidadModalidad() {
        return elementoModalidadModalidad;
    }

    public void setElementoModalidadModalidad(String modalidadModalidad) {
        this.elementoModalidadModalidad = modalidadModalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ElementoDTO elementoDTO = (ElementoDTO) o;
        if (elementoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), elementoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ElementoDTO{" +
            "id=" + getId() +
            ", elemento='" + getElemento() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", elementoFormato=" + getElementoFormatoId() +
            ", elementoFormato='" + getElementoFormatoFormato() + "'" +
            ", elementoModalidad=" + getElementoModalidadId() +
            ", elementoModalidad='" + getElementoModalidadModalidad() + "'" +
            "}";
    }
}
