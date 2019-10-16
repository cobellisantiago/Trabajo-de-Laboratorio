package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;

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

        //TODO hacer metodo del canal como en las diapositivas
        String CHANNEL_ID = "Notificacion oferta";

        Intent intent = new Intent(context, Home.class);
        //String name = null;
        //intent.putExtra("data", name);
        //System.out.println("SETEO EN BROADCAST   " + intent.getExtras());
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_hamburguesa2_round);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_hamburguesa2_round)
                .setLargeIcon(icon)
                .setContentTitle("Oferta!")
                .setContentText("El plato se encuentra en oferta")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());

        //context.sendBroadcast(intent);
    }

    /*private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }*/

}