package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;

import androidx.core.app.NotificationCompat;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.Home;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class BroadcastReceiverOferta extends BroadcastReceiver {

    public BroadcastReceiverOferta(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    private void showNotification(Context context) {
        String CHANNEL_ID = "Notificacion oferta";
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, Home.class), 0);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_hamburguesa_layer);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.ic_hamburguesa_layer).setLargeIcon(icon).setContentTitle("El plato se encuentra en oferta").setContentText("").setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(contentIntent);
        //mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, builder.build());

    }
}
