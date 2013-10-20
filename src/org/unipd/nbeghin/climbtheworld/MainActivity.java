package org.unipd.nbeghin.climbtheworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.unipd.nbeghin.climbtheworld.adapters.PagerAdapter;
import org.unipd.nbeghin.climbtheworld.db.DbHelper;
import org.unipd.nbeghin.climbtheworld.db.PreExistingDbLoader;
import org.unipd.nbeghin.climbtheworld.fragments.BuildingsFragment;
import org.unipd.nbeghin.climbtheworld.fragments.ToursFragment;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.BuildingTour;
import org.unipd.nbeghin.climbtheworld.models.Climbing;
import org.unipd.nbeghin.climbtheworld.models.Photo;
import org.unipd.nbeghin.climbtheworld.models.Tour;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Main activity
 * 
 */
@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	private static final String									APP_TITLE						= "Climb the world";
	public static final String									AppName							= "ClimbTheWorld";
	public static List<Building>								buildings;
	private List<Climbing>										climbings;																						// list of loaded climbings
	public static List<Tour>									tours;																							// list of loaded tours
	private ActionBar											ab;																							// reference to action bar
	public static RuntimeExceptionDao<Building, Integer>		buildingDao;																					// DAO for buildings
	public static RuntimeExceptionDao<Climbing, Integer>		climbingDao;																					// DAO for climbings
	public static RuntimeExceptionDao<Tour, Integer>			tourDao;																						// DAO for tours
	public static RuntimeExceptionDao<BuildingTour, Integer>	buildingTourDao;																				// DAO for building_tours
	public static RuntimeExceptionDao<Photo, Integer>			photoDao;
	public static final String									settings_file					= "ClimbTheWorldPreferences";
	public static final String									settings_detected_sampling_rate	= "samplingRate";
	private List<Fragment>										fragments						= new Vector<Fragment>();										// list of fragments to be loaded
	private PagerAdapter										mPagerAdapter;																					// page adapter for ViewPager
	public static final String									building_intent_object			= "org.unipd.nbeghin.climbtheworld.intents.object.building";	// intent key for sending building id
	private ViewPager											mPager;
	private static Context										sContext;
	private DbHelper											dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ab = getSupportActionBar();
		// ab.setHomeButtonEnabled(false);
		// ab.setTitle(APP_TITLE);
		// instance entities from db (to be moved inside AsyncTask)
		loadDb(); // instance db connection
		// loading fragments
		fragments.add(Fragment.instantiate(this, BuildingsFragment.class.getName())); // instance building fragments
		fragments.add(Fragment.instantiate(this, ToursFragment.class.getName())); // instance tours fragments
		mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		mPager = (ViewPager) super.findViewById(R.id.pager);
		mPager.setAdapter(this.mPagerAdapter);
		sContext = getApplicationContext();
	}

	/**
	 * Helper method to access application context
	 */
	public static Context getContext() {
		return sContext;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar, menu);
		return true;
	}

	/**
	 * Check and return if a climbing exists for given building Returns null if no climbing exists yet
	 * 
	 * @param building_id building ID
	 * @return Climbing
	 */
	public static Climbing getClimbingForBuilding(int building_id) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("building_id", building_id); // filter for building ID
		List<Climbing> climbings = climbingDao.queryForFieldValuesArgs(conditions);
		if (climbings.size() == 0) return null;
		return climbings.get(0);
	}

	/**
	 * Load db and setup DAOs NB: extracts DB from assets/databases/ClimbTheWorld.zip
	 */
	private void loadDb() {
		PreExistingDbLoader preExistingDbLoader = new PreExistingDbLoader(getApplicationContext()); // extract db from zip
		SQLiteDatabase db = preExistingDbLoader.getReadableDatabase();
		db.close(); // close connection to extracted db
		dbHelper = new DbHelper(getApplicationContext()); // instance new db connection to now-standard db
		buildingDao = dbHelper.getBuildingDao(); // create building DAO
		climbingDao = dbHelper.getClimbingDao(); // create climbing DAO
		tourDao = dbHelper.getTourDao(); // create tour DAO
		buildingTourDao = dbHelper.getBuildingTourDao(); // create building tour DAO
		photoDao = dbHelper.getPhotoDao();
		refresh(); // loads all buildings and tours
	}

	public void onShowActionProfile(MenuItem v) {
		Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
		startActivity(intent);
	}

	public void onShowSettings(MenuItem v) {
		Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
		startActivity(intent);
	}

	/**
	 * Reload all buildings
	 */
	public static void refreshBuildings() {
		buildings = buildingDao.queryForAll();
	}

	/**
	 * Reload all tours
	 */
	public static void refreshTours() {
		tours = tourDao.queryForAll();
	}

	/**
	 * Reload buildings and tours
	 */
	public static void refresh() {
		refreshBuildings();
		refreshTours();
	}

	public void onBtnShowGallery(View v) {
		Intent intent = new Intent(sContext, GalleryActivity.class);
		intent.putExtra("building_id", 1);
		startActivity(intent);
	}

	public static List<BuildingTour> getBuildingsForTour(int tour_id) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("tour_id", tour_id);
		return buildingTourDao.queryForFieldValuesArgs(conditions); // get all buildings associated to a tour
	}

	public static int getBuildingImageResource(Building building) {
		return getContext().getResources().getIdentifier(building.getPhoto(), "drawable", getContext().getPackageName());
	}

	public static List<Integer> getBuildingPhotosForTour(int tour_id) {
		List<Integer> images = new ArrayList<Integer>();
		List<BuildingTour> buildingsTour = getBuildingsForTour(tour_id);
		for (BuildingTour buildingTour : buildingsTour) {
			images.add(getBuildingImageResource(buildingTour.getBuilding()));
		}
		return images;
	}

	@Override
	protected void onResume() {
		Log.i(MainActivity.AppName, "onResume MainActivity");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(MainActivity.AppName, "onPause MainActivity");
		super.onPause();
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	public void onShareDb(MenuItem v) {
		shareDb();
	}
	
	private void shareDb() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String output_name = "ClimbTheWorld_" + df.format(new Date()) + ".db";
		try {
			File file = new File(dbHelper.getDbPath()); // get private db reference
			if (file.exists() == false || file.length() == 0) throw new Exception("Empty DB");
			this.copyFile(new FileInputStream(file), this.openFileOutput(output_name, MODE_WORLD_READABLE));
			file = this.getFileStreamPath(output_name);
			Intent i = new Intent(Intent.ACTION_SEND);
			i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
			i.setType("*/*");
			startActivity(Intent.createChooser(i, "Share to"));
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Unable to export db: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			Log.e(AppName, e.getMessage());
		}
	}
}
