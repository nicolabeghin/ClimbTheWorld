package org.unipd.nbeghin.climbtheworld.ui.card;

import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;

/**
 * CardsUI card for a single tour
 *
 */
public class TourCard extends Card {
	final Tour	tour;

	public TourCard(Tour tour) {
		super(tour.getTitle());
		this.tour = tour;
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_tour_ex, null);
		((TextView) view.findViewById(R.id.title)).setText(tour.getTitle());
		((TextView) view.findViewById(R.id.numBuildingForTour)).setText(Integer.toString(tour.getNum_buildings())+" buildings");
		((TextView) view.findViewById(R.id.description)).setText(tour.getDescription());
		return view;
	}
}
