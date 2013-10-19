package org.unipd.nbeghin.climbtheworld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.unipd.nbeghin.climbtheworld.adapters.StaggeredPhotoAdapter;
import org.unipd.nbeghin.climbtheworld.models.Photo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.origamilabs.library.views.StaggeredGridView;

public class GalleryActivity extends Activity {
	private ImageLoaderConfiguration	config;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
		setContentView(R.layout.activity_gallery);
		// ImageLoader.getInstance().init(config);
		int building_id=getIntent().getIntExtra("building_id", 0);
		if (building_id!=0) {
			Log.i(MainActivity.AppName, "Loading gallery for building #"+building_id);
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("building_id", building_id); // filter for building ID
			List<Photo> photos = MainActivity.photoDao.queryForFieldValuesArgs(conditions);
			if (photos.isEmpty()==false) { // at least one photo exists for the given building
				setupUIL();
				Log.i(MainActivity.AppName, "Photos found: "+photos.size());
				StaggeredGridView gridView = (StaggeredGridView) this.findViewById(R.id.photoGallery);
				int margin = getResources().getDimensionPixelSize(R.dimen.margin);
				gridView.setItemMargin(margin); // set the GridView margin
				gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well
				StaggeredPhotoAdapter adapter = new StaggeredPhotoAdapter(this, R.id.imageView1, photos);
				gridView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			} else {
				Log.w(MainActivity.AppName, "No photo for building id "+building_id);
			}
		} else {
			Log.w(MainActivity.AppName, "No building id in received intent");
		}
	}

	/**
	 * Setup and configure Universal-image-loader
	 */
	private void setupUIL() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build(); // enable image caching
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);
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
		// getMenuInflater().inflate(R.menu.gallery, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
