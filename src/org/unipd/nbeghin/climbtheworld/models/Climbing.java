package org.unipd.nbeghin.climbtheworld.models;

import java.util.concurrent.TimeUnit;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "climbings")
public class Climbing {
	@DatabaseField(generatedId = true)
	private int			_id;
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private Building	building;
	@DatabaseField
	private int			completed_steps;
	@DatabaseField
	private int			remaining_steps;
	@DatabaseField
	private double		percentage;
	@DatabaseField
	private long		created;
	@DatabaseField
	private long		modified;
	@DatabaseField
	private long		completed;

	public Climbing() {} // needed by ormlite

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

	public int getCompleted_steps() {
		return completed_steps;
	}

	public void setCompleted_steps(int completed_steps) {
		this.completed_steps = completed_steps;
	}

	public int getRemaining_steps() {
		return remaining_steps;
	}

	public boolean isCompleted() {
		return this.completed > 0;
	}

	public String getFBStatusMessage() {
		return "I just climbed " + building.getName() + " (" + building.getHeight() + "mt, " + building.getSteps() + " steps) in " + totalTime();
	}

	public String totalTime() {
		long diff = 0;
		if (isCompleted() == false) diff = this.modified - this.created; // not completed
		else
			diff = this.completed - this.created; // completed
		return String.format("%dm%ds", TimeUnit.MILLISECONDS.toMinutes(diff), TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));
	}

	public void setRemaining_steps(int remaining_steps) {
		this.remaining_steps = remaining_steps;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage2) {
		this.percentage = percentage2;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getModified() {
		return modified;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public long getCompleted() {
		return completed;
	}

	public void setCompleted(long completed) {
		this.completed = completed;
	}
}
