package ar.com.moobile.fototag.activity;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.activity.adapter.PicturesAdapter;
import ar.com.moobile.fototag.domain.Folder;
import ar.com.moobile.fototag.domain.Picture;

/**
 * Activity that displays the pictures in a grid.
 * 
 * @author gastonortiz@gmail.com
 */
public class ListedPicturesActivity extends FotoTagActivity {

	public static final String FOLDER_EXTRA = "ar.com.moobile.fototag.activity.ListedPicturesActivity.FOLDER_EXTRA";

	@InjectExtra(FOLDER_EXTRA)
	private Folder folder;

	@InjectView(R.id.pictures_grid)
	private GridView pictures;

	@InjectView(R.id.selected_picture)
	private ImageView selectedPicture;

	/**
	 * Default constructor.
	 */
	public ListedPicturesActivity() {
		super(R.layout.listed_pictures);
	}

	/**
	 * @see Activity#onCreate(Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pictures.setAdapter(new PicturesAdapter(this, folder));
		pictures.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Picture picture = Picture.class.cast(view.getTag());
				selectedPicture.setImageBitmap(picture.createPreview());
			}
		});
	}
}
