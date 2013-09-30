package org.unipd.nbeghin.climbtheworld.comparator;

import java.util.Comparator;
import org.unipd.nbeghin.climbtheworld.models.FeatureSet;

public class StdComparator implements Comparator<FeatureSet> {
	@Override
	public int compare(FeatureSet first, FeatureSet second) {
		return new Double(second.getStd()).compareTo(new Double(first.getStd()));
	}
}
