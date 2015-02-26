package com.mygdx.Buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by KevinJohn on 2/18/2015.
 */
public class gameOver implements Screen {

    final Buttons game;

    private Texture backgroundTexture = new Texture(Gdx.files.internal("whiteBackground.png"));
    private Stage stage = new Stage();
    private Table table = new Table();

    // Label
    // private Skin skin = new Skin();
    // private Label title = new Label("Buttons", skin);


    public gameOver (final Buttons it) {
        game = it;
    }


    @Override
    public void show() {

        // Set table up
        table.setFillParent(true);
        table.defaults().pad(10);
        table.debug();

        // Create button using buttonsHelper
        Button button = buttonsHelper.createButton("ButtonOffTest", "ButtonOnTest", false);
        Button button2 = buttonsHelper.createButton("ButtonOffTest", "ButtonOnTest", false);

        // Assign stuff
        table.add(button);
        table.row();
        table.add(button2);
        stage.addActor(table);


        // Set input for button
        Gdx.input.setInputProcessor(stage);
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("BUTTON PRESSED!");
                game.setScreen(new play(game));
                // super.clicked(event, x, y);

            }
        });

        button2.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
        stage.getBatch().draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
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
        dispose();

    }

    @Override
    public void dispose() {
        // backgroundTexture.dispose();
        stage.dispose();
    }
}
