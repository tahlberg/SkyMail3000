package org.csc133.a3.components;

import com.codename1.charts.util.ColorUtil;
import org.csc133.a3.GameWorld;

public class HeadingComponent extends CockpitComponent{
    private GameWorld gameWorld;

    public HeadingComponent(GameWorld gw) {
        super(3, ColorUtil.YELLOW);
        this.gameWorld = gw;
    }

    private void setCurrentHeading() {
        setDisplayData(gameWorld.getGameObjectList().getPlayerHelicopter()
                .getInstance().getHeading());
    }

    public void update(){
        setCurrentHeading();
    }

    public boolean animate(){
        return true;
    }
}
