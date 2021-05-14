package org.csc133.a3.components;

import com.codename1.ui.Component;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import java.io.IOException;

public class CockpitComponent extends Component {
    Image[] digitImages = new Image[10];

    private int ledColor;
    private int numDigitsShowing;
    Image[] displayDigits;

    public CockpitComponent(int numDigits, int color) {
        try{
            digitImages[0] = Image.createImage("/LED_digit_0.png");
            digitImages[1] = Image.createImage("/LED_digit_1.png");
            digitImages[2] = Image.createImage("/LED_digit_2.png");
            digitImages[3] = Image.createImage("/LED_digit_3.png");
            digitImages[4] = Image.createImage("/LED_digit_4.png");
            digitImages[5] = Image.createImage("/LED_digit_5.png");
            digitImages[6] = Image.createImage("/LED_digit_6.png");
            digitImages[7] = Image.createImage("/LED_digit_7.png");
            digitImages[8] = Image.createImage("/LED_digit_8.png");
            digitImages[9] = Image.createImage("/LED_digit_9.png");
        } catch (IOException e) {e.printStackTrace();}

        numDigitsShowing = numDigits;
        displayDigits = new Image[numDigitsShowing];

        for(int i = 0; i < numDigits; i++)
            displayDigits[i] = digitImages[0];

        this.ledColor = color;
    }

    public void setLedColor(int ledColor){
        this.ledColor = ledColor;
    }

    protected void setDisplayData(int data){
        long mathVal = (long) Math.pow(10, numDigitsShowing-1);
        long remainVal = data;
        for(int i = 0; i < numDigitsShowing; i++){
            int imagNum = Math.toIntExact((long)Math
                    .floor((double)(remainVal/mathVal)));
            displayDigits[i] = digitImages[imagNum];
            remainVal = remainVal%mathVal;
            mathVal = mathVal/10;
        }
    }

    public void start(){
        getComponentForm().registerAnimated(this);
    }

    public void stop(){
        getComponentForm().deregisterAnimated(this);
    }

    public void laidOut(){
        this.start();
    }

    protected Dimension calcPreferredSize(){
        return new Dimension(digitImages[0].getWidth()*numDigitsShowing,
                digitImages[0].getHeight());
    }

    public void update(){
        //To be overridden by subclass
    }

    public void paint(Graphics g){
        super.paint(g);
        final int COLOR_PAD = 1;

        int digitWidth = displayDigits[0].getWidth();
        int digitHeight = displayDigits[0].getHeight();
        int displayWidth = numDigitsShowing*digitWidth;

        float scaleFactor = Math.min(
                getInnerHeight()/(float)digitHeight,
                getInnerWidth()/(float)displayWidth);

        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight  = (int)(scaleFactor*digitHeight);
        int displayDisplayWidth = displayDigitWidth*numDigitsShowing;

        int displayX = getX() + (getWidth()-displayDisplayWidth)/2;
        int displayY = getY() + (getHeight()-displayDigitHeight)/2;

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.setColor(ledColor);
        g.fillRect(displayX+COLOR_PAD,
                displayY+COLOR_PAD,
                displayDisplayWidth-COLOR_PAD*2,
                displayDigitHeight-COLOR_PAD*2
        );

        for(int digitIndex = 0;
            digitIndex < numDigitsShowing;
            digitIndex++){

            g.drawImage(
                    displayDigits[digitIndex],
                    displayX + digitIndex * displayDigitWidth,
                    displayY,
                    displayDigitWidth,
                    displayDigitHeight
            );
        }

    }

}
