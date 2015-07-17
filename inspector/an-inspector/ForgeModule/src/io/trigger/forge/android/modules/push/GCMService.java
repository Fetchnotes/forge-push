package io.trigger.forge.android.modules.push;

import io.trigger.forge.android.core.ForgeApp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.kinvey.android.push.KinveyGCMService;

public class GCMService extends KinveyGCMService {
	
	private static final Gson gson = new Gson();

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
		PushMessage notification;
		try {
			notification = gson.fromJson(message, PushMessage.class);
		} catch (JsonSyntaxException e) {
			notification = new PushMessage(message);
		}
		notification.show();
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

}
