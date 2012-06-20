package ar.com.moobile.fototag.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import ar.com.moobile.fototag.action.ShowFolderAction;
import ar.com.moobile.fototag.domain.Folder;

/**
 * {@link BroadcastReceiver} that handles the receipt of bluetooth files.
 * 
 * @author gastonortiz@gmail.com
 */
public class FileReceivedBroadcastReceiver extends BroadcastReceiver {

	/**
	 * @see BroadcastReceiver#onReceive(Context, Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Folder folder = new Folder(Folder.BLUETOOTH_PATH);
		if (folder.countPictures() == 0) {
			new ShowFolderAction(Folder.BLUETOOTH_PATH).execute();
		} else {
			Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
