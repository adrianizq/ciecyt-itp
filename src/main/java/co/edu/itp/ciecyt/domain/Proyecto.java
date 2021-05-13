package co.edu.itp.ciecyt.domain;

import co.edu.itp.ciecyt.domain.enumeration.EnumViabilidad;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Proyecto.
 */
@Entity
@Table(name = "proyecto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "url")
    private String url;

    @Column(name = "lugar_ejecucion")
    private String lugarEjecucion;

    @Column(name = "duracion")
    private String duracion;

    @Column(name = "fecha_ini")
    private LocalDate fechaIni;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "contrapartida_pesos")
    private Double contrapartidaPesos;

    @Column(name = "contrapartida_especie")
    private Double contrapartidaEspecie;

    @Column(name = "palabras_clave")
    private String palabrasClave;

    @Column(name = "convocatoria")
    private String convocatoria;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "referencias")
    private String referencias;

    @Column(name = "programa")
    private String programa;

    @Column(name = "fase")
    private String fase;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "municipio")
    private String municipio;

    @Enumerated(EnumType.STRING)
    @Column(name = "viabilidad")
    private EnumViabilidad viabilidad;

    @Column(name = "viable")
    private Boolean viable;

    @Column(name = "enviado")
    private Boolean enviado;

    @Column(name = "pre_enviado")
    private Boolean preEnviado;



    @Column(name = "sustentar")
    private Boolean sustentar;

    @Column(name = "fecha_envio_propuesta")
    private ZonedDateTime fechaEnvioPropuesta;

    @Column(name = "fecha_envio_proyecto")
    private ZonedDateTime fechaEnvioProyecto;


    @Column(name = "fecha_pre_envio_propuesta")
    private ZonedDateTime fechaPreEnvioPropuesta;

    @Column(name = "fecha_viabilidad_propuesta")
    private ZonedDateTime fechaViabilidadPropuesta;

    @Column(name = "fecha_sustentacion_proyecto")
    private ZonedDateTime fechaSustentacionProyecto;

    @Column(name = "nota")
    private Float nota;

    @Column(name = "conclusion")
    private String conclusion;

    @Column(name = "recomendaciones")
    private String recomendaciones;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private LineaInvestigacion proyectoLineaInvestigacion;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private GrupoSemillero proyectoGrupoSemillero;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private Modalidad proyectoModalidad;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private LineaInvestigacion subLineaLineaInvestigacion;

    @ManyToOne
    @JsonIgnoreProperties(value = "facultadProyectos", allowSetters = true)
    private Facultad facultad;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private Programa proyectoPrograma;

    @ManyToOne
    @JsonIgnoreProperties(value = "proyectos", allowSetters = true)
    private Fases proyectoFase;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Proyecto titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public Proyecto url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLugarEjecucion() {
        return lugarEjecucion;
    }

    public Proyecto lugarEjecucion(String lugarEjecucion) {
        this.lugarEjecucion = lugarEjecucion;
        return this;
    }

    public void setLugarEjecucion(String lugarEjecucion) {
        this.lugarEjecucion = lugarEjecucion;
    }

    public String getDuracion() {
        return duracion;
    }

    public Proyecto duracion(String duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public Proyecto fechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
        return this;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Proyecto fechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getContrapartidaPesos() {
        return contrapartidaPesos;
    }

    public Proyecto contrapartidaPesos(Double contrapartidaPesos) {
        this.contrapartidaPesos = contrapartidaPesos;
        return this;
    }

    public void setContrapartidaPesos(Double contrapartidaPesos) {
        this.contrapartidaPesos = contrapartidaPesos;
    }

    public Double getContrapartidaEspecie() {
        return contrapartidaEspecie;
    }

    public Proyecto contrapartidaEspecie(Double contrapartidaEspecie) {
        this.contrapartidaEspecie = contrapartidaEspecie;
        return this;
    }

    public void setContrapartidaEspecie(Double contrapartidaEspecie) {
        this.contrapartidaEspecie = contrapartidaEspecie;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public Proyecto palabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
        return this;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public Proyecto convocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
        return this;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getTipo() {
        return tipo;
    }

    public Proyecto tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReferencias() {
        return referencias;
    }

    public Proyecto referencias(String referencias) {
        this.referencias = referencias;
        return this;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getPrograma() {
        return programa;
    }

    public Proyecto programa(String programa) {
        this.programa = programa;
        return this;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }


    public String getFase() {
        return fase;
    }

    public Proyecto fase(String fase) {
        this.fase = fase;
        return this;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Proyecto departamento(String departamento) {
        this.departamento = departamento;
        return this;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Proyecto municipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    ///////////////////////77
    public EnumViabilidad getViabilidad() {
        return viabilidad;
    }

    public Proyecto viabilidad(EnumViabilidad viabilidad) {
        this.viabilidad = viabilidad;
        return this;
    }

    public void setViabilidad(EnumViabilidad viabilidad) {
        this.viabilidad = viabilidad;
    }

    ///////////////////////77

    public Boolean isViable() {
        return viable;
    }

    public Proyecto viable(Boolean viable) {
        this.viable = viable;
        return this;
    }

    public void setViable(Boolean viable) {
        this.viable = viable;
    }

    public Boolean isEnviado() {
        return enviado;
    }

    public Proyecto enviado(Boolean enviado) {
        this.enviado = enviado;
        return this;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public Boolean isPreEnviado() {
        return preEnviado;
    }

    public Proyecto preEnviado(Boolean preEnviado) {
        this.preEnviado = preEnviado;
        return this;
    }

    public void setPreEnviado(Boolean preEnviado) {
        this.preEnviado = preEnviado;
    }

    public void setSustentar(Boolean sustentar) {
        this.sustentar = sustentar;
    }

    public Proyecto sustentar(Boolean sustentar) {
        this.sustentar = sustentar;
        return this;
    }

    public Boolean isSustentar() {
        return sustentar;
    }





    public ZonedDateTime getFechaEnvioPropuesta() {
        return fechaEnvioPropuesta;
    }

    public Proyecto fechaEnvioPropuesta(ZonedDateTime fechaEnvioPropuesta) {
        this.fechaEnvioPropuesta = fechaEnvioPropuesta;
        return this;
    }

    public void setFechaEnvioPropuesta(ZonedDateTime fechaEnvioPropuesta) {
        this.fechaEnvioPropuesta = fechaEnvioPropuesta;
    }

    public ZonedDateTime getFechaEnvioProyecto() {
        return fechaEnvioProyecto;
    }

    public Proyecto fechaEnvioProyecto(ZonedDateTime fechaEnvioProyecto) {
        this.fechaEnvioProyecto = fechaEnvioProyecto;
        return this;
    }

    public void setFechaEnvioProyecto(ZonedDateTime fechaEnvioProyecto) {
        this.fechaEnvioProyecto = fechaEnvioProyecto;
    }

    public ZonedDateTime getFechaPreEnvioPropuesta() {
        return fechaPreEnvioPropuesta;
    }

    public Proyecto fechaPreEnvioPropuesta(ZonedDateTime fechaPreEnvioPropuesta) {
        this.fechaPreEnvioPropuesta = fechaPreEnvioPropuesta;
        return this;
    }

    public void setFechaPreEnvioPropuesta(ZonedDateTime fechaPreEnvioPropuesta) {
        this.fechaPreEnvioPropuesta = fechaPreEnvioPropuesta;
    }

    public ZonedDateTime getFechaViabilidadPropuesta() {
        return fechaViabilidadPropuesta;
    }

    public Proyecto fechaViabilidadPropuesta(ZonedDateTime fechaViabilidadPropuesta) {
        this.fechaViabilidadPropuesta = fechaViabilidadPropuesta;
        return this;
    }

    public void setFechaViabilidadPropuesta(ZonedDateTime fechaViabilidadPropuesta) {
        this.fechaViabilidadPropuesta = fechaViabilidadPropuesta;
    }

    public ZonedDateTime getFechaSustentacionProyecto() {
        return fechaSustentacionProyecto;
    }

    public Proyecto fechaSustentacionProyecto(ZonedDateTime fechaSustentacionProyecto) {
        this.fechaSustentacionProyecto = fechaSustentacionProyecto;
        return this;
    }

    public void setFechaSustentacionProyecto(ZonedDateTime fechaSustentacionProyecto) {
        this.fechaSustentacionProyecto = fechaSustentacionProyecto;
    }


    public Float getNota() {
        return nota;
    }

    public Proyecto nota(Float nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getConclusion() {
        return conclusion;
    }

    public Proyecto conclusion(String conclusion) {
        this.conclusion = conclusion;
        return this;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public Proyecto recomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
        return this;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public LineaInvestigacion getProyectoLineaInvestigacion() {
        return proyectoLineaInvestigacion;
    }

    public Proyecto proyectoLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.proyectoLineaInvestigacion = lineaInvestigacion;
        return this;
    }

    public void setProyectoLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.proyectoLineaInvestigacion = lineaInvestigacion;
    }

    public GrupoSemillero getProyectoGrupoSemillero() {
        return proyectoGrupoSemillero;
    }

    public Proyecto proyectoGrupoSemillero(GrupoSemillero grupoSemillero) {
        this.proyectoGrupoSemillero = grupoSemillero;
        return this;
    }

    public void setProyectoGrupoSemillero(GrupoSemillero grupoSemillero) {
        this.proyectoGrupoSemillero = grupoSemillero;
    }

    public Modalidad getProyectoModalidad() {
        return proyectoModalidad;
    }

    public Proyecto proyectoModalidad(Modalidad modalidad) {
        this.proyectoModalidad = modalidad;
        return this;
    }

    public void setProyectoModalidad(Modalidad modalidad) {
        this.proyectoModalidad = modalidad;
    }

    public LineaInvestigacion getSubLineaLineaInvestigacion() {
        return subLineaLineaInvestigacion;
    }

    public Proyecto subLineaLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.subLineaLineaInvestigacion = lineaInvestigacion;
        return this;
    }

    public void setSubLineaLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.subLineaLineaInvestigacion = lineaInvestigacion;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public Proyecto facultad(Facultad facultad) {
        this.facultad = facultad;
        return this;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Programa getProyectoPrograma() {
        return proyectoPrograma;
    }

    public Proyecto proyectoPrograma(Programa programa) {
        this.proyectoPrograma = programa;
        return this;
    }

    public void setProyectoPrograma(Programa programa) {
        this.proyectoPrograma = programa;
    }



    public Fases getProyectoFase() {
        return proyectoFase;
    }

    public Proyecto proyectoFase(Fases fase) {
        this.proyectoFase = fase;
        return this;
    }

    public void setProyectoFase(Fases fase) {
        this.proyectoFase = fase;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proyecto)) {
            return false;
        }
        return id != null && id.equals(((Proyecto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proyecto{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", url='" + getUrl() + "'" +
            ", lugarEjecucion='" + getLugarEjecucion() + "'" +
            ", duracion='" + getDuracion() + "'" +
            ", fechaIni='" + getFechaIni() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", contrapartidaPesos=" + getContrapartidaPesos() +
            ", contrapartidaEspecie=" + getContrapartidaEspecie() +
            ", palabrasClave='" + getPalabrasClave() + "'" +
            ", convocatoria='" + getConvocatoria() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", referencias='" + getReferencias() + "'" +
            ", programa='" + getPrograma() + "'" +
            ", departamento='" + getDepartamento() + "'" +
            ", municipio='" + getMunicipio() + "'" +
            ", viabilidad='" + getViabilidad() + "'" +
            ", viable='" + isViable() + "'" +
            ", enviado='" + isEnviado() + "'" +
            ", preEnviado='" + isPreEnviado() + "'" +
            ", sustentar='" + isSustentar() + "'" +
            ", fechaEnvioPropuesta='" + getFechaEnvioPropuesta() + "'" +
            ", fechaEnvioProyecto='" + getFechaEnvioProyecto() + "'" +
            ", fechaPreEnvioPropuesta='" + getFechaPreEnvioPropuesta() + "'" +
            ", fechaViabilidadPropuesta='" + getFechaViabilidadPropuesta() + "'" +
            ", fechaSustentacionProyecto='" + getFechaSustentacionProyecto() + "'" +
            ", nota=" + getNota() +
            ", conclusion='" + getConclusion() + "'" +
            ", recomendaciones='" + getRecomendaciones() + "'" +
            "}";
    }
}
