package org.unipd.nbeghin.climbtheworld;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.unipd.nbeghin.climbtheworld.exceptions.ClimbingNotFound;
import org.unipd.nbeghin.climbtheworld.listeners.AccelerometerSamplingRateDetect;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.ClassifierCircularBuffer;
import org.unipd.nbeghin.climbtheworld.models.Climbing;
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
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VerticalSeekBar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e. status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ClimbActivity extends Activity {
	public static final String		SAMPLING_TYPE				= "ACTION_SAMPLING";
	public static final String		SAMPLING_TYPE_NON_STAIR		= "NON_STAIR";
	public static final String		SAMPLING_DELAY				= "DELAY";
	private boolean					samplingEnabled				= false;
	private static double					detectedSamplingRate		= 0;
	private static int 					samplingDelay;
	private double					minimumSamplingRate			= 13;
	private Intent					backgroundClassifySampler;
	private Intent					backgroundSamplingRateDetector;
	private IntentFilter			classifierFilter			= new IntentFilter(ClassifierCircularBuffer.CLASSIFIER_ACTION);
	private IntentFilter			samplingRateDetectorFilter	= new IntentFilter(AccelerometerSamplingRateDetect.SAMPLING_RATE_ACTION);
	private BroadcastReceiver		classifierReceiver			= new ClassifierReceiver();
	private BroadcastReceiver		sampleRateDetectorReceiver	= new SamplingRateDetectorReceiver();
	private int						num_steps					= 0;
	private double					percentage					= 0.0;
	private Building				building;
	private Climbing				climbing;
	private VerticalSeekBar			seekbarIndicator;
	/**
	 * Whether or not the system UI should be auto-hidden after {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean	AUTO_HIDE					= true;
	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after user interaction before hiding the system UI.
	 */
	private static final int		AUTO_HIDE_DELAY_MILLIS		= 3000;
	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise, will show the system UI visibility upon interaction.
	 */
	private static final boolean	TOGGLE_ON_CLICK				= true;
	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int		HIDER_FLAGS					= 0;
	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider			mSystemUiHider;

	public static double getDetectedSamplingRate() {
		return detectedSamplingRate;
	}
	
	public class ClassifierReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String result = intent.getExtras().getString(ClassifierCircularBuffer.CLASSIFIER_NOTIFICATION_STATUS);
			if (result.equals("STAIR")) {
				// num_steps++;
				num_steps += 100; // to be removed
				seekbarIndicator.setProgress(num_steps);
				percentage = (double) num_steps / (double) building.getSteps();
				boolean win = (num_steps >= building.getSteps());
				if (win) {
					num_steps = building.getSteps(); // ensure it did not exceed the number of steps (when multiple steps-at-once are detected)
					percentage = 100.00;
				}
				updateStats();
				if (win) {
					stopClassify();
					// animate "ready to climb" text
					Toast.makeText(getApplicationContext(), "You successfully climbed " + building.getSteps() + " steps (" + building.getHeight() + "m) of " + building.getName() + "!",
							Toast.LENGTH_LONG).show();
					findViewById(R.id.lblWin).setVisibility(View.VISIBLE);
					findViewById(R.id.lblWin).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink));
					findViewById(R.id.btnStartClimbing).setEnabled(false);
				}
			}
			((TextView) findViewById(R.id.lblClassifierOutput)).setText(result);
		}
	}

	public class SamplingRateDetectorReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			detectedSamplingRate = intent.getExtras().getDouble(AccelerometerSamplingRateDetect.SAMPLING_RATE);
			samplingDelay = intent.getExtras().getInt(SAMPLING_DELAY);
			Log.i(MainActivity.AppName, "Detected sampling rate: " + Double.toString(detectedSamplingRate) + "Hz");
			if (detectedSamplingRate >= minimumSamplingRate) { // sampling rate high enough
//				getSharedPreferences(name, mode)
//				SharedPreferences settings = getSharedPreferences(MainActivity.settings_file, 0);
				SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
				editor.putFloat("detectedSamplingRate", (float) detectedSamplingRate); // store detected sampling rate
				editor.putInt("sensor_delay", samplingDelay); // store used sampling delay
				editor.apply();
				Log.i(MainActivity.AppName, "Stored detected sampling rate");
				stopService(backgroundSamplingRateDetector);
				unregisterReceiver(this);
				setupByDetectedSamplingRate();
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
					((TextView) findViewById(R.id.lblSamplingRateDetected)).setText("TOO LOW: " + (int) detectedSamplingRate + " Hz");
					AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
					alert.setTitle("Sampling rate not high enough");
					alert.setMessage("Your accelerometer is not fast enough for this application. Make sure to use at least " + minimumSamplingRate + " Hz");
					alert.show();
				}
			}
		}
	}

	private void updateStats() {
		((TextView) findViewById(R.id.lblNumSteps)).setText(Integer.toString(num_steps) + " of " + Integer.toString(building.getSteps()) + " ("
				+ (new DecimalFormat("#.##")).format(percentage * 100.00) + "%)");
	}

	private void setupByDetectedSamplingRate() {
		backgroundClassifySampler.putExtra(AccelerometerSamplingRateDetect.SAMPLING_RATE, detectedSamplingRate);
		backgroundClassifySampler.putExtra(SAMPLING_DELAY, samplingDelay);
		Toast.makeText(getApplicationContext(), "Start climbing some stairs!", Toast.LENGTH_LONG).show();
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
		// app-specific logic
		seekbarIndicator = (VerticalSeekBar) findViewById(R.id.seekBarPosition);
		seekbarIndicator.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
			}
		});
		int building_id = getIntent().getIntExtra(MainActivity.building_intent_object, 0);
		try {
			// get building
			if (building_id == 0) throw new Exception("ERROR: unable to get intent data");
			building = MainActivity.buildingDao.queryForId(building_id);
			setup_from_building();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backgroundClassifySampler = new Intent(this, SamplingClassifyService.class); // instance (without starting) background classifier
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		detectedSamplingRate = settings.getFloat("detectedSamplingRate", 0.00f);
		samplingDelay = settings.getInt("sensor_delay", 0);
		Log.i(MainActivity.AppName, "Previous detected sampling rate of " + Double.toString(detectedSamplingRate) + "Hz");
		if (samplingDelay == 0 || detectedSamplingRate < minimumSamplingRate) { // start sampling rate detector
			backgroundSamplingRateDetector = new Intent(this, SamplingRateDetectorService.class); // instance (without starting) background sampling rate detected
			backgroundSamplingRateDetector.putExtra(SAMPLING_DELAY, SensorManager.SENSOR_DELAY_NORMAL);
			registerReceiver(sampleRateDetectorReceiver, samplingRateDetectorFilter);
			startService(backgroundSamplingRateDetector); // start background service
		} else {
			Log.i(MainActivity.AppName, "Using the previously detected sampling rate");
			setupByDetectedSamplingRate();
		}
	}

	private void setup_from_building() {
		// get building photo photo resource
		int imageId = getApplicationContext().getResources().getIdentifier(building.getPhoto(), "drawable", getApplicationContext().getPackageName());
		if (imageId > 0) ((ImageView) findViewById(R.id.buildingPhoto)).setImageResource(imageId);
		// set building info
		((TextView) findViewById(R.id.lblBuildingName)).setText(building.getName() + " (" + building.getLocation() + ")");
		((TextView) findViewById(R.id.lblNumSteps)).setText(Integer.toString(building.getSteps()) + " steps");
		((TextView) findViewById(R.id.lblHeight)).setText(Integer.toString(building.getHeight()) + "mt");
		try { // get previous climbing
			loadPreviousClimbing();
		} catch (ClimbingNotFound e) {
			Log.i(MainActivity.AppName, "No previous climbing found");
			climbing = new Climbing();
			climbing.setBuilding(building);
			climbing.setCompleted(0);
			climbing.setCompleted_steps(0);
			climbing.setCreated(new Date().getTime());
			climbing.setModified(new Date().getTime());
			seekbarIndicator.setMax(building.getSteps());
			seekbarIndicator.setProgress(climbing.getCompleted_steps());
			MainActivity.climbingDao.create(climbing);
			Log.i(MainActivity.AppName, "Created new climbing #" + climbing.get_id());
		}
		// animate "ready to climb" text
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_slide_in_top);
		anim.setDuration(2500);
		findViewById(R.id.lblReadyToClimb).startAnimation(anim);
	}

	private void loadPreviousClimbing() throws ClimbingNotFound {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("building_id", building.get_id());
		List<Climbing> climbings = MainActivity.climbingDao.queryForFieldValues(conditions);
		if (climbings.isEmpty()) throw new ClimbingNotFound();
		climbing = climbings.get(0);
		num_steps = climbing.getCompleted_steps();
		percentage = climbing.getPercentage();
		seekbarIndicator.setMax(building.getSteps());
		seekbarIndicator.setProgress(climbing.getCompleted_steps());
		updateStats();
		Log.i(MainActivity.AppName, "Loaded existing climbing (#" + climbing.get_id() + ")");
		if (percentage >= 100) {
			findViewById(R.id.btnStartClimbing).setVisibility(View.INVISIBLE);
		}
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
		} else {
			startClassifyService();
		}
	}

	private void stopAllServices() {
		try {
			unregisterReceiver(sampleRateDetectorReceiver);
			stopService(backgroundSamplingRateDetector);
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "Sampling rate service not running or unable to stop");
		}
		try {
			unregisterReceiver(classifierReceiver);
			stopService(backgroundClassifySampler);
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "Classifier service not running or unable to stop");
		}
	}

	public void stopClassify() {
		stopService(backgroundClassifySampler); // stop background server
		samplingEnabled = false;
		unregisterReceiver(classifierReceiver);
		((TextView) findViewById(R.id.lblClassifierOutput)).setText("Classifier");
		// update db
		climbing.setModified(new Date().getTime());
		climbing.setCompleted_steps(num_steps);
		climbing.setPercentage(percentage);
		climbing.setRemaining_steps(building.getSteps() - num_steps);
		MainActivity.climbingDao.update(climbing);
		Log.i(MainActivity.AppName, "Updated climbing #" + climbing.get_id());
		samplingEnabled = false;
		((ImageButton) findViewById(R.id.btnStartClimbing)).setImageResource(R.drawable.av_play);
		findViewById(R.id.progressBarClimbing).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_out));
		findViewById(R.id.progressBarClimbing).setVisibility(View.INVISIBLE);
	}

	public void startClassifyService() {
		startService(backgroundClassifySampler); // start background service
		registerReceiver(classifierReceiver, classifierFilter);
		samplingEnabled = true;
		((ImageButton) findViewById(R.id.btnStartClimbing)).setImageResource(R.drawable.av_pause);
		findViewById(R.id.lblReadyToClimb).setVisibility(View.GONE);
		findViewById(R.id.progressBarClimbing).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_in));
		findViewById(R.id.progressBarClimbing).setVisibility(View.VISIBLE);
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
