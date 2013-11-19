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
		p = WekaClassifier.N243670130(i);
		return p;
	}

	static double N243670130(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() > 0.1) {
			p = WekaClassifier.N71f801f71(i);
		}
		return p;
	}

	static double N71f801f71(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.07) {
			p = WekaClassifier.N514939952(i);
		} else if (((Double) i[7]).doubleValue() > 0.07) {
			p = WekaClassifier.N268dc2d37(i);
		}
		return p;
	}

	static double N514939952(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.11) {
			p = WekaClassifier.N1e638ee43(i);
		} else if (((Double) i[1]).doubleValue() > 0.11) {
			p = WekaClassifier.N625795ce6(i);
		}
		return p;
	}

	static double N1e638ee43(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.02) {
			p = WekaClassifier.N1a3a2a524(i);
		}
		return p;
	}

	static double N1a3a2a524(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = WekaClassifier.N60b07af15(i);
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N60b07af15(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.82) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.82) {
			p = 0;
		}
		return p;
	}

	static double N625795ce6(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = WekaClassifier.N642c39d27(i);
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = WekaClassifier.N22dd0f8730(i);
		}
		return p;
	}

	static double N642c39d27(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.2) {
			p = WekaClassifier.N13883d5f8(i);
		} else if (((Double) i[6]).doubleValue() > 0.2) {
			p = WekaClassifier.N12504e010(i);
		}
		return p;
	}

	static double N13883d5f8(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.12) {
			p = WekaClassifier.N557531fd9(i);
		} else if (((Double) i[4]).doubleValue() > 0.12) {
			p = 0;
		}
		return p;
	}

	static double N557531fd9(Object[] i) {
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

	static double N12504e010(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N25630eb611(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = WekaClassifier.N3c640f1a24(i);
		}
		return p;
	}

	static double N25630eb611(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.3) {
			p = WekaClassifier.Na5ae1e712(i);
		} else if (((Double) i[6]).doubleValue() > 0.3) {
			p = WekaClassifier.N40e9e79923(i);
		}
		return p;
	}

	static double Na5ae1e712(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.04) {
			p = WekaClassifier.N54a50a0013(i);
		} else if (((Double) i[5]).doubleValue() > 0.04) {
			p = WekaClassifier.N2b87514a22(i);
		}
		return p;
	}

	static double N54a50a0013(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = WekaClassifier.N1f33b16a14(i);
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = WekaClassifier.N7490449721(i);
		}
		return p;
	}

	static double N1f33b16a14(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.13) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.13) {
			p = WekaClassifier.N7ac4b62615(i);
		}
		return p;
	}

	static double N7ac4b62615(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.76) {
			p = WekaClassifier.N892b7c216(i);
		} else if (((Double) i[0]).doubleValue() > 0.76) {
			p = 0;
		}
		return p;
	}

	static double N892b7c216(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.64) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.64) {
			p = WekaClassifier.N585e25f317(i);
		}
		return p;
	}

	static double N585e25f317(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.21) {
			p = WekaClassifier.Nd0da1d818(i);
		} else if (((Double) i[6]).doubleValue() > 0.21) {
			p = WekaClassifier.N79fc729919(i);
		}
		return p;
	}

	static double Nd0da1d818(Object[] i) {
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

	static double N79fc729919(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.16) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.16) {
			p = WekaClassifier.N2cc7d96020(i);
		}
		return p;
	}

	static double N2cc7d96020(Object[] i) {
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

	static double N7490449721(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.67) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() > 0.67) {
			p = 1;
		}
		return p;
	}

	static double N2b87514a22(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.04) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.04) {
			p = 1;
		}
		return p;
	}

	static double N40e9e79923(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.13) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.13) {
			p = 0;
		}
		return p;
	}

	static double N3c640f1a24(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 1;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.N4d1b92ef25(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = WekaClassifier.N4634745628(i);
		}
		return p;
	}

	static double N4d1b92ef25(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.35) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.35) {
			p = WekaClassifier.N4d4bb07526(i);
		}
		return p;
	}

	static double N4d4bb07526(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = WekaClassifier.N242df8f827(i);
		}
		return p;
	}

	static double N242df8f827(Object[] i) {
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

	static double N4634745628(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.04) {
			p = WekaClassifier.N1dc1de1c29(i);
		} else if (((Double) i[2]).doubleValue() > 0.04) {
			p = 1;
		}
		return p;
	}

	static double N1dc1de1c29(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.38) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.38) {
			p = 1;
		}
		return p;
	}

	static double N22dd0f8730(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.07) {
			p = WekaClassifier.N5f2679f231(i);
		} else if (((Double) i[10]).doubleValue() > 0.07) {
			p = 1;
		}
		return p;
	}

	static double N5f2679f231(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.01) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() > 0.01) {
			p = WekaClassifier.N57102fab32(i);
		}
		return p;
	}

	static double N57102fab32(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.15) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.15) {
			p = WekaClassifier.N67a9b03433(i);
		}
		return p;
	}

	static double N67a9b03433(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.04) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.04) {
			p = WekaClassifier.N356f5b1734(i);
		}
		return p;
	}

	static double N356f5b1734(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.05) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() > 0.05) {
			p = WekaClassifier.N21c55e6935(i);
		}
		return p;
	}

	static double N21c55e6935(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.1) {
			p = WekaClassifier.N24b950d136(i);
		} else if (((Double) i[9]).doubleValue() > 0.1) {
			p = 1;
		}
		return p;
	}

	static double N24b950d136(Object[] i) {
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

	static double N268dc2d37(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.08) {
			p = WekaClassifier.N324f0f9738(i);
		} else if (((Double) i[7]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N324f0f9738(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = WekaClassifier.N64889c4e39(i);
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = 1;
		}
		return p;
	}

	static double N64889c4e39(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.31) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.31) {
			p = WekaClassifier.N44cdf87240(i);
		}
		return p;
	}

	static double N44cdf87240(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 0.05) {
			p = WekaClassifier.N2e7227a841(i);
		} else if (((Double) i[5]).doubleValue() > 0.05) {
			p = 0;
		}
		return p;
	}

	static double N2e7227a841(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = WekaClassifier.N48899e6a42(i);
		}
		return p;
	}

	static double N48899e6a42(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.03) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() > 0.03) {
			p = WekaClassifier.N42ff665a43(i);
		}
		return p;
	}

	static double N42ff665a43(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.19) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.19) {
			p = WekaClassifier.Nd98c11344(i);
		}
		return p;
	}

	static double Nd98c11344(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.2) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() > 0.2) {
			p = WekaClassifier.N27abcd5e45(i);
		}
		return p;
	}

	static double N27abcd5e45(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.37) {
			p = WekaClassifier.N37eaab46(i);
		} else if (((Double) i[6]).doubleValue() > 0.37) {
			p = 0;
		}
		return p;
	}

	static double N37eaab46(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N570f80a947(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = 1;
		}
		return p;
	}

	static double N570f80a947(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.04) {
			p = WekaClassifier.N3ac803e648(i);
		} else if (((Double) i[10]).doubleValue() > 0.04) {
			p = 0;
		}
		return p;
	}

	static double N3ac803e648(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.54) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() > 0.54) {
			p = WekaClassifier.N21780f3049(i);
		}
		return p;
	}

	static double N21780f3049(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.21) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.21) {
			p = 1;
		}
		return p;
	}
}