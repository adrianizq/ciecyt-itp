package co.edu.itp.ciecyt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PreguntaAuthority.
 */
@Entity
@Table(name = "pregunta_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PreguntaAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "preguntaAuthorities", allowSetters = true)
    private Pregunta pregunta3;

    @ManyToOne
    @JsonIgnoreProperties(value = "preguntaAuthorities", allowSetters = true)
    private Authority authority3;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pregunta getPregunta3() {
        return pregunta3;
    }

    public PreguntaAuthority pregunta3(Pregunta pregunta) {
        this.pregunta3 = pregunta;
        return this;
    }

    public void setPregunta3(Pregunta pregunta) {
        this.pregunta3 = pregunta;
    }

    public Authority getAuthority3() {
        return authority3;
    }

    public PreguntaAuthority authority3(Authority authority) {
        this.authority3 = authority;
        return this;
    }

    public void setAuthority3(Authority authority) {
        this.authority3 = authority;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreguntaAuthority)) {
            return false;
        }
        return id != null && id.equals(((PreguntaAuthority) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreguntaAuthority{" +
            "id=" + getId() +
            "}";
    }
}
