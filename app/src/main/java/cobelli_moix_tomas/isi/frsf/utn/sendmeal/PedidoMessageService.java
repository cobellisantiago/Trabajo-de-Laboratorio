package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class PedidoMessageService extends FirebaseMessagingService {

   private final String TAG = "APP_MSG";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    @Override
    public void onNewToken(@NonNull String token) {

        Log.d(TAG, "Refresh token: " + token);

    }

    private void sendNotification(String title, String body) {
        //TODO Aqui crear notificacion

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_hamburguesa2_round);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "002")
                .setSmallIcon(R.mipmap.ic_hamburguesa2_round)
                .setLargeIcon(icon)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }

}
