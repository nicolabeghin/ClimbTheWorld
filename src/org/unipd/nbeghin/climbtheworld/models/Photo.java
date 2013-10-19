package org.unipd.nbeghin.climbtheworld.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A building's photo
 * 
 */
@DatabaseTable(tableName = "photos")
public class Photo {
	@DatabaseField(generatedId = true)
	private int			_id;
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private Building	building;
	@DatabaseField(canBeNull = false)
	private String		url;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}