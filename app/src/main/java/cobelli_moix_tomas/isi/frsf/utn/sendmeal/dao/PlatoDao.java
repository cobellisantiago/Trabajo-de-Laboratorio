package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlatoDao {

    @Insert (onConflict = REPLACE)
    void insert(Plato plato);

    @Insert
    void insertAll(Plato... platos);

    @Delete
    void delete(Plato plato);

    @Update
    void actualizar(Plato plato);

}
