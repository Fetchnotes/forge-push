package io.trigger.forge.android.modules.push;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.JsonPrimitive;

import io.trigger.forge.android.core.ForgeApp;
import io.trigger.forge.android.core.ForgeTask;

public class API {
	
	private static boolean isRegistered = false;
	
	public static void registerWithAPNS(final ForgeTask task){
		final GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(ForgeApp.getApp());
		try {
			String senderID = ForgeApp.getApp().getString(R.string.gcm_senderid);
			//TODO: the register method is deprecated
			String regid = gcm.register(senderID);
			task.success(regid);
			isRegistered = true;
			task.success(regid);
			ForgeApp.event("onDidRegisterWithAPNS", new JsonPrimitive(regid));
		} catch (IOException e) {
			task.error(e.getMessage());
		}
	}
	public static void checkIfRegisteredWithAPNS(final ForgeTask task){
		task.success(isRegistered);
	}
}
