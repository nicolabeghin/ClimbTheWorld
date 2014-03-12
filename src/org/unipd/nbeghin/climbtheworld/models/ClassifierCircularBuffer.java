package org.unipd.nbeghin.climbtheworld.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.unipd.nbeghin.climbtheworld.MainActivity;
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
	private long 				bufferDuration					= 500000000; // 500 ms
	IntentService				service;
	public final static String	CLASSIFIER_ACTION				= "org.unipd.nbeghin.classifier.notification";
	public final static String	CLASSIFIER_NOTIFICATION_STATUS	= "CLASSIFIER_NOTIFICATION_STATUS";
	private final static long	MIN_WINDOW_DURATION				= 300000000;	// 300 ms
	private final static long 	MAX_WINDOW_DURATION				= 2000000000;	// 2 seconds
	
	private long lastTime = 0;
	
	/**
	 * @param size Size of the buffer
	 * @param service Reference to the service that will be used to issue BroadcastRequest
	 */
	public ClassifierCircularBuffer(IntentService service) {
		this.service = service;
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
			
			if (samples.size() > 1 && samples.get(samples.size() - 2).getValueZ() <= 0 
					&& samples.get(samples.size() - 1).getValueZ() >= 0) {
				
				classify();
			}
		}
	}
	
	/**
	 * Rotates the values of the sample depending on the values of the 
	 * rotation vector
	 * @param sample: initial x,y,z values
	 * @param rotationValues: x,y,z values of the rotation vector
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
		sample.updateVAndXPlusY();
		
	}

	private void classify() {
		
		if (samples.get(samples.size() - 1).getTime() - 
				samples.get(0).getTime() > MIN_WINDOW_DURATION && 
			samples.get(samples.size() - 1).getTime() - 
				samples.get(0).getTime() < MAX_WINDOW_DURATION) {
			
			List<Sample> used_samples = new ArrayList<Sample>(samples); // clone given samples in order to unlock access to main samples
			samples.clear(); 
			
			try {
				/**
				 * Make sure it's ordered by timestamp
				 */
				Collections.sort(used_samples, new SampleTimeComparator()); // 
				
				/**
				 * Creates a new batch with the given sample that is responsible 
				 * to calculate all the required features
				 */
				Batch batch = new Batch(used_samples); // create a new batch with given samples
	
				List<FeatureSet> features = batch.getBasicFeatures(); // calculate features
				List<Double> data_row = new ArrayList<Double>();
	
				for (FeatureSet featureSet : features) {
					data_row.add(featureSet.getMean());
					data_row.add(featureSet.getStd());
					data_row.add(featureSet.getVariance());
					data_row.add(featureSet.getDifferenceMinMax());
				}
				
				batch.addRatios(data_row);
				batch.addIntelligentRatios(data_row);
				batch.addCorrelations(data_row);
				data_row.add(batch.calculateMagnitudeMean());
				data_row.add(batch.calculateSignalMagnitudeArea());
				
				Intent intent = new Intent();
				intent.setAction(CLASSIFIER_ACTION);
				//intent.putExtra(CLASSIFIER_NOTIFICATION_STATUS, WekaClassifier.classify(data_row));
				intent.putExtra(CLASSIFIER_NOTIFICATION_STATUS, WekaClassifier.classify(data_row));
				service.sendBroadcast(intent); // broadcast the classifier output
				
				used_samples.clear();
				
			} catch (Exception e) {
				Log.e(MainActivity.AppName, "Unable to classify batch:" + e.getMessage());
			}
		}
		else {
			samples.clear();
			Intent intent = new Intent();
			intent.setAction(CLASSIFIER_ACTION);
			intent.putExtra(CLASSIFIER_NOTIFICATION_STATUS, -0.001);
			service.sendBroadcast(intent);
		}
	}
}
