package org.unipd.nbeghin.climbtheworld.weka;

/**
 * J48 classifier
 * NB: extracted from Weka model
 * 
 */
public class WekaClassifier {
	private static String[]	classifications	= new String[] { "STAIR", "NONSTAIR" };

	/**
	 * @param i Classifier numeric output
	 * @return String A string explicitly stating the classifier output
	 * @throws Exception
	 */
	public static String explicit_classify(Object[] i) throws Exception {
		return classifications[(int) classify(i)];
	}

	public static double classify(Object[] i) throws Exception {
		double p = Double.NaN;
		p = WekaClassifier.N1393537d0(i);
		return p;
	}

	static double N1393537d0(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.1) {
			p = 0;
		}
		return p;
	}
}