package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.Game;

public class AngleRightCommand extends Command {
    private Game game;

    public AngleRightCommand(Game g){
        super("Angle Right");
        game = g;
    }

    public void actionPerformed(ActionEvent evt){
        game.getGameWorld().angleRight(5);
    }
}
