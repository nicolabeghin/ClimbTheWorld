package org.unipd.nbeghin.climbtheworld.adapters;

import java.util.List;

import org.unipd.nbeghin.climbtheworld.MainActivity;
import org.unipd.nbeghin.climbtheworld.R;
import org.unipd.nbeghin.climbtheworld.models.Photo;
import org.unipd.nbeghin.climbtheworld.util.ScaleImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class StaggeredPhotoAdapter extends ArrayAdapter<Photo> {
	private final List<Photo> photos;

	public StaggeredPhotoAdapter(Context context, int textViewResourceId, List<Photo> objects) {
		super(context, textViewResourceId, objects);
		this.photos=objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		try {
			if (photos.get(position)==null) throw new NullPointerException();
			if (convertView == null) {
				LayoutInflater layoutInflator = LayoutInflater.from(getContext());
				convertView = layoutInflator.inflate(R.layout.row_staggered_demo, null);
				holder = new ViewHolder(); // view-holder pattern
				holder.imageView = (ScaleImageView) convertView.findViewById(R.id.imgRocket);
				holder.url=photos.get(position).getUrl();
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			ImageLoader.getInstance().displayImage(holder.url, holder.imageView);
		} catch(NullPointerException e) {
			Log.w(MainActivity.AppName, "No photo at position @"+position);
		} catch(Exception e) {
			Log.e(MainActivity.AppName, "GalleryActivity: unable to show image");
		}
		return convertView;
	}

	static class ViewHolder {
		ScaleImageView	imageView;
		String url;
	}
}
