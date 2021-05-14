package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a3.Game;

public class HelpCommand extends Command {
    private Game game;

    public HelpCommand(Game g){
        super("Help");
        game = g;
    }

    public void actionPerformed(ActionEvent evt){
        game.pause();
        Dialog aboutDialog = new Dialog("Help");
        TextArea aboutBody = new TextArea("a - Accelerate\n" +
                "b - Brake\n" + "l - Turn Left\n" +
                "r - Turn Right\n" + "n - Collide with NPH\n" +
                "s - Collide with SkyScraper\n" + "e - Collide with Blimp\n" +
                "g - Collide with Bird\n" + "x - Exit");
        aboutBody.setUIID("AboutBody");
        aboutBody.setEditable(false);
        aboutDialog.setLayout(new BorderLayout());
        aboutDialog.add(BorderLayout.CENTER, aboutBody);
        aboutDialog.setDisposeWhenPointerOutOfBounds(true);
        aboutDialog.show();
        game.play();
    }
}
