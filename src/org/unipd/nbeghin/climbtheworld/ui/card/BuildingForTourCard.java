package org.unipd.nbeghin.climbtheworld.ui.card;

import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Building;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;

public class BuildingForTourCard extends Card {
	final private Building	building;
	final private int		order;

	public BuildingForTourCard(Building building, int order) {
		super(building.getName());
		this.building = building;
		this.order = order;
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_building_for_tour_ex, null);
		((TextView) view.findViewById(R.id.title)).setText(building.getName());
		int imageId = context.getResources().getIdentifier(building.getPhoto(), "drawable", context.getPackageName());
		if (imageId > 0) ((ImageView) view.findViewById(R.id.photo)).setImageResource(imageId);
		((TextView) view.findViewById(R.id.buildingStat)).setText(building.getSteps() + " steps (" + building.getHeight() + "m)");
		((TextView) view.findViewById(R.id.location)).setText(building.getLocation());
		((TextView) view.findViewById(R.id.description)).setText(building.getDescription());
		((TextView) view.findViewById(R.id.tourOrder)).setText(Integer.toString(order));
		return view;
	}
}
