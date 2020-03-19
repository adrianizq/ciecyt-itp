package co.edu.itp.ciecyt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Menu.
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "url")
    private String url;

    @Column(name = "icono")
    private String icono;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JsonIgnoreProperties("menus")
    private Menu menuPadre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Menu nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public Menu url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public Menu icono(String icono) {
        this.icono = icono;
        return this;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Boolean isActivo() {
        return activo;
    }

    public Menu activo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Menu getMenuPadre() {
        return menuPadre;
    }

    public Menu menuPadre(Menu menu) {
        this.menuPadre = menu;
        return this;
    }

    public void setMenuPadre(Menu menu) {
        this.menuPadre = menu;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        return id != null && id.equals(((Menu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", url='" + getUrl() + "'" +
            ", icono='" + getIcono() + "'" +
            ", activo='" + isActivo() + "'" +
            "}";
    }
}
