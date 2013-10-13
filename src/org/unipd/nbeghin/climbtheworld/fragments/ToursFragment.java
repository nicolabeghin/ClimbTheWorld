package org.unipd.nbeghin.climbtheworld.fragments;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.TourDetailActivity;
import org.unipd.nbeghin.climbtheworld.models.Tour;
import org.unipd.nbeghin.climbtheworld.ui.card.TourCard;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.fima.cardsui.views.CardUI;

public class ToursFragment extends Fragment {
	public static final String	tour_intent_object	= "org.unipd.nbeghin.climbtheworld.intents.object.tour";
	public CardUI											toursCards;

	private class LoadToursTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (final Tour tour : MainActivity.tours) {
				TourCard tourCard = new TourCard(tour);
				tourCard.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getActivity().getApplicationContext(), TourDetailActivity.class);
						intent.putExtra(tour_intent_object, tour.get_id());
						startActivity(intent);
					}
				});
				toursCards.addCard(tourCard);
			}
			toursCards.refresh();
			return null;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.fragment_tours, container, false);
		toursCards = (CardUI) result.findViewById(R.id.cardsTours);
		toursCards.setSwipeable(false);
		new LoadToursTask().execute();
		return (result);
	}
}
