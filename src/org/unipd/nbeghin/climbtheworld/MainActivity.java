package org.unipd.nbeghin.climbtheworld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.unipd.nbeghin.climbtheworld.db.DbHelper;
import org.unipd.nbeghin.climbtheworld.db.PreExistingDbLoader;
import org.unipd.nbeghin.climbtheworld.fragments.BuildingsForTourFragment;
import org.unipd.nbeghin.climbtheworld.fragments.BuildingsFragment;
import org.unipd.nbeghin.climbtheworld.fragments.ToursFragment;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.BuildingTour;
import org.unipd.nbeghin.climbtheworld.models.Climbing;
import org.unipd.nbeghin.climbtheworld.models.Tour;
import org.unipd.nbeghin.climbtheworld.util.PagerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fima.cardsui.views.CardUI;
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
	private List<Climbing>										climbings; // list of loaded climbings
	public static List<Tour>									tours; // list of loaded tours
	private ActionBar											ab; // reference to action bar
	public static RuntimeExceptionDao<Building, Integer>		buildingDao; // DAO for buildings
	public static RuntimeExceptionDao<Climbing, Integer>		climbingDao; // DAO for climbings
	public static RuntimeExceptionDao<Tour, Integer>			tourDao; // DAO for tours
	public static RuntimeExceptionDao<BuildingTour, Integer>	buildingTourDao; // DAO for building_tours
	public static final String									settings_file					= "ClimbTheWorldPreferences";
	public static final String									settings_detected_sampling_rate	= "samplingRate";
	private List<Fragment>										fragments						= new Vector<Fragment>(); // list of fragments to be loaded
	private PagerAdapter										mPagerAdapter; // page adapter for ViewPager
	public static final String									building_intent_object			= "org.unipd.nbeghin.climbtheworld.intents.object.building"; // intent key for sending building id
	private ViewPager											mPager;
	private static Context sContext;
	
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
	 * Check and return if a climbing exists for given building
	 * Returns null if no climbing exists yet
	 * 
	 * @param building_id building ID
	 * @return Climbing
	 */
	public static Climbing getClimbingForBuilding(int building_id) {
		Map<String, Object> conditions=new HashMap<String, Object> ();
		conditions.put("building_id", building_id); // filter for building ID
		List<Climbing> climbings=climbingDao.queryForFieldValuesArgs(conditions);
		if (climbings.size()==0) return null;
		return climbings.get(0);
	}
	
	/**
	 * Load db and setup DAOs
	 * NB: extracts DB from assets/databases/ClimbTheWorld.zip
	 */
	private void loadDb() {
		PreExistingDbLoader preExistingDbLoader = new PreExistingDbLoader(getApplicationContext()); // extract db from zip
		SQLiteDatabase db = preExistingDbLoader.getReadableDatabase();
		db.close(); // close connection to extracted db
		DbHelper dbHelper = new DbHelper(getApplicationContext()); // instance new db connection to now-standard db
		buildingDao = dbHelper.getBuildingDao(); // create building DAO
		climbingDao = dbHelper.getClimbingDao(); // create climbing DAO
		tourDao = dbHelper.getTourDao(); // create tour DAO
		buildingTourDao = dbHelper.getBuildingTourDao(); // create building tour DAO
		climbings = climbingDao.queryForAll(); // loads all climbings
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
		buildings=buildingDao.queryForAll();
	}
	
	/**
	 * Reload all tours
	 */
	public static void refreshTours() {
		tours=tourDao.queryForAll();
	}
	
	/**
	 * Reload buildings and tours
	 */
	public static void refresh() {
		refreshBuildings();
		refreshTours();
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

}
