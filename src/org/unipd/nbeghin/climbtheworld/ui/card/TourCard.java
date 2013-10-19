package org.unipd.nbeghin.climbtheworld.ui.card;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Tour;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
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
		LinearLayout layout=(LinearLayout) view.findViewById(R.id.buildingsForTourPhotoList);
		List<Integer> images=MainActivity.getBuildingPhotosForTour(tour.get_id());
		Log.i(MainActivity.AppName, images.size()+" immagini per tour #"+tour.get_id());
//        android:id="@+id/ImageView01"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:adjustViewBounds="true"
//        android:scaleType="fitStart"
		for(int image: images) {
			ImageView imageView=new ImageView(context);
			imageView.setImageResource(image);
			imageView.setAdjustViewBounds(true);
			imageView.setPadding(0, 0, 7, 0);
			imageView.setScaleType(ScaleType.FIT_START);
			layout.addView(imageView);
		}
		layout.refreshDrawableState();
		return view;
	}
}
