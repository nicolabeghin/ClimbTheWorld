package org.unipd.nbeghin.climbtheworld;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.db.DbHelper;
import org.unipd.nbeghin.climbtheworld.db.PreExistingDbLoader;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Climbing;
import org.unipd.nbeghin.climbtheworld.ui.card.BuildingCard;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.fima.cardsui.views.CardUI;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MainActivity extends Activity {
	public static final String	AppName	= "ClimbTheWorld";
	public CardUI				mCardView;
	private List<Building>		buildings;
	private List<Climbing>		climbings;

	private class LoadBuildingsTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			loadDb();
			mCardView = (CardUI) findViewById(R.id.cardsview);
			mCardView.setSwipeable(false);
			for (Building building : buildings) {
				mCardView.addCard(new BuildingCard(building));				
			}
			mCardView.refresh();
			return null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new LoadBuildingsTask().execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void loadDb() {
		PreExistingDbLoader preExistingDbLoader = new PreExistingDbLoader(getApplicationContext());
		SQLiteDatabase db = preExistingDbLoader.getReadableDatabase();
		db.close();
		DbHelper dbHelper = new DbHelper(getApplicationContext());
		RuntimeExceptionDao<Building, Integer> buildingDao = dbHelper.getBuildingDao();
		RuntimeExceptionDao<Climbing, Integer> climbingDao = dbHelper.getClimbingDao();
		buildings = buildingDao.queryForAll();
		climbings = climbingDao.queryForAll();
		Log.i(AppName, buildings.size() + " buildings detected");
		Log.i(AppName, climbings.size() + " climbings detected");
		if (climbings.size() > 0) System.out.println(climbings.get(0).getBuilding().getName());
	}

	public void onButton1Click(View v) {
		Log.i(AppName, "Click");
		loadDb();
	}
}
