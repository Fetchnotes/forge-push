package io.trigger.forge.android.modules.push;

import io.trigger.forge.android.core.ForgeApp;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class PushMessage {

	private String message;
	private String from;
	private String subject;
	
	
	public PushMessage(String message) {
		this(message, "Fetchnotes", "");
	}
	public PushMessage(String message, String from, String subject) {
		this.message = message;
		this.from = from;
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void show() {
		Context context = ForgeApp.getApp();
		Intent intent = new Intent(context, ForgeApp.getActivity().getClass());
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
			.setSmallIcon(ForgeApp.getResourceId("icons", "drawable"))
			.setDefaults(Notification.DEFAULT_VIBRATE)
			.setContentIntent(pendingIntent)
			.setAutoCancel(true)
			.setContentTitle(from)
			.setContentText(message);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(1, mBuilder.getNotification());
	}
	
}
