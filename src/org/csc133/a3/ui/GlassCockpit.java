package org.csc133.a3.ui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a3.GameWorld;
import org.csc133.a3.components.LivesComponent;
import org.csc133.a3.components.*;
import org.csc133.a3.gameobjects.PlayerHelicopter;


public class GlassCockpit extends Container {
    public GlassCockpit(GameWorld gw){
        this.getAllStyles().setBgTransparency(255);
        this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        this.setLayout(new GridLayout(2, 6));

        Label clockLabel = new Label("Game Time");
        Label fuelLabel = new Label("Fuel");
        Label damageLabel = new Label("Damage");
        Label livesLabel = new Label("Lives");
        Label lastLabel = new Label("Last");
        Label headingLabel = new Label("Heading");
        this.add(clockLabel)
                .add(fuelLabel)
                .add(damageLabel)
                .add(livesLabel)
                .add(lastLabel)
                .add(headingLabel);

        PlayerHelicopter playerHeli =
                gw.getGameObjectList().getPlayerHelicopter();
        GameClockComponent gameClock = new GameClockComponent();
        FuelComponent fuelComponent = new FuelComponent(gw);
        DamageComponent damageComponent = new DamageComponent(gw);
        LivesComponent livesComponent = new LivesComponent(gw);
        SkyScraperComponent skyScraperComponent =
                new SkyScraperComponent(gw);
        HeadingComponent headingComponent = new HeadingComponent(gw);

        this.add(gameClock)
                .add(fuelComponent)
                .add(damageComponent)
                .add(livesComponent)
                .add(skyScraperComponent)
                .add(headingComponent);

    }

    public void play() {
        for(int i = 0; i < this.getComponentCount(); i++){
            if(this.getComponentAt(i) instanceof GameClockComponent){
                GameClockComponent gameClockComponent =
                        (GameClockComponent) this.getComponentAt(i);
                gameClockComponent.startElapsedTime();
            }
        }
    }

    public void pause(){
        for(int i = 0; i < this.getComponentCount(); i++){
            if(this.getComponentAt(i) instanceof GameClockComponent){
                GameClockComponent gameClockComponent =
                        (GameClockComponent) this.getComponentAt(i);
                gameClockComponent.stopElapsedTime();
            }
        }
    }

    public void update(){
        for(int i = 0; i < this.getComponentCount(); i++){
            if(this.getComponentAt(i) instanceof CockpitComponent){
                CockpitComponent cpitComponent =
                        (CockpitComponent) this.getComponentAt(i);
                cpitComponent.update();
            }
        }
    }
}
