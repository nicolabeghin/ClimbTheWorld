package org.unipd.nbeghin.climbtheworld.adapters;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Stat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StatAdapter extends ArrayAdapter<Stat> {
	private final List<Stat>	stats;

	public StatAdapter(Context context, int layoutResourceId, List<Stat> objects) {
		super(context, layoutResourceId, objects);
		this.stats = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflator = LayoutInflater.from(getContext());
		convertView = layoutInflator.inflate(R.layout.stat_item, null);
		Stat stat=stats.get(position);
		if (stat!=null) {
			((ImageView) convertView.findViewById(R.id.statIcon)).setImageResource(stat.getIconId());
			((TextView) convertView.findViewById(R.id.statName)).setText(stat.getName());
			((TextView) convertView.findViewById(R.id.statValue)).setText(stat.toString());
		}
		return convertView;
	}
	
}
