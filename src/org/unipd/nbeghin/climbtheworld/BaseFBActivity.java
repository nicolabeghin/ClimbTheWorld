package org.unipd.nbeghin.climbtheworld;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.ProfilePictureView;

public class BaseFBActivity extends FragmentActivity {
	protected UiLifecycleHelper		uiHelper;
	protected static final int		REAUTH_ACTIVITY_CODE	= 100;
	protected static final String	PERMISSION				= "publish_actions";
	protected boolean				canPresentShareDialog = false;
	protected PendingAction pendingAction = PendingAction.NONE;
	protected GraphUser user;

	protected enum PendingAction {
		NONE, POST_PHOTO, POST_STATUS_UPDATE
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(MainActivity.AppName, "opened super");
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
	}

	protected Session.StatusCallback	callback	= new Session.StatusCallback() {
														@Override
														public void call(final Session session, final SessionState state, final Exception exception) {
															onSessionStateChange(session, state, exception);
														}
													};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

	protected void onSessionStateChange(final Session session, SessionState state, Exception exception) {
		if (session != null && session.isOpened()) {
			// Get the user's data.
			makeMeRequest(session);
		}
	}

	protected void makeMeRequest(final Session session) {
		Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
			@Override
			public void onCompleted(GraphUser user, Response response) {
				if (session == Session.getActiveSession()) {
					if (user != null) {
						TextView welcome = (TextView) findViewById(R.id.welcome);
						ProfilePictureView profilePictureView = (ProfilePictureView) findViewById(R.id.fb_profile_picture);
						profilePictureView.setCropped(true);
						profilePictureView.setProfileId(user.getId());
						welcome.setText("Hello " + user.getName() + "!");
					} else
						System.err.println("no user");
				}
				if (response.getError() != null) {
					Log.e(MainActivity.AppName, "FB exception: " + response.getError());
				}
			}
		});
		request.executeAsync();
	}

	protected boolean ensureOpenSession() {
		if (Session.getActiveSession() == null || !Session.getActiveSession().isOpened()) {
			Session.openActiveSession(this, true, new Session.StatusCallback() {
				@Override
				public void call(Session session, SessionState state, Exception exception) {
					onSessionStateChange(session, state, exception);
				}
			});
			return false;
		}
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		uiHelper.onSaveInstanceState(bundle);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	protected boolean hasPublishPermission() {
		Session session = Session.getActiveSession();
		return session != null && session.getPermissions().contains("publish_actions");
	}

	@SuppressWarnings("incomplete-switch")
	protected void handlePendingAction() {
		PendingAction previouslyPendingAction = pendingAction;
		// These actions may re-set pendingAction if they are still pending, but we assume they
		// will succeed.
		pendingAction = PendingAction.NONE;
		switch (previouslyPendingAction) {
			case POST_STATUS_UPDATE:
				postStatusUpdate();
				break;
		}
	}

    private FacebookDialog.ShareDialogBuilder createShareDialogBuilder() {
        return new FacebookDialog.ShareDialogBuilder(this)
                .setName("ClimbTheWorld")
                .setDescription("ClimbTheWorld: a serious game to promote physical activity")
                .setLink("http://www.climbtheworld.com");
    }
    
    private void showPublishResult(String message, GraphObject result, FacebookRequestError error) {
        String title = null;
        String alertMessage = null;
        if (error == null) {
            title = "Pubblicazione ok";
//            String id = result.cast(GraphObjectWithId.class).getId();
            alertMessage = "Pubblicato con successo";
        } else {
            title = "Error";
            alertMessage = error.getErrorMessage();
        }

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(alertMessage)
                .setPositiveButton("Ok", null)
                .show();
    }

    protected void postStatusUpdate() {
        if (canPresentShareDialog) {
            FacebookDialog shareDialog = createShareDialogBuilder().build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else if (hasPublishPermission()) {
            final String message = "messaggio da pubblicare";
            Request request = Request
                    .newStatusUpdateRequest(Session.getActiveSession(), message, new Request.Callback() {
                        @Override
                        public void onCompleted(Response response) {
                            showPublishResult(message, response.getGraphObject(), response.getError());
                        }
                    });
            request.executeAsync();
        } else {
            pendingAction = PendingAction.POST_STATUS_UPDATE;
        }
    }
    
	protected void performPublish(PendingAction action, boolean allowNoSession) {
		Session session = Session.getActiveSession();
		if (session != null) {
			pendingAction = action;
			if (hasPublishPermission()) {
				// We can do the action right away.
				handlePendingAction();
				return;
			} else if (session.isOpened()) {
				// We need to get new permissions, then complete the action when we get called back.
				session.requestNewPublishPermissions(new Session.NewPermissionsRequest(this, PERMISSION));
				return;
			}
		}
		if (allowNoSession) {
			pendingAction = action;
			handlePendingAction();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}
}
