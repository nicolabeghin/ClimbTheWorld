package org.unipd.nbeghin.climbtheworld.models;

/**
 * A model representing a single sampling (X,Y,Z)
 * It automatically calculates |V|
 *
 */
public class Sample {
	final private long		time;
	final private double	valueX;
	final private double	valueY;
	final private double	valueZ;
	final private double	valueV;

	public Sample(long time, double valueX, double valueY, double valueZ) {
		this.time = time;
		this.valueX = valueX;
		this.valueY = valueY;
		this.valueZ = valueZ;
		this.valueV = Math.sqrt(Math.pow(valueX, 2) + Math.pow(valueY, 2) + Math.pow(valueZ, 2));
	}

	public long getTime() {
		return time;
	}

	public double getValueX() {
		return valueX;
	}

	public double getValueY() {
		return valueY;
	}

	public double getValueZ() {
		return valueZ;
	}

	public double getValueV() {
		return valueV;
	}

	@Override
	public String toString() {
		return "[" + time + "," + valueX + "," + valueY + "," + valueZ + "]\t(|V|=" + valueV + ")";
	}
}
