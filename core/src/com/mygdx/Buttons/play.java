package com.mygdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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
    private Texture texture = new Texture(Gdx.files.internal("circutBoardBackground.png"));
    private Table table = new Table();

    // Score
    private SpriteBatch batch = new SpriteBatch();
    private int playerScore = 0;
    private BitmapFont font = new BitmapFont();

    // ::TIMER::
    private int time = 10;

    // Buttons
    Button[] buttons = new Button[9];

    /*
    Button button = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button2 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button3 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button4 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button5 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button6 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button7 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button8 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    Button button9 = buttonsHelper.createButton("ButtonOnTest", "ButtonOffTest");
    */

    // Flags
    private boolean lvl2 = false;
    private boolean lvl3 = false;

    public play (final Buttons it) {
        this.game = it;

        // Font
        font.setScale(2);

        // Set buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = buttonsHelper.createButton("GreenButtonOff", "GreenButtonOn", false);
            System.out.println("Index: " + i);
        }

        // TESTING::Button Change style...

        // Set Stage
        stage.clear();
        stage.addActor(table);

        // Set Table
        table.defaults().pad(10);
        table.setFillParent(true);
        // table.debug();

        // Add start stuff
        table.add(buttons[0]);  table.add(buttons[1]); table.add(buttons[2]); table.row();
        // table.add(button4); table.add(button5); table.add(button6); table.row();
        // table.add(button7); table.add(button8); table.add(button9); table.row();

        // Set input for button
        Gdx.input.setInputProcessor(stage);
        addClickListener(buttons[0]); addClickListener(buttons[1]); addClickListener(buttons[2]);

        // ::TIMER::
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                pickRandomButton(buttons);
                time -= 1;
                if (lvl2 == true)
                    pickRandomButton(buttons);
                if (lvl3 == true)
                    pickRandomButton(buttons);
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

        stage.getBatch().begin();
        stage.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        stage.getBatch().end();

        batch.begin();

        // Draw score
        font.draw(batch, "Score: " + playerScore, 0, Gdx.graphics.getHeight() - 40F);

        // ::TIME::
        font.draw(batch, "Time: " + (time), 0, Gdx.graphics.getHeight() - 60F);

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
        texture.dispose();
    }

    // Helper functions
    public void addClickListener ( final Button button ) {
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("BUTTON PRESSED!");
                playerScore += 1;
                time += 1;
                button.setTouchable(Touchable.disabled);
                button.setStyle(createButtonStyle("GreenButtonOff", "GreenButtonOn", true));
                // super.clicked(event, x, y);

                // pickRandomButton(buttons);
            }
        });
    }

    public void pickRandomButton ( Button[] buttons ) {
        int i = Math.round(random(7));
        System.out.println("pickRandomButton called: " + i);

        // Sets button touchable
        buttons[i].setTouchable(Touchable.enabled);

        buttons[i].setStyle(createButtonStyle("GreenButtonOff", "GreenButtonOn", false));
    }


}
