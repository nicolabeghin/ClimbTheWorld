package org.unipd.nbeghin.climbtheworld.comparator;

import java.util.Comparator;

import org.unipd.nbeghin.climbtheworld.models.BuildingTour;

public class BuildingTourComparator implements Comparator<BuildingTour> {
	@Override
	public int compare(BuildingTour first, BuildingTour second) {
		return new Integer(first.getOrder()).compareTo(new Integer(second.getOrder()));
	}
}
