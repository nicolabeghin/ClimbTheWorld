package org.unipd.nbeghin.climbtheworld;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VerticalSeekBar;

/**
 * Climbing activity: shows a given building and starts classifier. At start it calculates the sampling rate of the device it's run from (only once, after that it just saves the value in standard Android preferences)
 * 
 */
public class ClimbActivity extends Activity {
	public static final String		SAMPLING_TYPE				= "ACTION_SAMPLING";														// intent's action
	public static final String		SAMPLING_TYPE_NON_STAIR		= "NON_STAIR";																// classifier's output
	public static final String		SAMPLING_DELAY				= "DELAY";																	// intent's action
	private boolean					samplingEnabled				= false;																	// sentinel if sampling is running
	private static double			detectedSamplingRate		= 0;																		// detected sampling rate (after sampling rate detector)
	private static int				samplingDelay;																							// current sampling delay (SensorManager)
	private double					minimumSamplingRate			= 13;																		// minimum sampling rate for using this app
	private Intent					backgroundClassifySampler;																				// classifier background service intent
	private Intent					backgroundSamplingRateDetector;																		// sampling rate detector service intent
	private IntentFilter			classifierFilter			= new IntentFilter(ClassifierCircularBuffer.CLASSIFIER_ACTION);			// intent filter (for BroadcastReceiver)
	private IntentFilter			samplingRateDetectorFilter	= new IntentFilter(AccelerometerSamplingRateDetect.SAMPLING_RATE_ACTION);	// intent filter (for BroadcastReceiver)
	private BroadcastReceiver		classifierReceiver			= new ClassifierReceiver();												// implementation of BroadcastReceiver for classifier service
	private BroadcastReceiver		sampleRateDetectorReceiver	= new SamplingRateDetectorReceiver();										// implementation of BroadcastReceiver for sampling rate detector
	private int						num_steps					= 0;																		// number of currently detected steps
	private double					percentage					= 0.0;																		// current progress percentage
	private Building				building;																								// current building
	private Climbing				climbing;																								// current climbing
	private VerticalSeekBar			seekbarIndicator;																						// reference to vertical seekbar
	private int						vstep_for_rstep				= 1;																		// number of virtual step for each real step
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

	// public static double getDetectedSamplingRate() {
	// return detectedSamplingRate;
	// }
	/**
	 * Handles classifier service intents (STAIR/NON_STAIR)
	 * 
	 */
	public class ClassifierReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String result = intent.getExtras().getString(ClassifierCircularBuffer.CLASSIFIER_NOTIFICATION_STATUS);
			if (result.equals("STAIR")) {
				num_steps += vstep_for_rstep; // increase the number of steps
				seekbarIndicator.setProgress(num_steps); // increase the seekbar progress
				percentage = (double) num_steps / (double) building.getSteps(); // increase the progress percentage
				boolean win = (num_steps >= building.getSteps()); // user wins?
				if (win) {
					num_steps = building.getSteps(); // ensure it did not exceed the number of steps (when multiple steps-at-once are detected)
					percentage = 1.00;
				}
				updateStats(); // update the view of current stats
				if (win) {
					stopClassify(); // stop classifier service service
					Toast.makeText(getApplicationContext(), "You successfully climbed " + building.getSteps() + " steps (" + building.getHeight() + "m) of " + building.getName() + "!",
							Toast.LENGTH_LONG).show(); // show completion text
					findViewById(R.id.lblWin).setVisibility(View.VISIBLE); // load and animate completed climbing test
					findViewById(R.id.lblWin).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink));
					findViewById(R.id.btnStartClimbing).setEnabled(false);
					findViewById(R.id.btnAccessPhotoGallery).setVisibility(View.VISIBLE);
				}
			}
			((TextView) findViewById(R.id.lblClassifierOutput)).setText(result); // debug: show currently detected classifier output
		}
	}
	
	private void enableRocket() {
		Animation animSequential = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rocket);
		findViewById(R.id.imgRocket).startAnimation(animSequential);
	}

	/**
	 * Handles sampling rate detector service intents (STAIR/NON_STAIR)
	 * 
	 */
	public class SamplingRateDetectorReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			detectedSamplingRate = intent.getExtras().getDouble(AccelerometerSamplingRateDetect.SAMPLING_RATE); // get detected sampling rate from received intent
			samplingDelay = backgroundSamplingRateDetector.getExtras().getInt(SAMPLING_DELAY); // get used sampling delay from received intent
			Log.i(MainActivity.AppName, "Detected sampling rate: " + Double.toString(detectedSamplingRate) + "Hz");
			if (detectedSamplingRate >= minimumSamplingRate) { // sampling rate high enough
				SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit(); // get refence to android preferences
				editor.putFloat("detectedSamplingRate", (float) detectedSamplingRate); // store detected sampling rate
				editor.putInt("sensor_delay", samplingDelay); // store used sampling delay
				editor.apply(); // commit preferences
				Log.i(MainActivity.AppName, "Stored detected sampling rate of " + detectedSamplingRate + "Hz");
				Log.i(MainActivity.AppName, "Stored sampling delay of " + samplingDelay);
				stopService(backgroundSamplingRateDetector); // stop sampling rate detector service
				unregisterReceiver(this); // unregister listener
				setupByDetectedSamplingRate(); // setup app with detected sampling rate
			} else { // sampling rate not high enough: try to decrease the sampling delay
				if (backgroundSamplingRateDetector.getExtras().getInt(ClimbActivity.SAMPLING_DELAY) != SensorManager.SENSOR_DELAY_GAME) { // decrease sampling delay in order to increase sampling rate
					Log.w(MainActivity.AppName, "Sampling rate not high enough: trying to decrease the sampling delay");
					stopService(backgroundSamplingRateDetector); // stop previous sampling rate detector service
					backgroundSamplingRateDetector.putExtra(SAMPLING_DELAY, SensorManager.SENSOR_DELAY_GAME); // set new sampling delay (lower than the previous one)
					startService(backgroundSamplingRateDetector); // start new sampling rate detector service
				} else { // unable to determine a sampling rate high enough for our purposes: stop
					Log.e(MainActivity.AppName, "Sampling rate not high enough for this application");
					unregisterReceiver(this); // unregister listener
					stopService(backgroundSamplingRateDetector); // stop sampling rate detector service
					((TextView) findViewById(R.id.lblSamplingRateDetected)).setText("TOO LOW: " + (int) detectedSamplingRate + " Hz");
					AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
					alert.setTitle("Sampling rate not high enough");
					alert.setMessage("Your accelerometer is not fast enough for this application. Make sure to use at least " + minimumSamplingRate + " Hz");
					alert.show();
				}
			}
		}
	}

	public void accessPhotoGallery(View v) {
		Intent intent = new Intent(this, GalleryActivity.class);
		intent.putExtra("building_id", building.get_id());
		startActivity(intent);
	}

	/**
	 * Update the stat panel
	 */
	private void updateStats() {
		((TextView) findViewById(R.id.lblNumSteps)).setText(Integer.toString(num_steps) + " of " + Integer.toString(building.getSteps()) + " ("
				+ (new DecimalFormat("#.##")).format(percentage * 100.00) + "%)");
	}

	/**
	 * Setup the activity with a given sampling rate and sampling delay
	 */
	private void setupByDetectedSamplingRate() {
		backgroundClassifySampler.putExtra(AccelerometerSamplingRateDetect.SAMPLING_RATE, detectedSamplingRate);
		backgroundClassifySampler.putExtra(SAMPLING_DELAY, samplingDelay);
		// if (climbing.getPercentage() < 100) {
		// Toast.makeText(getApplicationContext(), "Start climbing some stairs!", Toast.LENGTH_LONG).show();
		// }
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
		seekbarIndicator = (VerticalSeekBar) findViewById(R.id.seekBarPosition); // get reference to vertical seekbar (only once for performance-related reasons)
		seekbarIndicator.setOnTouchListener(new OnTouchListener() { // disable user-driven seekbar changes
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						return true;
					}
				});
		int building_id = getIntent().getIntExtra(MainActivity.building_intent_object, 0); // get building id from received intent
		try {
			// get building ID from intent
			if (building_id == 0) throw new Exception("ERROR: unable to get intent data"); // no building id found in received intent
			building = MainActivity.buildingDao.queryForId(building_id); // query db to get asked building
			setup_from_building(); // load building info
			backgroundClassifySampler = new Intent(this, SamplingClassifyService.class); // instance (without starting) background classifier
			// check for pre-detected sampling rate
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); // reference to android preferences
			detectedSamplingRate = settings.getFloat("detectedSamplingRate", 0.00f); // load previously detected sampling rate
			samplingDelay = settings.getInt("sensor_delay", -1); // load previously detected sampling delay
			Log.i(MainActivity.AppName, "Previous detected sampling rate of " + Double.toString(detectedSamplingRate) + "Hz");
			// sampling rate not detected or lower than the minimum one
			if (samplingDelay == -1 || detectedSamplingRate < minimumSamplingRate) { // start sampling rate detector
				Log.w(MainActivity.AppName, "Sampling rate not previously detected or too low. Detecting a new one");
				backgroundSamplingRateDetector = new Intent(this, SamplingRateDetectorService.class); // instance (without starting) background sampling rate detected
				backgroundSamplingRateDetector.putExtra(SAMPLING_DELAY, SensorManager.SENSOR_DELAY_NORMAL); // set default sampling delay
				registerReceiver(sampleRateDetectorReceiver, samplingRateDetectorFilter); // register listener for background sampling rate detector
				startService(backgroundSamplingRateDetector); // start background service
			} else {
				Log.i(MainActivity.AppName, "Using the previously detected sampling rate");
				setupByDetectedSamplingRate(); // setup activity with given sampling rate
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setup view with a given building and create/load an associated climbing
	 */
	private void setup_from_building() {
		int imageId = getApplicationContext().getResources().getIdentifier(building.getPhoto(), "drawable", getApplicationContext().getPackageName()); // get building's photo resource ID
		if (imageId > 0) ((ImageView) findViewById(R.id.buildingPhoto)).setImageResource(imageId);
		// set building info
		((TextView) findViewById(R.id.lblBuildingName)).setText(building.getName() + " (" + building.getLocation() + ")"); // building's location
		((TextView) findViewById(R.id.lblNumSteps)).setText(Integer.toString(building.getSteps()) + " steps"); // building's steps
		((TextView) findViewById(R.id.lblHeight)).setText(Integer.toString(building.getHeight()) + "mt"); // building's height (in mt)
		try { // get previous climbing for this building
			loadPreviousClimbing();
		} catch (ClimbingNotFound e) {
			Log.i(MainActivity.AppName, "No previous climbing found");
			climbing = new Climbing(); // create a new empty climbing for this building
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

	/**
	 * Check (and load) if a climbing exists for the given building
	 * 
	 * @throws ClimbingNotFound
	 */
	private void loadPreviousClimbing() throws ClimbingNotFound {
		climbing = MainActivity.getClimbingForBuilding(building.get_id());
		if (climbing == null) throw new ClimbingNotFound(); // no climbing found
		num_steps = climbing.getCompleted_steps();
		percentage = climbing.getPercentage();
		seekbarIndicator.setMax(building.getSteps());
		seekbarIndicator.setProgress(climbing.getCompleted_steps());
		updateStats();
		Log.i(MainActivity.AppName, "Loaded existing climbing (#" + climbing.get_id() + ")");
		if (percentage >= 1.00) { // building already climbed
			findViewById(R.id.btnStartClimbing).setVisibility(View.INVISIBLE);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			((TextView) findViewById(R.id.lblReadyToClimb)).setText("ALREADY CLIMBED ON " + sdf.format(new Date(climbing.getCompleted())));
			findViewById(R.id.btnAccessPhotoGallery).setVisibility(View.VISIBLE);
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

	/**
	 * onClick listener for starting/stopping classifier
	 * 
	 * @param v
	 */
	public void onBtnStartClimbing(View v) {
		if (detectedSamplingRate == 0 || detectedSamplingRate < minimumSamplingRate) { // sampling rate detection still in progress
			Toast.makeText(getApplicationContext(), "Accelerometer calibration is not ready yet. Please wait", Toast.LENGTH_SHORT).show();
			return;
		}
		if (samplingEnabled) { // if sampling is enabled stop the classifier
			stopClassify();
		} else { // if sampling is not enabled stop the classifier
			startClassifyService();
		}
	}

	/**
	 * Make sure that all background services are stopped
	 */
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

	/**
	 * Stop background classifier service
	 */
	public void stopClassify() {
		stopService(backgroundClassifySampler); // stop background service
		samplingEnabled = false;
		unregisterReceiver(classifierReceiver); // unregister listener
		// update db
		climbing.setModified(new Date().getTime()); // update climbing last edit date
		climbing.setCompleted_steps(num_steps); // update completed steps
		climbing.setPercentage(percentage); // update progress percentage
		climbing.setRemaining_steps(building.getSteps() - num_steps); // update remaining steps
		if (percentage >= 1.00) climbing.setCompleted(new Date().getTime());
		MainActivity.climbingDao.update(climbing); // save to db
		Log.i(MainActivity.AppName, "Updated climbing #" + climbing.get_id());
		((ImageButton) findViewById(R.id.btnStartClimbing)).setImageResource(R.drawable.av_play); // set button icon to play again
		findViewById(R.id.progressBarClimbing).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_out)); // hide progress bar
		findViewById(R.id.progressBarClimbing).setVisibility(View.INVISIBLE);
	}

	/**
	 * Start background classifier service
	 */
	public void startClassifyService() {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); // get reference to android preferences
		int difficulty = Integer.parseInt(settings.getString("difficulty", "10")); // get difficulty from preferences
		switch (difficulty) { // set several parameters related to difficulty
			case 100: // easy
				Log.i(MainActivity.AppName, "Selected difficulty: EASY");
				vstep_for_rstep = 100;
				break;
			case 1: // hard
				Log.i(MainActivity.AppName, "Selected difficulty: HARD");
				vstep_for_rstep = 1;
				break;
			default: // normal and default
				Log.i(MainActivity.AppName, "Selected difficulty: NORMAL");
				vstep_for_rstep = 10;
				break;
		}
		Log.i(MainActivity.AppName, "Using " + vstep_for_rstep + " steps for each real step");
		startService(backgroundClassifySampler); // start background service
		registerReceiver(classifierReceiver, classifierFilter); // register listener
		samplingEnabled = true;
		((ImageButton) findViewById(R.id.btnStartClimbing)).setImageResource(R.drawable.av_pause); // set button image to stop service
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
		stopAllServices(); // make sure to stop all background services
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		if (samplingEnabled == false) super.onBackPressed();
		else { // disable back button if sampling is enabled
			Toast.makeText(getApplicationContext(), "Sampling running - Stop it before exiting", Toast.LENGTH_SHORT).show();
		}
	}
}
