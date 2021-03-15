package co.edu.itp.ciecyt.service.dto;

import co.edu.itp.ciecyt.domain.Elemento;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Elemento} entity.
 */
public class ElementoDTO implements Serializable {

    private Long id;

    private String elemento;

    private String descripcion;


    private Long elementoFormatoId;

    private String elementoFormatoFormato;

    private Long elementoFasesId;

    private String elementoFasesFase;

    private Long modalidadId;

    public Long getModalidadId() {
        return modalidadId;
    }

    public void setModalidadId(Long modalidadId) {
        this.modalidadId = modalidadId;
    }

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

    public Long getElementoFasesId() {
        return elementoFasesId;
    }

    public void setElementoFasesId(Long fasesId) {
        this.elementoFasesId = fasesId;
    }

    public String getElementoFasesFase() {
        return elementoFasesFase;
    }

    public void setElementoFasesFase(String fasesFase) {
        this.elementoFasesFase = fasesFase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElementoDTO)) {
            return false;
        }

        return id != null && id.equals(((ElementoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElementoDTO{" +
            "id=" + getId() +
            ", elemento='" + getElemento() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", elementoFormatoId=" + getElementoFormatoId() +
            ", elementoFormatoFormato='" + getElementoFormatoFormato() + "'" +
            ", elementoFasesId=" + getElementoFasesId() +
            ", elementoFasesFase='" + getElementoFasesFase() + "'" +
            ", modalidadId=" + getModalidadId() +
            "}";
    }
}
