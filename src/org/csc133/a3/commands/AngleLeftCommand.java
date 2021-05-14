package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.Game;

public class AngleLeftCommand extends Command {
    private Game game;

    public AngleLeftCommand(Game g){
        super("Angle Left");
        game = g;
    }

    public void actionPerformed(ActionEvent evt){
        game.getGameWorld().angleLeft(5);
    }
}
