package com.example;

public class Banana{
    public Banana(){

    }
    /**
     * Gets the volume of an object assuming its a cylander.
     */
    public static double Volume(double a, double b){
        return (.5 * (getTerm(b) - getTerm(a))) * 2;
    }

    /**
     * Gets the radius.
     */
    public static double getR(double x){
        return (1/2) * (((.1/3) * Math.pow(x,3)) - (((.1/3) * Math.pow(x,3)) - (2 * x)));
    }

    /**
     * Squares the radius.
     */
    public static double getRSquared(double x){
        double r = getR(x);
        return r*r;
    }
    /**
     * Gets the full term of b or a.
     */
    public static double getTerm(double x){
        return Math.PI * getRSquared(x);
    }
}