package org.unipd.nbeghin.climbtheworld.comparator;

import java.util.Comparator;
import org.unipd.nbeghin.climbtheworld.models.FeatureSet;

public class MeanComparator implements Comparator<FeatureSet> {
  
  @Override
  public int compare(FeatureSet first, FeatureSet second) {
    return new Double(second.getMean()).compareTo(new Double(first.getMean()));
  }
  
}
