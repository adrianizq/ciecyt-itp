package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ImpactosEsperados.
 */
@Entity
@Table(name = "impactos_esperados")
public class ImpactosEsperados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "impacto")
    private String impacto;

    @Column(name = "plazo")
    private Integer plazo;

    @Column(name = "indicador")
    private String indicador;

    @Column(name = "supuestos")
    private String supuestos;

    @Column(name = "orden_vista")
    private Integer ordenVista;

    @ManyToOne
    @JsonIgnoreProperties("impactosEsperados")
    private Proyecto impactosEsperadoProyecto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImpacto() {
        return impacto;
    }

    public ImpactosEsperados impacto(String impacto) {
        this.impacto = impacto;
        return this;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public ImpactosEsperados plazo(Integer plazo) {
        this.plazo = plazo;
        return this;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public String getIndicador() {
        return indicador;
    }

    public ImpactosEsperados indicador(String indicador) {
        this.indicador = indicador;
        return this;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getSupuestos() {
        return supuestos;
    }

    public ImpactosEsperados supuestos(String supuestos) {
        this.supuestos = supuestos;
        return this;
    }

    public void setSupuestos(String supuestos) {
        this.supuestos = supuestos;
    }

    public Integer getOrdenVista() {
        return ordenVista;
    }

    public ImpactosEsperados ordenVista(Integer ordenVista) {
        this.ordenVista = ordenVista;
        return this;
    }

    public void setOrdenVista(Integer ordenVista) {
        this.ordenVista = ordenVista;
    }

    public Proyecto getImpactosEsperadoProyecto() {
        return impactosEsperadoProyecto;
    }

    public ImpactosEsperados impactosEsperadoProyecto(Proyecto proyecto) {
        this.impactosEsperadoProyecto = proyecto;
        return this;
    }

    public void setImpactosEsperadoProyecto(Proyecto proyecto) {
        this.impactosEsperadoProyecto = proyecto;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImpactosEsperados)) {
            return false;
        }
        return id != null && id.equals(((ImpactosEsperados) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ImpactosEsperados{" +
            "id=" + getId() +
            ", impacto='" + getImpacto() + "'" +
            ", plazo=" + getPlazo() +
            ", indicador='" + getIndicador() + "'" +
            ", supuestos='" + getSupuestos() + "'" +
            ", ordenVista=" + getOrdenVista() +
            "}";
    }
}
