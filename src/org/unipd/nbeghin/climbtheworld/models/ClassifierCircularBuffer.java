package org.unipd.nbeghin.climbtheworld.models;

import java.util.ArrayList;
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
	private Double[]			data_row						= new Double[12];
	IntentService				service;
	public final static String	CLASSIFIER_ACTION				= "org.unipd.nbeghin.classifier.notification";
	public final static String	CLASSIFIER_NOTIFICATION_STATUS	= "CLASSIFIER_NOTIFICATION_STATUS";
	private int					axis_to_be_considered			= 4;											// (4 == |V|)
	private int					size							= 0;
	private int					average_step_duration			= 497000000;											// in ms
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

	public void add(Sample sample) {
		samples.add(sample);
		deltaTime=(samples.get(samples.size()-1).getTime()-samples.get(0).getTime());
		if (deltaTime >= average_step_duration) {
			this.classify();
		}
	}

	private void classify() {
		List<Sample> used_samples=new ArrayList<Sample>(samples); // clone given samples in order to unlock access to main samples
		samples.clear();
		try {
			Collections.sort(used_samples, new SampleTimeComparator()); // make sure it's ordered by timestamp
			Batch batch = new Batch(used_samples); // create a new batch with given samples
			Log.i(MainActivity.AppName, "deltaTime=" + deltaTime/1000000 + "ms, batch size=" + used_samples.size());
			List<FeatureSet> features = batch.getFeatures(); // calculate features
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
			Intent intent = new Intent();
			intent.setAction(CLASSIFIER_ACTION);
			intent.putExtra(CLASSIFIER_NOTIFICATION_STATUS, WekaClassifier.explicit_classify(data_row));
			service.sendBroadcast(intent); // broadcast the classifier output
//			samples = samples.subList(size / 2, samples.size()); // overlapping sliding window
		} catch (Exception e) {
			Log.e(MainActivity.AppName, "Unable to classify batch:" + e.getMessage());
		}
	}
}
