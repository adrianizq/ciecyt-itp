package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Fases.
 */
@Entity
@Table(name = "fases")
public class Fases implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fase")
    private String fase;

    @Column(name = "notificable")
    private Boolean notificable;

    @ManyToOne
    @JsonIgnoreProperties("fases")
    private Modalidad fasesModalidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFase() {
        return fase;
    }

    public Fases fase(String fase) {
        this.fase = fase;
        return this;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Boolean isNotificable() {
        return notificable;
    }

    public Fases notificable(Boolean notificable) {
        this.notificable = notificable;
        return this;
    }

    public void setNotificable(Boolean notificable) {
        this.notificable = notificable;
    }

    public Modalidad getFasesModalidad() {
        return fasesModalidad;
    }

    public Fases fasesModalidad(Modalidad modalidad) {
        this.fasesModalidad = modalidad;
        return this;
    }

    public void setFasesModalidad(Modalidad modalidad) {
        this.fasesModalidad = modalidad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fases)) {
            return false;
        }
        return id != null && id.equals(((Fases) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Fases{" +
            "id=" + getId() +
            ", fase='" + getFase() + "'" +
            ", notificable='" + isNotificable() + "'" +
            "}";
    }
}
