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
		p = WekaClassifier.N3e2f1b1a0(i);
		return p;
	}

	static double N3e2f1b1a0(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.1) {
			p = WekaClassifier.N69c67db1(i);
		}
		return p;
	}

	static double N69c67db1(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.07) {
			p = WekaClassifier.N6665e412(i);
		} else if (((Double) i[7]).doubleValue() > 0.07) {
			p = WekaClassifier.N1a779dce57(i);
		}
		return p;
	}

	static double N6665e412(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.57) {
			p = WekaClassifier.N2ab600af3(i);
		} else if (((Double) i[0]).doubleValue() > 0.57) {
			p = WekaClassifier.N12e6f7114(i);
		}
		return p;
	}

	static double N2ab600af3(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() > 0.02) {
			p = 1;
		}
		return p;
	}

	static double N12e6f7114(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.01) {
			p = WekaClassifier.N796686c85(i);
		} else if (((Double) i[2]).doubleValue() > 0.01) {
			p = WekaClassifier.Nae3865e14(i);
		}
		return p;
	}

	static double N796686c85(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.02) {
			p = WekaClassifier.N3a1af2bc6(i);
		} else if (((Double) i[7]).doubleValue() > 0.02) {
			p = WekaClassifier.N3f3632717(i);
		}
		return p;
	}

	static double N3a1af2bc6(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.8) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.8) {
			p = 1;
		}
		return p;
	}

	static double N3f3632717(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.11) {
			p = WekaClassifier.N7f29b9228(i);
		} else if (((Double) i[1]).doubleValue() > 0.11) {
			p = WekaClassifier.N1d5f087610(i);
		}
		return p;
	}

	static double N7f29b9228(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.04) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.04) {
			p = WekaClassifier.Nc39a8679(i);
		}
		return p;
	}

	static double Nc39a8679(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.83) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.83) {
			p = 0;
		}
		return p;
	}

	static double N1d5f087610(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = WekaClassifier.N4a248a0a11(i);
		}
		return p;
	}

	static double N4a248a0a11(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = WekaClassifier.N24e2bc8d12(i);
		}
		return p;
	}

	static double N24e2bc8d12(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 0.01) {
			p = WekaClassifier.N21c8dfe613(i);
		} else if (((Double) i[5]).doubleValue() > 0.01) {
			p = 1;
		}
		return p;
	}

	static double N21c8dfe613(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.05) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() > 0.05) {
			p = 0;
		}
		return p;
	}

	static double Nae3865e14(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.05) {
			p = WekaClassifier.N5f8a8ae715(i);
		} else if (((Double) i[10]).doubleValue() > 0.05) {
			p = WekaClassifier.N533790eb45(i);
		}
		return p;
	}

	static double N5f8a8ae715(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.06) {
			p = WekaClassifier.Na574b216(i);
		} else if (((Double) i[9]).doubleValue() > 0.06) {
			p = WekaClassifier.N15e3d24a29(i);
		}
		return p;
	}

	static double Na574b216(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.3) {
			p = WekaClassifier.N6e90527217(i);
		} else if (((Double) i[6]).doubleValue() > 0.3) {
			p = 0;
		}
		return p;
	}

	static double N6e90527217(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.N4514f31318(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = WekaClassifier.N4839880a26(i);
		}
		return p;
	}

	static double N4514f31318(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = WekaClassifier.N626287d319(i);
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N626287d319(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.2) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.2) {
			p = WekaClassifier.N32c3601b20(i);
		}
		return p;
	}

	static double N32c3601b20(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.03) {
			p = WekaClassifier.N38daa6a621(i);
		} else if (((Double) i[5]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N38daa6a621(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.74) {
			p = WekaClassifier.N1af2f97322(i);
		} else if (((Double) i[0]).doubleValue() > 0.74) {
			p = WekaClassifier.N24ee5d1324(i);
		}
		return p;
	}

	static double N1af2f97322(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.15) {
			p = WekaClassifier.N45d6fad723(i);
		} else if (((Double) i[4]).doubleValue() > 0.15) {
			p = 0;
		}
		return p;
	}

	static double N45d6fad723(Object[] i) {
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

	static double N24ee5d1324(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.02) {
			p = WekaClassifier.N1d209d5625(i);
		} else if (((Double) i[5]).doubleValue() > 0.02) {
			p = 1;
		}
		return p;
	}

	static double N1d209d5625(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.78) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.78) {
			p = 1;
		}
		return p;
	}

	static double N4839880a26(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() > 0.03) {
			p = WekaClassifier.N39126d9127(i);
		}
		return p;
	}

	static double N39126d9127(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.04) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.04) {
			p = WekaClassifier.N68d767dc28(i);
		}
		return p;
	}

	static double N68d767dc28(Object[] i) {
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

	static double N15e3d24a29(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.66) {
			p = WekaClassifier.N2003038030(i);
		} else if (((Double) i[0]).doubleValue() > 0.66) {
			p = WekaClassifier.N25964fe835(i);
		}
		return p;
	}

	static double N2003038030(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.61) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.61) {
			p = WekaClassifier.N25dad8eb31(i);
		}
		return p;
	}

	static double N25dad8eb31(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.59) {
			p = WekaClassifier.N42d73fb732(i);
		} else if (((Double) i[3]).doubleValue() > 0.59) {
			p = WekaClassifier.N419829a933(i);
		}
		return p;
	}

	static double N42d73fb732(Object[] i) {
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

	static double N419829a933(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.65) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.65) {
			p = WekaClassifier.N6cd737e334(i);
		}
		return p;
	}

	static double N6cd737e334(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.16) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() > 0.16) {
			p = 1;
		}
		return p;
	}

	static double N25964fe835(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.13) {
			p = WekaClassifier.N2f581b9f36(i);
		} else if (((Double) i[1]).doubleValue() > 0.13) {
			p = WekaClassifier.N417d7c0137(i);
		}
		return p;
	}

	static double N2f581b9f36(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.14) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.14) {
			p = 0;
		}
		return p;
	}

	static double N417d7c0137(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.23) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.23) {
			p = WekaClassifier.N1558473e38(i);
		}
		return p;
	}

	static double N1558473e38(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.32) {
			p = WekaClassifier.N56ad426439(i);
		} else if (((Double) i[6]).doubleValue() > 0.32) {
			p = WekaClassifier.N1872c95042(i);
		}
		return p;
	}

	static double N56ad426439(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N8e1dfb140(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = WekaClassifier.N2524e20541(i);
		}
		return p;
	}

	static double N8e1dfb140(Object[] i) {
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

	static double N2524e20541(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() > 0.02) {
			p = 1;
		}
		return p;
	}

	static double N1872c95042(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.09) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.09) {
			p = WekaClassifier.N17feafba43(i);
		}
		return p;
	}

	static double N17feafba43(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.1) {
			p = WekaClassifier.N51c2e8a444(i);
		} else if (((Double) i[9]).doubleValue() > 0.1) {
			p = 1;
		}
		return p;
	}

	static double N51c2e8a444(Object[] i) {
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

	static double N533790eb45(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.16) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.16) {
			p = WekaClassifier.N650b5efb46(i);
		}
		return p;
	}

	static double N650b5efb46(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = WekaClassifier.N4d88e49047(i);
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N4d88e49047(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.29) {
			p = WekaClassifier.N5655d1b448(i);
		} else if (((Double) i[6]).doubleValue() > 0.29) {
			p = WekaClassifier.N474e8d6755(i);
		}
		return p;
	}

	static double N5655d1b448(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = WekaClassifier.N1c3aacb449(i);
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = 1;
		}
		return p;
	}

	static double N1c3aacb449(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.05) {
			p = WekaClassifier.N50206be650(i);
		} else if (((Double) i[2]).doubleValue() > 0.05) {
			p = 1;
		}
		return p;
	}

	static double N50206be650(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.03) {
			p = WekaClassifier.N8bf22351(i);
		} else if (((Double) i[2]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N8bf22351(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.06) {
			p = WekaClassifier.N63c5d81c52(i);
		} else if (((Double) i[7]).doubleValue() > 0.06) {
			p = 1;
		}
		return p;
	}

	static double N63c5d81c52(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.74) {
			p = WekaClassifier.N51ef497053(i);
		} else if (((Double) i[0]).doubleValue() > 0.74) {
			p = 0;
		}
		return p;
	}

	static double N51ef497053(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.02) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() > 0.02) {
			p = WekaClassifier.N34be821654(i);
		}
		return p;
	}

	static double N34be821654(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.17) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() > 0.17) {
			p = 1;
		}
		return p;
	}

	static double N474e8d6755(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = WekaClassifier.N762589c356(i);
		}
		return p;
	}

	static double N762589c356(Object[] i) {
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

	static double N1a779dce57(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.08) {
			p = WekaClassifier.N23194cf558(i);
		} else if (((Double) i[7]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N23194cf558(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = WekaClassifier.N520b7ad359(i);
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = 1;
		}
		return p;
	}

	static double N520b7ad359(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.31) {
			p = WekaClassifier.N6facdcb960(i);
		} else if (((Double) i[6]).doubleValue() > 0.31) {
			p = WekaClassifier.N6d14382d62(i);
		}
		return p;
	}

	static double N6facdcb960(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.02) {
			p = WekaClassifier.N3dcf2ef661(i);
		} else if (((Double) i[10]).doubleValue() > 0.02) {
			p = 1;
		}
		return p;
	}

	static double N3dcf2ef661(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.6) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.6) {
			p = 0;
		}
		return p;
	}

	static double N6d14382d62(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = WekaClassifier.N4a744a4d63(i);
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = WekaClassifier.N705063a579(i);
		}
		return p;
	}

	static double N4a744a4d63(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.13) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() > 0.13) {
			p = WekaClassifier.N18ba2b6b64(i);
		}
		return p;
	}

	static double N18ba2b6b64(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.37) {
			p = WekaClassifier.N66788a7b65(i);
		} else if (((Double) i[6]).doubleValue() > 0.37) {
			p = WekaClassifier.N77fef1a075(i);
		}
		return p;
	}

	static double N66788a7b65(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.19) {
			p = WekaClassifier.N73cbc5cb66(i);
		} else if (((Double) i[1]).doubleValue() > 0.19) {
			p = WekaClassifier.N1857161570(i);
		}
		return p;
	}

	static double N73cbc5cb66(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 0.03) {
			p = WekaClassifier.N4726cdd167(i);
		} else if (((Double) i[5]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N4726cdd167(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N449278d568(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = 1;
		}
		return p;
	}

	static double N449278d568(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = WekaClassifier.N6c3b0b1e69(i);
		}
		return p;
	}

	static double N6c3b0b1e69(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.59) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() > 0.59) {
			p = 1;
		}
		return p;
	}

	static double N1857161570(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 0;
		} else if (((Double) i[2]).doubleValue() <= 0.05) {
			p = WekaClassifier.N7d6ac92e71(i);
		} else if (((Double) i[2]).doubleValue() > 0.05) {
			p = 0;
		}
		return p;
	}

	static double N7d6ac92e71(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.04) {
			p = WekaClassifier.N6dbe2b5572(i);
		} else if (((Double) i[5]).doubleValue() > 0.04) {
			p = WekaClassifier.N7d557ee874(i);
		}
		return p;
	}

	static double N6dbe2b5572(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.21) {
			p = WekaClassifier.N600f11bc73(i);
		} else if (((Double) i[1]).doubleValue() > 0.21) {
			p = 0;
		}
		return p;
	}

	static double N600f11bc73(Object[] i) {
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

	static double N7d557ee874(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.33) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.33) {
			p = 0;
		}
		return p;
	}

	static double N77fef1a075(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.38) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.38) {
			p = WekaClassifier.N2a97cec76(i);
		}
		return p;
	}

	static double N2a97cec76(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.04) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() > 0.04) {
			p = WekaClassifier.N45486b5177(i);
		}
		return p;
	}

	static double N45486b5177(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.39) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.39) {
			p = WekaClassifier.N157db66078(i);
		}
		return p;
	}

	static double N157db66078(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.4) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.4) {
			p = 0;
		}
		return p;
	}

	static double N705063a579(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.05) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.05) {
			p = WekaClassifier.N2dbe1f3e80(i);
		}
		return p;
	}

	static double N2dbe1f3e80(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.1) {
			p = WekaClassifier.N1cf536e881(i);
		} else if (((Double) i[9]).doubleValue() > 0.1) {
			p = 1;
		}
		return p;
	}

	static double N1cf536e881(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.69) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.69) {
			p = 0;
		}
		return p;
	}
}