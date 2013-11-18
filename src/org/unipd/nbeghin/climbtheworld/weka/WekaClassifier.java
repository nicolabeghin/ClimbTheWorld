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
		p = WekaClassifier.N6dc6480f0(i);
		return p;
	}

	static double N6dc6480f0(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.11) {
			p = WekaClassifier.N74a509161(i);
		} else if (((Double) i[1]).doubleValue() > 0.11) {
			p = WekaClassifier.N58dca3ed4(i);
		}
		return p;
	}

	static double N74a509161(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.1) {
			p = WekaClassifier.N7ef7bf2d2(i);
		}
		return p;
	}

	static double N7ef7bf2d2(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.02) {
			p = WekaClassifier.N3b28647a3(i);
		}
		return p;
	}

	static double N3b28647a3(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.28) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.28) {
			p = 0;
		}
		return p;
	}

	static double N58dca3ed4(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = WekaClassifier.N7d8e4a2e5(i);
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = WekaClassifier.N4a412f4b22(i);
		}
		return p;
	}

	static double N7d8e4a2e5(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.33) {
			p = WekaClassifier.N8a852686(i);
		} else if (((Double) i[6]).doubleValue() > 0.33) {
			p = 0;
		}
		return p;
	}

	static double N8a852686(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.18) {
			p = WekaClassifier.N62ba2e487(i);
		} else if (((Double) i[6]).doubleValue() > 0.18) {
			p = WekaClassifier.N5cee644c9(i);
		}
		return p;
	}

	static double N62ba2e487(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.12) {
			p = WekaClassifier.N2d34ab9b8(i);
		} else if (((Double) i[4]).doubleValue() > 0.12) {
			p = 0;
		}
		return p;
	}

	static double N2d34ab9b8(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N5cee644c9(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N26fae93e10(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = WekaClassifier.N744574820(i);
		}
		return p;
	}

	static double N26fae93e10(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.74) {
			p = WekaClassifier.N22e6f97011(i);
		} else if (((Double) i[3]).doubleValue() > 0.74) {
			p = 0;
		}
		return p;
	}

	static double N22e6f97011(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.3) {
			p = WekaClassifier.N6a0239f612(i);
		} else if (((Double) i[6]).doubleValue() > 0.3) {
			p = 0;
		}
		return p;
	}

	static double N6a0239f612(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.64) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.64) {
			p = WekaClassifier.N382b7bd913(i);
		}
		return p;
	}

	static double N382b7bd913(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.13) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.13) {
			p = WekaClassifier.N2ed0005e14(i);
		}
		return p;
	}

	static double N2ed0005e14(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = WekaClassifier.N59c8335415(i);
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = WekaClassifier.N18d210ab19(i);
		}
		return p;
	}

	static double N59c8335415(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.21) {
			p = WekaClassifier.N66a1fdd816(i);
		} else if (((Double) i[6]).doubleValue() > 0.21) {
			p = WekaClassifier.N1520a48c17(i);
		}
		return p;
	}

	static double N66a1fdd816(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.69) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.69) {
			p = 0;
		}
		return p;
	}

	static double N1520a48c17(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.16) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.16) {
			p = WekaClassifier.N1212ee2a18(i);
		}
		return p;
	}

	static double N1212ee2a18(Object[] i) {
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

	static double N18d210ab19(Object[] i) {
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

	static double N744574820(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = WekaClassifier.N17be3bb221(i);
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N17be3bb221(Object[] i) {
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

	static double N4a412f4b22(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.01) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.01) {
			p = WekaClassifier.N7d880b0223(i);
		}
		return p;
	}

	static double N7d880b0223(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.15) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.15) {
			p = WekaClassifier.N6e8af0b024(i);
		}
		return p;
	}

	static double N6e8af0b024(Object[] i) {
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