package co.edu.itp.ciecyt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Pregunta.
 */
@Entity
@Table(name = "pregunta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    @Column(name = "puntaje")
    private Float puntaje;

    @Column(name = "puntaje_maximo")
    private Float puntajeMaximo;

    @ManyToOne
    @JsonIgnoreProperties(value = "preguntas", allowSetters = true)
    private TipoPregunta preguntaTipoPregunta;

    @ManyToOne
    @JsonIgnoreProperties(value = "preguntas", allowSetters = true)
    private Modalidad preguntaModalidad;

    @ManyToOne
    @JsonIgnoreProperties(value = "preguntas", allowSetters = true)
    private RolesModalidad preguntaRolesModalidad;

    @ManyToOne
    @JsonIgnoreProperties(value = "preguntas", allowSetters = true)
    private Elemento preguntaElemento;

    @ManyToOne
    @JsonIgnoreProperties(value = "preguntas", allowSetters = true)
    private Fases preguntaFase;

    // jhipster-needle-entity-add-field - JHipster will add fields here
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

    public Float getPuntaje() {
        return puntaje;
    }

    public Pregunta puntaje(Float puntaje) {
        this.puntaje = puntaje;
        return this;
    }

    public void setPuntaje(Float puntaje) {
        this.puntaje = puntaje;
    }

    public Float getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public Pregunta puntajeMaximo(Float puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
        return this;
    }

    public void setPuntajeMaximo(Float puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
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

    public Elemento getPreguntaElemento() {
        return preguntaElemento;
    }

    public Pregunta preguntaElemento(Elemento elemento) {
        this.preguntaElemento = elemento;
        return this;
    }

    public void setPreguntaElemento(Elemento elemento) {
        this.preguntaElemento = elemento;
    }

    public Fases getPreguntaFase() {
        return preguntaFase;
    }

    public Pregunta preguntaFase(Fases fases) {
        this.preguntaFase = fases;
        return this;
    }

    public void setPreguntaFase(Fases fases) {
        this.preguntaFase = fases;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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

    // prettier-ignore
    @Override
    public String toString() {
        return "Pregunta{" +
            "id=" + getId() +
            ", encabezado='" + getEncabezado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", pregunta='" + getPregunta() + "'" +
            ", puntaje=" + getPuntaje() +
            ", puntajeMaximo=" + getPuntajeMaximo() +
            "}";
    }
}
