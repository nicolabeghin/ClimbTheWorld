package org.unipd.nbeghin.climbtheworld.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.unipd.nbeghin.climbtheworld.MainActivity;

import android.util.Log;

/**
 * A set of samples on which a feature will be calculated 
 *
 */
public class Batch {
	private List<SingleCoordinateSet>		values				= new ArrayList<SingleCoordinateSet>();
	private static HashMap<Integer, String>	coordinates_mapping	= new HashMap<Integer, String>(5);
	private String							title;
	private int								trunk				= 0;	
	private List<FeatureSet>				basicFeatures		= new ArrayList<FeatureSet>();
	private int 							featuresSize 		= 5 * 4 + 10 * 4 + 10 + 2; // coordinates_mapping.size + binomial + correlations + 
																						   // calculated magnitude area and signal magnitude area
	
	static {
		coordinates_mapping.put(0, "X");
		coordinates_mapping.put(1, "Y");
		coordinates_mapping.put(2, "Z");
		coordinates_mapping.put(3, "|V|");
		coordinates_mapping.put(4, "(X+Y)/2");
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public int getTrunk() {
		return trunk;
	}

	public void setTrunk(int trunk) {
		this.trunk = trunk;
	}

	public int size() {
		return values.get(0).size();
	}

	public List<SingleCoordinateSet> getValues() {
		return values;
	}
	
	public int getFeaturesSize() {
		return featuresSize;
	}

	public Batch(List<Sample> samples) throws Exception {
		
		if (samples.isEmpty()) throw new Exception("No element given for this batch");
		for (int i = 0; i < coordinates_mapping.size(); i++) {
			values.add(new SingleCoordinateSet());
			values.get(i).setTitle(coordinates_mapping.get(i));
		}
		for (int axis = 0; axis < samples.size(); axis++) {
			Sample sample = samples.get(axis);
			values.get(0).addValue(new DataTime(sample.getTime(), sample.getValueX()));
			values.get(1).addValue(new DataTime(sample.getTime(), sample.getValueY()));
			values.get(2).addValue(new DataTime(sample.getTime(), sample.getValueZ()));
			values.get(3).addValue(new DataTime(sample.getTime(), sample.getValueV()));
			values.get(4).addValue(new DataTime(sample.getTime(), sample.getMeanXAndY()));
		}
		
		calculateBasicFeatures();
	}

	public void printFeatures() {
		List<FeatureSet> features = this.getBasicFeatures();
		for (FeatureSet featureSet : features) {
			System.out.println(featureSet);
		}
	}
	
	private void calculateBasicFeatures() {
		
		for (int i = 0; i < values.size(); i++) {
			Log.d(MainActivity.AppName, "MEAN: " + values.get(i).getMean());
			basicFeatures.add(new FeatureSet(coordinates_mapping.get(i), 
					values.get(i).getMean(), 
					values.get(i).getStandardDeviation(), 
					values.get(i).getVariance(), 
					values.get(i).getMin(), 
					values.get(i).getMax()));
		}
	}
	
	public List<FeatureSet> getBasicFeatures() {
		return basicFeatures;
	}
	
	/**
	 * Calculcates all the ratios between all the basic features of the sample
	 */
	public int calculateRatios(Double[] data_row, int index) {
		
		for (int i = 0; i < basicFeatures.size() - 1; i++) {
			for (int j = i+1; j < basicFeatures.size(); j++) {
				
				Double ratioMean = basicFeatures.get(i).getMean() / basicFeatures.get(j).getMean(),
						ratioStd = basicFeatures.get(i).getStd() / basicFeatures.get(j).getStd(),
						ratioVariance = basicFeatures.get(i).getVariance() / basicFeatures.get(j).getStd(),
						ratioMinMax = basicFeatures.get(i).getDifferenceMinMax() / basicFeatures.get(j).getDifferenceMinMax();
				
				if (Double.isInfinite(ratioMean) || Double.isNaN(ratioMean)) {
					ratioMean = 0.0;
				}
				if (Double.isInfinite(ratioStd) || Double.isNaN(ratioStd)) {
					ratioStd = 0.0;
				}
				if (Double.isInfinite(ratioVariance) || Double.isNaN(ratioVariance)) {
					ratioVariance = 0.0;
				}
				if (Double.isInfinite(ratioMinMax) || Double.isNaN(ratioMinMax)) {
					ratioMinMax = 0.0;
				}
				data_row[index] = ratioMean; index++;
				data_row[index] = ratioStd; index++; 
				data_row[index] = ratioVariance; index++;
				data_row[index] = ratioMinMax; index++;
			}
			
		}
		return index;
	}
	
	/**
	 * Calculates correlation between all the axis used to calculate 
	 * the features
	 */
	public int calculateCorrelations(Double[] data_row, int index) {
		
		for (int i = 0; i < values.size() - 1; i++) {
			for (int j = i+1; j < values.size(); j++) {
				
				Double covariance = calculateCovariance(values.get(i).getValues(), values.get(j).getValues());
				
				data_row[index] = covariance / 
						(basicFeatures.get(i).getStd() * basicFeatures.get(j).getStd());
				
				if (Double.isNaN(data_row[index]) || Double.isInfinite(data_row[index])) {
					data_row[index] = 0.0;
				}
				
				index++;
			}
		}
		return index;
	}
	
	/**
	 * Calculate the covariance between two axis
	 * 
	 * @param first: the first axis
	 * @param second: the second axis
	 * @return the covariance between the two axis
	 */
	private Double calculateCovariance(List<DataTime> first, List<DataTime> second) {
		
		Double covariance = 0.0, sumX = 0.0, sumY = 0.0, product = 0.0;
		
		for (int i = 0; i < first.size(); i++) {
			
			product += (first.get(i).getValue() * second.get(i).getValue());
			sumX += first.get(i).getValue();
			sumY += second.get(i).getValue();
		}
		
		covariance = (product / first.size()) - ((sumX * sumY) / Math.pow(first.size(), 2));
		
		return covariance;
	}
	
	public Double calculateSignalMagnitudeArea() {
		
		Double signalMagnitudeArea = 0.0;
		
		for (int i = 0; i < values.get(0).getValues().size(); i++) {
			
			signalMagnitudeArea += Math.abs(values.get(0).getValues().get(i).getValue()) + 
					Math.abs(values.get(1).getValues().get(i).getValue()) + 
					Math.abs(values.get(2).getValues().get(i).getValue());
			
		}
		
		signalMagnitudeArea /= values.get(0).getValues().size();
		return signalMagnitudeArea;
	}
	
	public Double calculateMagnitudeMean() {
		
		return Math.sqrt(Math.pow(basicFeatures.get(0).getMean(), 2) 
				+ Math.pow(basicFeatures.get(1).getMean(), 2) + Math.pow(basicFeatures.get(2).getMean(), 2));
	}
}