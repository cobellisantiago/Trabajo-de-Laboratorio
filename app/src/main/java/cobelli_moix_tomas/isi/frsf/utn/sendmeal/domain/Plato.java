package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import java.util.ArrayList;
import java.util.List;

public class Plato {

    private static List<Plato> platos = new ArrayList<>();
    private static Plato platoGenerico = new Plato("Hamburguesa re gorda", "muy rica", 1800.00, 2000);
    private Integer idPlato;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer calorias;

    public Plato() {
    }

    public Plato (String nombre, String descripcion, Double precio, Integer calorias) {

        this.idPlato = platos.size();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        platos.add(this);
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
}