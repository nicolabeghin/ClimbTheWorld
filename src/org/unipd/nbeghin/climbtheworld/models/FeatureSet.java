package org.unipd.nbeghin.climbtheworld.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.unipd.nbeghin.climbtheworld.MainActivity;

import android.util.Log;

/**
 * A set of calculated features
 *
 */
public class FeatureSet {
	private String	title	= "";
	private double	mean;
	private double	variance;
	private double	std;
	private double minValue;
	private double maxValue;

	public FeatureSet(double mean, double variance, double std, double min, double max) {
		this.mean = mean;
		this.variance = variance;
		this.std = std;
		this.minValue = min; this.maxValue = max;
	}

	public FeatureSet(String title, double mean, double variance, double std, double min, double max) {
		this(mean, variance, std, min, max);
		this.title = title;
	}

	public double getMean() {
		return mean;
	}

	public double getVariance() {
		return variance;
	}

	public double getStd() {
		return std;
	}
	
	public double getDifferenceMinMax() {
		return this.maxValue - this.minValue;
	}
	
	public double getMin() {
		return minValue;
	}
	
	public double getMax() {
		return maxValue;
	}

	@Override
	public String toString() {
		NumberFormat nf = new DecimalFormat("0.#####");
		return "\n" + title + "\tMEAN " + nf.format(this.mean) + "\tVARIANCE " + nf.format(this.variance) + "\tSTD " + nf.format(this.std) + 
				"\tMIN " + nf.format(this.minValue) + "\tMAX " + nf.format(this.maxValue);
	}
}