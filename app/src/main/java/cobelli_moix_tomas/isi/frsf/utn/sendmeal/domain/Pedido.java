package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.enumeration.EstadoPedido;


//@Entity(tableName = "PEDIDO")
public class Pedido implements Serializable{

    //@PrimaryKey(autoGenerate = true)
    private Integer idPedido;

    //@ColumnInfo(name = "FECHA_CREACION")
    private Date fechaCreacion;

    //@ColumnInfo(name = "ESTADO_PEDIDO")
    private EstadoPedido estadoPedido;

    //@ColumnInfo(name = "LATITUD_CORDENADA")
    private Double latitudCordenada;

    //@ColumnInfo(name = "LONGITUD_CORDENADA")
    private Double longitudCordenada;

    //@Relation(parentColumn = "idPedido", entityColumn = "idItemsPedido", entity = ItemsPedido.class)
    private List<ItemsPedido> itemsPedidoList;

    public Pedido() {
    }

    public Pedido(Integer idPedido, Date fechaCreacion, EstadoPedido estadoPedido, Double latitudCordenada, Double longitudCordenada, List<ItemsPedido> itemsPedidoList) {
        this.idPedido = idPedido;
        this.fechaCreacion = fechaCreacion;
        this.estadoPedido = estadoPedido;
        this.latitudCordenada = latitudCordenada;
        this.longitudCordenada = longitudCordenada;
        this.itemsPedidoList = itemsPedidoList;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

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

    public List<ItemsPedido> getItemsPedidoList() {
        return itemsPedidoList;
    }

    public void setItemsPedidoList(List<ItemsPedido> itemsPedidoList) {
        this.itemsPedidoList = itemsPedidoList;
    }
}