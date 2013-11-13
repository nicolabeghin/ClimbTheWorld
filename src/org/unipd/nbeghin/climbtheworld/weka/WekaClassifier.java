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
		p = WekaClassifier.N10e9c5920(i);
		return p;
	}

	static double N10e9c5920(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.1) {
			p = WekaClassifier.N63a9fcea1(i);
		}
		return p;
	}

	static double N63a9fcea1(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.01) {
			p = WekaClassifier.N2d1b99c42(i);
		} else if (((Double) i[5]).doubleValue() > 0.01) {
			p = 0;
		}
		return p;
	}

	static double N2d1b99c42(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.21) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.21) {
			p = 0;
		}
		return p;
	}
}