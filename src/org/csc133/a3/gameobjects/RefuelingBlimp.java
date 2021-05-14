package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.csc133.a3.GameWorld;
import org.csc133.a3.Point;
import org.csc133.a3.visitors.*;

import java.io.IOException;
import java.util.Random;

public class RefuelingBlimp extends Fixed {
    private int capacity;
    private boolean empty;
    Image blimpImage;

    public RefuelingBlimp(Point maxDim){
        super(200 + new Random().nextInt(10),
                new Random().nextInt((int) maxDim.getX())+
                        new Random().nextDouble(),
                new Random().nextInt((int) maxDim.getY())+
                        new Random().nextDouble(),
                ColorUtil.rgb(255,0,0));
        this.capacity = this.getSize();
        this.empty = false;
        try{
            blimpImage = Image.createImage("/blimp.png");
        } catch (IOException e) {e.printStackTrace();}
    }

    //Returns the capacity of the blimp
    public int getCapacity(){
        return this.capacity;
    }

    public void emptyBlimp(){
        this.empty = true;
    }

    public boolean isEmpty(){
        return empty;
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw){
        if(this.checkCollisionVector(otherObject)){
            return;
        }
        IVisitor visitor;
        if(otherObject instanceof NonPlayerHelicopter){
            visitor = new NPHVisitor((NonPlayerHelicopter) otherObject, gw);
        } else if(otherObject instanceof Bird){
            visitor = new BirdVisitor((Bird) otherObject, gw);
        } else if(otherObject instanceof PlayerHelicopter) {
            visitor = new PlayerVisitor((PlayerHelicopter) otherObject, gw);
        } else if(otherObject instanceof SkyScraper){
            visitor = new SkyScraperVisitor((SkyScraper) otherObject, gw);
        } else{
            visitor = null;
            return;
        }
        this.accept(visitor);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    //Overrides the toString function with object details
    @Override
    public String toString(){
        String objString;
        objString = "Refueling Blimp: " + super.toString()
                + " capacity=" + this.capacity;
        return objString;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int xTransformation =
                (int) (containerOrigin.getX()+getLocation().getX()+getSize()/2);
        int yTransformation =
                (int) (containerOrigin.getY()+getLocation().getY()+getSize()/4);
        g.drawImage(blimpImage,
                xTransformation, yTransformation, getSize(), getSize()/2);
        g.drawString(String.valueOf(this.getCapacity()),
                xTransformation+getSize()/4,
                yTransformation+getSize()/8);
    }
}
