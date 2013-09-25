package org.unipd.nbeghin.climbtheworld.ui.card;

import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Building;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
		View view = LayoutInflater.from(context).inflate(R.layout.card_ex, null);
		((TextView) view.findViewById(R.id.title)).setText(building.getName());
		((TextView) view.findViewById(R.id.description)).setText(building.getLocation()+"\n"+building.getUrl());
		
		return view;
	}
}
