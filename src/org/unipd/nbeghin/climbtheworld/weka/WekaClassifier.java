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
		p = WekaClassifier.N761db1c50(i);
		return p;
	}

	static double N761db1c50(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.1) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.1) {
			p = WekaClassifier.N178239181(i);
		}
		return p;
	}

	static double N178239181(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.07) {
			p = WekaClassifier.N667262b62(i);
		} else if (((Double) i[7]).doubleValue() > 0.07) {
			p = WekaClassifier.N6411c21b37(i);
		}
		return p;
	}

	static double N667262b62(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 1;
		} else if (((Double) i[5]).doubleValue() <= 0.01) {
			p = WekaClassifier.N5faecf453(i);
		} else if (((Double) i[5]).doubleValue() > 0.01) {
			p = WekaClassifier.N3e10c9867(i);
		}
		return p;
	}

	static double N5faecf453(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = WekaClassifier.N4da9ec164(i);
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = 1;
		}
		return p;
	}

	static double N4da9ec164(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.11) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.11) {
			p = WekaClassifier.N4ecac02f5(i);
		}
		return p;
	}

	static double N4ecac02f5(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.11) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.11) {
			p = WekaClassifier.N610f76126(i);
		}
		return p;
	}

	static double N610f76126(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = 0;
		}
		return p;
	}

	static double N3e10c9867(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.05) {
			p = WekaClassifier.N401e9c3f8(i);
		} else if (((Double) i[10]).doubleValue() > 0.05) {
			p = WekaClassifier.Na0002df32(i);
		}
		return p;
	}

	static double N401e9c3f8(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() > 0.02) {
			p = WekaClassifier.N19a40cfc9(i);
		}
		return p;
	}

	static double N19a40cfc9(Object[] i) {
		double p = Double.NaN;
		if (i[8] == null) {
			p = 0;
		} else if (((Double) i[8]).doubleValue() <= 0.0) {
			p = WekaClassifier.N6150818a10(i);
		} else if (((Double) i[8]).doubleValue() > 0.0) {
			p = WekaClassifier.N6521f95623(i);
		}
		return p;
	}

	static double N6150818a10(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.78) {
			p = WekaClassifier.N6c68bcef11(i);
		} else if (((Double) i[0]).doubleValue() > 0.78) {
			p = WekaClassifier.N53f336eb19(i);
		}
		return p;
	}

	static double N6c68bcef11(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = WekaClassifier.N504c268312(i);
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = WekaClassifier.N4bbf8a4116(i);
		}
		return p;
	}

	static double N504c268312(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.3) {
			p = WekaClassifier.N37748ba413(i);
		} else if (((Double) i[6]).doubleValue() > 0.3) {
			p = 0;
		}
		return p;
	}

	static double N37748ba413(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() <= 0.18) {
			p = 0;
		} else if (((Double) i[6]).doubleValue() > 0.18) {
			p = WekaClassifier.N447bd86d14(i);
		}
		return p;
	}

	static double N447bd86d14(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.73) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.73) {
			p = WekaClassifier.N1ebdff3b15(i);
		}
		return p;
	}

	static double N1ebdff3b15(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.16) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.16) {
			p = 1;
		}
		return p;
	}

	static double N4bbf8a4116(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.7) {
			p = WekaClassifier.Nc0dd84117(i);
		} else if (((Double) i[0]).doubleValue() > 0.7) {
			p = 0;
		}
		return p;
	}

	static double Nc0dd84117(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.04) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.04) {
			p = WekaClassifier.N60f0999518(i);
		}
		return p;
	}

	static double N60f0999518(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.32) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.32) {
			p = 0;
		}
		return p;
	}

	static double N53f336eb19(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.03) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.03) {
			p = WekaClassifier.N49aa95c20(i);
		}
		return p;
	}

	static double N49aa95c20(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.32) {
			p = WekaClassifier.N45e4d96021(i);
		} else if (((Double) i[6]).doubleValue() > 0.32) {
			p = 0;
		}
		return p;
	}

	static double N45e4d96021(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.77) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() > 0.77) {
			p = WekaClassifier.N337b517922(i);
		}
		return p;
	}

	static double N337b517922(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() <= 0.13) {
			p = 1;
		} else if (((Double) i[4]).doubleValue() > 0.13) {
			p = 0;
		}
		return p;
	}

	static double N6521f95623(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.22) {
			p = WekaClassifier.N1978b0f924(i);
		} else if (((Double) i[1]).doubleValue() > 0.22) {
			p = 0;
		}
		return p;
	}

	static double N1978b0f924(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.21) {
			p = WekaClassifier.N42aab87f25(i);
		} else if (((Double) i[4]).doubleValue() > 0.21) {
			p = 1;
		}
		return p;
	}

	static double N42aab87f25(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 0;
		} else if (((Double) i[1]).doubleValue() <= 0.2) {
			p = WekaClassifier.N5636bc0a26(i);
		} else if (((Double) i[1]).doubleValue() > 0.2) {
			p = 0;
		}
		return p;
	}

	static double N5636bc0a26(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.08) {
			p = WekaClassifier.N237360be27(i);
		} else if (((Double) i[9]).doubleValue() > 0.08) {
			p = 1;
		}
		return p;
	}

	static double N237360be27(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() > 0.02) {
			p = WekaClassifier.N6bd46c2028(i);
		}
		return p;
	}

	static double N6bd46c2028(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.17) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.17) {
			p = WekaClassifier.N4706e02e29(i);
		}
		return p;
	}

	static double N4706e02e29(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.18) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.18) {
			p = WekaClassifier.N1468107030(i);
		}
		return p;
	}

	static double N1468107030(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (((Double) i[3]).doubleValue() <= 0.61) {
			p = WekaClassifier.N3c0f338731(i);
		} else if (((Double) i[3]).doubleValue() > 0.61) {
			p = 1;
		}
		return p;
	}

	static double N3c0f338731(Object[] i) {
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

	static double Na0002df32(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.58) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.58) {
			p = WekaClassifier.N6bc839a33(i);
		}
		return p;
	}

	static double N6bc839a33(Object[] i) {
		double p = Double.NaN;
		if (i[11] == null) {
			p = 0;
		} else if (((Double) i[11]).doubleValue() <= 0.0) {
			p = WekaClassifier.N4263f6ea34(i);
		} else if (((Double) i[11]).doubleValue() > 0.0) {
			p = WekaClassifier.N366412da36(i);
		}
		return p;
	}

	static double N4263f6ea34(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.09) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.09) {
			p = WekaClassifier.N30e79eb335(i);
		}
		return p;
	}

	static double N30e79eb335(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.05) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.05) {
			p = 1;
		}
		return p;
	}

	static double N366412da36(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.29) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.29) {
			p = 0;
		}
		return p;
	}

	static double N6411c21b37(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 1;
		} else if (((Double) i[7]).doubleValue() <= 0.08) {
			p = WekaClassifier.N49deadf038(i);
		} else if (((Double) i[7]).doubleValue() > 0.08) {
			p = WekaClassifier.N7d05e56062(i);
		}
		return p;
	}

	static double N49deadf038(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.06) {
			p = WekaClassifier.N3a6d8d7339(i);
		} else if (((Double) i[10]).doubleValue() > 0.06) {
			p = 1;
		}
		return p;
	}

	static double N3a6d8d7339(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.09) {
			p = WekaClassifier.N6e6196fc40(i);
		} else if (((Double) i[9]).doubleValue() > 0.09) {
			p = 1;
		}
		return p;
	}

	static double N6e6196fc40(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.33) {
			p = WekaClassifier.N48ffb30141(i);
		} else if (((Double) i[6]).doubleValue() > 0.33) {
			p = WekaClassifier.N1471cb2554(i);
		}
		return p;
	}

	static double N48ffb30141(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.05) {
			p = WekaClassifier.Nb412c1842(i);
		} else if (((Double) i[10]).doubleValue() > 0.05) {
			p = WekaClassifier.N592fa61752(i);
		}
		return p;
	}

	static double Nb412c1842(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.16) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() > 0.16) {
			p = WekaClassifier.N63b5e16d43(i);
		}
		return p;
	}

	static double N63b5e16d43(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.19) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.19) {
			p = WekaClassifier.N39e2ee3f44(i);
		}
		return p;
	}

	static double N39e2ee3f44(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.27) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.27) {
			p = WekaClassifier.N618787c945(i);
		}
		return p;
	}

	static double N618787c945(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.05) {
			p = WekaClassifier.N8e3cf2546(i);
		} else if (((Double) i[2]).doubleValue() > 0.05) {
			p = WekaClassifier.N5b941dc951(i);
		}
		return p;
	}

	static double N8e3cf2546(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.04) {
			p = WekaClassifier.N24348ab247(i);
		} else if (((Double) i[5]).doubleValue() > 0.04) {
			p = WekaClassifier.N12720f6c49(i);
		}
		return p;
	}

	static double N24348ab247(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.21) {
			p = WekaClassifier.N757dbeaf48(i);
		} else if (((Double) i[1]).doubleValue() > 0.21) {
			p = 0;
		}
		return p;
	}

	static double N757dbeaf48(Object[] i) {
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

	static double N12720f6c49(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.22) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.22) {
			p = WekaClassifier.N7e5284e950(i);
		}
		return p;
	}

	static double N7e5284e950(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.6) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.6) {
			p = 1;
		}
		return p;
	}

	static double N5b941dc951(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.04) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() > 0.04) {
			p = 1;
		}
		return p;
	}

	static double N592fa61752(Object[] i) {
		double p = Double.NaN;
		if (i[3] == null) {
			p = 1;
		} else if (((Double) i[3]).doubleValue() <= 0.57) {
			p = WekaClassifier.N47415dbf53(i);
		} else if (((Double) i[3]).doubleValue() > 0.57) {
			p = 1;
		}
		return p;
	}

	static double N47415dbf53(Object[] i) {
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

	static double N1471cb2554(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.04) {
			p = WekaClassifier.N3acff49f55(i);
		} else if (((Double) i[2]).doubleValue() > 0.04) {
			p = 0;
		}
		return p;
	}

	static double N3acff49f55(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.41) {
			p = WekaClassifier.N2ac510e356(i);
		} else if (((Double) i[6]).doubleValue() > 0.41) {
			p = 1;
		}
		return p;
	}

	static double N2ac510e356(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.03) {
			p = WekaClassifier.N6fd7bd0457(i);
		} else if (((Double) i[5]).doubleValue() > 0.03) {
			p = WekaClassifier.N3561260061(i);
		}
		return p;
	}

	static double N6fd7bd0457(Object[] i) {
		double p = Double.NaN;
		if (i[5] == null) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() <= 0.02) {
			p = 0;
		} else if (((Double) i[5]).doubleValue() > 0.02) {
			p = WekaClassifier.N3cba8af958(i);
		}
		return p;
	}

	static double N3cba8af958(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = WekaClassifier.N7afa009459(i);
		}
		return p;
	}

	static double N7afa009459(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.37) {
			p = WekaClassifier.N333c339f60(i);
		} else if (((Double) i[6]).doubleValue() > 0.37) {
			p = 0;
		}
		return p;
	}

	static double N333c339f60(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() <= 0.07) {
			p = 0;
		} else if (((Double) i[9]).doubleValue() > 0.07) {
			p = 1;
		}
		return p;
	}

	static double N3561260061(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.04) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() > 0.04) {
			p = 0;
		}
		return p;
	}

	static double N7d05e56062(Object[] i) {
		double p = Double.NaN;
		if (i[1] == null) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() <= 0.22) {
			p = 1;
		} else if (((Double) i[1]).doubleValue() > 0.22) {
			p = WekaClassifier.Ne1641c063(i);
		}
		return p;
	}

	static double Ne1641c063(Object[] i) {
		double p = Double.NaN;
		if (i[9] == null) {
			p = 1;
		} else if (((Double) i[9]).doubleValue() <= 0.04) {
			p = WekaClassifier.N5736ab7964(i);
		} else if (((Double) i[9]).doubleValue() > 0.04) {
			p = WekaClassifier.N4a9a1ac70(i);
		}
		return p;
	}

	static double N5736ab7964(Object[] i) {
		double p = Double.NaN;
		if (i[4] == null) {
			p = 0;
		} else if (((Double) i[4]).doubleValue() <= 0.24) {
			p = WekaClassifier.N4633c1aa65(i);
		} else if (((Double) i[4]).doubleValue() > 0.24) {
			p = 1;
		}
		return p;
	}

	static double N4633c1aa65(Object[] i) {
		double p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (((Double) i[2]).doubleValue() <= 0.05) {
			p = WekaClassifier.N6fefa3e766(i);
		} else if (((Double) i[2]).doubleValue() > 0.05) {
			p = WekaClassifier.N2d8eef2568(i);
		}
		return p;
	}

	static double N6fefa3e766(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.09) {
			p = WekaClassifier.N5df1cc1a67(i);
		} else if (((Double) i[7]).doubleValue() > 0.09) {
			p = 1;
		}
		return p;
	}

	static double N5df1cc1a67(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() <= 0.59) {
			p = 1;
		} else if (((Double) i[0]).doubleValue() > 0.59) {
			p = 0;
		}
		return p;
	}

	static double N2d8eef2568(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 0;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = WekaClassifier.N2f67d8169(i);
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = 0;
		}
		return p;
	}

	static double N2f67d8169(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.09) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.09) {
			p = 1;
		}
		return p;
	}

	static double N4a9a1ac70(Object[] i) {
		double p = Double.NaN;
		if (i[10] == null) {
			p = 1;
		} else if (((Double) i[10]).doubleValue() <= 0.03) {
			p = WekaClassifier.N1cb5259871(i);
		} else if (((Double) i[10]).doubleValue() > 0.03) {
			p = WekaClassifier.N38b72ce172(i);
		}
		return p;
	}

	static double N1cb5259871(Object[] i) {
		double p = Double.NaN;
		if (i[0] == null) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() <= 0.53) {
			p = 0;
		} else if (((Double) i[0]).doubleValue() > 0.53) {
			p = 1;
		}
		return p;
	}

	static double N38b72ce172(Object[] i) {
		double p = Double.NaN;
		if (i[6] == null) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() <= 0.35) {
			p = 1;
		} else if (((Double) i[6]).doubleValue() > 0.35) {
			p = WekaClassifier.N1e384de73(i);
		}
		return p;
	}

	static double N1e384de73(Object[] i) {
		double p = Double.NaN;
		if (i[7] == null) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() <= 0.09) {
			p = 0;
		} else if (((Double) i[7]).doubleValue() > 0.09) {
			p = 1;
		}
		return p;
	}
}