package org.csc133.a3.strategies;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.NonPlayerHelicopter;
import org.csc133.a3.Point;

public class AttackStrategy implements IStrategy {

    public void doStrategy(GameWorld gw, NonPlayerHelicopter nph){
        Point chasePoint = gw.getGameObjectList().getPlayerHelicopter()
                .getInstance().getLocation();
        double deltaX = chasePoint.getX()-nph.getLocation().getX();
        double deltaY = chasePoint.getY()-nph.getLocation().getY();
        double theta = Math.toDegrees(Math.atan2(deltaY, deltaX));
        nph.setHeading((int) (180-theta));
    }
}
