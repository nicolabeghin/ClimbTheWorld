package org.unipd.nbeghin.climbtheworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.unipd.nbeghin.climbtheworld.comparator.BuildingTourComparator;
import org.unipd.nbeghin.climbtheworld.fragments.BuildingsForTourFragment;
import org.unipd.nbeghin.climbtheworld.fragments.ToursFragment;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.BuildingTour;
import org.unipd.nbeghin.climbtheworld.models.Tour;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TourDetailActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int tour_id=getIntent().getIntExtra(ToursFragment.tour_intent_object, 0);
		setContentView(R.layout.activity_tour_detail);

		if (tour_id>0) {
			Log.i(MainActivity.AppName, "Loading buildings for tour "+tour_id);
			Map<String, Object> conditions=new HashMap<String, Object> ();
			conditions.put("tour_id", tour_id);
			List<BuildingTour> buildingTours=MainActivity.buildingTourDao.queryForFieldValuesArgs(conditions);
			Log.i(MainActivity.AppName, "Detected "+buildingTours.size()+" building for tour #"+tour_id);
			Collections.sort(buildingTours, new BuildingTourComparator());
			BuildingsForTourFragment fragment=(BuildingsForTourFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentBuildingsForTour);
			List<Building> buildings=new ArrayList<Building>();
			for(BuildingTour buildingTour: buildingTours) {
				buildings.add(buildingTour.getBuilding());
			}
			fragment.loadBuildings(buildings);
		} else {
			Log.e(MainActivity.AppName, "No tour ID detected");
		}
		// ((TextView) findViewById(R.id.tourTitle)).setText(tour.getTitle());
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
		// getMenuInflater().inflate(R.menu.tour_detail, menu);
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
