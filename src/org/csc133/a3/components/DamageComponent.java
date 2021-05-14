package org.csc133.a3.components;

import com.codename1.charts.util.ColorUtil;
import org.csc133.a3.GameWorld;

public class DamageComponent extends CockpitComponent{
    private GameWorld gameWorld;

    public DamageComponent(GameWorld gw){
        super(2, ColorUtil.rgb(255,0,0));
        this.gameWorld = gw;
    }

    private void setCurrentDamage() {
        setDisplayData(gameWorld.getGameObjectList().getPlayerHelicopter()
                .getInstance().getDamageLevel());
    }

    public void update(){
        setCurrentDamage();
    }

    public boolean animate(){
        return true;
    }

}
