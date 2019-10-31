package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface PedidoDao {
    //With LiveData<Pedido> automatically dispatch changes as the DB changes

    @Transaction
    @Query("SELECT * FROM PEDIDO")
    List<Pedido> getAllPedidos();

    @Insert (onConflict = REPLACE)
    void insert(Pedido pedido);

    @Insert
    void insertAll(Pedido... pedidos);

    @Delete
    void delete(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);
}
