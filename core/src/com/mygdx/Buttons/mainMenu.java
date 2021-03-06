package com.mygdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by KevinJohn on 2/18/2015.
 */
public class mainMenu implements Screen {

    // TODO: ASSET MANAGER ~ Maybe
    // Asset Manager
    // public static AssetManager manager = new AssetManager();

    final Buttons game;

    private Texture backgroundTexture = new Texture(Gdx.files.internal("beveledBackground.png"));
    private Stage stage = new Stage();
    private Table table = new Table();

    // Buttons
    public buttonsHelper buttonsHelper = new buttonsHelper();
    private TextButton button = new TextButton("Play", com.mygdx.Buttons.buttonsHelper.getTextButtonStyleGray());
    private TextButton button2 = new TextButton("Quit", com.mygdx.Buttons.buttonsHelper.getTextButtonStyleGray());
    float buttonWidth = 318;
    float buttonHeight = 144;

    // Labels
    private Label.LabelStyle labelStyle = new Label.LabelStyle( com.mygdx.Buttons.buttonsHelper.getFont(), Color.RED );
    private Label.LabelStyle labelStyleLarge = new Label.LabelStyle( com.mygdx.Buttons.buttonsHelper.getFontLarge(), Color.RED );
    private Label title = new Label("Buttons", labelStyleLarge);
    private Label highscoreLabel = new Label("Highscore: " + com.mygdx.Buttons.buttonsHelper.getPref().getInteger("score", 0), labelStyle );

    private boolean muted = com.mygdx.Buttons.buttonsHelper.getPref().getBoolean("mute", false);

    public mainMenu (final Buttons it) {
        game = it;
    }

    @Override
    public void show() {

        // Set input for button
        Gdx.input.setInputProcessor(stage);

        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                com.mygdx.Buttons.buttonsHelper.isMute(muted);
                dispose();
                game.setScreen(new play(game));
                // super.clicked(event, x, y);
            }
        });

        button2.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                com.mygdx.Buttons.buttonsHelper.isMute(muted);
                Gdx.app.exit();
                // super.clicked(event, x, y);

            }
        });

        // Set table up
        table.setFillParent(true);
        table.defaults().pad(10);
        // table.debug();

        // Add objects to table
        table.add(title);
        table.row();
        table.add(button).size(buttonWidth, buttonHeight);
        table.row();
        table.add(button2).size(buttonWidth, buttonHeight);
        table.row();
        table.add(highscoreLabel);

        // Add table to stage
        // stage.getViewport().setWorldSize(Gdx.graphics.getWidth() - 50F, Gdx.graphics.getHeight() - 50F);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {

        Gdx.gl20.glClearColor( 0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawBackground();

        // stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // TODO: Resize bug fixed?

        stage.getViewport().update(width, height, true);
        // table.invalidateHierarchy();
        // button.setSize( button.getPrefWidth()*0.5F, button.getPrefHeight()*0.5F);
        // button2.setSize( button.getPrefWidth()*0.5F, button.getPrefHeight()*0.5F);
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
        // TODO: HIDE ALL THE THINGS
        backgroundTexture.dispose();
        stage.dispose();
    }

    public void drawBackground () {
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, stage.getWidth(),
                stage.getHeight());
        stage.getBatch().end();
    }
}
