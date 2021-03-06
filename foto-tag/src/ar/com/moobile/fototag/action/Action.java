package ar.com.moobile.fototag.action;

import roboguice.util.RoboAsyncTask;

/**
 * Interface representing actions to be taken by the user.
 * 
 * @author gastonortiz@gmail.com
 */
public interface Action {
	
	/**
	 * @see RoboAsyncTask#execute()
	 */
	public void execute(); 
}
