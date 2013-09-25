package org.unipd.nbeghin.climbtheworld.ui.card;

import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;

public class TourCard extends Card {
	final Tour	tour;

	public TourCard(Tour tour) {
		super(tour.getTitle());
		this.tour = tour;
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_building_ex, null);
		((TextView) view.findViewById(R.id.title)).setText(tour.getTitle());
		((TextView) view.findViewById(R.id.num_buildings)).setText(tour.getNum_buildings());
		((TextView) view.findViewById(R.id.description)).setText(tour.getDescription());
		
		return view;
	}
}
