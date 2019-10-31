package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


//TODO ver si es necesaria la foreign key
@Entity(tableName = "ITEMS_PEDIDO", foreignKeys = @ForeignKey(entity = Pedido.class, parentColumns = "ID_PEDIDO", childColumns = "PEDIDO"), indices = {@Index("PEDIDO")})
public class ItemsPedido {

    @PrimaryKey(autoGenerate = true)
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

    @Ignore
    public ItemsPedido() {
    }

    public ItemsPedido(Integer pedido, Plato plato, Integer cantidad, Double precio) {
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
