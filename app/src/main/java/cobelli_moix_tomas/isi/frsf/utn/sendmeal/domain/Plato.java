package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Entity(tableName = "PLATO")
public class Plato implements Serializable {

    private static List<Plato> platos = new ArrayList<>();
    private static Plato platoGenerico = new Plato("Hamburguesa re gorda", "muy rica", 1800.00, 2000);

    //@PrimaryKey(autoGenerate = true)
    private Integer idPlato;

    //@ColumnInfo(name = "NOMBRE")
    private String nombre;

    //@ColumnInfo(name = "DESCRIPCION")
    private String descripcion;

    //@ColumnInfo(name = "PRECIO")
    private Double precio;

    //@ColumnInfo(name = "CALORIAS")
    private Integer calorias;

    //@ColumnInfo(name = "OFERTA")
    private Boolean oferta;

    public Plato() {

    }


    public Plato (String nombre, String descripcion, Double precio, Integer calorias) {

        //TODO implementar otra manera de hacer el id
        this.idPlato = platos.size();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        this.oferta = false;
        platos.add(this);
    }

    public static void setPlatos(List<Plato> platos) {
        Plato.platos = platos;
    }

    public static List<Plato> getPlatos() {
        return platos;
    }

    public static Plato getPlato (int pos){
        return platos.get(pos);
    }

    public Integer getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
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
        return Objects.equals(getIdPlato(), plato.getIdPlato());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPlato(), getNombre(), getDescripcion(), getPrecio(), getCalorias());
    }
}