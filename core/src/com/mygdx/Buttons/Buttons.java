package com.mygdx.Buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Buttons extends Game {

    // TODO: Error thingys
    // private Game game;

    //private Button button;
    /*
    private Button.ButtonStyle buttonStyle;
    private TextureAtlas buttonAtlas;
    private Skin buttonSkin;
    */

    // private Table table;
    // private int score;

    // Background
    // private SpriteBatch backgroundBatch;
    // private Texture backgroundTexture;
    // private Sprite sprite;

    // private Camera camera;
    // private Viewport viewport;

    // Asset Manager ~ ::TO BE TESTED::
    //public static AssetManager manager = new AssetManager();

    @Override
    public void create() {

        // ::NEW SCREEN TESTING::
        this.setScreen(new mainMenu(this));
        System.out.println("Main Menu Created");

        // Setting viewport
        // camera = new OrthographicCamera();
        // camera.viewportHeight = 800;
        // camera.viewportWidth = 460;
        // camera.position.set(camera.viewportWidth * .5F, camera.viewportHeight * .5F, 0F);
        // viewport = new ScreenViewport(camera);
        // camera.update();

        // Creating background
        // backgroundBatch = new SpriteBatch();
        // backgroundTexture = new Texture(Gdx.files.internal("WhiteBackground.png"));
        // TextureRegion region = new TextureRegion(backgroundTexture, 0, 0, 800, 460);
        // sprite = new Sprite(backgroundTexture);
        // sprite.setSize(sprite.getWidth(),sprite.getHeight());



        // Creating table
        // table = new Table();
        // table.setFillParent(true);
        // table.debug();


        /*
        // Button ~ Setting texture
        buttonAtlas = new TextureAtlas(Gdx.files.internal("ButtonTest.pack"), false);
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonSkin.getDrawable("ButtonOffTest");
        buttonStyle.down = buttonSkin.getDrawable("ButtonOnTest");
        */

        // Create button using ButtonHelper
        // Button button = buttonsHelper.createButton("ButtonOffTest", "ButtonOnTest");

        // Assigning
        // table.add(button);
        // stage.addActor(table);

        // Make the button do stuff
        /*Gdx.input.setInputProcessor(stage);
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("BUTTON PRESSED!");
                score++;
                System.out.println(score);
                // super.clicked(event, x, y);

            }
        });
        */
        // table.pack();
    }

    @Override
    public void resize(int width, int height) {
        // stage.getViewport().update(width, height, true);
        // viewport.update(width, height, true);
    }

    @Override
    public void render() {
        super.render();

        /*
        Gdx.gl20.glClearColor( 0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // backgroundBatch.begin();
        // sprite.draw(backgroundBatch);
        // backgroundBatch.end();
        stage.getBatch().begin();
        stage.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth(),
               Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.draw();
        */
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        // buttonsHelper.buttonDispose();
        // stage.dispose();
        // texture.dispose();
        // stage.dispose();
    }
}
