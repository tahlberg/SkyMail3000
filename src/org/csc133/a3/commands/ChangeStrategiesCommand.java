package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.Game;

public class ChangeStrategiesCommand extends Command {
    private Game game;

    public ChangeStrategiesCommand(Game g){
        super("Change Strategies");
        game = g;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        game.getGameWorld().changeStrategies();
    }
}
