package ar.com.moobile.fototag.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import ar.com.moobile.fototag.domain.Folder;
import ar.com.moobile.fototag.domain.Picture;

/**
 * Adapter that handles the {@link Picture}s.
 * 
 * @author gastonortiz@gmail.com
 */
public class PicturesAdapter extends BaseAdapter {
	private List<Picture> pictures;
	private Context context;

	/**
	 * Default constructor. Sets the images to be displayed.
	 * 
	 * @param context
	 *            The {@link Context} that contains the adapter.
	 * @param folder
	 *            The {@link Folder} containing the pictures to be displayed.
	 */
	public PicturesAdapter(Context context, Folder folder) {
		this.context = context;
		this.pictures = folder.getPictures();
	}

	/**
	 * @see Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return pictures.size();
	}

	/**
	 * @see Adapter#getItem(int)
	 */
	@Override
	public Picture getItem(int position) {
		return pictures.get(position);
	}

	/**
	 * @see Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * @see Adapter#getView(int, View, ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		Picture picture = getItem(position);
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
			imageView.setBackgroundColor(android.R.color.white);
			imageView.setDrawingCacheEnabled(false);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setTag(getItem(position));
		imageView.setImageBitmap(picture.createThumbnail());
		return imageView;
	}

}
