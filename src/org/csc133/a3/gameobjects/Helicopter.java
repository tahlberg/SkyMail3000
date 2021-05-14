package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.csc133.a3.GameWorld;
import org.csc133.a3.Point;
import org.csc133.a3.visitors.PlayerVisitor;
import org.csc133.a3.visitors.IVisitor;

import java.io.IOException;

public class Helicopter extends Movable implements ISteerable {
    private int stickAngle;
    private int maximumSpeed;
    private int fuelLevel;
    private int fuelConsumptionRate;
    private int damageLevel;
    private int healthColor;
    private int lastSkyScraperReached;
    private int bladeDeg;
    Image heliBody;
    Image heliBlades;
    Image[] heliBladeRotations = new Image[72];

    public Helicopter(double x, double y){
        super(150, x, y, ColorUtil.rgb(0,255,0), 0, 0);
        this.maximumSpeed = 50;
        this.lastSkyScraperReached = 0;
        this.fuelLevel = 1000;
        this.fuelConsumptionRate = 2;
        this.damageLevel = 0;
        this.healthColor = 255;
        this.bladeDeg = 0;
        try{
            heliBody = Image.createImage("/helicopterbody.png");
            heliBlades = Image.createImage("/helicopterblades.png");
        } catch (IOException e) {e.printStackTrace();}
        for(int i = 0; i < heliBladeRotations.length; i++){
            heliBladeRotations[i] = heliBlades.rotate(5*i);
        }
    }

    //Returns the last reached SkyScraper object
    public int getLastSkyScraperReached(){
        return lastSkyScraperReached;
    }

    //Increments to counter of last skyscraper reached
    public void reachNextSkyScraper(){
        this.lastSkyScraperReached += 1;
    }

    //Returns fuel level
    public int getFuelLevel(){
        return this.fuelLevel;
    }

    //Returns damage level
    public int getDamageLevel(){
        return this.damageLevel;
    }

    //Implements damage taken of helicopter by increasing damage,
    //reducing speed, and decreasing color
    public void takeDamage(int numDamage){
        this.damageLevel += numDamage;
        this.maximumSpeed -= numDamage;
        healthColor -= 2*numDamage;
        this.setColor(0,healthColor,0);
    }

    //Modifies the stick angle of the helicopter
    public void changeStickAngle(int deg){
        this.stickAngle += deg;
    }

    //Changes the heading based on the stick angle
    public void changeHeading(){
        if(stickAngle >= 5){
            //Change heading to the left
            this.setHeading(this.getHeading()-5);
            if(this.getHeading() < 0){
                this.setHeading(360+this.getHeading());
            }
            stickAngle -= 5;

        }else if(stickAngle <= -5){
            //Change heading to the right
            this.setHeading(this.getHeading()+5);
            if(this.getHeading() > 360){
                this.setHeading(this.getHeading()-360);
            }
            stickAngle +=5;
        }
    }

    //Accelerates the helicopter
    public void accelerate(){
        this.setSpeed(this.getSpeed() + 5);
        //Don't go above maximum speed
        if(this.getSpeed() > this.maximumSpeed){
            this.setSpeed(maximumSpeed);
        }
    }

    //Decelerates the helicopter
    public void brake(){
        this.setSpeed(this.getSpeed() - 10);
        //Don't go below minimum speed
        if(this.getSpeed() < 0){
            this.setSpeed(0);
        }
    }

    //Refuels the helicopter
    public void refuel(int fuel){
        this.fuelLevel += fuel;
    }

    //Reduces fuel of the helicopter
    public void consumeFuel(){
        this.fuelLevel -= fuelConsumptionRate;
        //Prevent negative fuel value
        if(this.fuelLevel < 0){
            this.fuelLevel = 0;
        }
        //Prevent helicopter from moving while out of fuel
        if(fuelLevel == 0){
            this.setSpeed(0);
        }
    }

    //Overrides the toString function with object details
    @Override
    public String toString(){
        String objString;
        objString = "Helicopter: " + super.toString() + " maxSpeed="
                + this.maximumSpeed + " stickAngle=" + stickAngle
                + " fuelLevel=" + fuelLevel + " damageLevel=" + damageLevel;
        return objString;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        if(this.getSpeed() != 0){
            this.bladeDeg += 1*this.getSpeed()/5;
            if(bladeDeg > 71){
                bladeDeg = bladeDeg-72;
            }
        }
        int xTransformation =
                (int) (containerOrigin.getX()+getLocation().getX()-getSize()/2);
        int yTransformation =
                (int) (containerOrigin.getY()+getLocation().getY()-getSize()/2);
        g.setColor(this.getColor());
        g.drawImage(heliBody.rotate(270-this.getHeading()),xTransformation,
                yTransformation, getSize(), getSize());
        g.drawImage(heliBladeRotations[bladeDeg],xTransformation,
                yTransformation, getSize(), getSize());
    }

}
