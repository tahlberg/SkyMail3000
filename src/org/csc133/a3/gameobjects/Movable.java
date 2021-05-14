package org.csc133.a3.gameobjects;

import org.csc133.a3.Point;

public class Movable extends GameObject{
    private int heading;
    private int speed;

    public Movable(int s, double x, double y, int c, int h, int sp){
        super(s, x, y, c);
        this.heading = h;
        this.speed = sp;
    }

    //Moves the object based on heading and speed
    public void move(Point maxBounds) {
        int theta = heading - 90;
        Point currLocation = this.getLocation();
        double deltaX = Math.sin(Math.toRadians(theta)) * this.speed;
        double deltaY = Math.cos(Math.toRadians(theta)) * this.speed;
        Point newLocation = new Point(currLocation.getX() + deltaX,
                currLocation.getY() + deltaY);
        if (newLocation.getX() > maxBounds.getX())
            newLocation.setPos(maxBounds.getX(), newLocation.getY());
        if (newLocation.getX() < 0)
            newLocation.setPos(0, newLocation.getY());
        if (newLocation.getY() > maxBounds.getY())
            newLocation.setPos(newLocation.getX(), maxBounds.getY());
        if (newLocation.getY() < 0)
            newLocation.setPos(newLocation.getX(), 0);
        this.setLocation(newLocation);
    }

    //Returns the heading of the object
    public int getHeading(){
        return this.heading;
    }

    //Returns the speed of the object
    public int getSpeed(){
        return this.speed;
    }

    //Sets the heading of the object
    public void setHeading(int num){
        this.heading = num;
    }

    //Sets the speed of the object
    public void setSpeed(int num){
        this.speed = num;
    }

    //Overrides the toString function with object details
    @Override
    public String toString(){
        String objString;
        objString = super.toString() + " heading=" + this.heading
                + " speed=" + this.speed;
        return objString;
    }
}
