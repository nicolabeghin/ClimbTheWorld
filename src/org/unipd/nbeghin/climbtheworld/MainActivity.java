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

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	private static final String									APP_TITLE						= "Climb the world";
	public static final String									AppName							= "ClimbTheWorld";
	public CardUI												toursCards;
	public static List<Building>								buildings;
	private List<Climbing>										climbings;
	public static List<Tour>									tours;
	private ActionBar											ab;
	public static RuntimeExceptionDao<Building, Integer>		buildingDao;
	public static RuntimeExceptionDao<Climbing, Integer>		climbingDao;
	public static RuntimeExceptionDao<Tour, Integer>			tourDao;
	public static RuntimeExceptionDao<BuildingTour, Integer>	buildingTourDao;
	public static final String									settings_file					= "ClimbTheWorldPreferences";
	public static final String									settings_detected_sampling_rate	= "samplingRate";
	private List<Fragment>										fragments						= new Vector<Fragment>();
	private PagerAdapter										mPagerAdapter;
	public static final String									building_intent_object			= "org.unipd.nbeghin.climbtheworld.intents.object.building";
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
		loadDb();
		// loading fragments
		fragments.add(Fragment.instantiate(this, BuildingsFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, ToursFragment.class.getName()));
		mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		mPager = (ViewPager) super.findViewById(R.id.pager);
		mPager.setAdapter(this.mPagerAdapter);
		sContext = getApplicationContext();
	}

	public static Context getContext() {
		return sContext;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar, menu);
		return true;
	}

	public static Climbing getClimbingForBuilding(int building_id) {
		Map<String, Object> conditions=new HashMap<String, Object> ();
		conditions.put("building_id", building_id);
		List<Climbing> climbings=climbingDao.queryForFieldValuesArgs(conditions);
		if (climbings.size()==0) return null;
		return climbings.get(0);
	}
	
	private void loadDb() {
		PreExistingDbLoader preExistingDbLoader = new PreExistingDbLoader(getApplicationContext());
		SQLiteDatabase db = preExistingDbLoader.getReadableDatabase();
		db.close();
		DbHelper dbHelper = new DbHelper(getApplicationContext());
		buildingDao = dbHelper.getBuildingDao();
		climbingDao = dbHelper.getClimbingDao();
		tourDao = dbHelper.getTourDao();
		buildingTourDao = dbHelper.getBuildingTourDao();
//		buildings = buildingDao.queryForAll();
		climbings = climbingDao.queryForAll();
		refreshBuildings();
		refreshTours();
//		tours = tourDao.queryForAll();
	}

	public void onShowActionProfile(MenuItem v) {
		Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
		startActivity(intent);
	}

	public void onShowSettings(MenuItem v) {
		Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
		startActivity(intent);
	}
	
	public static void refreshBuildings() {
		buildings=buildingDao.queryForAll();
	}
	
	public static void refreshTours() {
		tours=tourDao.queryForAll();
	}
	
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
