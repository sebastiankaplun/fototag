package ar.com.moobile.fototag.activity.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.DisplayPicturesAction;
import ar.com.moobile.fototag.activity.ExecuteOnClickListener;
import ar.com.moobile.fototag.domain.Folder;

/**
 * Default adapter to display {@link Folder}s.
 * 
 * @author gastonortiz@gmail.com
 */
public class FoldersAdapter extends ArrayAdapter<Folder> {

	/**
	 * Default constructor. Sets the context and folders used by this adapter.
	 * 
	 * @param context
	 *            The {@link Context}.
	 * @param folders
	 *            The {@link Folder}s.
	 */
	public FoldersAdapter(Context context, List<Folder> folders) {
		super(context, R.layout.folder, R.id.folder_name, folders);
	}

	/**
	 * @see ArrayAdapter#getView(int, View, ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);
		Folder folder = getItem(position);
		TextView.class.cast(v.findViewById(R.id.folder_name)).setText(
				folder.getName());
		v.findViewById(R.id.folder_selection_button).setOnClickListener(
				new ExecuteOnClickListener(new DisplayPicturesAction(folder)));

		return v;
	}

}
