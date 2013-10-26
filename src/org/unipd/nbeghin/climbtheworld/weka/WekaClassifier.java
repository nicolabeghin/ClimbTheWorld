package org.unipd.nbeghin.climbtheworld.weka;

/**
 * J48 classifier
 * NB: extracted from Weka model
 * 
 */
public class WekaClassifier {
	private static String[]	classifications	= new String[] { "NONSTAIR", "STAIR" };

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
		p = WekaClassifier.N42b7141a0(i);
		return p;
	}

	static double N42b7141a0(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.74) {
			p = WekaClassifier.N188d92e1(i);
		} else if (((Double) i[3]).doubleValue() > 0.74) {
			p = WekaClassifier.N5fb08cf33(i);
		}
		return p;
	}

	static double N188d92e1(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.08) {
			p = WekaClassifier.N3f6a5bcb2(i);
		} else if (((Double) i[1]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N3f6a5bcb2(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.0) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}

	static double N5fb08cf33(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.36) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.36) {
			p = WekaClassifier.N3ff5d6994(i);
		}
		return p;
	}

	static double N3ff5d6994(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.0) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}
}