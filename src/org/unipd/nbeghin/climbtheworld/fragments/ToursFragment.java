package org.unipd.nbeghin.climbtheworld.fragments;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Tour;
import org.unipd.nbeghin.climbtheworld.ui.card.TourCard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fima.cardsui.views.CardUI;

public class ToursFragment extends Fragment {
	public static final String	building_intent_object	= "org.unipd.nbeghin.climbtheworld.intents.object.building";
	public CardUI											toursCards;

	private class LoadToursTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (final Tour tour : MainActivity.tours) {
				TourCard tourCard = new TourCard(tour);
//				buildingCard.setOnClickListener(new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						Intent intent = new Intent(getActivity().getApplicationContext(), ClimbActivity.class);
//						intent.putExtra(building_intent_object, building.get_id());
//						startActivity(intent);
//					}
//				});
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
