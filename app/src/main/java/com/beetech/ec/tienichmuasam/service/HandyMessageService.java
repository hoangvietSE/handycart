package com.beetech.ec.tienichmuasam.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.entity.response.NotificationResponse;
import com.beetech.ec.tienichmuasam.ui.main.MainActivity;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HandyMessageService extends FirebaseMessagingService {
    private static final String TAG = HandyMessageService.class.getSimpleName();

    // [START_EXCLUDE]
    // There are two types of messages data messages and notification messages. Data messages are handled
    // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
    // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
    // is in the foreground. When the app is in the background an automatically generated notification is displayed.
    // When the user taps on the notification they are returned to the app. Messages containing both notification
    // and data payloads are treated as notification messages. The Firebase console always sends notification
    // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
    // [END_EXCLUDE]

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "data : " + remoteMessage.getData());
        // Check if message contains a data payload.
        Map<String, String> dataPayload = remoteMessage.getData();
        if (!dataPayload.isEmpty()) {
            JSONObject js = new JSONObject(dataPayload);
            NotificationResponse notificationResponse = new Gson().fromJson(js.toString(), NotificationResponse.class);
            sendNotification(notificationResponse);
        }
//        RemoteMessage.Notification notificationPayload = remoteMessage.getNotification();
//        Log.d(TAG, "Message Notification Body: title: " + notificationPayload.getTitle() + "" +
//                "\nbody: " + notificationPayload.getBody());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "Token: " + token);
    }

    private void sendNotification(NotificationResponse notificationResponse) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT
        );
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(this).asBitmap().load(R.mipmap.ic_launcher)
                    .submit(100, 100). // Width and height
                    get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .setLargeIcon(bitmap)
                .setContentTitle(Html.fromHtml(notificationResponse.getTitle()))
                .setContentText(Html.fromHtml(notificationResponse.getBody()))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed (recommend).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    getResources().getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }
}
