package org.unipd.nbeghin.climbtheworld.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tours")
public class Tour {
	@DatabaseField(generatedId = true)
	private int		_id;
	@DatabaseField
	private String	title;
	@DatabaseField
	private String	description;
	@DatabaseField
	private int	num_buildings;
	Tour() {} // needed by ormlite
}
