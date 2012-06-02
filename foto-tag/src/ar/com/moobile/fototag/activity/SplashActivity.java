package ar.com.moobile.fototag.activity;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.Action;

/**
 * Splash activity that will be shown whenever the app is loading.
 * 
 * @author gastonortiz@gmail.com
 */
public class SplashActivity extends FotoTagActivity {

	public static final String ACTION_EXTRA = "ar.com.moobile.fototag.activity.SplashActivity.ACTION_EXTRA";
	public static final String MESSAGE_EXTRA = "ar.com.moobile.fototag.activity.SplashActivity.MESSAGE_EXTRA";

	@InjectExtra(ACTION_EXTRA)
	private Action action;

	@InjectExtra(value = MESSAGE_EXTRA, optional = true)
	private String message;

	@InjectView(R.id.message_text_view)
	private TextView messageTextView;

	/**
	 * Default constructor.
	 */
	public SplashActivity() {
		super(R.layout.splash);
	}

	/**
	 * @see Activity#onCreate(Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		action.execute();
		if(message != null) {
			messageTextView.setText(message);
		}
	}
}