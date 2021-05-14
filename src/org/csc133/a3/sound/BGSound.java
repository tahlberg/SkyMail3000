package org.csc133.a3.sound;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.InputStream;

public class BGSound implements Runnable{
    private Media m;
    public BGSound(String fileName){
        new Thread(this).start();
        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);

            m = MediaManager.createMedia(is, "audio/wav", this);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        m.play();
    }

    public void pause(){
        m.pause();
    }

    @Override
    public void run() {
        m.setTime(0);
        m.play();
    }
}
