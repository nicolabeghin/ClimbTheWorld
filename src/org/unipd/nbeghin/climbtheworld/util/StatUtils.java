package org.unipd.nbeghin.climbtheworld.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.exceptions.NoStatFound;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Stat;

import android.util.Log;

import com.j256.ormlite.dao.GenericRawResults;

public class StatUtils {
	/**
	 * Check if a climbing has been performed yesterday
	 * 
	 * @return boolean
	 */
	public static boolean climbedYesterday(int climbing_id) {
//		try {
			return true; // for testing purposes
//			String max_modified = execQuery("SELECT MAX(modified) FROM climbings WHERE id!="+climbing_id);
//			long diff = new Date().getTime() - Long.parseLong(max_modified);
//			Log.i(MainActivity.AppName, "Last climbing diff: "+diff);
//			return diff <= 86400000;
//		} catch (SQLException e) {
//			return false;
//		} catch (NoStatFound e) {
//			return false;
//		}
	}

	public static List<Stat> calculateStats() {
		List<Stat> stats = new ArrayList<Stat>();
		String statName = "Fastest building so far";
		String sql = "SELECT building_id,MIN(completed-created) FROM climbings WHERE completed>created";
		try {
			String building_id = execQuery(sql);
			Building building = MainActivity.buildingDao.queryForId(Integer.valueOf(building_id));
			stats.add(new Stat(statName, building.getName()));
		} catch (NoStatFound e) {
			stats.add(new Stat(statName, "No completed climbing yet"));
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: " + e.getMessage());
		}
		statName = "Climbed buildings";
		sql = "SELECT COUNT(*) FROM climbings WHERE completed>0";
		try {
			String count = execQuery(sql);
			stats.add(new Stat(statName, count + " buildings have been climbed so far"));
		} catch (NoStatFound e) {
			stats.add(new Stat(statName, "No completed climbing yet"));
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: " + e.getMessage());
		}
		statName = "In-progress climbings";
		sql = "SELECT COUNT(*) FROM climbings WHERE completed=0";
		try {
			String count = execQuery(sql);
			stats.add(new Stat(statName, count + " buildings in progress"));
		} catch (NoStatFound e) {
			stats.add(new Stat(statName, "No completed climbing yet"));
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: " + e.getMessage());
		}
		statName = "Climbings so far";
		sql = "SELECT COUNT(*) FROM climbings";
		try {
			String count = execQuery(sql);
			stats.add(new Stat(statName, count + " climbings so far"));
		} catch (NoStatFound e) {
			stats.add(new Stat(statName, "No climbing yet"));
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: " + e.getMessage());
		}
		statName = "Available buildings";
		sql = "SELECT COUNT(*) FROM buildings";
		try {
			String count = execQuery(sql);
			stats.add(new Stat(statName, count + " buildings"));
		} catch (NoStatFound e) {
			stats.add(new Stat(statName, "No building"));
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: " + e.getMessage());
		}
		statName = "Available tours";
		sql = "SELECT COUNT(*) FROM tours";
		try {
			String count = execQuery(sql);
			stats.add(new Stat(statName, count + " tours"));
		} catch (NoStatFound e) {
			stats.add(new Stat(statName, "No tour"));
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "SQL exception: " + e.getMessage());
		}
		return stats;
	}

	private static String execQuery(String sql) throws SQLException, NoStatFound {
		GenericRawResults<String[]> rawResults = MainActivity.climbingDao.queryRaw(sql);
		List<String[]> results = rawResults.getResults();
		if (results.isEmpty()) throw new NoStatFound();
		String[] resultArray = results.get(0);
		return resultArray[0];
	}
}
