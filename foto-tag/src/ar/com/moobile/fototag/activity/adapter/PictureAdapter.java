package ar.com.moobile.fototag.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.CreateThumbnailAction;
import ar.com.moobile.fototag.domain.Folder;
import ar.com.moobile.fototag.domain.Picture;
import ar.com.moobile.fototag.domain.Thumbnail;

/**
 * Adapter that handles the {@link Thumbnail}s.
 * 
 * @author gastonortiz@gmail.com
 */
public class PictureAdapter extends BaseAdapter {
	private List<Picture> pictures;
	private LayoutInflater inflater;
	private OnClickListener listener;

	/**
	 * Default constructor. Sets the images to be displayed.
	 * 
	 * @param context
	 *            The {@link Context} that contains the adapter.
	 * @param pictures
	 *            The {@link Folder} containing the pictures to be displayed.
	 */
	public PictureAdapter(Context context, List<Picture> pictures,
			OnClickListener listener) {
		this.pictures = pictures;
		inflater = LayoutInflater.from(context);
		this.listener = listener;
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
		RelativeLayout view;
		Picture picture = getItem(position);
		if (convertView == null) {
			view = RelativeLayout.class.cast(inflater.inflate(R.layout.picture,
					null));
			view.setOnClickListener(listener);
			CheckBox checkBox = CheckBox.class.cast(view
					.findViewById(R.id.print_mark));
			checkBox.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Picture.class.cast(v.getTag()).setPrintable(
							CheckBox.class.cast(v).isChecked());
				}
			});
		} else {
			view = RelativeLayout.class.cast(convertView);
		}
		view.setTag(picture);
		ImageView thumbnail = ImageView.class.cast(view
				.findViewById(R.id.thumbnail));
		ImageView resolutionIcon = ImageView.class.cast(view
				.findViewById(R.id.resolution_icon));
		CheckBox checkBox = CheckBox.class.cast(view
				.findViewById(R.id.print_mark));
		checkBox.setChecked(picture.isPrintable());
		checkBox.setTag(picture);
		resolutionIcon.setImageResource(picture.getResolution().getIcon());
		new CreateThumbnailAction(thumbnail, picture).execute();
		return view;
	}
}
