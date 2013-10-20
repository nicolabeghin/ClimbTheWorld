package org.unipd.nbeghin.climbtheworld;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unipd.nbeghin.climbtheworld.adapters.StatAdapter;
import org.unipd.nbeghin.climbtheworld.exceptions.NoStatFound;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Stat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.j256.ormlite.dao.GenericRawResults;

public class ProfileActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		// Show the Up button in the action bar.
		// setupActionBar();
		List<Stat> stats = new ArrayList<Stat>();
		stats.add(new Stat("Nr. of climbed buildings", new Double(5)));
		stats.add(new Stat("Avg. time for 100 steps", new Double(16), R.drawable.device_access_alarms));
		stats.add(new Stat("Fastest building so far", new Double(19), R.drawable.device_access_flash_on));
		stats=calculateStats();
		((ListView) findViewById(R.id.listStatistics)).setAdapter(new StatAdapter(this, R.layout.stat_item, stats));
	}

	private List<Stat> calculateStats() {
		List<Stat> stats = new ArrayList<Stat>();		
		// fastest building so far
		String statName="Fastest building so far";
		String sql="SELECT building_id,MIN(completed-created) FROM climbings WHERE completed>created";
		try {
			String building_id=execQuery(sql);
			Building building=MainActivity.buildingDao.queryForId(Integer.valueOf(building_id));
			stats.add(new Stat(statName, building.getName()));
		} catch(NoStatFound e) {
			stats.add(new Stat(statName, "No completed climbing yet"));
		} catch(Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: "+e.getMessage());
		}
		statName="Climbed buildings";
		sql="SELECT COUNT(*) FROM climbings WHERE completed>0";
		try {
			String count=execQuery(sql);
			stats.add(new Stat(statName, count+" buildings have been climbed so far"));
		} catch(NoStatFound e) {
			stats.add(new Stat(statName, "No completed climbing yet"));
		} catch(Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: "+e.getMessage());
		}
		statName="In-progress climbings";
		sql="SELECT COUNT(*) FROM climbings WHERE completed=0";
		try {
			String count=execQuery(sql);
			stats.add(new Stat(statName, count+" buildings in progress"));
		} catch(NoStatFound e) {
			stats.add(new Stat(statName, "No completed climbing yet"));
		} catch(Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: "+e.getMessage());
		}
		statName="Climbings so far";
		sql="SELECT COUNT(*) FROM climbings";
		try {
			String count=execQuery(sql);
			stats.add(new Stat(statName, count+" climbings so far"));
		} catch(NoStatFound e) {
			stats.add(new Stat(statName, "No climbing yet"));
		} catch(Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: "+e.getMessage());
		}
		statName="Available buildings";
		sql="SELECT COUNT(*) FROM buildings";
		try {
			String count=execQuery(sql);
			stats.add(new Stat(statName, count+" buildings"));
		} catch(NoStatFound e) {
			stats.add(new Stat(statName, "No building"));
		} catch(Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: "+e.getMessage());
		}
		statName="Available tours";
		sql="SELECT COUNT(*) FROM tours";
		try {
			String count=execQuery(sql);
			stats.add(new Stat(statName, count+" tours"));
		} catch(NoStatFound e) {
			stats.add(new Stat(statName, "No tour"));
		} catch(Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: "+e.getMessage());
		}
		return stats;
	}

	private String execQuery(String sql) throws SQLException, NoStatFound {
		GenericRawResults<String[]> rawResults = MainActivity.climbingDao.queryRaw(sql);
		List<String[]> results = rawResults.getResults();
		if (results.isEmpty()) throw new NoStatFound();
		String[] resultArray = results.get(0);
		return resultArray[0];
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
		// getMenuInflater().inflate(R.menu.profile, menu);
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
