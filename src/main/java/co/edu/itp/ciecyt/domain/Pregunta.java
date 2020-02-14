package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Pregunta.
 */
@Entity
@Table(name = "pregunta")
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "encabezado")
    private String encabezado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "pregunta")
    private String pregunta;

    @ManyToOne
    @JsonIgnoreProperties("preguntas")
    private TipoPregunta preguntaTipoPregunta;

    @ManyToOne
    @JsonIgnoreProperties("preguntas")
    private Modalidad preguntaModalidad;

    @ManyToOne
    @JsonIgnoreProperties("preguntas")
    private RolesModalidad preguntaRolesModalidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public Pregunta encabezado(String encabezado) {
        this.encabezado = encabezado;
        return this;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Pregunta descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPregunta() {
        return pregunta;
    }

    public Pregunta pregunta(String pregunta) {
        this.pregunta = pregunta;
        return this;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public TipoPregunta getPreguntaTipoPregunta() {
        return preguntaTipoPregunta;
    }

    public Pregunta preguntaTipoPregunta(TipoPregunta tipoPregunta) {
        this.preguntaTipoPregunta = tipoPregunta;
        return this;
    }

    public void setPreguntaTipoPregunta(TipoPregunta tipoPregunta) {
        this.preguntaTipoPregunta = tipoPregunta;
    }

    public Modalidad getPreguntaModalidad() {
        return preguntaModalidad;
    }

    public Pregunta preguntaModalidad(Modalidad modalidad) {
        this.preguntaModalidad = modalidad;
        return this;
    }

    public void setPreguntaModalidad(Modalidad modalidad) {
        this.preguntaModalidad = modalidad;
    }

    public RolesModalidad getPreguntaRolesModalidad() {
        return preguntaRolesModalidad;
    }

    public Pregunta preguntaRolesModalidad(RolesModalidad rolesModalidad) {
        this.preguntaRolesModalidad = rolesModalidad;
        return this;
    }

    public void setPreguntaRolesModalidad(RolesModalidad rolesModalidad) {
        this.preguntaRolesModalidad = rolesModalidad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pregunta)) {
            return false;
        }
        return id != null && id.equals(((Pregunta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
            "id=" + getId() +
            ", encabezado='" + getEncabezado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", pregunta='" + getPregunta() + "'" +
            "}";
    }
}
