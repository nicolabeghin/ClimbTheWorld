package org.unipd.nbeghin.climbtheworld.adapters;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Stat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StatAdapter extends ArrayAdapter<Stat> {
	private List<Stat>	stats;
	private Context		context;
	int					layoutResourceId;

	public StatAdapter(Context context, int layoutResourceId, List<Stat> objects) {
		super(context, layoutResourceId, objects);
		this.stats = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.stat_item, parent, false);
		((TextView) rowView.findViewById(R.id.testo1)).setText("test");
		return rowView;
	}
}
