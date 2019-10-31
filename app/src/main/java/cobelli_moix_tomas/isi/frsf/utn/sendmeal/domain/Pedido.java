package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import java.util.Date;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.enumeration.EstadoPedido;


@Entity(tableName = "PEDIDO")
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_PEDIDO")
    public Integer idPedido;

    @ColumnInfo(name = "FECHA_CREACION")
    private Date fechaCreacion;

    //@ColumnInfo(name = "ESTADO_PEDIDO")
    //private EstadoPedido estadoPedido;

    @ColumnInfo(name = "LATITUD_CORDENADA")
    private Double latitudCordenada;

    @ColumnInfo(name = "LONGITUD_CORDENADA")
    private Double longitudCordenada;

    //TODO ver si conviene esto o el metodo que esta hecho al final con las Relations
    //@Embedded
    //private List<ItemsPedido> itemsPedidoList;


    public Pedido() {
    }

    public Pedido(Date fechaCreacion, EstadoPedido estadoPedido, Double latitudCordenada, Double longitudCordenada, List<ItemsPedido> itemsPedidoList) {

        this.fechaCreacion = fechaCreacion;
        //this.estadoPedido = estadoPedido;
        this.latitudCordenada = latitudCordenada;
        this.longitudCordenada = longitudCordenada;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /*public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }*/

    public Double getLatitudCordenada() {
        return latitudCordenada;
    }

    public void setLatitudCordenada(Double latitudCordenada) {
        this.latitudCordenada = latitudCordenada;
    }

    public Double getLongitudCordenada() {
        return longitudCordenada;
    }

    public void setLongitudCordenada(Double longitudCordenada) {
        this.longitudCordenada = longitudCordenada;
    }

    public class PedidoToItemsPedido {
        @Embedded
        public Pedido pedido;

        @Relation(parentColumn = "ID_PEDIDO", entityColumn = "ID_ITEMS_PEDIDO", entity = ItemsPedido.class)
        private List<ItemsPedido> itemsPedidoList;

        public List<ItemsPedido> getItemsPedidoList() {
            return itemsPedidoList;
        }

        public void setItemsPedidoList(List<ItemsPedido> itemsPedidoList) {
            this.itemsPedidoList = itemsPedidoList;
        }
    }
}