package com.example;

/**
 * A class that handles the attributes of a banana.
 * 
 * @author Carter
 */
public class Banana {

	private final double a, b;
	private final double sizeCubicInch;
	private final double length;

	/**
	 * Creates a banana object.
	 * Takes the length of the banana in pixels.
	 * 
	 * @param lengthInPixels The length of the banana in pixels.
	 */
	public Banana(double lengthInPixels) {
		this.a = 0;
		this.b = lengthInPixels * BananaConstants.PIXEL_TO_INCHES;
		this.length = lengthOfBanana();
		this.sizeCubicInch = volume();
	}

	/**
	 * Gets the volume of an object assuming its a cylander.
	 * 
	 * @return The calculated volume of the banana in cubic inches.
	 */
	public final double volume() {
		double volume, t1, t2;
		t1 = this.b * Math.PI;
		t2 = this.a * Math.PI;
		volume = t1 - t2;
		volume *= 0.5;
		return volume;
	}

	/**
	 * @return The weight of the banana in ounces.
	 */
	public double toOunce() {
		return this.sizeCubicInch * BananaConstants.CUBIC_INCH_TO_OUNCE;
	}

	/**
	 * @return The microsiverts of the banana per cubic inch.
	 */
	public double microsivertsPerInch() {
		return this.sizeCubicInch * BananaConstants.MICROSIVERTS_PER_INCH;
	}

	/**
	 * @return The length of the banana in inches.
	 */
	public final double lengthOfBanana() {
		return Math.abs(this.a - this.b);
	}

	/**
	 * @return The amount of bananas needed to match the lengtnh of a football
	 *         field.
	 */
	public double bananaToFootballField() {
		return BananaConstants.INCH_TO_FOOTBALL_FIELD / this.length;
	}

	/**
	 * @return The amount of bananas needed to fill an oil barrel.
	 */
	public double bananaToOilbarrel() {
		return BananaConstants.CUBIC_INCH_TO_OILBARREL / this.sizeCubicInch;
	}

	/**
	 * Prints out to the console the attributes of the banana.
	 */
	public void printBananaAttributes() {
		System.out.printf(
				"Banana Length: %fin.\nWeight in ounces: %foz\nBanana Volume: %fin^3\nMicrosieverts Per Inch^3: %fSv\nBananas Across Football Field: %f bananas\nBananas could fit in an oil barrel: %f bananas",
				lengthOfBanana(),
				toOunce(),
				this.sizeCubicInch,
				microsivertsPerInch(),
				bananaToFootballField(),
				bananaToOilbarrel());
	}
}
