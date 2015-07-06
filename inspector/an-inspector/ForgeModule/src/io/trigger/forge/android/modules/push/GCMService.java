package io.trigger.forge.android.modules.push;

import io.trigger.forge.android.core.ForgeApp;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.JsonPrimitive;
import com.kinvey.android.push.KinveyGCMService;

public class GCMService extends KinveyGCMService {
	
	@Override
	public Class getReceiver() {
		return GCMReceiver.class;
	}

	@Override
	public void onDelete(String arg0) {
		Log.i(TAG, arg0);
	}

	@Override
	public void onError(String arg0) {
		Log.e(TAG, arg0);
	}

	@Override
	public void onMessage(String message) {
		displayNotification(message);
		ForgeApp.event("push.message", new JsonPrimitive(message));
	}

	@Override
	public void onRegistered(String arg0) {
		Log.i(TAG, arg0);

	}

	@Override
	public void onUnregistered(String arg0) {
		Log.i(TAG, arg0);

	}
	private void displayNotification(String message){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.icons)
            .setContentTitle("Fetchnotes")
            .setContentText(message);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

}
