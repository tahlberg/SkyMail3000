package org.csc133.a3.commands;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a3.Game;

public class AboutCommand extends Command {
    private Game game;

    public AboutCommand(Game g){
        super("About");
        this.game = g;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        game.pause();
        Dialog aboutDialog = new Dialog("About");
        TextArea aboutBody = new TextArea("Trevor Ahlberg\n" +
                "CSC 133-05\n" + "Version 2.0");
        aboutBody.setUIID("AboutBody");
        aboutBody.setEditable(false);
        aboutDialog.setLayout(new BorderLayout());
        aboutDialog.add(BorderLayout.CENTER, aboutBody);
        aboutDialog.setDisposeWhenPointerOutOfBounds(true);
        aboutDialog.show();
        game.play();
    }

}
