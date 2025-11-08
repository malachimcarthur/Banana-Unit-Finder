package com.example;

public class Banana{
    
	private double a,b;
	private double sizeCubicInch;
	private double length;
	

	/**
	 * Creates a banana object.
	 * Takes the length of the banana in pixels. 
	 * @param b
	 */
    public Banana(double lengthInPixels){
    	this.a = 0;
    	this.b = lengthInPixels * BananaConstants.PIXEL_TO_INCHES;
    	this.length = lengthOfBanana();
    	sizeCubicInch = volume();
    }
    /**
     * Gets the volume of an object assuming its a cylander.
     */
    public double volume(){
    	double volume, t1, t2;
    	t1 = this.b * Math.PI;
    	t2 = this.a * Math.PI;
    	volume = t1 - t2;
    	volume *= 0.5;
    	//volume *= 2;
    	return volume;
        //return (.5 * (getTerm(b) - getTerm(a))) * 2;
    }
    
    public double toOunce() {
    	return this.sizeCubicInch * 0.57674377;
    }
    
    public double microsivertsPerInch() {
    	return lengthOfBanana() * BananaConstants.MICROSIVERTS_PER_INCH;
    }

    public double lengthOfBanana(){
        return Math.abs(this.a-this.b);
    }
    
    public double bananaToFootballField() {
    	return BananaConstants.INCH_TO_FOOTBALL_FIELD / this.length;
    }
    
    public double bananaToOilbarrel() {
    	return  BananaConstants.CUBIC_INCH_TO_OILBARREL / this.sizeCubicInch;
    }

    public void printBananaAttributes(){
        System.out.printf("Banana Length: %f\nWeight in ounces: %f\nMicrosiverts Per Inch: %f\nBananas Across Football Field: %f\nBananas could fit in an oil barrel: %f",
                          lengthOfBanana(),
                          toOunce(),
                          microsivertsPerInch(),
                          bananaToFootballField(),
                          bananaToOilbarrel());
    }
}