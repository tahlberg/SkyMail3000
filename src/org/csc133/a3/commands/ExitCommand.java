package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.Game;

public class ExitCommand extends Command {
    Game game;

    public ExitCommand(Game g){
        super("Exit");
        game = g;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        game.pause();
        if (Dialog.show("Exit Game",
                "Are you sure you'd like to exit?",
                "Yes", "No") == true)
            game.getGameWorld().exit();
        game.play();
    }
}
