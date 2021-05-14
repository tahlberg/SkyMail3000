package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import org.csc133.a3.gameobjects.*;
import org.csc133.a3.sound.BGSound;
import org.csc133.a3.sound.Sound;

import java.util.*;

public class GameWorld {
    private int gameClock;
    private int playerLives;
    private int tickTimeElapsed;
    private Point maxGameDim = new Point();
    private Point initLoc;
    private Sound crashSound;
    private Sound blimpSound;
    private Sound lifeSound;

    //Create the list of GameObjects
    private GameObjectCollection gameObjectList = new GameObjectCollection();

    public void init() {
        this.playerLives = 3;
        Random randGen = new Random();
        initLoc = new Point(this.maxGameDim.getX() *
                randGen.nextDouble(),
                this.maxGameDim.getY() * randGen.nextDouble());
        //Initialize the skyscrapers
        SkyScraper skyScraper1 = new
                SkyScraper(1, initLoc.getX(), initLoc.getY());
        SkyScraper skyScraper2 = new SkyScraper(2,
                this.maxGameDim.getX() * randGen.nextDouble(),
                this.maxGameDim.getY() * randGen.nextDouble());
        SkyScraper skyScraper3 = new SkyScraper(3,
                this.maxGameDim.getX() * randGen.nextDouble(),
                this.maxGameDim.getY() * randGen.nextDouble());
        SkyScraper skyScraper4 = new SkyScraper(4,
                this.maxGameDim.getX() * randGen.nextDouble(),
                this.maxGameDim.getY() * randGen.nextDouble());
        this.gameObjectList.add(skyScraper1);
        this.gameObjectList.add(skyScraper2);
        this.gameObjectList.add(skyScraper3);
        this.gameObjectList.add(skyScraper4);
        //Initialize the refueling blimps
        RefuelingBlimp refuelingBlimp1 = new RefuelingBlimp(this.maxGameDim);
        RefuelingBlimp refuelingBlimp2 = new RefuelingBlimp(this.maxGameDim);
        this.gameObjectList.add(refuelingBlimp1);
        this.gameObjectList.add(refuelingBlimp2);
        //Initialize the birds
        Bird bird1 = new Bird(maxGameDim);
        Bird bird2 = new Bird(maxGameDim);
        this.gameObjectList.add(bird1);
        this.gameObjectList.add(bird2);
        //Initialize the NPHs
        NonPlayerHelicopter nph1 = new
                NonPlayerHelicopter((int) skyScraper1.getLocation().getX() - 300,
                (int) skyScraper1.getLocation().getY() - 300, this);
        NonPlayerHelicopter nph2 = new
                NonPlayerHelicopter((int) skyScraper1.getLocation().getX() + 300,
                (int) skyScraper1.getLocation().getY() + 300, this);
        NonPlayerHelicopter nph3 = new
                NonPlayerHelicopter((int) skyScraper1.getLocation().getX() - 300,
                (int) skyScraper1.getLocation().getY() + 300, this);
        this.gameObjectList.add(nph1);
        this.gameObjectList.add(nph2);
        this.gameObjectList.add(nph3);
        //Initialize player helicopter
        PlayerHelicopter playerCopter =
                PlayerHelicopter.createInstance(initLoc.getX(),
                        initLoc.getY());
        this.gameObjectList.add(playerCopter);
    }

    public GameObjectCollection getGameObjectList() {
        return this.gameObjectList;
    }

    //Accelerates the helicopter
    public void accelerate() {
        this.getGameObjectList().getPlayerHelicopter()
                .getInstance().accelerate();
        System.out.println("The helicopter has accelerated!");
    }

    //Brakes the helicopter
    public void brake() {
        this.getGameObjectList().getPlayerHelicopter()
                .getInstance().brake();
        System.out.println("The helicopter has braked!");
    }

    //Angles helicopter left
    public void angleLeft(int deg) {
        this.getGameObjectList().getPlayerHelicopter()
                .getInstance().changeStickAngle(deg * -1);
        System.out.println("Moved stick angle to the left!");
    }

    //Angles helicopter right
    public void angleRight(int deg) {
        this.getGameObjectList().getPlayerHelicopter()
                .getInstance().changeStickAngle(deg);
        System.out.println("Moved stick angle to the right!");
    }

    //Collides player helicopter with another helicopter
    public void collideWithHelicopter(Helicopter heli1, Helicopter heli2) {
        playSound(crashSound);
        heli1.takeDamage(20);
        heli2.takeDamage(20);
        System.out.println("Helicopters collided!");
    }

    //Collides player helicopter with a skyscraper
    public void collideWithSkyScraper(Helicopter heli, SkyScraper scraper) {
        if (scraper.getSequenceNumber() == heli.getLastSkyScraperReached() + 1) {
            heli.reachNextSkyScraper();
            System.out.println("Skyscraper " + scraper.getSequenceNumber() +
                    " was reached by a helicopter");
        }
    }

    //Collides player helicopter with a refueling blimp
    public void collideWithBlimp(Helicopter heli, RefuelingBlimp blimp) {
        playSound(blimpSound);
        heli.refuel(blimp.getCapacity());
        blimp.emptyBlimp();
        System.out.println("Helicopter refueled at a blimp");
    }

    //Collides player helicopter with a bird
    public void collideWithBird(Helicopter heli, Bird bird) {
        heli.takeDamage(10);
        bird.killBird();
        System.out.println("Helicopter collided with a bird!");
    }

    //Increments game tick
    public void tick(int timeElapsed) {
        this.tickTimeElapsed += 20;

        for (int i = 0; i < this.gameObjectList.getObjects().size(); i++) {
            if (this.gameObjectList.getObjects().get(i)
                    instanceof NonPlayerHelicopter) {
                NonPlayerHelicopter nph = (NonPlayerHelicopter)
                        this.gameObjectList.getObjects().get(i);
                nph.invokeStrategy();
            }
        }

        //Moves all GameObjects
        this.moveObjects();

        //Check for collisions
        this.detectCollisions();

        //Increment game clock
        this.gameClock++;
        System.out.println("Game clock has advanced to " + this.gameClock);
        //Display map to console
        this.map();
        //Display game info to console
        this.display();
        //Check if NPH objects are destroyed
        for (int i = 0; i < this.gameObjectList.getObjects().size(); i++) {
            if (this.gameObjectList.getObjects().get(i)
                    instanceof NonPlayerHelicopter) {
                NonPlayerHelicopter npHeli = (NonPlayerHelicopter)
                        this.gameObjectList.getObjects().get(i);
                if (npHeli.getDamageLevel() >= 100) {
                    this.gameObjectList.getObjects().remove(npHeli);
                }
            }
        }
        //Reinitialized destroyed blimps and birds
        for(int i = 0; i < this.gameObjectList.size(); i++){
            Object currObj = this.gameObjectList.getObjects().get(i);
            if(currObj instanceof RefuelingBlimp){
                if(((RefuelingBlimp) currObj).isEmpty()){
                    this.getGameObjectList().getObjects().remove(i);
                    this.getGameObjectList().getObjects().add(new
                            RefuelingBlimp(maxGameDim));
                }
            } else if(currObj instanceof Bird){
                if(((Bird) currObj).isDead()){
                    this.getGameObjectList().getObjects().remove(i);
                    this.getGameObjectList().getObjects().add(new
                            Bird(maxGameDim));
                }
            }
        }
        //Check if player helicopter is too damaged or out of fuel for loss of life
        if (this.getGameObjectList().getPlayerHelicopter()
                .getInstance().getDamageLevel() >= 100 ||
                this.getGameObjectList().getPlayerHelicopter()
                        .getInstance().getFuelLevel() == 0) {
            System.out.println("You lose a life!");
            try {
                lifeSound.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.playerLives--;
            //Exit on game over
            if (playerLives == 0) {
                System.out.println("GAME OVER");
                Dialog.show("GAME OVER", "Better luck next time!",
                        new Command("Exit"));
                System.exit(0);
            }
            //Reinitialize if not out of lives
            else {
                this.getGameObjectList().getPlayerHelicopter()
                        .getInstance().refuel(1000);
                this.getGameObjectList().getPlayerHelicopter().getInstance()
                        .takeDamage(this.getGameObjectList()
                                .getPlayerHelicopter().getInstance()
                                .getDamageLevel() * -1);
                this.getGameObjectList().getPlayerHelicopter()
                        .getInstance().setLocation(initLoc);
            }
        }
        //Find the highest skyscraper in the gameobject list
        int lastSkyscraper =
                this.gameObjectList.getPlayerHelicopter()
                        .getLastSkyScraperReached();
        for (int i = 0; i < this.gameObjectList.size(); i++) {
            if (this.gameObjectList.getObjects().get(i) instanceof SkyScraper) {
                SkyScraper skyScraper =
                        (SkyScraper) this.gameObjectList.getObjects().get(i);
                //Check if it's a higher number skyscraper
                if (skyScraper.getSequenceNumber() > lastSkyscraper) {
                    lastSkyscraper = skyScraper.getSequenceNumber();
                }
            }
        }
        //Check if we win, exit on win
        if (this.getGameObjectList().getPlayerHelicopter()
                .getInstance().getLastSkyScraperReached() == lastSkyscraper) {
            System.out.println("You won!");
            Dialog.show("VICTORY", "Congratulations, you won!",
                    new Command("Exit"));
            System.exit(0);
        }
        for (int i = 0; i < this.gameObjectList.getObjects().size(); i++) {
            if (this.getGameObjectList().getObjects().get(i)
                    instanceof NonPlayerHelicopter) {
                NonPlayerHelicopter nphCopter = (NonPlayerHelicopter)
                        this.getGameObjectList().getObjects().get(i);
                if (nphCopter.getLastSkyScraperReached() == lastSkyscraper) {
                    System.out.println("Game over! " +
                            "A non-player helicopter wins!");
                    Dialog.show("GAME OVER",
                            "A non-player helicopter wins!",
                            new Command("Exit"));
                    System.exit(0);
                }
            }
        }
    }

    //Displays the statistics of the player
    public void display() {
        Helicopter copter = this.getGameObjectList().getPlayerHelicopter()
                .getInstance();
        System.out.println("Player statistics:");
        System.out.print("Lives: ");
        System.out.println(this.playerLives);
        System.out.print("Game clock: ");
        System.out.println(this.gameClock);
        System.out.print("Highest Skyscraper Reached: ");
        System.out.println(copter.getLastSkyScraperReached());
        System.out.print("Fuel Level: ");
        System.out.println(copter.getFuelLevel());
        System.out.print("Damage Sustained: ");
        System.out.println(copter.getDamageLevel());
    }

    //Displays a map of the objects
    public void map() {
        for (int i = 0; i < this.gameObjectList.size(); i++) {
            System.out.println(this.gameObjectList.getObjects().get(i));
        }
    }


    //Exits the application
    public void exit() {
        //Exits the application
        System.out.println("Now exiting...");
        System.exit(0);
    }

    private void moveObjects() {
        //Move all movable objects
        //Iterate through all game objects
        for (int i = 0; i < this.gameObjectList.size(); i++) {
            //Check if the object is Movable
            if (this.gameObjectList.getObjects().get(i) instanceof Movable) {
                Movable movObj =
                        (Movable) this.gameObjectList.getObjects().get(i);
                movObj.move(maxGameDim);
                //If movable is a helicopter, consume fuel and change heading
                if (this.gameObjectList.getObjects().get(i)
                        instanceof Helicopter) {
                    Helicopter copter = (Helicopter)
                            this.gameObjectList.getObjects().get(i);
                    copter.consumeFuel();
                    copter.changeHeading();
                    //Update cockpit display to reflect fuel/heading changes
                }
                //If movable is a bird, change to random heading
                if (this.gameObjectList.getObjects().get(i) instanceof Bird) {
                    Bird bird = (Bird) this.gameObjectList.getObjects().get(i);
                    bird.randomHeading();
                }
            }
        }
    }

    private void detectCollisions() {
        for (GameObject i : (Vector<GameObject>)this.gameObjectList.getObjects()){
            for (GameObject n : (Vector<GameObject>)this.gameObjectList.getObjects()){
                if(i != n){
                    if(i.collidesWith(n) && !i.checkCollisionVector(n)){
                        i.handleCollision(n, this);
                        i.addToCollisionVector(n);
                        n.addToCollisionVector(i);
                    } else if(i.checkCollisionVector(n)){
                        i.removeFromCollisionVector(n);
                        n.removeFromCollisionVector(i);
                    }
                }
            }
        }
    }

    public void initSound(){
        try {
            crashSound = new Sound("crash.wav");
        } catch(Exception e){
        }
        try {
            lifeSound = new Sound("explosion.wav");
        } catch(Exception e){
        }
        try {
            blimpSound = new Sound("voltage.wav");
        } catch(Exception e){
        }
    }

    private void playSound(Sound s){
        try {
            s.play();
        } catch (Exception e) {
            initSound();
        }
    }

    public void setDimensions(double x, double y) {
        this.maxGameDim.setPos(x, y);
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public void changeStrategies() {
        for (int i = 0; i < this.gameObjectList.getObjects().size(); i++) {
            if (this.gameObjectList.getObjects().get(i)
                    instanceof NonPlayerHelicopter) {
                NonPlayerHelicopter nph = (NonPlayerHelicopter)
                        this.gameObjectList.getObjects().get(i);
                nph.setStrategy();
            }
        }
    }

}
