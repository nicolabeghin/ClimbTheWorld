package org.unipd.nbeghin.climbtheworld.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A set of samples on which a feature will be calculated 
 *
 */
public class Batch {
	private List<SingleCoordinateSet>		values				= new ArrayList<SingleCoordinateSet>();
	private static HashMap<Integer, String>	coordinates_mapping	= new HashMap<Integer, String>();
	private String							title;
	private int								trunk				= 0;
	private List<Double> 					ratios 				= new ArrayList<Double>();
	private List<FeatureSet>				basicFeatures		= new ArrayList<FeatureSet>();
	private List<Double>					correlations 		= new ArrayList<Double>();
	private double							signalMagnitudeArea = 0.0;
	private double 							magnitudeMean 		= 0.0;
	
	static {
		coordinates_mapping.put(0, "X");
		coordinates_mapping.put(1, "Y");
		coordinates_mapping.put(2, "Z");
		coordinates_mapping.put(3, "|V|");
		coordinates_mapping.put(4, "X+Y");
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
			values.get(4).addValue(new DataTime(sample.getTime(), sample.getValueXPlusY()));
		}
		
		calculateMagnitudeMean(); calculateRatios(); calculateSignalMagnitueArea();
		calculateCorrelations();
	}

	public void printFeatures() {
		List<FeatureSet> features = this.getBasicFeatures();
		for (FeatureSet featureSet : features) {
			System.out.println(featureSet);
		}
	}

	public List<FeatureSet> getBasicFeatures() {
		List<FeatureSet> features = new ArrayList<FeatureSet>();
		for (int i = 0; i < values.size(); i++) {
			features.add(new FeatureSet(coordinates_mapping.get(i), values.get(i).getMean(), 
					values.get(i).getVariance(), values.get(i).getStandardDeviation(), 
					values.get(i).getMin(), values.get(i).getMax()));
		}
		return features;
	}
	
	/**
	 * Calculcates all the ratios between all the basic features of the sample
	 */
	private void calculateRatios() {
		
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
				ratios.add(ratioMean); ratios.add(ratioStd); ratios.add(ratioVariance);
				ratios.add(ratioMinMax);
			}
			
		}
	}
	
	/**
	 * Calculates correlation between all the axis used to calculate 
	 * the features
	 */
	private void calculateCorrelations() {
		
		for (int i = 0; i < values.size() - 1; i++) {
			for (int j = i+1; j < values.size(); j++) {
				
				Double covariance = calculateCovariance(values.get(i).getValues(), values.get(j).getValues());
				
				Double correlation = covariance / 
						(basicFeatures.get(i).getStd() * basicFeatures.get(j).getStd());
				
				if (Double.isNaN(correlation) || Double.isInfinite(correlation)) {
					correlation = 0.0;
				}
				
				correlations.add(correlation);
			}
		}
		
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
	
	private void calculateSignalMagnitueArea() {
		
		for (int i = 0; i < values.get(0).getValues().size(); i++) {
			
			signalMagnitudeArea += Math.abs(values.get(0).getValues().get(i).getValue()) + 
					Math.abs(values.get(1).getValues().get(i).getValue()) + 
					Math.abs(values.get(2).getValues().get(i).getValue());
			
		}
		
		signalMagnitudeArea /= values.get(0).getValues().size();
	}
	
	private void calculateMagnitudeMean() {
		
		magnitudeMean = Math.sqrt(Math.pow(basicFeatures.get(0).getMean(), 2) 
				+ Math.pow(basicFeatures.get(1).getMean(), 2) + Math.pow(basicFeatures.get(3).getMean(), 2));
	}
	
	public List<Double> getRatios() {
		return ratios;
	}
	
	public List<Double> getCorrelations() {
		return this.correlations;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}