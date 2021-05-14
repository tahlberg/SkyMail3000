package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.csc133.a3.GameWorld;
import org.csc133.a3.Point;
import org.csc133.a3.visitors.*;

import java.io.IOException;
import java.util.Random;

public class Bird extends Movable {
    boolean dead;
    Image birdImage;

    public Bird(Point maxPoint){
        super(75+(new Random().nextInt(10)),
                new Random().nextInt((int)maxPoint.getX())+
                        new Random().nextDouble()
                , new Random().nextInt((int)maxPoint.getY())+
                        new Random().nextDouble(),
                ColorUtil.rgb(0,0,255),new Random().nextInt(364)
                , 5+(new Random().nextInt(5)));
        try{
            birdImage = Image.createImage("/bird.png");
        } catch (IOException e) {e.printStackTrace();}
    }

    //Create a new random heading for the bird
    public void randomHeading(){
        Random randGen = new Random();
        int changeChance = randGen.nextInt(3);
        switch(changeChance) {
            case 0:
                //Turns the bird left
                this.setHeading(this.getHeading()-5);
                break;
            case 1:
                //Bird continues on current heading
                break;
            case 2:
                //Turns the bird right
                this.setHeading((this.getHeading()+5));
        }

    }

    public boolean isDead(){
        return this.dead;
    }

    public void killBird(){
        this.dead = true;
    }

    @Override
    public void handleCollision(GameObject otherObject, GameWorld gw){
        if(this.checkCollisionVector(otherObject)){
            return;
        }
        IVisitor visitor;
        if(otherObject instanceof NonPlayerHelicopter){
            visitor = new NPHVisitor((NonPlayerHelicopter) otherObject, gw);
        } else if(otherObject instanceof PlayerHelicopter){
            visitor = new PlayerVisitor((PlayerHelicopter) otherObject, gw);
        } else if(otherObject instanceof RefuelingBlimp) {
            visitor = new BlimpVisitor((RefuelingBlimp) otherObject, gw);
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
        objString = "Bird: " + super.toString();
        return objString;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int xTransformation =
                (int) (containerOrigin.getX()+getLocation().getX()-getSize()/2);
        int yTransformation =
                (int) (containerOrigin.getY()+getLocation().getY()-getSize()/2);
        g.setColor(this.getColor());
        g.drawImage(birdImage.rotate(270-this.getHeading()),xTransformation,
                yTransformation, getSize(), getSize());
    }
}
