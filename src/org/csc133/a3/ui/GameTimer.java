package org.csc133.a3.ui;

import java.util.Calendar;
import java.lang.Math;

public class GameTimer {
     private long startTime;
     private long savedTime;
     private int minutes;
     private int seconds;
     private int tenths;

     public GameTimer(){
        Calendar rightNow = Calendar.getInstance();
        this.startTime = rightNow.getTimeInMillis();
        this.savedTime = 0;
    }

    private long getElapsedTime(){
        long currTime;
        long elapsedTime;
        Calendar rightNow = Calendar.getInstance();
        currTime = rightNow.getTimeInMillis();
        elapsedTime = currTime-this.startTime;
        return elapsedTime;
    }

    private void convertCurrentTime(){
         long remainingTime;
         long elapsedTime = this.getElapsedTime();
         elapsedTime += this.savedTime;
         this.minutes = Math.toIntExact(elapsedTime/60000);
         remainingTime = elapsedTime%60000;
         this.seconds = Math.toIntExact(remainingTime/1000);
         remainingTime = remainingTime%1000;
         this.tenths = Math.toIntExact(remainingTime/100);
    }

    public int[] getGameTime(){
         int[] gameTime = new int[3];
         convertCurrentTime();
         gameTime[0] = this.minutes;
         gameTime[1] = this.seconds;
         gameTime[2] = this.tenths;
         return gameTime;
    }

    public void restartTimer(){
        Calendar rightNow = Calendar.getInstance();
        this.startTime = rightNow.getTimeInMillis();
        this.savedTime = 0;
    }

    public void pauseTimer(){
        this.savedTime += this.getElapsedTime();
    }

    public void resumeTimer(){
        Calendar rightNow = Calendar.getInstance();
        this.startTime = rightNow.getTimeInMillis();
    }
}
