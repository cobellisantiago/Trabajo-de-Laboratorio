package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.content.Context;
import android.content.Intent;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;


public class ThreadOfertarPlato extends Thread {

    private Context context;
    private Plato plato;
    private BroadcastReceiverOferta receiver = new BroadcastReceiverOferta();
    private Intent intent = new Intent();

    public ThreadOfertarPlato(Context context, Plato plato){
        this.context = context;
        this.plato = plato;
    }


    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        receiver.onReceive(context,intent);
        receiver.setPlato(plato);
    }
}