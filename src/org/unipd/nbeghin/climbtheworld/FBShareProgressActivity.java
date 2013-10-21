package org.unipd.nbeghin.climbtheworld;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.Session;

public class FBShareProgressActivity extends BaseFBActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fb_share_progress);
		Session session = Session.getActiveSession();
		if (session != null && session.isOpened()) {
			makeMeRequest(session);
			onClickPostStatusUpdate();
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
		getMenuInflater().inflate(R.menu.fbshare_progress, menu);
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

	public void onClickPost(View v) {
		onClickPostStatusUpdate();
	}

	private void onClickPostStatusUpdate() {
		Session session = Session.getActiveSession();
		if (session != null && session.isOpened()) {
			performPublish(PendingAction.POST_STATUS_UPDATE, canPresentShareDialog);
		}
	}
}
