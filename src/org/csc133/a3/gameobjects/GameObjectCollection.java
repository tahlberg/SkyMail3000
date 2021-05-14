package org.csc133.a3.gameobjects;

import org.csc133.a3.gameobjects.GameObject;
import org.csc133.a3.gameobjects.PlayerHelicopter;

import java.util.Vector;

public class GameObjectCollection {
    private Vector theCollection;

    public GameObjectCollection(){
        theCollection = new Vector();
    }

    public void add(GameObject newObject){
        theCollection.addElement(newObject);
    }

    public Vector getObjects(){
        return theCollection;
    }

    public int size(){
        return theCollection.size();
    }

    public void deleteAllGameObjects(){
        for(int i = 0; i < this.size(); i++){
            if(this.getObjects().get(i) instanceof GameObject){
                this.getObjects().remove(i);
            }
        }
    }

    public PlayerHelicopter getPlayerHelicopter(){
        for(int i = 0; i < this.size(); i++){
            if(this.getObjects().get(i) instanceof PlayerHelicopter){
                PlayerHelicopter playerCopter = (PlayerHelicopter)
                        this.getObjects().get(i);
                return playerCopter;
            }
        }
        return null;
    }
}
