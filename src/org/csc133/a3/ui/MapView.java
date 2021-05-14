package org.csc133.a3.ui;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import org.csc133.a3.gameobjects.GameObjectCollection;
import org.csc133.a3.GameWorld;
import org.csc133.a3.Point;
import org.csc133.a3.gameobjects.GameObject;

public class MapView extends Container {
    private GameWorld gw;

    public MapView(GameWorld gworld){
        this.gw = gworld;
    }

    public void update(){
        this.repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
        GameObjectCollection paintList = this.gw.getGameObjectList();
        for(int i = 0; i < paintList.size(); i++){
            if(this.gw.getGameObjectList().getObjects().get(i)
                    instanceof GameObject) {
                GameObject gameObj = (GameObject)
                        this.gw.getGameObjectList().getObjects().get(i);
                gameObj.draw(g, new Point(this.getX(),this.getY()));
            }
        }
    }
}
