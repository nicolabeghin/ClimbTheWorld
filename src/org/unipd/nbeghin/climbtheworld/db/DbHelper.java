package org.unipd.nbeghin.climbtheworld.db;

import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.BuildingTour;
import org.unipd.nbeghin.climbtheworld.models.Climbing;
import org.unipd.nbeghin.climbtheworld.models.Tour;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

public class DbHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "ClimbTheWorld";
    public static final int DATABASE_VERSION = 1;
	private RuntimeExceptionDao<Building, Integer> buildingRuntimeDao = null;
	private RuntimeExceptionDao<Tour, Integer> tourRuntimeDao = null;
	private RuntimeExceptionDao<Climbing, Integer> climbingRuntimeDao = null;
	private RuntimeExceptionDao<BuildingTour, Integer> buildingTourRuntimeDao = null;
	
    public DbHelper(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {		
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

	public RuntimeExceptionDao<Building, Integer> getBuildingDao() {
		if (buildingRuntimeDao == null) {
			buildingRuntimeDao = getRuntimeExceptionDao(Building.class);
		}
		return buildingRuntimeDao;
	}

	public RuntimeExceptionDao<Tour, Integer> getTourDao() {
		if (tourRuntimeDao == null) {
			tourRuntimeDao = getRuntimeExceptionDao(Tour.class);
		}
		return tourRuntimeDao;
	}
	
	public RuntimeExceptionDao<BuildingTour, Integer> getBuildingTourDao() {
		if (buildingTourRuntimeDao == null) {
			buildingTourRuntimeDao = getRuntimeExceptionDao(BuildingTour.class);
		}
		return buildingTourRuntimeDao;
	}
	
	public RuntimeExceptionDao<Climbing, Integer> getClimbingDao() {
		if (climbingRuntimeDao == null) {
			climbingRuntimeDao = getRuntimeExceptionDao(Climbing.class);
		}
		return climbingRuntimeDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		buildingRuntimeDao = null;
		tourRuntimeDao = null;
		climbingRuntimeDao = null;
	}
}
