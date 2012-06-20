package ar.com.moobile.fototag.domain;

import ar.com.moobile.fototag.R;

/**
 * Enum that marks the resolution of a {@link Picture}.
 * 
 * @author gastonortiz@gmail.com
 */
public enum Resolution {
	HIGH(R.drawable.hi) {
		@Override
		protected Boolean isInRange(long pixelsAmount) {
			return UPPER_RANGE < pixelsAmount;
		}
	},
	MEDIUM(android.R.color.transparent) {
		@Override
		protected Boolean isInRange(long pixelsAmount) {
			return (LOWER_RANGE <= pixelsAmount)
					&& (UPPER_RANGE >= pixelsAmount);
		}
	},
	LOW(R.drawable.hi) {
		@Override
		protected Boolean isInRange(long pixelsAmount) {
			return LOWER_RANGE > pixelsAmount;
		}
	};
	private static final long LOWER_RANGE = 3000000;
	private static final long UPPER_RANGE = 300000;
	private int icon;

	/**
	 * Default constructor. Sets the icon of the resolution.
	 * 
	 * @param icon
	 *            The resource of the icon.
	 */
	private Resolution(int icon) {
		this.icon = icon;
	}

	/**
	 * Checks if the amount of pixels of an image is within the resolution of
	 * the image.
	 * 
	 * @param pixelsAmount
	 *            The amount of pixels.
	 * @return {@link Boolean} <code>true</code> if the pixels are in range.
	 */
	protected abstract Boolean isInRange(long pixelsAmount);

	public int getIcon() {
		return this.icon;
	}
}
