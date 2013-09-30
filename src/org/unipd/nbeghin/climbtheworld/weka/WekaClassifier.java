package org.unipd.nbeghin.climbtheworld.weka;

public class WekaClassifier {
	public static double classify(Object[] i) throws Exception {
		double p = Double.NaN;
		p = WekaClassifier.N7896b1b80(i);
		return p;
	}

	private static String[]	classifications	= new String[] { "NONSTAIR", "DOWNSTAIRS", "UPSTAIRS" };

	public static String explicit_classify(Object[] i) throws Exception {
		return classifications[(int) classify(i)];
	}

	static double N7896b1b80(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.9) {
			p = WekaClassifier.N6d6de4e11(i);
		} else if (((Double) i[0]).doubleValue() > 0.9) {
			p = 0;
		}
		return p;
	}

	static double N6d6de4e11(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.75) {
			p = WekaClassifier.N49cda7e72(i);
		} else if (((Double) i[3]).doubleValue() > 0.75) {
			p = WekaClassifier.N231d221e29(i);
		}
		return p;
	}

	static double N49cda7e72(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.09) {
			p = WekaClassifier.N5cca548b3(i);
		} else if (((Double) i[10]).doubleValue() > 0.09) {
			p = WekaClassifier.N7379567c28(i);
		}
		return p;
	}

	static double N5cca548b3(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.02) {
			p = WekaClassifier.N6774a1444(i);
		} else if (((Double) i[7]).doubleValue() > 0.02) {
			p = WekaClassifier.N6dc8f3cd6(i);
		}
		return p;
	}

	static double N6774a1444(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.71) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.71) {
			p = WekaClassifier.N1a07ac6c5(i);
		}
		return p;
	}

	static double N1a07ac6c5(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 2;
		} else if (((Double) i[0]).doubleValue() <= 0.74) {
			p = 2;
		} else if (((Double) i[0]).doubleValue() > 0.74) {
			p = 1;
		}
		return p;
	}

	static double N6dc8f3cd6(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 2;
		} else if (((Double) i[1]).doubleValue() <= 0.09) {
			p = WekaClassifier.N23fe500a7(i);
		} else if (((Double) i[1]).doubleValue() > 0.09) {
			p = WekaClassifier.N1f72e08e12(i);
		}
		return p;
	}

	static double N23fe500a7(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.06) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() > 0.06) {
			p = WekaClassifier.Nd38d2fc8(i);
		}
		return p;
	}

	static double Nd38d2fc8(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 2;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.Nda3a52c9(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = 2;
		}
		return p;
	}

	static double Nda3a52c9(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.82) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.82) {
			p = WekaClassifier.N72b0f2b210(i);
		}
		return p;
	}

	static double N72b0f2b210(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 2;
		} else if (((Double) i[6]).doubleValue() <= 0.53) {
			p = 2;
		} else if (((Double) i[6]).doubleValue() > 0.53) {
			p = WekaClassifier.N3f0dbef111(i);
		}
		return p;
	}

	static double N3f0dbef111(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.05) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() > 0.05) {
			p = 2;
		}
		return p;
	}

	static double N1f72e08e12(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.02) {
			p = WekaClassifier.N2ef49ac313(i);
		} else if (((Double) i[2]).doubleValue() > 0.02) {
			p = WekaClassifier.N7e820d5326(i);
		}
		return p;
	}

	static double N2ef49ac313(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.2) {
			p = WekaClassifier.N3cdc904a14(i);
		} else if (((Double) i[6]).doubleValue() > 0.2) {
			p = WekaClassifier.N663f3fbd20(i);
		}
		return p;
	}

	static double N3cdc904a14(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.N3485097d15(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}

	static double N3485097d15(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.01) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.01) {
			p = WekaClassifier.Na32087b16(i);
		}
		return p;
	}

	static double Na32087b16(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.05) {
			p = WekaClassifier.N5acac87717(i);
		} else if (((Double) i[7]).doubleValue() > 0.05) {
			p = 2;
		}
		return p;
	}

	static double N5acac87717(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.14) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() > 0.14) {
			p = WekaClassifier.N372f2b3218(i);
		}
		return p;
	}

	static double N372f2b3218(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 2;
		} else if (((Double) i[10]).doubleValue() <= 0.04) {
			p = 2;
		} else if (((Double) i[10]).doubleValue() > 0.04) {
			p = WekaClassifier.N79bcfbeb19(i);
		}
		return p;
	}

	static double N79bcfbeb19(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.74) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.74) {
			p = 2;
		}
		return p;
	}

	static double N663f3fbd20(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.07) {
			p = WekaClassifier.N4271c5bc21(i);
		} else if (((Double) i[7]).doubleValue() > 0.07) {
			p = WekaClassifier.N67cd2b8223(i);
		}
		return p;
	}

	static double N4271c5bc21(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 2;
		} else if (((Double) i[0]).doubleValue() <= 0.72) {
			p = WekaClassifier.N3b6cdbb822(i);
		} else if (((Double) i[0]).doubleValue() > 0.72) {
			p = 1;
		}
		return p;
	}

	static double N3b6cdbb822(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 2;
		}
		return p;
	}

	static double N67cd2b8223(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 2;
		} else if (((Double) i[2]).doubleValue() <= 0.01) {
			p = WekaClassifier.N4ee3990b24(i);
		} else if (((Double) i[2]).doubleValue() > 0.01) {
			p = 0;
		}
		return p;
	}

	static double N4ee3990b24(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = WekaClassifier.N4054c9a325(i);
		}
		return p;
	}

	static double N4054c9a325(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 2;
		} else if (((Double) i[4]).doubleValue() <= 0.11) {
			p = 2;
		} else if (((Double) i[4]).doubleValue() > 0.11) {
			p = 0;
		}
		return p;
	}

	static double N7e820d5326(Object[] i) {
		double p = Double.NaN;
		if (i[11] == null) {
			p = 2;
		} else if (((Double) i[11]).doubleValue() <= 0.0) {
			p = WekaClassifier.Nc39050827(i);
		} else if (((Double) i[11]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}

	static double Nc39050827(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.08) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.08) {
			p = 2;
		}
		return p;
	}

	static double N7379567c28(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.31) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.31) {
			p = 0;
		}
		return p;
	}

	static double N231d221e29(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.01) {
			p = WekaClassifier.N4daaf19430(i);
		} else if (((Double) i[2]).doubleValue() > 0.01) {
			p = WekaClassifier.N29be513c36(i);
		}
		return p;
	}

	static double N4daaf19430(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 2;
		} else if (((Double) i[6]).doubleValue() <= 0.11) {
			p = WekaClassifier.N11da536231(i);
		} else if (((Double) i[6]).doubleValue() > 0.11) {
			p = WekaClassifier.N1498501632(i);
		}
		return p;
	}

	static double N11da536231(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = 2;
		}
		return p;
	}

	static double N1498501632(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.25) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.25) {
			p = WekaClassifier.N1bb1deea33(i);
		}
		return p;
	}

	static double N1bb1deea33(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.1) {
			p = WekaClassifier.N3aa1e2da34(i);
		} else if (((Double) i[1]).doubleValue() > 0.1) {
			p = 1;
		}
		return p;
	}

	static double N3aa1e2da34(Object[] i) {
		double p = Double.NaN;
		if (i[11] == null) {
			p = 0;
		} else if (((Double) i[11]).doubleValue() <= 0.0) {
			p = WekaClassifier.N5a34744835(i);
		} else if (((Double) i[11]).doubleValue() > 0.0) {
			p = 0;
		}
		return p;
	}

	static double N5a34744835(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.38) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.38) {
			p = 2;
		}
		return p;
	}

	static double N29be513c36(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 2;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = WekaClassifier.Ndc74f8d37(i);
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = WekaClassifier.Nab245dc44(i);
		}
		return p;
	}

	static double Ndc74f8d37(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 2;
		} else if (((Double) i[1]).doubleValue() <= 0.16) {
			p = WekaClassifier.N1837b90c38(i);
		} else if (((Double) i[1]).doubleValue() > 0.16) {
			p = 1;
		}
		return p;
	}

	static double N1837b90c38(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 2;
		} else if (((Double) i[6]).doubleValue() <= 0.15) {
			p = WekaClassifier.N7255cf3f39(i);
		} else if (((Double) i[6]).doubleValue() > 0.15) {
			p = WekaClassifier.N5170765341(i);
		}
		return p;
	}

	static double N7255cf3f39(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.03) {
			p = WekaClassifier.N5f996bbd40(i);
		} else if (((Double) i[9]).doubleValue() > 0.03) {
			p = 2;
		}
		return p;
	}

	static double N5f996bbd40(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 2;
		} else if (((Double) i[1]).doubleValue() <= 0.14) {
			p = 2;
		} else if (((Double) i[1]).doubleValue() > 0.14) {
			p = 1;
		}
		return p;
	}

	static double N5170765341(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.04) {
			p = WekaClassifier.N2e686cea42(i);
		} else if (((Double) i[7]).doubleValue() > 0.04) {
			p = 2;
		}
		return p;
	}

	static double N2e686cea42(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.02) {
			p = WekaClassifier.N49c68e7343(i);
		} else if (((Double) i[2]).doubleValue() > 0.02) {
			p = 0;
		}
		return p;
	}

	static double N49c68e7343(Object[] i) {
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

	static double Nab245dc44(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.N3e08740045(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = 1;
		}
		return p;
	}

	static double N3e08740045(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}
}
