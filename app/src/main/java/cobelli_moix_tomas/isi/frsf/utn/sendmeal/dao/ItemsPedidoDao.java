package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;


@Dao
public interface ItemsPedidoDao {

    @Query("SELECT * FROM ITEMS_PEDIDO")
    List<ItemsPedido> getAll();

    @Insert
    void insert(ItemsPedido itemsPedido);

    @Insert
    void insertAll(ItemsPedido... itemsPedidos);

    @Delete
    void delete(ItemsPedido itemsPedido);

    @Update
    void actualizar(ItemsPedido itemsPedido);
}
