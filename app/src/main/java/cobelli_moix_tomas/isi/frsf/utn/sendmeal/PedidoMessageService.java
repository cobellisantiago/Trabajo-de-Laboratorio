package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class PedidoMessageService extends FirebaseMessagingService {

   private final String TAG = "APP_MSG";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
    }

    /*@Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);

            if (remoteMessage.getData().size() > 0){
                String idPedido = remoteMessage.getData().get("idPedido");
                String estadoAnterior = remoteMessage.getData().get("estadoAnterior");
                String estadoNuevo = remoteMessage.getData().get("estadoNuevo");
                if(idPedido != null && estadoAnterior != null && estadoNuevo != null){
                    sendNotification(idPedido, estadoAnterior, estadoNuevo);
                }
            }
    }*/

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refresh token: " + token);
    }


    /*private void sendNotification(String idPedido, String estadoAnterior, String estadoNuevo) {

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_hamburguesa2_round);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "002")
                .setSmallIcon(R.mipmap.ic_hamburguesa2_round)
                .setLargeIcon(icon)
                .setContentTitle("Cambio de estado en el pedido  " + idPedido)
                .setContentText("El pedido " + idPedido + " cambio de " + estadoAnterior + " a " + estadoNuevo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }*/

    public void sendNotification (String title, String body){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Push-Notification")
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1,builder.build());
    }
}