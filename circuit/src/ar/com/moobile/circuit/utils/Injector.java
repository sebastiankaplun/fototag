package ar.com.moobile.circuit.utils;

import roboguice.inject.InjectorProvider;
import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Singleton class that injects the members of a class.
 * 
 * @author gastonortiz@gmail.com
 */
public final class Injector {

	@Inject
	private static Provider<Context> contextProvider;
	private static Injector instance;

	/**
	 * Singleton constructor.
	 */
	private Injector() {
		// Do nothing.
	}

	/**
	 * Gets the only instance of this class.
	 * 
	 * @return {@link Injector} The only instance of this class.
	 */
	public static Injector getInstance() {
		if (instance == null) {
			instance = new Injector();
		}
		return instance;
	}

	/**
	 * Injects the members of one object.
	 * 
	 * @param object
	 *            The object that owns the members to inject.
	 */
	public void injectMembers(Object object) {
		((InjectorProvider) contextProvider.get()).getInjector().injectMembers(
				object);
	}
}
