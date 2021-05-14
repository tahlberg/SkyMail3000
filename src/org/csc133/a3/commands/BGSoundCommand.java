package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.sound.BGSound;

public class BGSoundCommand extends Command{
    private BGSound bgSound;
    private boolean bPause = false;
    public BGSoundCommand(){
        super("Play/Pause");
        bgSound = new BGSound("Kevin MacLeod - Aces High.wav");
        bgSound.play();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        bPause = !bPause;
        if (bPause){
            bgSound.pause();
        }else{
            bgSound.play();
        }
    }
}
