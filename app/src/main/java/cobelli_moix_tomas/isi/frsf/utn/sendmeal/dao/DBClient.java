package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao;

import android.content.Context;

import androidx.room.Room;

public class DBClient {
    private static DBClient DB = null;

    private AppDB appDB;

    private DBClient(Context ctx){
        appDB = Room.databaseBuilder(ctx, AppDB.class, "obrapp-db").allowMainThreadQueries().build();
    }

    public synchronized static DBClient getInstance(Context ctx){
        if(DB == null){
            DB = new DBClient(ctx);
        }
        return DB;
    }

    public AppDB getAppDB() {
        return appDB;
    }
}
