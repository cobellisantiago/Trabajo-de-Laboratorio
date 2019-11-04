package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity(tableName = "PEDIDO")
public class Pedido {

    public static final Integer PENDIENTE = 1;
    public static final Integer ENVIADO = 2;
    public static final Integer ACEPTADO = 3;
    public static final Integer RECHAZADO = 4;
    public static final Integer EN_PREPARACION = 5;
    public static final Integer EN_ENVIO = 6;
    public static final Integer ENTREGADO = 7;
    public static final Integer CANCELADO = 8;

    //TODO ver como generar id

    @PrimaryKey
    @ColumnInfo(name = "ID_PEDIDO")
    public Integer idPedido;

    @ColumnInfo(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @ColumnInfo(name = "ESTADO_PEDIDO")
    private Integer estadoPedido;

    @ColumnInfo(name = "LATITUD_CORDENADA")
    private Double latitudCordenada;

    @ColumnInfo(name = "LONGITUD_CORDENADA")
    private Double longitudCordenada;


    public Pedido() {
    }

    public Pedido(Date fechaCreacion, Integer estadoPedido, Double latitudCordenada, Double longitudCordenada, List<ItemsPedido> itemsPedidoList) {
        //this.idPedido = Integer.parseInt(UUID.randomUUID().toString());
        this.fechaCreacion = fechaCreacion;
        this.estadoPedido = estadoPedido;
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

    public Integer getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(Integer estadoPedido) {
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

    public static class PedidoToItemsPedido {
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