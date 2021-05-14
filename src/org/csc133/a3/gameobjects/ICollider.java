package org.csc133.a3.gameobjects;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.GameObject;
import org.csc133.a3.gameobjects.Helicopter;
import org.csc133.a3.visitors.IVisitor;

public interface ICollider {
    boolean collidesWith(GameObject otherObject);
    void handleCollision(GameObject otherObject, GameWorld gw);
    void accept(IVisitor visitor);
}
