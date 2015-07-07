package io.trigger.forge.android.modules.push;

import io.trigger.forge.android.core.ForgeApp;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class Notification {

	private String message;
	private String from;
	private String subject;
	
	
	public Notification(String message) {
		this(message, "Fetchnotes", "");
	}
	public Notification(String message, String from, String subject) {
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
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(ForgeApp.getResourceId("icons", "drawable"))
            .setContentTitle(from)
            .setContentText(message);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.getNotification());
    }
	
}
