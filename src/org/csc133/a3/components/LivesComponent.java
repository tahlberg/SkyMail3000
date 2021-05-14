package org.csc133.a3.components;

import com.codename1.charts.util.ColorUtil;
import org.csc133.a3.GameWorld;
import org.csc133.a3.components.CockpitComponent;

public class LivesComponent extends CockpitComponent {
    private GameWorld gameWorld;

    public LivesComponent(GameWorld gw) {
        super(1, ColorUtil.CYAN);
        this.gameWorld = gw;
    }

    private void setCurrentLives() {
        setDisplayData(gameWorld.getPlayerLives());
    }

    public void update(){
        setCurrentLives();
    }

    public boolean animate(){
        return true;
    }
}
