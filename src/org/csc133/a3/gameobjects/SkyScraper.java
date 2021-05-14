package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.csc133.a3.GameWorld;
import org.csc133.a3.Point;
import org.csc133.a3.visitors.*;

import java.io.IOException;

public class SkyScraper extends Fixed {
    private int sequenceNumber;
    Image[] skyScraperImages = new Image[10];
    Image skyScraperImage;
    public SkyScraper(int seqNum, double xPos, double yPos){
        super(200, xPos, yPos, ColorUtil.rgb(0,0,255));
        this.sequenceNumber = seqNum;
        try{
            skyScraperImages[0] = Image.createImage("/SkyScraper0.png");
            skyScraperImages[1] = Image.createImage("/SkyScraper1.png");
            skyScraperImages[2] = Image.createImage("/SkyScraper2.png");
            skyScraperImages[3] = Image.createImage("/SkyScraper3.png");
            skyScraperImages[4] = Image.createImage("/SkyScraper4.png");
            skyScraperImages[5] = Image.createImage("/SkyScraper5.png");
            skyScraperImages[6] = Image.createImage("/SkyScraper6.png");
            skyScraperImages[7] = Image.createImage("/SkyScraper7.png");
            skyScraperImages[8] = Image.createImage("/SkyScraper8.png");
            skyScraperImages[9] = Image.createImage("/SkyScraper9.png");
        } catch (IOException e) {e.printStackTrace();}

        skyScraperImage = skyScraperImages[seqNum];
    }

    //Returns the sequence number of the skyscraper
    public int getSequenceNumber(){
        return this.sequenceNumber;
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
        } else if(otherObject instanceof RefuelingBlimp) {
            visitor = new BlimpVisitor((RefuelingBlimp) otherObject, gw);
        } else if(otherObject instanceof PlayerHelicopter){
            visitor = new PlayerVisitor((PlayerHelicopter) otherObject, gw);
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
        objString = "Skyscraper: " + super.toString() + " seqNum=" + this.sequenceNumber;
        return objString;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int xTransformation =
                (int) (containerOrigin.getX()+getLocation().getX()-getSize()/2);
        int yTransformation =
                (int) (containerOrigin.getY()+getLocation().getY()-getSize()/2);
        g.drawImage(skyScraperImage,
                xTransformation, yTransformation, getSize(), getSize());
    }
}
