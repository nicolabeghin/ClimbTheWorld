package org.unipd.nbeghin.climbtheworld;

import org.unipd.nbeghin.climbtheworld.listeners.AccelerometerSamplingRateDetect;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.ClassifierCircularBuffer;
import org.unipd.nbeghin.climbtheworld.services.SamplingClassifyService;
import org.unipd.nbeghin.climbtheworld.services.SamplingRateDetectorService;
import org.unipd.nbeghin.climbtheworld.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e. status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ClimbActivity extends Activity {
	public static final String		SAMPLING_TYPE					= "ACTION_SAMPLING";
	public static final String		SAMPLING_TYPE_NON_STAIR			= "NON_STAIR";
	public static final String		SAMPLING_DELAY					= "DELAY";
	private boolean					samplingEnabled					= false;
	private double					detectedSamplingRate			= 0;
	private double					minimumSamplingRate				= 13;
	private Intent					backgroundClassifySampler;
	private Intent					backgroundSamplingRateDetector;
	private IntentFilter			classifierFilter				= new IntentFilter(ClassifierCircularBuffer.CLASSIFIER_ACTION);
	private IntentFilter			samplingRateDetectorFilter		= new IntentFilter(AccelerometerSamplingRateDetect.SAMPLING_RATE_ACTION);
	private BroadcastReceiver		classifierReceiver				= new ClassifierReceiver();
	private BroadcastReceiver		sampleRateDetectorReceiver		= new SamplingRateDetectorReceiver();
	private int						num_steps						= 0;
	/**
	 * Whether or not the system UI should be auto-hidden after {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean	AUTO_HIDE						= true;
	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after user interaction before hiding the system UI.
	 */
	private static final int		AUTO_HIDE_DELAY_MILLIS			= 3000;
	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise, will show the system UI visibility upon interaction.
	 */
	private static final boolean	TOGGLE_ON_CLICK					= true;
	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int		HIDER_FLAGS						= 0;
	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider			mSystemUiHider;

    public class ClassifierReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result=intent.getExtras().getString(ClassifierCircularBuffer.CLASSIFIER_NOTIFICATION_STATUS);
            Log.i(MainActivity.AppName, result);
            if (result.equals("STAIR")) {
                num_steps++;
                ((TextView) findViewById(R.id.lblNumSteps)).setText(Integer.toString(num_steps));
            }
            ((TextView) findViewById(R.id.lblClassifierOutput)).setText(result);
        }
    }

	public class SamplingRateDetectorReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			detectedSamplingRate = intent.getExtras().getDouble(AccelerometerSamplingRateDetect.SAMPLING_RATE);
			Log.i(MainActivity.AppName, Double.toString(detectedSamplingRate));
			if (detectedSamplingRate >= minimumSamplingRate) { // sampling rate high enough
				((TextView) findViewById(R.id.lblSamplingRate)).setText((int) detectedSamplingRate + " Hz");
				backgroundClassifySampler.putExtra(AccelerometerSamplingRateDetect.SAMPLING_RATE, detectedSamplingRate);
				backgroundClassifySampler.putExtra(SAMPLING_DELAY, backgroundSamplingRateDetector.getExtras().getInt(ClimbActivity.SAMPLING_DELAY));
				unregisterReceiver(this);
				stopService(backgroundSamplingRateDetector);
			} else { // sampling rate not high enough: try to decrease the sampling delay
				if (backgroundSamplingRateDetector.getExtras().getInt(ClimbActivity.SAMPLING_DELAY) != SensorManager.SENSOR_DELAY_GAME) {
					Log.w(MainActivity.AppName, "Sampling rate not high enough: trying decreasing the sampling delay");
					stopService(backgroundSamplingRateDetector);
					backgroundSamplingRateDetector.putExtra(SAMPLING_DELAY, SensorManager.SENSOR_DELAY_GAME);
					startService(backgroundSamplingRateDetector);
				} else { // unable to determine a sampling rate high enough for our purposes: stop
					Log.e(MainActivity.AppName, "Sampling rate not high enough for this application");
					unregisterReceiver(this);
					stopService(backgroundSamplingRateDetector);
					((TextView) findViewById(R.id.lblSamplingRate)).setText("TOO LOW: " + (int) detectedSamplingRate + " Hz");
					AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
					alert.setTitle("Sampling rate not high enough");
					alert.setMessage("Your accelerometer is not fast enough for this application. Make sure to use at least " + minimumSamplingRate + " Hz");
					alert.show();
					// Toast.makeText(getApplicationContext(), "Your accelerometer is not fast enough for this application (detected a frequency of "+detectedSamplingRate+"Hz)", Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_climb);
		setupActionBar();
		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.lblReadyToClimb);
		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
			// Cached values.
			int	mControlsHeight;
			int	mShortAnimTime;

			@Override
			@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
			public void onVisibilityChange(boolean visible) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
					// If the ViewPropertyAnimator API is available
					// (Honeycomb MR2 and later), use it to animate the
					// in-layout UI controls at the bottom of the
					// screen.
					if (mControlsHeight == 0) {
						mControlsHeight = controlsView.getHeight();
					}
					if (mShortAnimTime == 0) {
						mShortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
					}
					controlsView.animate().translationY(visible ? 0 : mControlsHeight).setDuration(mShortAnimTime);
				} else {
					// If the ViewPropertyAnimator APIs aren't
					// available, simply show or hide the in-layout UI
					// controls.
					controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
				}
				if (visible && AUTO_HIDE) {
					// Schedule a hide().
					delayedHide(AUTO_HIDE_DELAY_MILLIS);
				}
			}
		});
		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});
		// Upon interacting with UI controls, delay any scheduled hide()
		// operations to prevent the jarring behavior of controls going away
		// while interacting with the UI.
		findViewById(R.id.btnStartClimbing).setOnTouchListener(mDelayHideTouchListener);
		// MainActivity.buildings.get();
		int building_id = getIntent().getIntExtra(MainActivity.building_intent_object, 0);
		try {
			if (building_id == 0) throw new Exception("ERROR: unable to get intent data");
			Building building = MainActivity.buildingDao.queryForId(building_id);
			int imageId = getApplicationContext().getResources().getIdentifier(building.getPhoto(), "drawable", getApplicationContext().getPackageName());
			if (imageId > 0) ((ImageView) findViewById(R.id.buildingPhoto)).setImageResource(imageId);
			Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_slide_in_top);
			anim.setDuration(2500);
			((TextView) findViewById(R.id.lblBuildingName)).setText(building.getName());
			((TextView) findViewById(R.id.lblNumSteps)).setText(Integer.toString(building.getSteps()) + " steps");
			((TextView) findViewById(R.id.lblHeight)).setText(Integer.toString(building.getHeight()) + "mt");
			findViewById(R.id.lblReadyToClimb).startAnimation(anim);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backgroundClassifySampler = new Intent(this, SamplingClassifyService.class); // instance (without starting) background classifier
		backgroundSamplingRateDetector = new Intent(this, SamplingRateDetectorService.class); // instance (without starting) background sampling rate detected
		backgroundSamplingRateDetector.putExtra(SAMPLING_DELAY, SensorManager.SENSOR_DELAY_NORMAL);
		registerReceiver(sampleRateDetectorReceiver, samplingRateDetectorFilter);
		startService(backgroundSamplingRateDetector); // start background service
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
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
				// TODO: If Settings has multiple levels, Up should navigate up
				// that hierarchy.
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the system UI. This is to prevent the jarring behavior of controls going away while interacting with activity UI.
	 */
	View.OnTouchListener	mDelayHideTouchListener	= new View.OnTouchListener() {
														@Override
														public boolean onTouch(View view, MotionEvent motionEvent) {
															if (AUTO_HIDE) {
																delayedHide(AUTO_HIDE_DELAY_MILLIS);
															}
															return false;
														}
													};
	Handler					mHideHandler			= new Handler();
	Runnable				mHideRunnable			= new Runnable() {
														@Override
														public void run() {
															mSystemUiHider.hide();
														}
													};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any previously scheduled calls.
	 */
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}

	public void onBtnStartClimbing(View v) {
		if (detectedSamplingRate == 0 || detectedSamplingRate < minimumSamplingRate) {
			Toast.makeText(getApplicationContext(), "Accelerometer calibration is not ready yet. Please wait", Toast.LENGTH_SHORT).show();
			return;
		}
		if (samplingEnabled) {
			stopClassify();
			samplingEnabled = false;
			((ImageButton) v).setImageResource(R.drawable.av_play);
			findViewById(R.id.progressBarClimbing).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_out));
			findViewById(R.id.progressBarClimbing).setVisibility(View.INVISIBLE);
		} else {
			startClassifyService();
			samplingEnabled = true;
			((ImageButton) v).setImageResource(R.drawable.av_pause);
			findViewById(R.id.lblReadyToClimb).setVisibility(View.GONE);
			findViewById(R.id.progressBarClimbing).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_in));
			findViewById(R.id.progressBarClimbing).setVisibility(View.VISIBLE);
		}
	}

	private void stopAllServices() {
		try {
			stopService(backgroundSamplingRateDetector);
			unregisterReceiver(sampleRateDetectorReceiver);
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "Unable to stop sampling rate detector service");
			e.printStackTrace();
		}
		try {
			stopService(backgroundClassifySampler);
			unregisterReceiver(classifierReceiver);
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "Unable to stop classifier service");
			e.printStackTrace();
		}
	}

	public void stopClassify() {
		stopService(backgroundClassifySampler); // stop background server
		samplingEnabled = false;
		unregisterReceiver(classifierReceiver);
		((TextView) findViewById(R.id.lblClassifierOutput)).setText("Classifier");
	}

	public void startClassifyService() {
		startService(backgroundClassifySampler); // start background service
		registerReceiver(classifierReceiver, classifierFilter);
		num_steps = 0;
		((TextView) findViewById(R.id.lblNumSteps)).setText(Integer.toString(num_steps));
		samplingEnabled = true;
	}

	@Override
	protected void onResume() {
		Log.i(MainActivity.AppName, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(MainActivity.AppName, "onPause");
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		Log.i(MainActivity.AppName, "onDestroy");
		stopAllServices();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		if (samplingEnabled == false) super.onBackPressed();
		else
			Toast.makeText(getApplicationContext(), "Sampling running - Stop it before exiting", Toast.LENGTH_SHORT).show();
	}
}
