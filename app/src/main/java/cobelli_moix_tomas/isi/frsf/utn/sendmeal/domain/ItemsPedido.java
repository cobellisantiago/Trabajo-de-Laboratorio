package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


//@Entity(tableName = "ITEMS_PEDIDO")
public class ItemsPedido implements Serializable {

    //@PrimaryKey(autoGenerate = true)
    private Integer idItemsPedido;

    //@ColumnInfo(name = "PEDIDO")
    private Pedido pedido;

    //@ColumnInfo(name = "PLATO")
    private Plato plato;

    //@ColumnInfo(name = "CANTIDAD")
    private Integer cantidad;

    //@ColumnInfo(name = "PRECIO")
    private Double precio;

    public ItemsPedido() {
    }

    public ItemsPedido(Integer idItemsPedido, Pedido pedido, Plato plato, Integer cantidad, Double precio) {
        this.idItemsPedido = idItemsPedido;
        this.pedido = pedido;
        this.plato = plato;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getIdItemsPedido() {
        return idItemsPedido;
    }

    public void setIdItemsPedido(Integer idItemsPedido) {
        this.idItemsPedido = idItemsPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
