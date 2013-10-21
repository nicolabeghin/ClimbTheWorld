package org.unipd.nbeghin.climbtheworld.models;

import org.unipd.nbeghin.climbtheworld.R;

/**
 * Model representing a single statistic
 * 
 */
public class Stat {
	private String	name;
	private Object	value;
	private int		iconId = R.drawable.device_access_storage;

	public Stat(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Stat(String name, Object value, int iconId) {
		super();
		this.name = name;
		this.value = value;
		this.iconId = iconId;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public String toString() {
		if (this.value instanceof String) return ((String) this.value).toString();
		if (this.value instanceof Double) return ((Double) this.value).toString();
		return "No value set";
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
