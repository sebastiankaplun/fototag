package ar.com.moobile.fototag.activity;

import roboguice.activity.RoboActivity;
import android.app.Activity;
import android.os.Bundle;

/**
 * Default {@link Activity} defined by the framework to implement it's features.
 * 
 * @author gastonortiz@gmail.com
 */
public class FotoTagActivity extends RoboActivity {

	private Integer layoutId;

	/**
	 * Default constructor. Sets the layout ID of the activity.
	 * 
	 * @param layoutId
	 *            The Resource of the ID of the activity.
	 */
	public FotoTagActivity(Integer layoutId) {
		this.layoutId = layoutId;
	}

	/**
	 * @see RoboActivity#onCreate(Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutId);
	}
}