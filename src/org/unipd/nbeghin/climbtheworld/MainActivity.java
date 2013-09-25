package org.unipd.nbeghin.climbtheworld;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.db.DbHelper;
import org.unipd.nbeghin.climbtheworld.db.PreExistingDbLoader;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Climbing;
import org.unipd.nbeghin.climbtheworld.models.Tour;
import org.unipd.nbeghin.climbtheworld.ui.card.BuildingCard;
import org.unipd.nbeghin.climbtheworld.ui.card.TourCard;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.fima.cardsui.views.CardUI;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MainActivity extends ActionBarActivity {
	private static final String	APP_TITLE	= "Climb the world";
	public static final String	AppName	= "ClimbTheWorld";
	public CardUI				buildingCards;
	public CardUI				toursCards;
	private List<Building>		buildings;
	private List<Climbing>		climbings;
	private List<Tour>		tours;
	private ActionBar ab;
	
	private class LoadBuildingsTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (Building building : buildings) {
				buildingCards.addCard(new BuildingCard(building));				
			}
			buildingCards.refresh();
			return null;
		}
	}

	private class LoadToursTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (Tour tour: tours) {
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
        ab.setTitle(APP_TITLE);
        ab.setHomeButtonEnabled(true);
		buildingCards = (CardUI) findViewById(R.id.cardsviewBuildings);
		buildingCards.setSwipeable(false);
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
		RuntimeExceptionDao<Building, Integer> buildingDao = dbHelper.getBuildingDao();
		RuntimeExceptionDao<Climbing, Integer> climbingDao = dbHelper.getClimbingDao();
		RuntimeExceptionDao<Tour, Integer> tourDao = dbHelper.getTourDao();
		buildings = buildingDao.queryForAll();
		climbings = climbingDao.queryForAll();
		tours = tourDao.queryForAll();
		Log.i(AppName, buildings.size() + " buildings detected");
		Log.i(AppName, climbings.size() + " climbings detected");
		Log.i(AppName, tours.size() + " tours detected");
		if (climbings.size() > 0) System.out.println(climbings.get(0).getBuilding().getName());
	}

	public void onButton1Click(View v) {
		Log.i(AppName, "Click");
		loadDb();
	}
}
