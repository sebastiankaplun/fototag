package ar.com.moobile.fototag.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AndroidBitmapProcessingActivity extends Activity {

	ImageView imageView_Source, imageAfter;
	Bitmap bitmap_Source, bitmap_Dest;

	SeekBar brightnessBar;
	TextView brightnessText;
	Button doProcess;

	int brightnessValue = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// imageView_Source = (ImageView)findViewById(R.id.source);
		// imageAfter = (ImageView)findViewById(R.id.imageAfter);
		//
		// bitmap_Source = BitmapFactory.decodeResource(getResources(),
		// R.drawable.testpicture);
		imageAfter.setImageBitmap(processingBitmap_Brightness(bitmap_Source));

		// brightnessBar = (SeekBar)findViewById(R.id.brightnessBar);
		// brightnessText = (TextView)findViewById(R.id.brightnessText);
		// doProcess = (Button)findViewById(R.id.doProcess);

		brightnessBar.setOnSeekBarChangeListener(brightnessBarChangeListener);
		doProcess.setOnClickListener(doProcessClickListener);

	}

	OnClickListener doProcessClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			imageAfter
					.setImageBitmap(processingBitmap_Brightness(bitmap_Source));
		}
	};

	OnSeekBarChangeListener brightnessBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			brightnessValue = progress - 255;
			brightnessText.setText(String.valueOf(brightnessValue));
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

	};

	private Bitmap processingBitmap_Brightness(Bitmap src) {
		Bitmap dest = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				src.getConfig());

		for (int x = 0; x < src.getWidth(); x++) {
			for (int y = 0; y < src.getHeight(); y++) {
				int pixelColor = src.getPixel(x, y);
				int pixelAlpha = Color.alpha(pixelColor);

				int pixelRed = Color.red(pixelColor) + brightnessValue;
				int pixelGreen = Color.green(pixelColor) + brightnessValue;
				int pixelBlue = Color.blue(pixelColor) + brightnessValue;

				if (pixelRed > 255) {
					pixelRed = 255;
				} else if (pixelRed < 0) {
					pixelRed = 0;
				}

				if (pixelGreen > 255) {
					pixelGreen = 255;
				} else if (pixelGreen < 0) {
					pixelGreen = 0;
				}

				if (pixelBlue > 255) {
					pixelBlue = 255;
				} else if (pixelBlue < 0) {
					pixelBlue = 0;
				}

				int newPixel = Color.argb(pixelAlpha, pixelRed, pixelGreen,
						pixelBlue);

				dest.setPixel(x, y, newPixel);

			}
		}
		return dest;
	}

}