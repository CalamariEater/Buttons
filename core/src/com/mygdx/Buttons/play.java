package com.mygdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.mygdx.Buttons.buttonsHelper.createButtonStyle;

/**
 * Created by KevinJohn on 2/18/2015.
 */
public class play implements Screen {
    final Buttons game;

    private Stage stage = new Stage();
    private Texture backgroundTexture = new Texture(Gdx.files.internal("circuitBoardBackground.png"));
    private Table table = new Table();

    // ::TIMER::
    private double time = 10;

    // Score
    private int playerScore = 0;
    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont(Gdx.files.internal("digital.fnt"));

    private Label.LabelStyle labelStyle = new Label.LabelStyle( font, Color.RED );
    private Label labelScore = new Label( "Score: " + playerScore, labelStyle);
    private Label labelTime = new Label( "Time: " + time, labelStyle );

    // Buttons
    Button[] buttons = new Button[9];

    // Flags
    private boolean lvl2 = false;
    private boolean lvl3 = false;
    private boolean isPressed = false;

    public play (final Buttons it) {
        // TODO: Possibly. Format using Json files?
        this.game = it;

        // Font
        // font.setScale(2);

        // Set buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = buttonsHelper.createButton("GreenButtonOff", "GreenButtonOn", false);
            System.out.println("Index: " + i);
        }

        // Set Stage
        stage.clear();
        stage.addActor(table);

        // Set Table
        table.defaults().pad(10);
        table.setFillParent(true);
        table.debug();

        // Add start stuff
        table.add(labelScore);
        table.row();
        table.add(labelTime);
        table.row();
        table.add(buttons[0]);  table.add(buttons[1]); table.add(buttons[2]); table.row();

        // Set input for button
        Gdx.input.setInputProcessor(stage);
        addClickListener(buttons[0]); addClickListener(buttons[1]); addClickListener(buttons[2]);

        // TODO: Implement better timer ~ As time goes by
        // ::TIMER::
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                pickRandomButton();
                time -= 1D;
                if (lvl2 == true)
                    pickRandomButton();
                if (lvl3 == true)
                    pickRandomButton();
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

        // Draw background
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, stage.getWidth(),
                stage.getHeight());
        stage.getBatch().end();

        batch.begin();

        // Draw score
        // font.draw(batch, "Score: " + playerScore, 0, Gdx.graphics.getHeight() - 35F);
        // scoreEffect();
        labelScore.setText("Score: " + playerScore);
        labelTime.setText( "Time: " + time);
        // ::TIME::
        // font.draw(batch, "Time: " + (time), 0, Gdx.graphics.getHeight() - 60F);
        // timeEffect();

        batch.end();

        // Lvl check
        if (playerScore >= 5 && lvl2 == false) {
            table.add(buttons[3]); table.add(buttons[4]); table.add(buttons[5]); table.row();
            addClickListener(buttons[3]); addClickListener(buttons[4]); addClickListener(buttons[5]);
            lvl2 = true;
        }

        if (playerScore >= 10 && lvl3 == false) {
            table.add(buttons[6]);  table.add(buttons[7]);  table.add(buttons[8]); table.row();
            addClickListener(buttons[6]); addClickListener(buttons[7]); addClickListener(buttons[8]);
            lvl3 = true;
        }

        if ( time <= 0 ) {
            game.setScreen(new gameOver(game));
            dispose();
        }

        isPressed = false;

        // table.layout();

        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Timer.instance().clear();
        stage.dispose();
        backgroundTexture.dispose();
    }

    // TODO: Maybe. Add "bad button"?
    // Helper functions
    public void addClickListener ( final Button button ) {
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                isPressed = true;
                System.out.println("+");

                // Score add
                playerScore += 1;

                addTime();

                // Disable button
                button.setTouchable(Touchable.disabled);
                button.setStyle(createButtonStyle("GreenButtonOff", "GreenButtonOn", true));
            }
        });
    }

    // Picks a random button
    public void pickRandomButton () {
        int i = Math.round(random(7));
        System.out.println("pickRandomButton called: " + i);

        // Sets button touchable
        buttons[i].setTouchable(Touchable.enabled);

        buttons[i].setStyle(createButtonStyle("GreenButtonOff", "GreenButtonOn", false));
    }

    public void addTime () {
        if ( lvl2 && lvl3 )
            time += 1;
        else
            time += 2;
    }

    // TODO: Maybe not. Add visual effects?
    public void timeEffect () {
        if ( isPressed == true && lvl3 == false && lvl2 == true) {
            font.draw(batch, "+1", 150, Gdx.graphics.getHeight() - 60F);
        }
        else if ( isPressed == true && lvl3 == true ) {
            font.draw(batch, "+.05", 150, Gdx.graphics.getHeight() - 60F);
        }
        else if ( isPressed == true ) {
            font.draw(batch, "+2", 150, Gdx.graphics.getHeight() - 60F);
        }
    }

    public void scoreEffect () {
        if ( isPressed == true ) {
            font.draw(batch, "+1", 150, Gdx.graphics.getHeight() - 40F);
        }
    }
}
