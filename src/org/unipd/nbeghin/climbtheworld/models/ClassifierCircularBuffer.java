package org.unipd.nbeghin.climbtheworld.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.comparator.MeanComparator;
import org.unipd.nbeghin.climbtheworld.comparator.SampleTimeComparator;
import org.unipd.nbeghin.climbtheworld.weka.WekaClassifier;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Circular buffer containing collected samples.
 * It features a 50%-overlapping sliding window
 * 
 */
public class ClassifierCircularBuffer {
	private List<Sample>		samples							= new ArrayList<Sample>();
	private List<Sample>		bufferToRemoveGravity			= new ArrayList<Sample>();
	private boolean				bufferFull						= false;
	private float 				bufferDuration					= 5000000000F;
	private Double[]			data_row						= new Double[12];
	IntentService				service;
	public final static String	CLASSIFIER_ACTION				= "org.unipd.nbeghin.classifier.notification";
	public final static String	CLASSIFIER_NOTIFICATION_STATUS	= "CLASSIFIER_NOTIFICATION_STATUS";
	private int					axis_to_be_considered			= 4;											// (4 == |V|)
	private int					size							= 0;
	private float				average_step_duration			= 5000000000F;											// in ms
	private long deltaTime;
	
	/**
	 * @param size Size of the buffer
	 * @param service Reference to the service that will be used to issue BroadcastRequest
	 */
	public ClassifierCircularBuffer(int size, IntentService service) {
//		if (size % 2 != 0) { // not an even number
//			size++; // get an even number (odd + 1)
//			Log.w(MainActivity.AppName, (size - 1) + " is not an even number: using " + size + " as circular buffer size");
//		}
		this.service = service;
		this.size = size;
	}

	public void add(Sample sample, float[] rotationVector) {
		
		if ((bufferToRemoveGravity.size() > 0) && 
				(sample.getTime() - bufferToRemoveGravity.get(0).getTime())> bufferDuration) {
			bufferFull = true;
		}
		else {
			bufferFull = false;
			bufferToRemoveGravity.add(sample);
		}
		
		if (bufferFull) {
			
			float meanValueX = 0, meanValueY = 0, meanValueZ = 0;
			
			/**
			 * Calculates the mean values for the three axis
			 */
			for (Sample localSample: bufferToRemoveGravity) {
				meanValueX += localSample.getValueX();
				meanValueY += localSample.getValueY();
				meanValueZ += localSample.getValueZ();
			}
			
			meanValueX /= bufferToRemoveGravity.size();
			meanValueY /= bufferToRemoveGravity.size();
			meanValueZ /= bufferToRemoveGravity.size();
			
			/**
			 * Removes the first element of the buffer and 
			 * adds the new one to the end
			 */
			bufferToRemoveGravity.remove(0);
			bufferToRemoveGravity.add(sample);
			
			Sample finalSample = new Sample(sample.getTime(), sample.getValueX() - meanValueX,
					sample.getValueY() - meanValueY, sample.getValueZ() - meanValueZ);
			
			rotateAccelerometerValues(finalSample, rotationVector);
			samples.add(finalSample);
			
			deltaTime = sample.getTime() - samples.get(0).getTime();
			if (deltaTime > average_step_duration) {
				this.classify();
			}
		}
	}
	
	/**
	 * Rotates the values of the sample depending on the values of the 
	 * rotation vector
	 * @param sample
	 * @param rotationValues
	 */
	private void rotateAccelerometerValues(Sample sample, float[] rotationValues) {
		
		double norm = Math.sqrt(Math.pow(rotationValues[0], 2) + 
				Math.pow(rotationValues[1], 2) + Math.pow(rotationValues[2], 2));
		if (norm > 1) {
			norm = 1;
		}
		double alpha = Math.asin(norm);
		
		double x = rotationValues[0] / norm, y = rotationValues[1] / norm, z = rotationValues[2] / norm;
		double xSquare = Math.pow(x, 2), ySquare = Math.pow(y, 2), zSquare = Math.pow(z, 2);
		
		double sinAlpha = Math.sin(alpha), cosAlpha = Math.cos(alpha);
		
		double rotatedX = ((xSquare + (1 - xSquare) * cosAlpha) * sample.getValueX() + 
							(((1 - cosAlpha) * x * y) - sinAlpha * z) * sample.getValueY() + 
							(((1 - cosAlpha) * x * z) + sinAlpha * y) * sample.getValueZ());
		
		double rotatedY = ((((1 - cosAlpha) * y * x) + sinAlpha * z) * sample.getValueX() +
							(ySquare + (1 - ySquare) * cosAlpha) * sample.getValueY() + 
							(((1 - cosAlpha) * y * z ) - sinAlpha * x) * sample.getValueZ());
		
		double rotatedZ = ((((1 - cosAlpha) * z * x) - sinAlpha * y) * sample.getValueX() + 
							(((1 - cosAlpha) * z * y) + sinAlpha * x) * sample.getValueY() + 
							(zSquare + (1 - zSquare) * cosAlpha) * sample.getValueZ());
		
		sample.setValueX(rotatedX); sample.setValueY(rotatedY); sample.setValueZ(rotatedZ);
		
	}

	private void classify() {
		
		List<Sample> used_samples=new ArrayList<Sample>(samples); // clone given samples in order to unlock access to main samples
		samples = samples.subList((int)(samples.size() / 2), samples.size()); // overlapping sliding window
//		samples.clear();	
		try {
			Collections.sort(used_samples, new SampleTimeComparator()); // make sure it's ordered by timestamp
			Batch batch = new Batch(used_samples); // create a new batch with given samples
//			Log.i(MainActivity.AppName, "deltaTime=" + deltaTime/1000000 + "ms, batch size=" + used_samples.size());
			List<FeatureSet> features = batch.getBasicFeatures(); // calculate features
			features = features.subList(0, axis_to_be_considered); // get (if requested) a subset of the calculated features
			Collections.sort(features, new MeanComparator());
			int i = 0;
			for (FeatureSet featureSet : features) {
				data_row[i] = featureSet.getMean();
				i++;
				data_row[i] = featureSet.getStd();
				i++;
				data_row[i] = featureSet.getVariance();
				i++;
			}
//			Log.d(MainActivity.AppName, "FEATURES: "+Arrays.toString(data_row));
			Intent intent = new Intent();
			intent.setAction(CLASSIFIER_ACTION);
			intent.putExtra(CLASSIFIER_NOTIFICATION_STATUS, WekaClassifier.explicit_classify(data_row));
			service.sendBroadcast(intent); // broadcast the classifier output
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "Unable to classify batch:" + e.getMessage());
		}
	}
}
