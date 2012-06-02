package ar.com.moobile.fototag.activity;

import roboguice.inject.InjectExtra;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.ShowFolderAction;
import ar.com.moobile.fototag.activity.adapter.FoldersAdapter;
import ar.com.moobile.fototag.domain.Folder;

/**
 * Activity that allows the user to browse looking for a folder.
 * 
 * @author gastonortiz@gmail.com
 */
public class BrowseActivity extends FotoTagListActivity implements
		OnItemClickListener, OnClickListener {

	public static final String FOLDER_EXTRA = "ar.com.moobile.fototag.activity.BrowseActivity.FOLDER_EXTRA";

	@InjectExtra(FOLDER_EXTRA)
	private Folder folder;

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
		setListAdapter(new FoldersAdapter(this, folder.getSubfolders()));
		ListView listView = getListView();
		listView.setOnItemClickListener(this);
		listView.getEmptyView().setOnClickListener(this);
	}

	/**
	 * @see OnItemClickListener#onItemClick(AdapterView, View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		new ShowFolderAction(Folder.class.cast(adapterView
				.getItemAtPosition(position))).execute();
	}

	/**
	 * @see OnClickListener#onClick(View)
	 */
	@Override
	public void onClick(View v) {
		
	}
}
