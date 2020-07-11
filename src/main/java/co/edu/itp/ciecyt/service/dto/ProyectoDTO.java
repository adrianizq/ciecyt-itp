package co.edu.itp.ciecyt.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

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


    private Long proyectoLineaInvestigacionId;

    private String proyectoLineaInvestigacionLinea;

    private Long proyectoGrupoSemilleroId;

    private String proyectoGrupoSemilleroNombre;

    private Long proyectoModalidadId;

    private String proyectoModalidadModalidad;

    private Long facultadId;

    //adr
    
    private Long asesorId;

    
    public Long getAsesorId(){
        return asesorId;
    }
    
    public void setAsesorId(Long asesorId){
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

    public Long getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Long facultadId) {
        this.facultadId = facultadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProyectoDTO proyectoDTO = (ProyectoDTO) o;
        if (proyectoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proyectoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
            ", proyectoLineaInvestigacion=" + getProyectoLineaInvestigacionId() +
            ", proyectoLineaInvestigacion='" + getProyectoLineaInvestigacionLinea() + "'" +
            ", proyectoGrupoSemillero=" + getProyectoGrupoSemilleroId() +
            ", proyectoGrupoSemillero='" + getProyectoGrupoSemilleroNombre() + "'" +
            ", proyectoModalidad=" + getProyectoModalidadId() +
            ", proyectoModalidad='" + getProyectoModalidadModalidad() + "'" +
            ", facultad=" + getFacultadId() +
            "}";
    }
}
