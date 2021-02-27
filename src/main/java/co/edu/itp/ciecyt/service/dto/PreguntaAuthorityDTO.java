package co.edu.itp.ciecyt.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.itp.ciecyt.domain.PreguntaAuthority} entity.
 */
public class PreguntaAuthorityDTO implements Serializable {
    
    private Long id;


    private Long pregunta3Id;

    private String pregunta3Pregunta;

    private Long authority3Id;

    private String authority3Name;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPregunta3Id() {
        return pregunta3Id;
    }

    public void setPregunta3Id(Long preguntaId) {
        this.pregunta3Id = preguntaId;
    }

    public String getPregunta3Pregunta() {
        return pregunta3Pregunta;
    }

    public void setPregunta3Pregunta(String preguntaPregunta) {
        this.pregunta3Pregunta = preguntaPregunta;
    }

    public Long getAuthority3Id() {
        return authority3Id;
    }

    public void setAuthority3Id(Long authorityId) {
        this.authority3Id = authorityId;
    }

    public String getAuthority3Name() {
        return authority3Name;
    }

    public void setAuthority3Name(String authorityName) {
        this.authority3Name = authorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreguntaAuthorityDTO)) {
            return false;
        }

        return id != null && id.equals(((PreguntaAuthorityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreguntaAuthorityDTO{" +
            "id=" + getId() +
            ", pregunta3Id=" + getPregunta3Id() +
            ", pregunta3Pregunta='" + getPregunta3Pregunta() + "'" +
            ", authority3Id=" + getAuthority3Id() +
            ", authority3Name='" + getAuthority3Name() + "'" +
            "}";
    }
}
