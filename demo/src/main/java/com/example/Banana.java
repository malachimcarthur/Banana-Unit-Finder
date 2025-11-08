package com.example;

public class Banana{
    	private static final double MICROSIVERTS_PER_INCH = 0.0111428571;
	private double a,b;
	private double sizeCubicInch;
	private double microsiverts;
    public Banana(double a, double b){
    	this.a = a;
    	this.b = b;
    }
    /**
     * Gets the volume of an object assuming its a cylander.
     */
    public void Volume(){
    	double volume, t1, t2;
    	t1 = this.b * Math.PI;
    	t2 = this.a * Math.PI;
    	volume = t1 - t2;
    	volume *= 0.5;
    	//volume *= 2;
    	this.sizeCubicInch = volume;
        //return (.5 * (getTerm(b) - getTerm(a))) * 2;
    }
    
    public double toOunce() {
    	return this.sizeCubicInch * 0.57674377;
    }
    
    public double microsivertsPerInch() {
    	double length = Math.abs(a-b);
    	return length * MICROSIVERTS_PER_INCH;
    }
}