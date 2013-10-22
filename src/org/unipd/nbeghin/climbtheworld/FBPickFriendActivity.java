package org.unipd.nbeghin.climbtheworld;

import java.util.ArrayList;
import java.util.Collection;

import org.unipd.nbeghin.climbtheworld.exceptions.NoFBSession;
import org.unipd.nbeghin.climbtheworld.util.FacebookUtils;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.model.GraphUser;
import com.facebook.widget.FriendPickerFragment;
import com.facebook.widget.PickerFragment;

public class FBPickFriendActivity extends BaseFBActivity {
	private FriendPickerFragment	friendPickerFragment;
	private Collection<GraphUser>	selectedUsers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fb_pick_friend);
		// Show the Up button in the action bar.
		// setupActionBar();
		if (ensureOpenSession()) {
			FragmentManager fm = getSupportFragmentManager();
			if (savedInstanceState == null) {
				// First time through, we create our fragment programmatically.
				final Bundle args = getIntent().getExtras();
				friendPickerFragment = new FriendPickerFragment(args);
				fm.beginTransaction().add(R.id.friend_picker_fragment, friendPickerFragment).commit();
			} else {
				// Subsequent times, our fragment is recreated by the framework and already has saved and
				// restored its state, so we don't need to specify args again. (In fact, this might be
				// incorrect if the fragment was modified programmatically since it was created.)
				friendPickerFragment = (FriendPickerFragment) fm.findFragmentById(R.id.friend_picker_fragment);
			}
			friendPickerFragment.setOnErrorListener(new PickerFragment.OnErrorListener() {
				@Override
				public void onError(PickerFragment<?> fragment, FacebookException error) {
					FBPickFriendActivity.this.onError(error);
				}
			});
			friendPickerFragment.setOnDoneButtonClickedListener(new PickerFragment.OnDoneButtonClickedListener() {
				@Override
				public void onDoneButtonClicked(PickerFragment<?> fragment) {
					selectedUsers = friendPickerFragment.getSelection();
					FacebookUtils fb=new FacebookUtils(getApplicationContext());
					setResult(RESULT_OK, null);
					displaySelectedFriends();
					try {
						fb.inviteFriends(selectedUsers);
					} catch (NoFBSession e) {
						Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
						startActivity(intent);
					}
					finish();
				}
			});
		}
	}

	private void displaySelectedFriends() {
		String results = "";
		if (selectedUsers != null && selectedUsers.size() > 0) {
			ArrayList<String> names = new ArrayList<String>();
			for (GraphUser user : selectedUsers) {
				Log.i(MainActivity.AppName, user.getName());
			}
			results = TextUtils.join(", ", names);
		} else {
			results = "<No friends selected>";
		}
	}

	private void onError(Exception error) {
		Toast toast = Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	protected void onStart() {
		super.onStart();
		try {
			// Load data, unless a query has already taken place.
			friendPickerFragment.loadData(false);
		} catch (Exception ex) {
			onError(ex);
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fbpick_friend, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
