package org.unipd.nbeghin.climbtheworld.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.unipd.nbeghin.climbtheworld.comparator.CoupleTimeDataComparator;

/**
 * A model representing the coordinates on a single axis
 * 
 */
public class SingleCoordinateSet {
	private List<DataTime>	values;
	private String			title;

	public SingleCoordinateSet(List<DataTime> values) {
		super();
		this.values = values;
	}

	public SingleCoordinateSet() {
		super();
		this.values = new ArrayList<DataTime>();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DataTime> getValues() {
		return values;
	}

	public String getTitle() {
		return title;
	}

	public void addValue(DataTime ctd) {
		this.values.add(ctd);
	}

	public double getMax() {
		return Collections.max(this.values, new CoupleTimeDataComparator()).getValue();
	}

	public double getMin() {
		return Collections.min(this.values, new CoupleTimeDataComparator()).getValue();
	}

	/**
	 * Normalize on a single axis (this one)
	 */
	public void normalize() {
		DataTime max = Collections.max(this.values, new CoupleTimeDataComparator());
		DataTime min = Collections.min(this.values, new CoupleTimeDataComparator());
		this.normalize(min.getValue(), max.getValue());
	}

	public void normalize(double min, double max) {
		for (DataTime ctd : this.values) {
			ctd.normalize(min, max);
		}
	}

	/**
	 * Normalize on all given axes
	 * 
	 * @param values List of set of samples of single coordinates
	 */
	public void normalize(List<SingleCoordinateSet> values) {
		ArrayList<Double> maxmin = new ArrayList<Double>();
		for (int i = 0; i < values.size(); i++) {
			maxmin.add(values.get(i).getMin());
			maxmin.add(values.get(i).getMax());
		}
		for (int i = 0; i < values.size(); i++) {
			values.get(i).normalize(Collections.min(maxmin).doubleValue(), Collections.max(maxmin).doubleValue());
		}
	}

	public double getMean() {
		double sum = 0;
		for (DataTime ctd : this.values) {
			sum += ctd.getValue();
		}
		return sum / this.values.size();
	}

	public double getVariance() {
		double mean = this.getMean();
		double variance = 0;
		for (DataTime ctd : this.values) {
			variance += Math.pow((ctd.getValue() - mean), 2);
		}
		return variance / this.values.size();
	}

	public double getStandardDeviation() {
		return Math.sqrt(this.getVariance());
	}

	public int size() {
		return this.values.size();
	}
}
