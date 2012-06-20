package ar.com.moobile.fototag.action;

import java.util.concurrent.Callable;

import android.content.Context;
import android.content.Intent;
import ar.com.moobile.fototag.activity.HomeActivity;

/**
 * {@link SyncAction} that goes to the home screen.
 * 
 * @author gastonortiz@gmail.com
 */
public class HomeAction extends SyncAction {

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		Context context = contextProvider.get();
		Intent intent = new Intent(context, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
		return null;
	}
}
