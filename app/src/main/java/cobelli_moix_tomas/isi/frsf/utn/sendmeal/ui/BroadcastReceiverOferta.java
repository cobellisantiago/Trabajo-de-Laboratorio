package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.Home;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import static android.content.Context.NOTIFICATION_SERVICE;


public class BroadcastReceiverOferta extends BroadcastReceiver {

    private static Plato plato;

    public BroadcastReceiverOferta(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    private void showNotification(Context context) {

        String message = "El plato se encuentra en oferta";

        Intent intent=new Intent(context, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Mensage oferta plato", message);
        intent.putExtra("MSJ-OFERTA", "OFERTA");
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_hamburguesa2_round);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context).setAutoCancel(true).setContentTitle("Oferta!").setContentText(message).setSmallIcon(R.mipmap.ic_hamburguesa2_round).setLargeIcon(icon).setContentIntent(pendingIntent);
        NotificationManager manager= (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }


    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal 1 - SendMead";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("001", name, importance);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
}