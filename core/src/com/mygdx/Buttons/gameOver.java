package com.mygdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.mygdx.Buttons.play.playerScore;

/**
 * Created by KevinJohn on 2/18/2015.
 */
public class gameOver implements Screen {

    // TODO: LOCATE MEMORY BUG
    // TODO: MAKE BUTTONS BIGGERz

    final Buttons game;

    private Texture backgroundTexture = new Texture(Gdx.files.internal("beveledBackground.png"));
    private Stage stage = new Stage();
    private Table table = new Table();

    // Label
    private BitmapFont font = new BitmapFont(Gdx.files.internal("digital.fnt"));
    private Label.LabelStyle labelStyle = new Label.LabelStyle( font, Color.RED );
    // private Skin skin = new Skin(Gdx.files.internal("SkinTest.json"));
    private Label gameOver = new Label("Game Over", labelStyle);
    private Label labelHighscore = new Label( "Highscore: " + mainMenu.pref.getInteger("score", 0), labelStyle);
    private Label labelScore = new Label( "Score: " + playerScore, labelStyle);


    public gameOver (final Buttons it) {
        game = it;
    }

    @Override
    public void show() {

        final Sound soundClick = Gdx.audio.newSound(Gdx.files.internal("button16.mp3"));

        // Set table up
        table.setFillParent(true);
        table.defaults().pad(10);
        // table.debug();

        // Create button using buttonsHelper
        Button button = buttonsHelper.createButton("GrayButtonOff", "GrayButtonOn", false);
        Button button2 = buttonsHelper.createButton("GrayButtonOff", "GrayButtonOn", false);

        // Assign stuff
        table.add(gameOver);
        table.row();
        table.add(button);
        table.row();
        table.add(button2);
        table.row();
        table.add(labelScore);
        table.row();
        table.add(labelHighscore);

        stage.addActor(table);


        // Set input for button
        Gdx.input.setInputProcessor(stage);
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                soundClick.play();
                System.out.println("BUTTON PRESSED!");
                dispose();
                game.setScreen(new play(game));
                // super.clicked(event, x, y);

            }
        });

        button2.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                soundClick.play();
                System.out.println("Quit button test PRESSED");
                Gdx.app.exit();
                // super.clicked(event, x, y);

            }
        });



    }

    @Override
    public void render(float delta) {

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor( 0.0F, 0.0F, 0.0F, 0.0F);

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, stage.getWidth(),
                stage.getHeight());
        stage.getBatch().end();


        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        backgroundTexture.dispose();
        stage.dispose();
        font.dispose();
    }
}
