package ar.com.moobile.fototag;

import java.util.List;

import roboguice.application.RoboApplication;

import com.google.inject.Module;

/**
 * Application class.
 * 
 * @author gastonortiz@gmail.com
 */
public class FotoTagApplication extends RoboApplication {

	/**
	 * @see RoboApplication#addApplicationModules(List)
	 */
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new FotoTagModule());
	}
}
