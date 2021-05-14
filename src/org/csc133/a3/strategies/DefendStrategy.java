package org.csc133.a3.strategies;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.NonPlayerHelicopter;
import org.csc133.a3.Point;
import org.csc133.a3.gameobjects.SkyScraper;

public class DefendStrategy implements IStrategy {
    public void doStrategy(GameWorld gw, NonPlayerHelicopter nph){
        int nextSkyScraper =
                nph.getLastSkyScraperReached()+1;
        for(int i = 0; i < gw.getGameObjectList().size(); i++){
            if(gw.getGameObjectList().getObjects().get(i)
                    instanceof SkyScraper){
                SkyScraper currentScraper = (SkyScraper)
                        gw.getGameObjectList().getObjects().get(i);
                if(currentScraper.getSequenceNumber() == nextSkyScraper){
                    Point chasePoint = currentScraper.getLocation();
                    double deltaX = chasePoint.getX()-nph.getLocation().getX();
                    double deltaY = chasePoint.getY()-nph.getLocation().getY();
                    double theta = Math.toDegrees(Math.atan2(deltaY, deltaX));
                    nph.setHeading((int) (180-theta));
                }
            }
        }

    }
}
