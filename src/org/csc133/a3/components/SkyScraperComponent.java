package org.csc133.a3.components;

import com.codename1.charts.util.ColorUtil;
import org.csc133.a3.GameWorld;
import org.csc133.a3.components.CockpitComponent;

public class SkyScraperComponent extends CockpitComponent {
    private GameWorld gameWorld;

    public SkyScraperComponent(GameWorld gw) {
        super(1, ColorUtil.CYAN);
        this.gameWorld = gw;
    }

    private void setCurrentSkyScraper() {
        setDisplayData(gameWorld.getGameObjectList().getPlayerHelicopter()
                .getInstance().getLastSkyScraperReached());
    }

    public void update(){
        setCurrentSkyScraper();
    }

    public boolean animate(){
        return true;
    }
}
