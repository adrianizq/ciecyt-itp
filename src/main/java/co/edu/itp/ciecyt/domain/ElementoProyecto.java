package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ElementoProyecto.
 */
@Entity
@Table(name = "elemento_proyecto")
public class ElementoProyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dato")
    private String dato;

    @ManyToOne
    @JsonIgnoreProperties("elementoProyectos")
    private Elemento elementoProyectoElemento;

    @ManyToOne
    @JsonIgnoreProperties("elementoProyectos")
    private Proyecto elementoProyectoProyecto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDato() {
        return dato;
    }

    public ElementoProyecto dato(String dato) {
        this.dato = dato;
        return this;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Elemento getElementoProyectoElemento() {
        return elementoProyectoElemento;
    }

    public ElementoProyecto elementoProyectoElemento(Elemento elemento) {
        this.elementoProyectoElemento = elemento;
        return this;
    }

    public void setElementoProyectoElemento(Elemento elemento) {
        this.elementoProyectoElemento = elemento;
    }

    public Proyecto getElementoProyectoProyecto() {
        return elementoProyectoProyecto;
    }

    public ElementoProyecto elementoProyectoProyecto(Proyecto proyecto) {
        this.elementoProyectoProyecto = proyecto;
        return this;
    }

    public void setElementoProyectoProyecto(Proyecto proyecto) {
        this.elementoProyectoProyecto = proyecto;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElementoProyecto)) {
            return false;
        }
        return id != null && id.equals(((ElementoProyecto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ElementoProyecto{" +
            "id=" + getId() +
            ", dato='" + getDato() + "'" +
            "}";
    }
}
