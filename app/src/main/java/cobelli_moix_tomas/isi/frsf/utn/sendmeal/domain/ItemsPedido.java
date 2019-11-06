package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;


@Entity(tableName = "ITEMS_PEDIDO")
public class ItemsPedido implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID_ITEMS_PEDIDO")
    public String id;

    @ColumnInfo(name = "PEDIDO")
    public String pedido;

    @Embedded (prefix = "plato_")
    public Plato plato;

    @ColumnInfo(name = "CANTIDAD")
    private Integer cantidad;

    @ColumnInfo(name = "PRECIO")
    private Double precio;


    public ItemsPedido() {
        this.id = UUID.randomUUID().toString();
    }

    @Ignore
    public ItemsPedido(String pedido, Plato plato, Integer cantidad, Double precio) {
        this.id = UUID.randomUUID().toString();
        this.pedido = pedido;
        this.plato = plato;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
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
