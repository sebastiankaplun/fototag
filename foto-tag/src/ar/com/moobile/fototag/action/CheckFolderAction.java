package ar.com.moobile.fototag.action;

import java.util.concurrent.Callable;

import ar.com.moobile.fototag.domain.Folder;

/**
 * @author gastonortiz@gmail.com
 */
public class CheckFolderAction extends SyncAction {
	
	public static interface CheckFolderActionListener {
		public void onFolderChecked(Folder folder);
		public void onFolderError();
	}
	private Folder folder;
	private CheckFolderActionListener listener;

	public CheckFolderAction(CheckFolderActionListener listener, Folder folder) {
		this.listener = listener;
		this.folder = folder;
	}

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		try {
			folder.getSubfolders();
			listener.onFolderChecked(folder);
		} catch(NullPointerException e) {
			listener.onFolderError();
		}
		return null;
	}
}
