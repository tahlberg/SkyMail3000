package org.csc133.a3.components;

import com.codename1.charts.util.ColorUtil;
import org.csc133.a3.GameWorld;

public class FuelComponent extends CockpitComponent {
    private GameWorld gameWorld;

    public FuelComponent(GameWorld gw) {
        super(4, ColorUtil.GREEN);
        this.gameWorld = gw;
    }

    private void setCurrentFuel() {
        setDisplayData(gameWorld.getGameObjectList().getPlayerHelicopter()
                .getInstance().getFuelLevel());
    }

    public void update(){
        setCurrentFuel();
    }

    public boolean animate(){
        return true;
    }


}
