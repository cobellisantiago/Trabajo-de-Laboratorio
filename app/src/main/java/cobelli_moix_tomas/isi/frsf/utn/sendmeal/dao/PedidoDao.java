package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;


@Dao
public interface PedidoDao {
    //@Query("SELECT * FROM PEDIDO")
    List<Pedido> getAll();

    @Insert
    void insert(Pedido pedido);

    @Insert
    void insertAll(Pedido... pedidos);

    @Delete
    void delete(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);
}
