package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.Ignore;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class Plato implements Serializable {

    private static Plato platoGenerico = new Plato("Hamburguesa re gorda", "muy rica", 1800.00, 2000);

    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer calorias;
    private Boolean oferta;

    @Ignore
    public Plato() {
        this.id = UUID.randomUUID().toString();
    }


    public Plato (String nombre, String descripcion, Double precio, Integer calorias) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        this.oferta = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Boolean getOferta() {
        return oferta;
    }

    public void setOferta(Boolean oferta) {
        this.oferta = oferta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plato)) return false;
        Plato plato = (Plato) o;
        return Objects.equals(getId(), plato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getDescripcion(), getPrecio(), getCalorias());
    }
}