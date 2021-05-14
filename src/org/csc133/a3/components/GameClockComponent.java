package org.csc133.a3.components;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import org.csc133.a3.ui.GameTimer;

import java.io.IOException;

public class GameClockComponent extends Component{

    Image[] digitImages = new Image[10];
    Image[] digitWithDotImages = new Image[100];
    Image colonImage;
    private int ledColor;
    private int tenthColor;
    private static int MS_COLON_IDX=2;
    private static int DIGIT_WITH_DOT_IDX=4;
    private static final int numDigitsShowing=6;
    Image[] clockDigits = new Image[numDigitsShowing];
    GameTimer gameTimer;

    public GameClockComponent(){
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

            digitWithDotImages[0] =
                    Image.createImage("/LED_digit_0_with_dot.png");
            digitWithDotImages[1] =
                    Image.createImage("/LED_digit_1_with_dot.png");
            digitWithDotImages[2] =
                    Image.createImage("/LED_digit_2_with_dot.png");
            digitWithDotImages[3] =
                    Image.createImage("/LED_digit_3_with_dot.png");
            digitWithDotImages[4] =
                    Image.createImage("/LED_digit_4_with_dot.png");
            digitWithDotImages[5] =
                    Image.createImage("/LED_digit_5_with_dot.png");
            digitWithDotImages[6] =
                    Image.createImage("/LED_digit_6_with_dot.png");
            digitWithDotImages[7] =
                    Image.createImage("/LED_digit_7_with_dot.png");
            digitWithDotImages[8] =
                    Image.createImage("/LED_digit_8_with_dot.png");
            digitWithDotImages[9] =
                    Image.createImage("/LED_digit_9_with_dot.png");

            colonImage = Image.createImage("/LED_colon.png");
        } catch (IOException e) {e.printStackTrace();}

        for(int i = 0; i < numDigitsShowing; i++)
            clockDigits[i] = digitImages[i];

        clockDigits[MS_COLON_IDX] = colonImage;

        clockDigits[DIGIT_WITH_DOT_IDX] = digitWithDotImages[0];

        ledColor = ColorUtil.MAGENTA;
        tenthColor = ColorUtil.BLUE;

        GameTimer gameClock = new GameTimer();
        this.gameTimer = gameClock;

    }

    private void setTime(int m, int s, int t){
        clockDigits[0] = digitImages[m/10];
        clockDigits[1] = digitImages[m%10];
        clockDigits[3] = digitImages[s/10];
        clockDigits[4] = digitWithDotImages[s%10];
        clockDigits[5] = digitImages[t%10];
    }

    private void setCurrentTime(){
        int[] gameTime = new int[3];
        gameTime = this.gameTimer.getGameTime();
        if(gameTime[0] >= 10){
            this.ledColor = ColorUtil.rgb(255,0,0);
            this.tenthColor = ColorUtil.rgb(220,20,60);
        }
        setTime(gameTime[0], gameTime[1], gameTime[2]);
    }

    public void resetResetElapsedTime(){
        for(int i = 0; i < numDigitsShowing; i++)
            clockDigits[i] = digitImages[i];

        clockDigits[MS_COLON_IDX] = colonImage;

        clockDigits[DIGIT_WITH_DOT_IDX] = digitWithDotImages[0];
        this.ledColor = ColorUtil.CYAN;
        this.tenthColor = ColorUtil.BLUE;
        this.gameTimer.restartTimer();
    }

    public void startElapsedTime(){
        this.gameTimer.pauseTimer();
        getComponentForm().registerAnimated(this);
    }

    public void stopElapsedTime(){
        this.gameTimer.resumeTimer();
        getComponentForm().deregisterAnimated(this);
    }

    public void laidOut(){
        this.startElapsedTime();
    }

    public boolean animate(){
        setCurrentTime();
        return true;
    }

    protected Dimension calcPreferredSize(){
        return new Dimension(colonImage.getWidth()*numDigitsShowing,
                colonImage.getHeight());
    }

    public void paint(Graphics g){
        super.paint(g);
        final int COLOR_PAD = 1;

        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = numDigitsShowing*digitWidth;

        float scaleFactor = Math.min(
                getInnerHeight()/(float)digitHeight,
                getInnerWidth()/(float)clockWidth);

        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight  = (int)(scaleFactor*digitHeight);
        int displayClockWidth = displayDigitWidth*numDigitsShowing;
        int displayClockLightWidth = displayDigitWidth*(numDigitsShowing-1);

        int displayX = getX() + (getWidth()-displayClockWidth)/2;
        int displayY = getY() + (getHeight()-displayDigitHeight)/2;

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.setColor(ledColor);
        g.fillRect(displayX+COLOR_PAD,
                displayY+COLOR_PAD,
                displayClockLightWidth-COLOR_PAD*2,
                displayDigitHeight-COLOR_PAD*2
        );

        g.setColor(tenthColor);
        g.fillRect((displayX+displayClockLightWidth)+COLOR_PAD,
                displayY+COLOR_PAD, displayDigitWidth-COLOR_PAD*2,
                displayDigitHeight-COLOR_PAD*2);

        for(int digitIndex = 0;
            digitIndex < numDigitsShowing;
            digitIndex++){

            g.drawImage(
                    clockDigits[digitIndex],
                    displayX + digitIndex * displayDigitWidth,
                    displayY,
                    displayDigitWidth,
                    displayDigitHeight
            );
        }

    }
}
