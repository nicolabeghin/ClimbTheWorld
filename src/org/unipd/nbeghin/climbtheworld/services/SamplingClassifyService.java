package org.unipd.nbeghin.climbtheworld.services;

import org.unipd.nbeghin.climbtheworld.ClimbActivity;
import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.listeners.AccelerometerClassifyListener;
import org.unipd.nbeghin.climbtheworld.listeners.AccelerometerSamplingRateDetect;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Background service to classify collected samples
 *
 */
public class SamplingClassifyService extends IntentService {
	private AccelerometerClassifyListener	accelerometerListener; // assigned listener
	public static Sensor					mAccelerometer;
	public static Sensor					mRotationVector;
	private SensorManager					mSensorManager;
	private int								notification_id	= 1;
	private int								sensorDelay		= SensorManager.SENSOR_DELAY_NORMAL;
	private NotificationManager				notificationManager;

	public SamplingClassifyService() {
		super("SamplingClassifyService");
	}

	@Override
	public void onCreate() {
		try {
			notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			showNotification(); // show notification about background classifier
			mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
			Log.i(MainActivity.AppName, "Sensor manager instanced");
			mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			Log.i(MainActivity.AppName, "Accelerometer instanced");
			mRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
			Log.i(MainActivity.AppName, "Rotation Vector istantiated");
			accelerometerListener = new AccelerometerClassifyListener(getApplicationContext(), this);
			Log.i(MainActivity.AppName, "Accelerometer listener instanced");
		} catch (Exception e) {
			Log.e(MainActivity.AppName, e.getMessage());
		}
	}

	/**
	 * Show a notification while this service is running
	 */
	private void showNotification() {
		CharSequence text = "Accelbench classifying enabled";
		Notification notification = new Notification(R.drawable.ic_launcher, text, System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(getApplicationContext(), "Accelbench classifying", text, contentIntent);
		notificationManager.notify(notification_id, notification);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		startAccelerometer();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		stopAccelerometer();
		notificationManager.cancel(notification_id);
	}

	@Override
	protected void onHandleIntent(Intent workIntent) {
		Log.i(MainActivity.AppName, "handleIntent");
	}

	public void startAccelerometer() {
		Log.i(MainActivity.AppName, "Registering accelerometer listener");
		mSensorManager.registerListener(accelerometerListener, mAccelerometer, 20000); // SensorManager.SENSOR_DELAY_NORMAL
		mSensorManager.registerListener(accelerometerListener, mRotationVector, 20000);
	}

	public void stopAccelerometer() {
		Log.i(MainActivity.AppName, "Un-registering accelerometer listener");
		mSensorManager.unregisterListener(accelerometerListener, mAccelerometer);
		mSensorManager.unregisterListener(accelerometerListener, mRotationVector);
	}
}
