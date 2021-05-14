package org.csc133.a3.gameobjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;
import org.csc133.a3.GameWorld;
import org.csc133.a3.Point;
import org.csc133.a3.visitors.IVisitor;

import java.util.ArrayList;
import java.util.Vector;

public class GameObject implements IDrawable, ICollider {
    private org.csc133.a3.Point location;
    private int size;
    private int color;
    private Vector<GameObject> collisionVector;

    public GameObject(int s, double x, double y, int c){
        this.location = new org.csc133.a3.Point(x, y);
        this.size = s;
        this.color = c;
        collisionVector = new Vector<GameObject>();
    }

    //Changes the location of a created object
    //input: x, y values of location
    public void setLocation(double x, double y){
        this.location.setPos(x, y);
    }

    public void setLocation(org.csc133.a3.Point p){
        this.location = p;
    }

    //Returns the location of the object
    public org.csc133.a3.Point getLocation(){
        return this.location;
    }

    //Returns the size of the object
    public int getSize(){
        return this.size;
    }

    //Sets the color of an object based on rgb int values
    public void setColor(int red, int green, int blue){
        ColorUtil colorUtil = new ColorUtil();
        this.color = colorUtil.rgb(red, green, blue);
    }

    public int getColor(){
        return this.color;
    }

    public void addToCollisionVector(GameObject obj){
        this.collisionVector.add(obj);
    }

    public boolean checkCollisionVector(GameObject obj){
        return this.collisionVector.contains(obj);
    }

    public void removeFromCollisionVector(GameObject obj){
        this.collisionVector.remove(obj);
    }

    public Rectangle getBoundingRectangle(){
        return new Rectangle((int)+this.location.getX()-(size/2),
                (int)this.location.getY()-(size/2), size, size);
    }

    public boolean collidesWith(GameObject otherObject){
        return this.getBoundingRectangle()
                .intersects(otherObject.getBoundingRectangle());
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw) {

    }

    @Override
    public void accept(IVisitor visitor) {

    }

    //Overrides toString of object with object description
    @Override
    public String toString(){
        String objString = new String();
        String colorDesc = "[" + ColorUtil.red(this.color) + ","
                + ColorUtil.green(this.color) + ","
                + ColorUtil.blue(this.color) + "]";
        objString += "loc=" + Math.round(this.location.getX()*10.0)/10.0 + "," +
                Math.round(this.location.getY()*10.0)/10.0 +
                " size=" + this.size + " color=" + colorDesc;
        return objString;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {

    }
}
