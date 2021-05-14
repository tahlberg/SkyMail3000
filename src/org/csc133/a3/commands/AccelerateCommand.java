package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.Game;

public class AccelerateCommand extends Command {
    private Game game;

    public AccelerateCommand(Game g){
        super("Accelerate");
        game = g;
    }

    public void actionPerformed(ActionEvent evt){
        game.getGameWorld().accelerate();
    }
}
