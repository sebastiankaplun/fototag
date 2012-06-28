package ar.com.moobile.fototag.activity;

import java.util.List;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.CreatePreviewAction;
import ar.com.moobile.fototag.action.CreatePreviewAction.CreatePreviewListener;
import ar.com.moobile.fototag.action.PrintAction;
import ar.com.moobile.fototag.activity.adapter.PictureAdapter;
import ar.com.moobile.fototag.domain.Folder;
import ar.com.moobile.fototag.domain.Picture;

import com.google.inject.internal.Lists;

/**
 * Activity that displays the pictures in a grid.
 * 
 * @author gastonortiz@gmail.com
 */
public class ListedPicturesActivity extends FotoTagActivity implements
		OnSeekBarChangeListener, OnClickListener, CreatePreviewListener {

	public static final String FOLDER_EXTRA = "ar.com.moobile.fototag.activity.ListedPicturesActivity.FOLDER_EXTRA";

	@InjectExtra(FOLDER_EXTRA)
	private Folder folder;

	@InjectView(R.id.pictures_grid)
	private GridView pictures;

	@InjectView(R.id.selected_picture)
	private ImageView selectedPictureView;

	@InjectView(R.id.picture_highlighter)
	private SeekBar pictureHighlighter;

	@InjectView(R.id.printer_button)
	private ImageView printerButton;

	@InjectView(R.id.print_counter)
	private TextView printCounter;

	private int counter;

	private Picture selectedPicture;

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
		pictureHighlighter.setMax(Picture.MAX_BRIGHTNESS);
		pictureHighlighter.setProgress(Picture.AVERAGE_BRIGHTNESS);
		pictures.setAdapter(new PictureAdapter(this, folder.getPictures(), this));
		pictureHighlighter.setOnSeekBarChangeListener(this);
		printerButton.setOnClickListener(this);
		printCounter.setText(String.format(
				getString(R.string.label_print_counter), counter));
	}

	/**
	 * @see OnSeekBarChangeListener#onProgressChanged(SeekBar, int, boolean)
	 */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		selectedPicture.setBrightnessFactor(progress);
	}

	/**
	 * @see OnSeekBarChangeListener#onStartTrackingTouch(SeekBar)
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// Do nothing.
	}

	/**
	 * @see OnSeekBarChangeListener#onStopTrackingTouch(SeekBar)
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		new CreatePreviewAction(selectedPicture, this).execute();
	}

	/**
	 * @see OnClickListener#onClick(View)
	 */
	@Override
	public void onClick(View view) {
		if (printerButton.equals(view)) {
			for (int i = 0; i < pictures.getCount(); i++) {
				List<Picture> picturesToPrint = Lists.newArrayList();
				Picture picture = Picture.class.cast(pictures
						.getItemAtPosition(i));
				if (picture.isPrintable()) {
					picturesToPrint.add(picture);
				}
				new PrintAction(picturesToPrint).execute();
			}
		} else {
			if (view instanceof CheckBox) {
				boolean isChecked = CheckBox.class.cast(view).isChecked();
				Picture.class.cast(view.getTag()).setPrintable(isChecked);
				if (isChecked) {
					counter++;
				} else {
					counter--;
				}
				printCounter.setText(String.format(
						getString(R.string.label_print_counter), counter));

			} else {
				selectedPicture = Picture.class.cast(view.getTag());
				new CreatePreviewAction(selectedPicture, this).execute();
			}
		}
	}

	/**
	 * @see CreatePreviewAction.CreatePreviewListener#onPreviewCreationStart()
	 */
	@Override
	public void onPreviewCreationStart() {
		selectedPictureView.setImageResource(R.drawable.spinner);
	}

	/**
	 * @see CreatePreviewAction.CreatePreviewListener#onPreviewCreated(Bitmap)
	 */
	@Override
	public void onPreviewCreated(Bitmap preview) {
		selectedPictureView.setImageBitmap(preview);
		pictureHighlighter.setProgress(selectedPicture.getBrightnessFactor());
	}
}
