package org.csc133.a3;

public class Point {
    private double x;
    private double y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }

    public Point(double xPos, double yPos){
        this.x = xPos;
        this.y = yPos;
    }

    public double[] getPosArray(){
        double[] posArray = new double[2];
        posArray[0] = this.x;
        posArray[1] = this.y;
        return posArray;
    }

    public void setPos(double xPos, double yPos){
        this.x = xPos;
        this.y = yPos;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
}
