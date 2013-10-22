package org.unipd.nbeghin.climbtheworld.util;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.exceptions.NoFBSession;
import org.unipd.nbeghin.climbtheworld.models.Climbing;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphObject;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class FacebookUtils {
	private Context	context;

	public FacebookUtils(Context context) {
		this.context = context;
	}

//	private void showPublishResult(GraphObject result, FacebookRequestError error) {
//		if (error == null) {
//			Log.i(MainActivity.AppName, "Posted on Facebook wall");
//			Toast.makeText(context, "Posted on your wall", Toast.LENGTH_SHORT).show();
//		} else {
//			Log.e(MainActivity.AppName, "Unable to post on Facebook wall: " + error.getErrorMessage());
//			Toast.makeText(context, "Unable to post on your wall: " + error.getErrorMessage(), Toast.LENGTH_LONG).show();
//		}
//	}

	protected boolean hasPublishPermission() {
		Session session = Session.getActiveSession();
		return session != null && session.getPermissions().contains("publish_actions");
	}

	private void checkFBSession() throws NoFBSession {
		Session session = Session.getActiveSession();
		if (session == null) throw new NoFBSession();
	}

	public void postToWall(Climbing climbing) throws NoFBSession {
		Log.i(MainActivity.AppName, "Posting on FB wall");
		Bundle params = new Bundle();
		params.putString("name", "ClimbTheWorld");
		params.putString("caption", "Climb the world: a serious game to promote physical activity");
		params.putString("description", climbing.getFBStatusMessage());
		params.putString("link", "https://developers.facebook.com/android");
		params.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");
		publishFeedDialog(params);
	}

	public void publishFeedDialog() throws NoFBSession {
		Bundle params = new Bundle();
		params.putString("name", "ClimbTheWorld");
		params.putString("caption", "Climb the world: a serious game to promote physical activity");
		params.putString("description", "Download ClimbTheWorld from Google Play (FREE)");
		params.putString("link", "https://developers.facebook.com/android");
		params.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");
		publishFeedDialog(params);
	}

	public void publishFeedDialog(Bundle params) throws NoFBSession {
		this.checkFBSession();
		WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(context, Session.getActiveSession(), params)).setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(Bundle values, FacebookException error) {
				if (error == null) {
					final String postId = values.getString("post_id");
					if (postId != null) {
						Toast.makeText(context, "Posted successfully", Toast.LENGTH_SHORT).show();
					} else {
						// User clicked the Cancel button
						Toast.makeText(context.getApplicationContext(), "Publish cancelled", Toast.LENGTH_SHORT).show();
					}
				} else if (error instanceof FacebookOperationCanceledException) {
					// User clicked the "x" button
					Toast.makeText(context.getApplicationContext(), "Publish cancelled", Toast.LENGTH_SHORT).show();
				} else {
					// Generic, ex: network error
					Toast.makeText(context.getApplicationContext(), "Error posting story", Toast.LENGTH_SHORT).show();
				}
			}
		}).build();
		feedDialog.show();
	}
}
