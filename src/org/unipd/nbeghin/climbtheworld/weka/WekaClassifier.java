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
		p = WekaClassifier.N2207b0fb0(i);
		return p;
	}

	static double N2207b0fb0(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() > 0.1) {
			p = WekaClassifier.N26f440311(i);
		}
		return p;
	}

	static double N26f440311(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = WekaClassifier.N6da264f12(i);
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = WekaClassifier.N132d984416(i);
		}
		return p;
	}

	static double N6da264f12(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.18) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.18) {
			p = WekaClassifier.N409142723(i);
		}
		return p;
	}

	static double N409142723(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.31) {
			p = WekaClassifier.Na16b7c4(i);
		} else if (((Double) i[6]).doubleValue() > 0.31) {
			p = 0;
		}
		return p;
	}

	static double Na16b7c4(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N65979a365(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = WekaClassifier.N3642251014(i);
		}
		return p;
	}

	static double N65979a365(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.64) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.64) {
			p = WekaClassifier.N440d83556(i);
		}
		return p;
	}

	static double N440d83556(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.72) {
			p = WekaClassifier.N5329645a7(i);
		} else if (((Double) i[3]).doubleValue() > 0.72) {
			p = 0;
		}
		return p;
	}

	static double N5329645a7(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = WekaClassifier.N427b7b5d8(i);
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = WekaClassifier.N7c1c8c5813(i);
		}
		return p;
	}

	static double N427b7b5d8(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.13) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.13) {
			p = WekaClassifier.N38e038c49(i);
		}
		return p;
	}

	static double N38e038c49(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.21) {
			p = WekaClassifier.N8aee90810(i);
		} else if (((Double) i[6]).doubleValue() > 0.21) {
			p = WekaClassifier.N134510ac11(i);
		}
		return p;
	}

	static double N8aee90810(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.15) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.15) {
			p = 0;
		}
		return p;
	}

	static double N134510ac11(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.16) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.16) {
			p = WekaClassifier.N1b49961612(i);
		}
		return p;
	}

	static double N1b49961612(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.67) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.67) {
			p = 0;
		}
		return p;
	}

	static double N7c1c8c5813(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.2) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.2) {
			p = 1;
		}
		return p;
	}

	static double N3642251014(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = WekaClassifier.N308f594415(i);
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N308f594415(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.15) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() > 0.15) {
			p = 1;
		}
		return p;
	}

	static double N132d984416(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.01) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.01) {
			p = WekaClassifier.N1667a23217(i);
		}
		return p;
	}

	static double N1667a23217(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.15) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.15) {
			p = WekaClassifier.N6dc98c1b18(i);
		}
		return p;
	}

	static double N6dc98c1b18(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.27) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.27) {
			p = 1;
		}
		return p;
	}
}