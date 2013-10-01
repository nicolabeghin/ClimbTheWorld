package org.unipd.nbeghin.climbtheworld;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.db.DbHelper;
import org.unipd.nbeghin.climbtheworld.db.PreExistingDbLoader;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Climbing;
import org.unipd.nbeghin.climbtheworld.models.Tour;
import org.unipd.nbeghin.climbtheworld.ui.card.BuildingCard;
import org.unipd.nbeghin.climbtheworld.ui.card.TourCard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fima.cardsui.views.CardUI;
import com.j256.ormlite.dao.RuntimeExceptionDao;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	private static final String		APP_TITLE				= "Climb the world";
	public static final String		AppName					= "ClimbTheWorld";
	public CardUI					buildingCards;
	public CardUI					toursCards;
	public static List<Building>			buildings;
	private List<Climbing>			climbings;
	private List<Tour>				tours;
	private ActionBar				ab;
	private String[]				mPlanetTitles;
	private DrawerLayout			mDrawerLayout;
	private ListView				mDrawerList;
	private ActionBarDrawerToggle	mDrawerToggle;
	public static final String		building_intent_object	= "org.unipd.nbeghin.climbtheworld.intents.object.building";
	public static RuntimeExceptionDao<Building, Integer>	buildingDao;
	public static RuntimeExceptionDao<Climbing, Integer>	climbingDao;
	public static RuntimeExceptionDao<Tour, Integer>	tourDao;

	private class LoadBuildingsTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (final Building building : buildings) {
				BuildingCard buildingCard = new BuildingCard(building);
				buildingCard.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getApplicationContext(), ClimbActivity.class);
						intent.putExtra(building_intent_object, building.get_id());
						startActivity(intent);
					}
				});
				buildingCards.addCard(buildingCard);
			}
			buildingCards.refresh();
			return null;
		}
	}

	private class LoadToursTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (Tour tour : tours) {
				toursCards.addCard(new TourCard(tour));
			}
			toursCards.refresh();
			return null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ab = getSupportActionBar();
		ab.setHomeButtonEnabled(false);
		ab.setTitle(APP_TITLE);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mPlanetTitles = new String[] { "test1", "test2" };
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
		// set refs
		buildingCards = (CardUI) findViewById(R.id.cardsviewBuildings);
		buildingCards.setSwipeable(false);
		// instance entities from db (to be moved inside AsyncTask)
		loadDb();
		new LoadBuildingsTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar, menu);
		return true;
	}

	private void loadDb() {
		PreExistingDbLoader preExistingDbLoader = new PreExistingDbLoader(getApplicationContext());
		SQLiteDatabase db = preExistingDbLoader.getReadableDatabase();
		db.close();
		DbHelper dbHelper = new DbHelper(getApplicationContext());
		buildingDao = dbHelper.getBuildingDao();
		climbingDao = dbHelper.getClimbingDao();
		tourDao = dbHelper.getTourDao();
		buildings = buildingDao.queryForAll();
		climbings = climbingDao.queryForAll();
		tours = tourDao.queryForAll();
		Log.i(AppName, buildings.size() + " buildings detected");
		Log.i(AppName, climbings.size() + " climbings detected");
		Log.i(AppName, tours.size() + " tours detected");
	}

	public void onButton1Click(View v) {
		Log.i(AppName, "Click");
		loadDb();
	}
}
