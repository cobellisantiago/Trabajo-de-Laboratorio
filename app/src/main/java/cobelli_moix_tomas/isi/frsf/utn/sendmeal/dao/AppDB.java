package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.rest.Converters;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;


@Database(entities = {Pedido.class, ItemsPedido.class, Plato.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDB extends RoomDatabase {
    public abstract PedidoDao pedidoDao();
    public abstract ItemsPedidoDao itemsPedidoDao();
    public abstract PlatoDao platoDao();



}
