package ar.com.moobile.fototag;

import roboguice.config.AbstractAndroidModule;
import ar.com.moobile.fototag.utils.Injector;

import com.google.inject.AbstractModule;

/**
 * Class that handles the custom injection of dependencies.
 * 
 * @author gaston.ortiz@gmail.com
 */
public class FotoTagModule extends AbstractAndroidModule {

	/**
	 * @see AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		requestStaticInjection(Injector.class);
	}
}
