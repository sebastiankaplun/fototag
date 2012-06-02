package ar.com.moobile.fototag.activity;

import roboguice.activity.RoboActivity;
import roboguice.activity.RoboListActivity;
import android.app.ListActivity;
import android.os.Bundle;

/**
 * Default {@link ListActivity} defined by the framework to implement it's features.
 * 
 * @author gastonortiz@gmail.com
 */
public class FotoTagListActivity extends RoboListActivity {

	private Integer layoutId;

	/**
	 * Default constructor. Sets the layout ID of the activity.
	 * 
	 * @param layoutId
	 *            The Resource of the ID of the activity.
	 */
	public FotoTagListActivity(Integer layoutId) {
		this.layoutId = layoutId;
	}

	/**
	 * @see RoboActivity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutId);
	}
}