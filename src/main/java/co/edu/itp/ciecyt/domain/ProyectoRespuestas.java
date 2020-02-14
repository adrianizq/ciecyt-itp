package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import co.edu.itp.ciecyt.domain.enumeration.EnumRespuestas;

/**
 * A ProyectoRespuestas.
 */
@Entity
@Table(name = "proyecto_respuestas")
public class ProyectoRespuestas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "respuesta")
    private EnumRespuestas respuesta;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "viable")
    private Boolean viable;

    @ManyToOne
    @JsonIgnoreProperties("proyectoRespuestas")
    private Pregunta proyectoRespuestasPregunta;

    @ManyToOne
    @JsonIgnoreProperties("proyectoRespuestas")
    private Proyecto proyectoRespuestasProyecto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumRespuestas getRespuesta() {
        return respuesta;
    }

    public ProyectoRespuestas respuesta(EnumRespuestas respuesta) {
        this.respuesta = respuesta;
        return this;
    }

    public void setRespuesta(EnumRespuestas respuesta) {
        this.respuesta = respuesta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public ProyectoRespuestas observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean isViable() {
        return viable;
    }

    public ProyectoRespuestas viable(Boolean viable) {
        this.viable = viable;
        return this;
    }

    public void setViable(Boolean viable) {
        this.viable = viable;
    }

    public Pregunta getProyectoRespuestasPregunta() {
        return proyectoRespuestasPregunta;
    }

    public ProyectoRespuestas proyectoRespuestasPregunta(Pregunta pregunta) {
        this.proyectoRespuestasPregunta = pregunta;
        return this;
    }

    public void setProyectoRespuestasPregunta(Pregunta pregunta) {
        this.proyectoRespuestasPregunta = pregunta;
    }

    public Proyecto getProyectoRespuestasProyecto() {
        return proyectoRespuestasProyecto;
    }

    public ProyectoRespuestas proyectoRespuestasProyecto(Proyecto proyecto) {
        this.proyectoRespuestasProyecto = proyecto;
        return this;
    }

    public void setProyectoRespuestasProyecto(Proyecto proyecto) {
        this.proyectoRespuestasProyecto = proyecto;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProyectoRespuestas)) {
            return false;
        }
        return id != null && id.equals(((ProyectoRespuestas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProyectoRespuestas{" +
            "id=" + getId() +
            ", respuesta='" + getRespuesta() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", viable='" + isViable() + "'" +
            "}";
    }
}
