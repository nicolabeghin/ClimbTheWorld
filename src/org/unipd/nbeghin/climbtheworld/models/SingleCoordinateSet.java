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
