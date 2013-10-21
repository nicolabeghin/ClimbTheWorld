package org.unipd.nbeghin.climbtheworld.util;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.exceptions.NoFBSession;
import org.unipd.nbeghin.climbtheworld.models.Climbing;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphObject;

public class FacebookUtils {
	private Context	context;

	public FacebookUtils(Context context) {
		this.context = context;
	}

	private void showPublishResult(GraphObject result, FacebookRequestError error) {
		if (error == null) {
			Log.i(MainActivity.AppName, "Posted on Facebook wall");
			Toast.makeText(context, "Posted on your wall", Toast.LENGTH_SHORT).show();
		} else {
			Log.e(MainActivity.AppName, "Unable to post on Facebook wall: " + error.getErrorMessage());
			Toast.makeText(context, "Unable to post on your wall: " + error.getErrorMessage(), Toast.LENGTH_LONG).show();
		}
	}

	protected boolean hasPublishPermission() {
		Session session = Session.getActiveSession();
		return session != null && session.getPermissions().contains("publish_actions");
	}

	public void postToWall(Climbing climbing) throws NoFBSession {
		Log.i(MainActivity.AppName, "Posting on FB wall");
		Session session = Session.getActiveSession();
		if (session != null) {
			if (hasPublishPermission()) {
				Request.newStatusUpdateRequest(Session.getActiveSession(), climbing.getFBStatusMessage(), new Request.Callback() {
					@Override
					public void onCompleted(Response response) {
						showPublishResult(response.getGraphObject(), response.getError());
					}
				}).executeAsync();
				return;
			} else {
				Log.e(MainActivity.AppName, "No publish permission");
				Toast.makeText(context, "No publish permission", Toast.LENGTH_LONG).show();
				return;
			}
		} else {
			throw new NoFBSession();
		}
	}
}
