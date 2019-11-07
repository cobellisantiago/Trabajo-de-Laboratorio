package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;


@Entity(tableName = "PEDIDO")
public class Pedido implements Serializable {

    public static final Integer PENDIENTE = 1;
    public static final Integer ENVIADO = 2;
    public static final Integer ACEPTADO = 3;
    public static final Integer RECHAZADO = 4;
    public static final Integer EN_PREPARACION = 5;
    public static final Integer EN_ENVIO = 6;
    public static final Integer ENTREGADO = 7;
    public static final Integer CANCELADO = 8;

    private List<ItemsPedido> itemsPedidoList = new ArrayList<ItemsPedido>();


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID_PEDIDO")
    public String id;

    @ColumnInfo(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @ColumnInfo(name = "ESTADO_PEDIDO")
    private Integer estadoPedido;

    @ColumnInfo(name = "LATITUD_CORDENADA")
    private Double latitudCordenada;

    @ColumnInfo(name = "LONGITUD_CORDENADA")
    private Double longitudCordenada;

    @Ignore
    public Pedido() {
        this.id = UUID.randomUUID().toString();
    }

    public Pedido(Date fechaCreacion, Integer estadoPedido, Double latitudCordenada, Double longitudCordenada) {
        this.id = UUID.randomUUID().toString();
        this.fechaCreacion = fechaCreacion;
        this.estadoPedido = estadoPedido;
        this.latitudCordenada = latitudCordenada;
        this.longitudCordenada = longitudCordenada;
    }

    public String getId() {
        return id;
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

    public List<ItemsPedido> getItemsPedidoList() {
        return itemsPedidoList;
    }

    public void setItemsPedidoList(List<ItemsPedido> itemsPedidoList) {
        this.itemsPedidoList = itemsPedidoList;
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
        private static List<ItemsPedido> itemsPedidoList = new ArrayList<ItemsPedido>();


        public static List<ItemsPedido> getItemsPedidoList() {
            return itemsPedidoList;
        }

        public static void setItemsPedidoList(List<ItemsPedido> itemsPedidoList) {
            PedidoToItemsPedido.itemsPedidoList = itemsPedidoList;
        }
    }


}