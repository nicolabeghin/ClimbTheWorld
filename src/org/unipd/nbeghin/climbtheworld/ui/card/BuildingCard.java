package org.unipd.nbeghin.climbtheworld.ui.card;

import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Building;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;

public class BuildingCard extends Card {
	final Building	building;

	public BuildingCard(Building building) {
		super(building.getName());
		this.building = building;
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_building_ex, null);
		((TextView) view.findViewById(R.id.title)).setText(building.getName());
		int imageId=context.getResources().getIdentifier(building.getPhoto(), "drawable", context.getPackageName());
		if (imageId>0) ((ImageView) view.findViewById(R.id.photo)).setImageResource(imageId);
		((TextView) view.findViewById(R.id.buildingStat)).setText(building.getSteps()+" steps ("+building.getHeight()+"m)");
		((TextView) view.findViewById(R.id.location)).setText(building.getLocation());
		((TextView) view.findViewById(R.id.description)).setText(building.getDescription());
		return view;
	}
}
