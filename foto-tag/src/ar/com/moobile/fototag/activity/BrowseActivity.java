package ar.com.moobile.fototag.activity;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.CheckFolderAction;
import ar.com.moobile.fototag.action.CheckFolderAction.CheckFolderActionListener;
import ar.com.moobile.fototag.action.DisplayPicturesAction;
import ar.com.moobile.fototag.action.ShowFolderAction;
import ar.com.moobile.fototag.activity.adapter.FolderAdapter;
import ar.com.moobile.fototag.domain.Folder;

/**
 * Activity that allows the user to browse looking for a folder.
 * 
 * @author gastonortiz@gmail.com
 */
public class BrowseActivity extends FotoTagListActivity implements
		OnItemClickListener, CheckFolderActionListener {

	public static final String FOLDER_EXTRA = "ar.com.moobile.fototag.activity.BrowseActivity.FOLDER_EXTRA";

	@InjectExtra(FOLDER_EXTRA)
	private Folder folder;

	@InjectView(R.id.select_folder_button)
	private Button selectFolderButton;

	/**
	 * Default constructor.
	 */
	public BrowseActivity() {
		super(R.layout.browse);
	}

	/**
	 * @see Activity#onCreate(Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new CheckFolderAction(this, folder).execute();
	}

	/**
	 * @see OnItemClickListener#onItemClick(AdapterView, View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		new ShowFolderAction(Folder.class.cast(adapterView
				.getItemAtPosition(position))).execute();
	}

	/**
	 * @see CheckFolderActionListener#onFolderChecked(Folder)
	 */
	@Override
	public void onFolderChecked(Folder folder) {
		setListAdapter(new FolderAdapter(this, folder.getSubfolders()));
		getListView().setOnItemClickListener(this);
		selectFolderButton
				.setText(String.format(getString(R.string.label_select_folder),
						folder.countPictures()));
		selectFolderButton.setOnClickListener(new ExecuteOnClickListener(
				new DisplayPicturesAction(folder)));
	}

	/**
	 * @see CheckFolderActionListener#onFolderError()
	 */
	@Override
	public void onFolderError() {
		selectFolderButton.setText(String.format(
				getString(R.string.label_select_folder), 0));
		selectFolderButton.setVisibility(View.INVISIBLE);
	}
}
