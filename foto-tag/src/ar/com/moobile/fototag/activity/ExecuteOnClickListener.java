package ar.com.moobile.fototag.activity;

import android.view.View;
import android.view.View.OnClickListener;
import ar.com.moobile.fototag.action.Action;

/**
 * {@link OnClickListener} that executes an action.
 * 
 * @author gastonortiz@gmail.com
 */
public class ExecuteOnClickListener implements OnClickListener {
	private Action action;

	/**
	 * Default constructor.<br>
	 * Sets the action to be executed.
	 * 
	 * @param action
	 *            The action to be executed.
	 */
	public ExecuteOnClickListener(Action action) {
		this.action = action;
	}

	/**
	 * @see OnClickListener#onClick(View)
	 */
	@Override
	public void onClick(View v) {
		action.execute();
	}
}
