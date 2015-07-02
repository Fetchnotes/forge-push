package io.trigger.forge.android.modules.push;

import io.trigger.forge.android.core.ForgeApp;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.gson.JsonPrimitive;
import com.kinvey.android.push.KinveyGCMService;

public class GCMService extends KinveyGCMService {
	
	@Override
	public Class getReceiver() {
		return GCMReceiver.class;
	}

	@Override
	public void onDelete(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(String message) {
		displayNotification(message);
		ForgeApp.event("push.message", new JsonPrimitive(message));
	}

	@Override
	public void onRegistered(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnregistered(String arg0) {
		// TODO Auto-generated method stub

	}
	private void displayNotification(String message){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
            //.setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle("Fetchnotes")
            .setContentText(message);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

}
