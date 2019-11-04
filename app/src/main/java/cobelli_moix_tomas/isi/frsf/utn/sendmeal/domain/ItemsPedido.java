package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;


@Entity(tableName = "ITEMS_PEDIDO")
public class ItemsPedido {

    @PrimaryKey
    @ColumnInfo(name = "ID_ITEMS_PEDIDO")
    public Integer idItemsPedido;

    @ColumnInfo(name = "PEDIDO")
    public Integer pedido;

    @Embedded (prefix = "plato_")
    public Plato plato;

    @ColumnInfo(name = "CANTIDAD")
    private Integer cantidad;

    @ColumnInfo(name = "PRECIO")
    private Double precio;


    public ItemsPedido() {
        //this.idItemsPedido = Integer.parseInt(UUID.randomUUID().toString());
    }

    @Ignore
    public ItemsPedido(Integer pedido, Plato plato, Integer cantidad, Double precio) {
        //this.idItemsPedido = Integer.parseInt(UUID.randomUUID().toString());
        this.pedido = pedido;
        this.plato = plato;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getIdItemsPedido() {
        return idItemsPedido;
    }

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
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
