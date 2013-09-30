package org.unipd.nbeghin.climbtheworld.comparator;

import java.util.Comparator;
import org.unipd.nbeghin.climbtheworld.models.Sample;

public class SampleTimeComparator implements Comparator<Sample> {
	@Override
	public int compare(Sample first, Sample second) {
		return new Double(first.getTime()).compareTo(new Double(second.getTime()));
	}
}
