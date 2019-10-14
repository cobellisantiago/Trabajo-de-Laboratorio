package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.content.Context;
import android.content.Intent;

public class ThreadOfertarPlato extends Thread {

    Context context;

    public ThreadOfertarPlato(){}
    public ThreadOfertarPlato(Context context){
        this.context = context;
    }


    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BroadcastReceiverOferta receiver = new BroadcastReceiverOferta();
        Intent intent = new Intent();
        receiver.onReceive(context,intent);
    }
}
