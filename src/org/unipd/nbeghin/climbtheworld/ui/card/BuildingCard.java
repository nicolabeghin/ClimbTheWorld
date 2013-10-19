package org.unipd.nbeghin.climbtheworld.ui.card;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Building;
import org.unipd.nbeghin.climbtheworld.models.Climbing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;

/**
 * CardsUI card for a single building
 *
 */
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
		int imageId = MainActivity.getBuildingImageResource(building);
		if (imageId > 0) ((ImageView) view.findViewById(R.id.photo)).setImageResource(imageId);
		((TextView) view.findViewById(R.id.buildingStat)).setText(building.getSteps() + " steps (" + building.getHeight() + "m)");
		((TextView) view.findViewById(R.id.location)).setText(building.getLocation());
		((TextView) view.findViewById(R.id.description)).setText(building.getDescription());
		TextView climbingStatus = (TextView) view.findViewById(R.id.climbingStatus);
		Climbing climbing = MainActivity.getClimbingForBuilding(building.get_id());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		if (climbing != null) {
			if (climbing.getPercentage() >= 100) {
				climbingStatus.setText("Climbing: COMPLETED! (on "+sdf.format(new Date(climbing.getModified()))+")");
			} else {
				climbingStatus.setText("Climbing status: " + new DecimalFormat("#.##").format(climbing.getPercentage()*100) + "% (last attempt @ "+sdf.format(new Date(climbing.getModified()))+")");
			}
		} else {
			climbingStatus.setText("Not climbed yet");
		}
		return view;
	}
}
