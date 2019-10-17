package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.content.Context;
import android.content.Intent;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;

public class ThreadOfertarPlato extends Thread {

    public interface EventoOnClickListenerNotification{
        void onButtonClickNotification();
    }


    private Context context;
    private EventoOnClickListenerNotification listener;

    public ThreadOfertarPlato(){}

    public ThreadOfertarPlato(Context context, EventoOnClickListenerNotification listener){
        this.context = context;
        this.listener = listener;
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
        receiver.setListener(listener);
    }
}
