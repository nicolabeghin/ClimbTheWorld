package org.unipd.nbeghin.climbtheworld;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.db.DbHelper;
import org.unipd.nbeghin.climbtheworld.db.PreExistingDbLoader;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Climbing;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MainActivity extends Activity {
	
	public static final String AppName="ClimbTheWorld";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onButton1Click(View v) {
		Log.i(AppName, "Click");
		PreExistingDbLoader preExistingDbLoader=new PreExistingDbLoader(getApplicationContext());
		SQLiteDatabase db = preExistingDbLoader.getReadableDatabase();
		db.close();
		DbHelper dbHelper = new DbHelper(getApplicationContext());
		RuntimeExceptionDao<Building, Integer> buildingDao = dbHelper.getBuildingDao();
		RuntimeExceptionDao<Climbing, Integer> climbingDao = dbHelper.getClimbingDao();
		List<Building> buildings = buildingDao.queryForAll();
		List<Climbing> climbings = climbingDao.queryForAll();
		Log.i(AppName, buildings.size()+" buildings detected");
		Log.i(AppName, climbings.size()+" climbings detected");
		if (climbings.size()>0) System.out.println(climbings.get(0).getBuilding().getName());
	}
}
