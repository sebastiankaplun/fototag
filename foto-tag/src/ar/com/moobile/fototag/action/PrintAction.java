/**
 * 
 */
package ar.com.moobile.fototag.action;

import java.util.List;
import java.util.concurrent.Callable;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import ar.com.moobile.fototag.activity.PrintDialogActivity;
import ar.com.moobile.fototag.domain.Picture;

/**
 * {@link AsyncAction} that prints the selected pictures.
 * 
 * @author gastonortiz@gmail.com
 */
public class PrintAction extends AsyncAction<Void> {

	private List<Picture> pictures;

	/**
	 * Default constructor. Sets the {@link Picture}s to print.
	 * 
	 * @param pictures
	 *            The {@link Picture}s to print.
	 */
	public PrintAction(List<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		for (Picture picture : pictures) {
			Log.d("TEST", "PRINTING PICTURE WITH ID: "
					+ picture.getUri().toString());
//			try {
//				// create builder
//				String method = "Builder";
//				Builder builder = new Builder("", 0);
//
//				// add command
//				method = "addImage";
//				Bitmap bitmap = picture.getBitmap();
//				builder.addImage(bitmap, 0, 0, bitmap.getWidth(),
//						bitmap.getHeight(), Builder.COLOR_1);
//
//				// send builder data
//				int[] status = new int[1];
////				Print printer = EPOSPrintSampleActivity.getPrinter();
////				printer.sendData(builder, SEND_TIMEOUT, status);
//				Toast.makeText(contextProvider.get(), "", Toast.LENGTH_SHORT)
//						.show();
//			} catch (Exception e) {
//				
//			}
			Context context = contextProvider.get();
			Intent printIntent = new Intent(context, PrintDialogActivity.class);
			printIntent.setDataAndType(picture.getUri(), "image/png");
			printIntent.putExtra("title", "Picture");
			context.startActivity(printIntent);
		}
		return null;
	}
}
