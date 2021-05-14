package org.csc133.a3;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a3.commands.*;
import org.csc133.a3.ui.GlassCockpit;
import org.csc133.a3.ui.MapView;

import java.lang.Runnable;

public class Game extends Form implements Runnable{
    private GameWorld gw;
    UITimer timer;
    private static final int TickTime = 20;
    private MapView gameView;
    private GlassCockpit gameCockpit;
    private AccelerateCommand accelerateCommand;
    private BrakeCommand brakeCommand;
    private AngleLeftCommand angleLeftCommand;
    private AngleRightCommand angleRightCommand;
    private ExitCommand exitCommand;
    private ChangeStrategiesCommand strategiesCommand;
    private BGSoundCommand bgCommand;
    private AboutCommand aboutCommand;
    private HelpCommand helpCommand;



    public Game(){
        gw = new GameWorld();

        timer = new UITimer(this);
        gameView = new MapView(gw);
        gameCockpit = new GlassCockpit(gw);

        this.getAllStyles().setBgTransparency(255);
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.CENTER, gameView);
        this.add(BorderLayout.NORTH, gameCockpit);

        accelerateCommand = new AccelerateCommand(this);
        brakeCommand = new BrakeCommand(this);
        angleLeftCommand = new AngleLeftCommand(this);
        angleRightCommand = new AngleRightCommand(this);
        exitCommand = new ExitCommand(this);
        strategiesCommand = new ChangeStrategiesCommand(this);
        bgCommand = new BGSoundCommand();
        aboutCommand = new AboutCommand(this);
        helpCommand = new HelpCommand(this);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new GridLayout(1,4));
        Button accelerateButton = new Button("Accelerate");
        accelerateButton.setCommand(accelerateCommand);
        Button brakeButton = new Button("Brake");
        brakeButton.setCommand(brakeCommand);
        Button angleLeftButton = new Button("Angle Left");
        angleLeftButton.setCommand(angleLeftCommand);
        Button angleRightButton = new Button("Angle Right");
        angleRightButton.setCommand(angleRightCommand);
        buttonContainer.addComponent(accelerateButton);
        buttonContainer.addComponent(brakeButton);
        buttonContainer.addComponent(angleLeftButton);
        buttonContainer.addComponent(angleRightButton);

        Toolbar menuBar = new Toolbar();
        this.setToolbar(menuBar);
        menuBar.setTitle("SkyMail 3000");
        menuBar.addCommandToSideMenu(strategiesCommand);
        menuBar.addCommandToSideMenu(bgCommand);
        menuBar.addCommandToSideMenu(aboutCommand);
        menuBar.addCommandToSideMenu(helpCommand);
        menuBar.addCommandToSideMenu(exitCommand);


        this.add(BorderLayout.SOUTH, buttonContainer);

        this.show();

        new Thread(gw::initSound).start();

        gw.setDimensions(this.gameView.getWidth(), this.gameView.getHeight());

        gw.init();

        gameCockpit.update();
        gameView.update();

        play();
    }

    public void run(){
        gw.tick(TickTime);
        gameCockpit.update();
        gameView.update();
    }

    public void play(){
        gameCockpit.play();
        addKeyListener('a',accelerateCommand);
        addKeyListener('b',brakeCommand);
        addKeyListener('l',angleLeftCommand);
        addKeyListener('r',angleRightCommand);
        addKeyListener('x',exitCommand);
        timer.schedule(TickTime,true,this);
    }

    public void pause(){
        gameCockpit.pause();
        removeKeyListener('a',accelerateCommand);
        removeKeyListener('b',brakeCommand);
        removeKeyListener('l',angleLeftCommand);
        removeKeyListener('r',angleRightCommand);
        removeKeyListener('x',exitCommand);
        timer.cancel();
    }

    public GameWorld getGameWorld(){
        return this.gw;
    }

}
