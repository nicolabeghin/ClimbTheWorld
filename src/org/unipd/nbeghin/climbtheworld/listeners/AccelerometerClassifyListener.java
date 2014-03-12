package org.unipd.nbeghin.climbtheworld.listeners;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.models.ClassifierCircularBuffer;
import org.unipd.nbeghin.climbtheworld.models.Sample;
import org.unipd.nbeghin.climbtheworld.services.SamplingClassifyService;

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
	private float[] 			lastValuesRotationVector;
	ClassifierCircularBuffer	buffer;
	IntentService				service;

	private void initialize_buffer() {
		buffer = new ClassifierCircularBuffer(service);
	}

	public AccelerometerClassifyListener(Context context, IntentService service) {
		this.service = service;
		initialize_buffer();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		
		if (event.sensor == SamplingClassifyService.mRotationVector) {
			lastValuesRotationVector = (float[])event.values.clone();
		}
		else {
			if (lastValuesRotationVector != null) {
				buffer.add(new Sample(event.timestamp, event.values[0], event.values[1], event.values[2]), lastValuesRotationVector);
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO
	}
}
