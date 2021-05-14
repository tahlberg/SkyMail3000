package org.csc133.a3.strategies;

import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.NonPlayerHelicopter;

public interface IStrategy {
    public void doStrategy(GameWorld gw, NonPlayerHelicopter nph);
}
