package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.UUID;


@Entity(tableName = "ITEMS_PEDIDO", foreignKeys = {@ForeignKey(entity = Pedido.class, parentColumns = "ID_PEDIDO", childColumns = "ID_ITEMS_PEDIDO"),
                                    @ForeignKey(entity = Plato.class, parentColumns = "ID_PLATO", childColumns = "ID_ITEMS_PEDIDO")})
public class ItemsPedido {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_ITEMS_PEDIDO")
    public Integer idItemsPedido;

    //@Relation(parentColumn = "idItemsPedido", entityColumn = "idPedido", entity = Pedido.class)
    //@ColumnInfo(name = "PEDIDO")
    //public Pedido pedido;

    @ColumnInfo(name = "PEDIDO")
    public Integer pedido;

    //@Relation(parentColumn = "idItemsPedido", entityColumn = "idPlato", entity = Plato.class)
    //@ColumnInfo(name = "PLATO")
    //public Plato plato;

    @ColumnInfo(name = "PLATO")
    public Integer plato;

    @ColumnInfo(name = "CANTIDAD")
    private Integer cantidad;

    @ColumnInfo(name = "PRECIO")
    private Double precio;

    @Ignore
    public ItemsPedido() {
    }

    public ItemsPedido(Integer pedido, Integer plato, Integer cantidad, Double precio) {
        this.idItemsPedido = Integer.parseInt(UUID.randomUUID().toString());
        this.pedido = pedido;
        this.plato = plato;
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
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public Integer getPlato() {
        return plato;
    }

    public void setPlato(Integer plato) {
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
