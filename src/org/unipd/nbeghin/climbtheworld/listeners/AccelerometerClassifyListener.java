package org.unipd.nbeghin.climbtheworld.listeners;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.models.ClassifierCircularBuffer;
import org.unipd.nbeghin.climbtheworld.models.Sample;

import android.app.IntentService;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * Listener for classifier
 * 
 */
public class AccelerometerClassifyListener implements SensorEventListener {
	private int					sensorDelay;
	private double		samplingRate	= 15;	// default
	ClassifierCircularBuffer	buffer;
	IntentService				service;

	public void setSensorDelay(int sensorDelay) {
		this.sensorDelay = sensorDelay;
	}

	private void initialize_buffer(double samplingRate) {
		double step_per_second=((double)ClassifierCircularBuffer.average_step_duration/1000000000);
//		Log.i(MainActivity.AppName, Double.toString(step_per_second)+" step per second");
		int buffer_size = (int) (samplingRate * step_per_second);
		buffer = new ClassifierCircularBuffer(buffer_size, service);
		Log.i(MainActivity.AppName, "Accelerometer listener instanced with a circular buffer size of " + buffer_size + " (detected sampling rate: " + (int)samplingRate + " Hz)");
	}

	public void setSamplingRate(double samplingRate) {
		this.samplingRate = samplingRate;
		 initialize_buffer(samplingRate);
	}

	public AccelerometerClassifyListener(Context context, IntentService service) {
		this.service = service;
//		initialize_buffer((int) samplingRate);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		buffer.add(new Sample(event.timestamp, event.values[0], event.values[1], event.values[2]));
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO
	}
}
