package org.unipd.nbeghin.climbtheworld.models;

/**
 * A model representing a single sampling (X,Y,Z)
 * It automatically calculates |V|
 *
 */
public class Sample {
	private long	time;
	private double	valueX;
	private double	valueY;
	private double	valueZ;
	private double	valueV;
	private double 	meanXAndY;

	public Sample(long time, double valueX, double valueY, double valueZ) {
		this.time = time;
		this.valueX = valueX;
		this.valueY = valueY;
		this.valueZ = valueZ;
		this.valueV = Math.sqrt(Math.pow(valueX, 2) + Math.pow(valueY, 2) + Math.pow(valueZ, 2));
		this.meanXAndY = (this.valueX + this.valueY) / 2.0;
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
	
	public double getMeanXAndY() {
		return meanXAndY;
	}
	
	public void setValueX(double x) {
		this.valueX = x;
	}
	
	public void setValueY(double y) {
		this.valueY = y;
	}
	
	public void setValueZ(double z) {
		this.valueZ = z;
	}
	
	public void updateVAndXPlusY() {
		this.valueV = Math.sqrt(Math.pow(this.valueX, 2) + Math.pow(this.valueY, 2) + Math.pow(this.valueZ, 2));
		this.meanXAndY = (this.valueX + this.valueY) / 2.0;
	}

	@Override
	public String toString() {
		return "[" + time + "," + valueX + "," + valueY + "," + valueZ + "]\t(|V|=" + valueV + ")";
	}
}
