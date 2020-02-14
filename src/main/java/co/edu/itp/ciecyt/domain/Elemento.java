package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Elemento.
 */
@Entity
@Table(name = "elemento")
public class Elemento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "elemento")
    private String elemento;

    @ManyToOne
    @JsonIgnoreProperties("elementos")
    private Formato elementoFormato;

    @ManyToOne
    @JsonIgnoreProperties("elementos")
    private Modalidad elementoModalidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElemento() {
        return elemento;
    }

    public Elemento elemento(String elemento) {
        this.elemento = elemento;
        return this;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Formato getElementoFormato() {
        return elementoFormato;
    }

    public Elemento elementoFormato(Formato formato) {
        this.elementoFormato = formato;
        return this;
    }

    public void setElementoFormato(Formato formato) {
        this.elementoFormato = formato;
    }

    public Modalidad getElementoModalidad() {
        return elementoModalidad;
    }

    public Elemento elementoModalidad(Modalidad modalidad) {
        this.elementoModalidad = modalidad;
        return this;
    }

    public void setElementoModalidad(Modalidad modalidad) {
        this.elementoModalidad = modalidad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Elemento)) {
            return false;
        }
        return id != null && id.equals(((Elemento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Elemento{" +
            "id=" + getId() +
            ", elemento='" + getElemento() + "'" +
            "}";
    }
}
