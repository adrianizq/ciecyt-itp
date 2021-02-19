package co.edu.itp.ciecyt.service.dto;

import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.domain.enumeration.EnumViabilidad;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.Column;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.Proyecto} entity.
 */
public class ProyectoDTO implements Serializable {

    private Long id;

    private String titulo;

    private String url;

    private String lugarEjecucion;

    private String duracion;

    private LocalDate fechaIni;

    private LocalDate fechaFin;

    private Double contrapartidaPesos;

    private Double contrapartidaEspecie;

    private String palabrasClave;

    private String convocatoria;

    private String tipo;

    private String referencias;

    private String programa;
    private String fase;

    private String departamento;

    private String municipio;

    private EnumViabilidad viabilidad;

    private Boolean viable;

    private Long proyectoLineaInvestigacionId;

    private String proyectoLineaInvestigacionLinea;

    private Long proyectoGrupoSemilleroId;

    private String proyectoGrupoSemilleroNombre;

    private Long proyectoModalidadId;

    private String proyectoModalidadModalidad;

    private Long subLineaLineaInvestigacionId;

    private String subLineaLineaInvestigacionLinea;

    private Long facultadId;

    private Long proyectoProgramaId;
    private Long proyectoFaseId;

    private List<IntegranteProyectoDTO> listaIntegrantesProyecto;

    private Boolean tieneJurado;

    private Boolean tieneJuradoViabilidad;

    private Boolean tieneAsesor;

    private Boolean enviado;

    private Boolean preEnviado;

    private ZonedDateTime fechaEnvioPropuesta;

    private ZonedDateTime fechaEnvioProyecto;

    private Float nota;

    private String conclusion;

    private String recomendaciones;

    // private String proyectoProgramaPrograma;

    //adr

    private Long asesorId;

    public Long getAsesorId() {
        return asesorId;
    }

    public void setAsesorId(Long asesorId) {
        this.asesorId = asesorId;
    }

    // -----adr

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLugarEjecucion() {
        return lugarEjecucion;
    }

    public void setLugarEjecucion(String lugarEjecucion) {
        this.lugarEjecucion = lugarEjecucion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getContrapartidaPesos() {
        return contrapartidaPesos;
    }

    public void setContrapartidaPesos(Double contrapartidaPesos) {
        this.contrapartidaPesos = contrapartidaPesos;
    }

    public Double getContrapartidaEspecie() {
        return contrapartidaEspecie;
    }

    public void setContrapartidaEspecie(Double contrapartidaEspecie) {
        this.contrapartidaEspecie = contrapartidaEspecie;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    /********** */

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    //////////////////////////////////77

    public Long getProyectoLineaInvestigacionId() {
        return proyectoLineaInvestigacionId;
    }

    public void setProyectoLineaInvestigacionId(Long lineaInvestigacionId) {
        this.proyectoLineaInvestigacionId = lineaInvestigacionId;
    }

    public String getProyectoLineaInvestigacionLinea() {
        return proyectoLineaInvestigacionLinea;
    }

    public void setProyectoLineaInvestigacionLinea(String lineaInvestigacionLinea) {
        this.proyectoLineaInvestigacionLinea = lineaInvestigacionLinea;
    }

    public Long getProyectoGrupoSemilleroId() {
        return proyectoGrupoSemilleroId;
    }

    public void setProyectoGrupoSemilleroId(Long grupoSemilleroId) {
        this.proyectoGrupoSemilleroId = grupoSemilleroId;
    }

    public String getProyectoGrupoSemilleroNombre() {
        return proyectoGrupoSemilleroNombre;
    }

    public void setProyectoGrupoSemilleroNombre(String grupoSemilleroNombre) {
        this.proyectoGrupoSemilleroNombre = grupoSemilleroNombre;
    }

    public Long getProyectoModalidadId() {
        return proyectoModalidadId;
    }

    public void setProyectoModalidadId(Long modalidadId) {
        this.proyectoModalidadId = modalidadId;
    }

    public String getProyectoModalidadModalidad() {
        return proyectoModalidadModalidad;
    }

    public void setProyectoModalidadModalidad(String modalidadModalidad) {
        this.proyectoModalidadModalidad = modalidadModalidad;
    }

    public Long getSubLineaLineaInvestigacionId() {
        return subLineaLineaInvestigacionId;
    }

    public void setSubLineaLineaInvestigacionId(Long lineaInvestigacionId) {
        this.subLineaLineaInvestigacionId = lineaInvestigacionId;
    }

    public String getSubLineaLineaInvestigacionLinea() {
        return subLineaLineaInvestigacionLinea;
    }

    public void setSubLineaLineaInvestigacionLinea(String lineaInvestigacionLinea) {
        this.subLineaLineaInvestigacionLinea = lineaInvestigacionLinea;
    }

    public Long getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Long facultadId) {
        this.facultadId = facultadId;
    }

    public Long getProyectoProgramaId() {
        return proyectoProgramaId;
    }

    public void setProyectoProgramaId(Long programaId) {
        this.proyectoProgramaId = programaId;
    }

    public Long getProyectoFaseId() {
        return proyectoFaseId;
    }

    public void setProyectoFaseId(Long faseId) {
        this.proyectoFaseId = faseId;
    }

    ////////////////////////////////7777
    public EnumViabilidad getViabilidad() {
        return viabilidad;
    }

    public void setViabilidad(EnumViabilidad viabilidad) {
        this.viabilidad = viabilidad;
    }

    ////////////////////////////////////////7777

    public Boolean isViable() {
        return viable;
    }

    public void setViable(Boolean viable) {
        this.viable = viable;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProyectoDTO)) {
            return false;
        }

        return id != null && id.equals(((ProyectoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProyectoDTO{" +
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
            ", proyectoLineaInvestigacionId=" + getProyectoLineaInvestigacionId() +
            ", proyectoLineaInvestigacionLinea='" + getProyectoLineaInvestigacionLinea() + "'" +
            ", proyectoGrupoSemilleroId=" + getProyectoGrupoSemilleroId() +
            ", proyectoGrupoSemilleroNombre='" + getProyectoGrupoSemilleroNombre() + "'" +
            ", proyectoModalidadId=" + getProyectoModalidadId() +
            ", proyectoModalidadModalidad='" + getProyectoModalidadModalidad() + "'" +
            ", subLineaLineaInvestigacionId=" + getSubLineaLineaInvestigacionId() +
            ", subLineaLineaInvestigacionLinea='" + getSubLineaLineaInvestigacionLinea() + "'" +
            ", facultadId=" + getFacultadId() +
            ", proyectoProgramaId=" + getProyectoProgramaId() +
            ", proyectoFaseId=" + getProyectoFaseId() +
            ", viabilidad='" + getViabilidad() + "'" +
            ", viable=" + isViable() +
            ", nota=" + getNota() +
            ", conclusion='" + getConclusion() + "'" +
            ", recomendaciones='" + getRecomendaciones() + "'" +
           // ", proyectoProgramaPrograma='" + getProyectoProgramaPrograma() + "'" +
            "}";
    }

    public List<IntegranteProyectoDTO> getListaIntegrantesProyecto() {
        return listaIntegrantesProyecto;
    }

    public void setListaIntegrantesProyecto(List<IntegranteProyectoDTO> listaIntegrantesProyecto) {
        this.listaIntegrantesProyecto = listaIntegrantesProyecto;
    }

    ////////////////////////////77
    public Boolean isTieneJurado() {
        return tieneJurado;
    }

    public void setTieneJurado(Boolean tieneJurado) {
        this.tieneJurado = tieneJurado;
    }

    /////////////////7
    public Boolean isTieneJuradoViabilidad() {
        return tieneJuradoViabilidad;
    }

    public void setTieneJuradoViabilidad(Boolean tieneJuradoViabilidad) {
        this.tieneJuradoViabilidad = tieneJuradoViabilidad;
    }

    ////////////7
    public Boolean isTieneAsesor() {
        return tieneAsesor;
    }

    public void setTieneAsesor(Boolean tieneAsesor) {
        this.tieneAsesor = tieneAsesor;
    }

    ///////////////7
    public Boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    /////////////


    public Boolean isPreEnviado() {
        return preEnviado;
    }

    public void setPreEnviado(Boolean preEnviado) {
        this.preEnviado = preEnviado;
    }

    public ZonedDateTime getFechaEnvioPropuesta() {
        return fechaEnvioPropuesta;
    }

    public void setFechaEnvioPropuesta(ZonedDateTime fechaEnvioPropuesta) {
        this.fechaEnvioPropuesta = fechaEnvioPropuesta;
    }

    public ZonedDateTime getFechaEnvioProyecto() {
        return fechaEnvioProyecto;
    }

    public void setFechaEnvioProyecto(ZonedDateTime fechaEnvioProyecto) {
        this.fechaEnvioProyecto = fechaEnvioProyecto;
    }
}
