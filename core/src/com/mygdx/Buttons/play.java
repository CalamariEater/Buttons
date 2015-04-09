package com.mygdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by KevinJohn on 2/18/2015.
 */
public class play implements Screen {

    final Buttons game;

    // ****** RUNNING ******
    private Stage stage = new Stage();
    private Texture backgroundTexture = new Texture(Gdx.files.internal("circuitBoardBackground.png"));
    private Table table = new Table();

    // Timer
    private double time = 10F;
    private Label.LabelStyle labelStyleTime = new Label.LabelStyle( buttonsHelper.getFont(), Color.RED );
    private Label labelTime = new Label( "Time: " + time, labelStyleTime );

    // Score
    public static int playerScore;

    private SpriteBatch batch = new SpriteBatch();
    private Label.LabelStyle labelStyle = new Label.LabelStyle( buttonsHelper.getFont(), Color.RED );
    private Label labelScore = new Label( "Score: " + playerScore, labelStyle);
    private Label labelHighscore = new Label( "Highscore: " + mainMenu.pref.getInteger("score", 0), labelStyle);


    // Buttons
    Button[] buttons = new Button[9];
    float buttonWidth = 212;
    float buttonHeight = 96;

    // Flags
    private boolean lvl2 = false;
    private boolean lvl3 = false;
    private boolean isPressed = false;
    private boolean isBadPressed = false;
    private boolean fluctuate = false;

    final Sound soundClick = Gdx.audio.newSound(Gdx.files.internal("button16.mp3"));

    // For timer output
    public DecimalFormat df = new DecimalFormat("##.##");

    // ****** PAUSED ******
    private Texture backgroundTexturePause = new Texture(Gdx.files.internal("beveledBackground.png"));
    private Stage stagePause = new Stage();
    private Table tablePause = new Table();

    // Label
    private Label.LabelStyle labelStyleLarge = new Label.LabelStyle( buttonsHelper.getFontLarge(), Color.RED );
    private Label pause = new Label("Paused", labelStyleLarge);

    // Muted
    public boolean muted = false;

    // ****** Visual Cues ****** TESTING

    /***************************************************************************************************************
     * PLAY START
     * @param it
     ***************************************************************************************************************/
    public play (final Buttons it) {

        PauseMenu();
        setFlags();

        // Reset current player score
        playerScore = 0;

        df.setRoundingMode(RoundingMode.DOWN);

        Buttons.gamestate = Buttons.GAMESTATE.RUNNING;

        this.game = it;

        // Set buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button(buttonsHelper.getButtonStyleGreen());
            System.out.println("Index: " + i);
        }

        // Set Stage
        stage.clear();
        stage.addActor(table);

        // Set Table
        table.defaults().pad(10);
        table.setFillParent(true);
        // table.debug();

        // Add start stuff
        table.add(labelScore); table.add(labelHighscore);
        table.row();
        table.add(labelTime);
        table.row();
        table.add(buttons[0]).size(buttonWidth, buttonHeight);  table.add(buttons[1]).size(buttonWidth, buttonHeight); table.add(buttons[2]).size(buttonWidth, buttonHeight); table.row();

        // Set input for button
        Gdx.input.setInputProcessor(stage);
        addClickListener(buttons[0]); addClickListener(buttons[1]); addClickListener(buttons[2]);

        // Timer
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (Buttons.gamestate == Buttons.GAMESTATE.RUNNING) {
                    pickRandomButton();
                    // time -= 1D;
                    if (lvl2)
                        pickRandomButton();
                    if (lvl3)
                        pickRandomButton();
                    isPressed = false;
                    isBadPressed = false;
                    fluctuate = !fluctuate;
                }
            }
        }, 1F, 1F);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl20.glClearColor( 0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Checks
        lvlChange();
        isGameover();
        isHighscore();

        switch (Buttons.gamestate) {
            case RUNNING:

                Gdx.input.setInputProcessor(stage);

                time -= delta;

                drawBackground();
                setLabels();

                // TESTING PAUSE
                if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
                    Buttons.gamestate = Buttons.GAMESTATE.PAUSED;

                stage.draw();

                batch.begin();
                displayPlusOne();
                batch.end();

                break;

            case PAUSED:

                Gdx.input.setInputProcessor(stagePause);

                // Draw background
                stagePause.getBatch().begin();
                stagePause.getBatch().draw(backgroundTexturePause, 0, 0, (stagePause.getWidth()),
                        (stagePause.getHeight()));
                stagePause.getBatch().end();

                stagePause.draw();
                break;

            case GAMEOVER:
                gameOver();
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO: PAUSE ALL THE THINGS
        Buttons.gamestate = Buttons.GAMESTATE.PAUSED;
    }

    @Override
    public void resume() {
        // TODO: RESUME ALL THE THINGS
    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        Timer.instance().clear();
        stage.dispose();
        backgroundTexture.dispose();
        backgroundTexturePause.dispose();
        stagePause.dispose();
        soundClick.dispose();
        batch.dispose();
    }

    /*****************************************************************
     * Helper Functions
     *****************************************************************/
    public void addClickListener ( final Button button ) {
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isMute();
                // For visual cues
                isPressed = true;
                isBadPressed = false;

                System.out.println("+");

                // Score add
                playerScore += 1;

                addTime();

                // Disable button
                button.setTouchable(Touchable.disabled);
                button.setStyle(buttonsHelper.getButtonStyleGreenInverse());
                button.clearListeners();
            }
        });
    }

    public void addBadClickListener ( final Button button ) {
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isMute();

                // For visual cues

                isBadPressed = true;
                isPressed = false;

                System.out.println("-");

                time -= 1;

                // Disable button
                button.setTouchable(Touchable.disabled);
                button.setStyle(buttonsHelper.getButtonStyleRedInverse());
                button.clearListeners();
            }
        });
    }

    // Picks a random index in terms of the level
    public int randomNumber ( ) {
        int i;
        if ( lvl2 ) {
            i = Math.round(random(5));
        }
        else if ( lvl3 ) {
            i = Math.round(random(8));
        }
        else {
            i = Math.round(random(2));
        }
        System.out.println("random called: " + i);
        return i;
    }

    // Sets button touchable
    public void setTouchable(int index) {
        if ( 0 == buttons[index].getListeners().size ) {
            buttons[index].setTouchable(Touchable.enabled);
            buttons[index].setStyle(buttonsHelper.getButtonStyleGreen());
            addClickListener(buttons[index]);
            System.out.println("added a click listener");
        }

    }

    // Sets a bad button
    public void setBadButton (int index) {
        if ( 0 == buttons[index].getListeners().size ) {
            buttons[index].setTouchable(Touchable.enabled);
            buttons[index].setStyle(buttonsHelper.getButtonStyleRed());
            addBadClickListener(buttons[index]);
            System.out.println("added a BAD click listener");
        }
    }

    // Picks random button and sets either bad button or good button.
    public void pickRandomButton () {
        int i = randomNumber();
        if ( lvl3 && randomNumber() > 4.5 )
            setTouchable(i);
        else if ( lvl2 && randomNumber() > 2.5 )
            setTouchable(i);
        else if ( !lvl2 && !lvl3 && randomNumber() > 1 )
            setTouchable(i);
        else setBadButton(i);
    }

    public void addTime () {
        if ( lvl2 )
            time += 1;
        else
            time += 2;
    }

    // Checks if go to next level
    public void lvlChange () {
        if (playerScore >= 5 && !lvl2) {
            table.add(buttons[3]).size(buttonWidth, buttonHeight); table.add(buttons[4]).size(buttonWidth, buttonHeight); table.add(buttons[5]).size(buttonWidth, buttonHeight); table.row();
            addClickListener(buttons[3]); addClickListener(buttons[4]); addClickListener(buttons[5]);
            lvl2 = true;
        }

        if (playerScore >= 10 && !lvl3) {
            table.add(buttons[6]).size(buttonWidth, buttonHeight);  table.add(buttons[7]).size(buttonWidth, buttonHeight);  table.add(buttons[8]).size(buttonWidth, buttonHeight); table.row();
            addClickListener(buttons[6]); addClickListener(buttons[7]); addClickListener(buttons[8]);
            lvl3 = true;
        }
    }

    // Checks if its game over; if it is, sets gamestate to game over
    public void isGameover () {
        if ( time <= 0 ) {
            Buttons.gamestate = Buttons.GAMESTATE.GAMEOVER;
        }
    }

    public void isHighscore () {
        if ( mainMenu.pref.getInteger("score", 0) < playerScore) {
            mainMenu.pref.putInteger( "score", playerScore );
            mainMenu.pref.flush();
        }
    }

    public void isMute () {
        if (!muted) {
            soundClick.play();
        }
    }

    // Displays boot leg visual plus ones
    public void displayPlusOne () {
        if ( isPressed ) {
            Vector2 loc = getStageLocation(labelTime);
            Vector2 loc2 = getStageLocation(labelScore);
            buttonsHelper.getFont().setColor(Color.YELLOW);
            buttonsHelper.getFont().draw(batch, "+1", loc.x, loc.y);
            buttonsHelper.getFont().draw(batch, "+1", loc2.x, loc2.y);
        }
        if ( isBadPressed ) {
            Vector2 loc = getStageLocation(labelTime);
            buttonsHelper.getFont().setColor(Color.RED);
            buttonsHelper.getFont().draw(batch, "-1", loc.x, loc.y);
        }
    }

    // Gets bottom left pos of actor passed
    public static Vector2 getStageLocation(Actor actor) {
        return actor.localToStageCoordinates(new Vector2(actor.getWidth() + 3 , actor.getHeight()));
    }

    public void drawBackground () {
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, stage.getWidth(),
                stage.getHeight());
        stage.getBatch().end();
    }

    public void setLabels () {
        labelScore.setText("Score: " + playerScore);
        labelHighscore.setText("Highscore: " + mainMenu.pref.getInteger("score", 0));

        // Fluctuate between yellow and red to warn player of low time.
        if (time < 5F) {

            labelStyleTime.fontColor = Color.YELLOW;
            labelTime.setText( "Time: " + (df.format(time)));

            if (fluctuate) {
                labelStyleTime.fontColor = Color.RED;
                labelTime.setText( "Time: " + (df.format(time)));
            }
        }
        else {
            labelStyleTime.fontColor = Color.RED;
            labelTime.setText( "Time: " + (df.format(time)));
        }
    }

    /************************************************************************************************************
     * Creates pause
     ************************************************************************************************************/
    public void PauseMenu () {
        // Pause Screen
        tablePause.setFillParent(true);
        tablePause.defaults().pad(10);
        // table.debug();

        // Create button using buttonsHelper
        Button buttonResume = new Button(buttonsHelper.getButtonStyleGray());
        Button buttonQuit = new Button(buttonsHelper.getButtonStyleGray());
        final Button buttonMute = new Button(buttonsHelper.getButtonStyleMute());

        // Assign stuff
        tablePause.add(pause);
        tablePause.row();
        tablePause.add(buttonResume).size(buttonWidth, buttonHeight);
        tablePause.row();
        tablePause.add(buttonQuit).size(buttonWidth, buttonHeight);
        tablePause.row();
        tablePause.add(labelHighscore);
        tablePause.row();
        tablePause.add(buttonMute);

        stagePause.addActor(tablePause);


        // Set input for button
        buttonResume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isMute();
                Buttons.gamestate = Buttons.GAMESTATE.RUNNING;
            }
        });

        buttonQuit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isMute();
                System.out.println("Quit button PRESSED");
                Gdx.app.exit();
            }
        });

        buttonMute.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!muted) {
                    muted = true;
                    buttonMute.setStyle(buttonsHelper.getButtonStyleMuteInverse());
                }
                else {
                    muted = false;
                    buttonMute.setStyle(buttonsHelper.getButtonStyleMute());
                }
            }
        });
    }

    public void gameOver () {
        dispose();
        game.setScreen(new gameOver(game));
    }

    public void setFlags () {
        lvl2 = false;
        lvl3 = false;
        isBadPressed = false;
        isPressed = false;
        fluctuate = false;
    }
    // TODO: Maybe not. Add BETTER visual effects?

    /* Pseudo: For Add rings around buttons
        When listener added
            increment corresponding listener (badListener or goodListener)
            upon press -> clear badListener/goodListener

        Separate
            in a new method: have said method calculate bounds of a cell

            public void getCellBounds
            table.getCell(anybutton).getX();
            table.getCell(anybutton).getY();
            ????

            When resize recalculate

        In render
            using bounds of the cell
                display rings based upon number of badListeners or goodListeners

     */
}
