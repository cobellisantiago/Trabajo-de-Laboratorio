package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.UUID;


@Entity(tableName = "ITEMS_PEDIDO")
public class ItemsPedido {

    @PrimaryKey(autoGenerate = true)
    public Integer idItemsPedido;

    @ColumnInfo(name = "PEDIDO")
    public Integer idPedido;

    @ColumnInfo(name = "PLATO")
    public Integer idPlato;

    @ColumnInfo(name = "CANTIDAD")
    private Integer cantidad;

    @ColumnInfo(name = "PRECIO")
    private Double precio;

    @Ignore
    public ItemsPedido() {
    }

    public ItemsPedido(Integer idPedido, Integer idPlato, Integer cantidad, Double precio) {
        this.idItemsPedido = Integer.parseInt(UUID.randomUUID().toString());
        this.idPedido = idPedido;
        this.idPlato = idPlato;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getIdItemsPedido() {
        return idItemsPedido;
    }

    public void setIdItemsPedido() {
        this.idItemsPedido = Integer.parseInt(UUID.randomUUID().toString());
    }

    public Integer getPedido() {
        return idPedido;
    }

    public void setPedido(Integer pedido) {
        this.idPedido = pedido;
    }

    public Integer getPlato() {
        return idPlato;
    }

    public void setPlato(Integer plato) {
        this.idPlato = plato;
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
