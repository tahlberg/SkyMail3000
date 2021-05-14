package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.Game;

public class BrakeCommand extends Command {
    private Game game;

    public BrakeCommand(Game g){
        super("Brake");
        game = g;
    }

    public void actionPerformed(ActionEvent evt){
        game.getGameWorld().brake();
    }
}
