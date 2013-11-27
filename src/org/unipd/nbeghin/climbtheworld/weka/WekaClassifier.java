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
		p = WekaClassifier.N1c8aeedc0(i);
		return p;
	}

	static double N1c8aeedc0(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.11) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.11) {
			p = WekaClassifier.N6547bc271(i);
		}
		return p;
	}

	static double N6547bc271(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.08) {
			p = WekaClassifier.N4891d8632(i);
		} else if (((Double) i[7]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N4891d8632(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.3) {
			p = WekaClassifier.N5f1570cd3(i);
		} else if (((Double) i[6]).doubleValue() > 0.3) {
			p = WekaClassifier.N7332557373(i);
		}
		return p;
	}

	static double N5f1570cd3(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.07) {
			p = WekaClassifier.N78a0d37f4(i);
		} else if (((Double) i[10]).doubleValue() > 0.07) {
			p = 1;
		}
		return p;
	}

	static double N78a0d37f4(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.14) {
			p = WekaClassifier.N69d74b85(i);
		} else if (((Double) i[1]).doubleValue() > 0.14) {
			p = WekaClassifier.N5ba3859820(i);
		}
		return p;
	}

	static double N69d74b85(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = WekaClassifier.N4b59df6c6(i);
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = WekaClassifier.N5669e73213(i);
		}
		return p;
	}

	static double N4b59df6c6(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.02) {
			p = WekaClassifier.N795b52197(i);
		} else if (((Double) i[7]).doubleValue() > 0.02) {
			p = WekaClassifier.N5a6b258d8(i);
		}
		return p;
	}

	static double N795b52197(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N5a6b258d8(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 0.01) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() > 0.01) {
			p = WekaClassifier.N306e7a5b9(i);
		}
		return p;
	}

	static double N306e7a5b9(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.17) {
			p = WekaClassifier.N2523053410(i);
		} else if (((Double) i[6]).doubleValue() > 0.17) {
			p = WekaClassifier.N1e2aabf811(i);
		}
		return p;
	}

	static double N2523053410(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.68) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.68) {
			p = 0;
		}
		return p;
	}

	static double N1e2aabf811(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.75) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.75) {
			p = WekaClassifier.N380364112(i);
		}
		return p;
	}

	static double N380364112(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.1) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.1) {
			p = 1;
		}
		return p;
	}

	static double N5669e73213(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.69) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.69) {
			p = WekaClassifier.N42c9aff014(i);
		}
		return p;
	}

	static double N42c9aff014(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.13) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() > 0.13) {
			p = WekaClassifier.N473dd7d115(i);
		}
		return p;
	}

	static double N473dd7d115(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.09) {
			p = WekaClassifier.N2d23fee116(i);
		} else if (((Double) i[9]).doubleValue() > 0.09) {
			p = 0;
		}
		return p;
	}

	static double N2d23fee116(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.15) {
			p = WekaClassifier.N16327cee17(i);
		} else if (((Double) i[6]).doubleValue() > 0.15) {
			p = WekaClassifier.N4ca7f8a418(i);
		}
		return p;
	}

	static double N16327cee17(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.07) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.07) {
			p = 0;
		}
		return p;
	}

	static double N4ca7f8a418(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.76) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.76) {
			p = WekaClassifier.N27b4fe4d19(i);
		}
		return p;
	}

	static double N27b4fe4d19(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N5ba3859820(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.15) {
			p = WekaClassifier.N4c97ed2821(i);
		} else if (((Double) i[6]).doubleValue() > 0.15) {
			p = WekaClassifier.N8f003c134(i);
		}
		return p;
	}

	static double N4c97ed2821(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.68) {
			p = WekaClassifier.Na53046122(i);
		} else if (((Double) i[0]).doubleValue() > 0.68) {
			p = WekaClassifier.N1073f62325(i);
		}
		return p;
	}

	static double Na53046122(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.04) {
			p = WekaClassifier.N5044819223(i);
		} else if (((Double) i[7]).doubleValue() > 0.04) {
			p = 1;
		}
		return p;
	}

	static double N5044819223(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = WekaClassifier.N4196c16924(i);
		}
		return p;
	}

	static double N4196c16924(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.66) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.66) {
			p = 1;
		}
		return p;
	}

	static double N1073f62325(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.04) {
			p = WekaClassifier.N2d247c4526(i);
		} else if (((Double) i[7]).doubleValue() > 0.04) {
			p = WekaClassifier.N7b1ebc4628(i);
		}
		return p;
	}

	static double N2d247c4526(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.68) {
			p = WekaClassifier.N365aad2a27(i);
		} else if (((Double) i[3]).doubleValue() > 0.68) {
			p = 0;
		}
		return p;
	}

	static double N365aad2a27(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.69) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.69) {
			p = 1;
		}
		return p;
	}

	static double N7b1ebc4628(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.73) {
			p = WekaClassifier.N1edad6d029(i);
		} else if (((Double) i[3]).doubleValue() > 0.73) {
			p = WekaClassifier.N612fcbd833(i);
		}
		return p;
	}

	static double N1edad6d029(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.05) {
			p = WekaClassifier.N314d018330(i);
		} else if (((Double) i[10]).doubleValue() > 0.05) {
			p = 1;
		}
		return p;
	}

	static double N314d018330(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = WekaClassifier.N369e58be31(i);
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N369e58be31(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.17) {
			p = WekaClassifier.N55d43df532(i);
		} else if (((Double) i[1]).doubleValue() > 0.17) {
			p = 1;
		}
		return p;
	}

	static double N55d43df532(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.06) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.06) {
			p = 1;
		}
		return p;
	}

	static double N612fcbd833(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.09) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.09) {
			p = 1;
		}
		return p;
	}

	static double N8f003c134(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.04) {
			p = WekaClassifier.N4586787c35(i);
		} else if (((Double) i[9]).doubleValue() > 0.04) {
			p = WekaClassifier.N173ebc5c44(i);
		}
		return p;
	}

	static double N4586787c35(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.79) {
			p = WekaClassifier.N7f50388c36(i);
		} else if (((Double) i[0]).doubleValue() > 0.79) {
			p = 1;
		}
		return p;
	}

	static double N7f50388c36(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.16) {
			p = WekaClassifier.N6bb0b0a037(i);
		} else if (((Double) i[1]).doubleValue() > 0.16) {
			p = WekaClassifier.N1d44d9b239(i);
		}
		return p;
	}

	static double N6bb0b0a037(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.7) {
			p = WekaClassifier.N1d1c0f9c38(i);
		} else if (((Double) i[3]).doubleValue() > 0.7) {
			p = 0;
		}
		return p;
	}

	static double N1d1c0f9c38(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N1d44d9b239(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.07) {
			p = WekaClassifier.N1330442140(i);
		} else if (((Double) i[7]).doubleValue() > 0.07) {
			p = WekaClassifier.N5fbc31d42(i);
		}
		return p;
	}

	static double N1330442140(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.22) {
			p = WekaClassifier.N45c8dc5e41(i);
		} else if (((Double) i[6]).doubleValue() > 0.22) {
			p = 0;
		}
		return p;
	}

	static double N45c8dc5e41(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.04) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.04) {
			p = 1;
		}
		return p;
	}

	static double N5fbc31d42(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.04) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() > 0.04) {
			p = WekaClassifier.N53c6a7fc43(i);
		}
		return p;
	}

	static double N53c6a7fc43(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.53) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.53) {
			p = 0;
		}
		return p;
	}

	static double N173ebc5c44(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.07) {
			p = WekaClassifier.N17c047f045(i);
		} else if (((Double) i[7]).doubleValue() > 0.07) {
			p = 1;
		}
		return p;
	}

	static double N17c047f045(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.57) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.57) {
			p = WekaClassifier.N50b2e9be46(i);
		}
		return p;
	}

	static double N50b2e9be46(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.67) {
			p = WekaClassifier.N120fe25647(i);
		} else if (((Double) i[0]).doubleValue() > 0.67) {
			p = WekaClassifier.N6c9d036656(i);
		}
		return p;
	}

	static double N120fe25647(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.05) {
			p = WekaClassifier.N50d4855d48(i);
		} else if (((Double) i[7]).doubleValue() > 0.05) {
			p = WekaClassifier.N14a877c050(i);
		}
		return p;
	}

	static double N50d4855d48(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.17) {
			p = WekaClassifier.N307fc62049(i);
		} else if (((Double) i[4]).doubleValue() > 0.17) {
			p = 0;
		}
		return p;
	}

	static double N307fc62049(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.66) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.66) {
			p = 0;
		}
		return p;
	}

	static double N14a877c050(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N404de8d851(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = 1;
		}
		return p;
	}

	static double N404de8d851(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.05) {
			p = WekaClassifier.N3af1dde352(i);
		} else if (((Double) i[5]).doubleValue() > 0.05) {
			p = 1;
		}
		return p;
	}

	static double N3af1dde352(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.N5d1e805053(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = WekaClassifier.N61e8953955(i);
		}
		return p;
	}

	static double N5d1e805053(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = WekaClassifier.N7d7a33f254(i);
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N7d7a33f254(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.64) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.64) {
			p = 1;
		}
		return p;
	}

	static double N61e8953955(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.05) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() > 0.05) {
			p = 0;
		}
		return p;
	}

	static double N6c9d036656(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.18) {
			p = WekaClassifier.N384a553f57(i);
		} else if (((Double) i[6]).doubleValue() > 0.18) {
			p = WekaClassifier.N3677859063(i);
		}
		return p;
	}

	static double N384a553f57(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.7) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.7) {
			p = WekaClassifier.N1822b7f858(i);
		}
		return p;
	}

	static double N1822b7f858(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.05) {
			p = WekaClassifier.Nf5c072959(i);
		} else if (((Double) i[7]).doubleValue() > 0.05) {
			p = 1;
		}
		return p;
	}

	static double Nf5c072959(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.02) {
			p = WekaClassifier.N62da1c9f60(i);
		} else if (((Double) i[5]).doubleValue() > 0.02) {
			p = WekaClassifier.N63bbad6f62(i);
		}
		return p;
	}

	static double N62da1c9f60(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.15) {
			p = WekaClassifier.N5d8d3d6c61(i);
		} else if (((Double) i[4]).doubleValue() > 0.15) {
			p = 0;
		}
		return p;
	}

	static double N5d8d3d6c61(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.06) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() > 0.06) {
			p = 0;
		}
		return p;
	}

	static double N63bbad6f62(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.76) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.76) {
			p = 1;
		}
		return p;
	}

	static double N3677859063(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.28) {
			p = WekaClassifier.N60e1ccdf64(i);
		} else if (((Double) i[6]).doubleValue() > 0.28) {
			p = WekaClassifier.N3d1258b271(i);
		}
		return p;
	}

	static double N60e1ccdf64(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.17) {
			p = WekaClassifier.N851762a65(i);
		} else if (((Double) i[1]).doubleValue() > 0.17) {
			p = WekaClassifier.N31a9dc5567(i);
		}
		return p;
	}

	static double N851762a65(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = WekaClassifier.N1c24c3aa66(i);
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N1c24c3aa66(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.15) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.15) {
			p = 1;
		}
		return p;
	}

	static double N31a9dc5567(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.68) {
			p = WekaClassifier.N6c069ec68(i);
		} else if (((Double) i[3]).doubleValue() > 0.68) {
			p = WekaClassifier.N426a086a69(i);
		}
		return p;
	}

	static double N6c069ec68(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.04) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.04) {
			p = 1;
		}
		return p;
	}

	static double N426a086a69(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.72) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.72) {
			p = WekaClassifier.N3f4e893670(i);
		}
		return p;
	}

	static double N3f4e893670(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.23) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.23) {
			p = 0;
		}
		return p;
	}

	static double N3d1258b271(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.76) {
			p = WekaClassifier.N7f7d2d7072(i);
		} else if (((Double) i[0]).doubleValue() > 0.76) {
			p = 1;
		}
		return p;
	}

	static double N7f7d2d7072(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N7332557373(Object[] i) {
		double p = Double.NaN;
		if (i[11] == null) {
			p = 0;
		} else if (((Double) i[11]).doubleValue() <= 0.0) {
			p = WekaClassifier.N6d882c1a74(i);
		} else if (((Double) i[11]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}

	static double N6d882c1a74(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.01) {
			p = WekaClassifier.N707972475(i);
		} else if (((Double) i[2]).doubleValue() > 0.01) {
			p = WekaClassifier.N353c01776(i);
		}
		return p;
	}

	static double N707972475(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() > 0.1) {
			p = 0;
		}
		return p;
	}

	static double N353c01776(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.07) {
			p = WekaClassifier.N6f6827b577(i);
		} else if (((Double) i[7]).doubleValue() > 0.07) {
			p = WekaClassifier.N43ad637e92(i);
		}
		return p;
	}

	static double N6f6827b577(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.05) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.05) {
			p = WekaClassifier.N1f07153778(i);
		}
		return p;
	}

	static double N1f07153778(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.09) {
			p = WekaClassifier.Na01ddcb79(i);
		} else if (((Double) i[9]).doubleValue() > 0.09) {
			p = WekaClassifier.N66d70fc89(i);
		}
		return p;
	}

	static double Na01ddcb79(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.77) {
			p = WekaClassifier.N883f8f80(i);
		} else if (((Double) i[0]).doubleValue() > 0.77) {
			p = WekaClassifier.N120942eb86(i);
		}
		return p;
	}

	static double N883f8f80(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = WekaClassifier.N7104c58e81(i);
		}
		return p;
	}

	static double N7104c58e81(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.02) {
			p = WekaClassifier.N7046279982(i);
		}
		return p;
	}

	static double N7046279982(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 0.02) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() > 0.02) {
			p = WekaClassifier.Ndd1e76583(i);
		}
		return p;
	}

	static double Ndd1e76583(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.33) {
			p = WekaClassifier.N4fae9ef984(i);
		} else if (((Double) i[6]).doubleValue() > 0.33) {
			p = WekaClassifier.N5147164d85(i);
		}
		return p;
	}

	static double N4fae9ef984(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.04) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() > 0.04) {
			p = 0;
		}
		return p;
	}

	static double N5147164d85(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}

	static double N120942eb86(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.35) {
			p = WekaClassifier.N1e085b8d87(i);
		} else if (((Double) i[6]).doubleValue() > 0.35) {
			p = 0;
		}
		return p;
	}

	static double N1e085b8d87(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.33) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.33) {
			p = WekaClassifier.N36b2956288(i);
		}
		return p;
	}

	static double N36b2956288(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.78) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.78) {
			p = 0;
		}
		return p;
	}

	static double N66d70fc89(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 1;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.N7b14b3af90(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}

	static double N7b14b3af90(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.1) {
			p = WekaClassifier.Nc20e54a91(i);
		} else if (((Double) i[9]).doubleValue() > 0.1) {
			p = 1;
		}
		return p;
	}

	static double Nc20e54a91(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N43ad637e92(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = WekaClassifier.N2c5f07e893(i);
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = WekaClassifier.N3020e74699(i);
		}
		return p;
	}

	static double N2c5f07e893(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.31) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.31) {
			p = WekaClassifier.N1300271a94(i);
		}
		return p;
	}

	static double N1300271a94(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = WekaClassifier.N6f0725b495(i);
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N6f0725b495(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.02) {
			p = WekaClassifier.N3e3c855e96(i);
		} else if (((Double) i[10]).doubleValue() > 0.02) {
			p = WekaClassifier.N7757fe3d97(i);
		}
		return p;
	}

	static double N3e3c855e96(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.04) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() > 0.04) {
			p = 1;
		}
		return p;
	}

	static double N7757fe3d97(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.21) {
			p = WekaClassifier.N31f4940198(i);
		} else if (((Double) i[1]).doubleValue() > 0.21) {
			p = 0;
		}
		return p;
	}

	static double N31f4940198(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.7) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.7) {
			p = 0;
		}
		return p;
	}

	static double N3020e74699(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.36) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.36) {
			p = WekaClassifier.N402fbd59100(i);
		}
		return p;
	}

	static double N402fbd59100(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.61) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() > 0.61) {
			p = WekaClassifier.N7e383efa101(i);
		}
		return p;
	}

	static double N7e383efa101(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.09) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.09) {
			p = 1;
		}
		return p;
	}
}