package com.mygdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by KevinJohn on 2/12/2015.
 * Helper Class ~ Creates a button
 */
public class buttonsHelper {

    public static TextureAtlas buttonAtlas;
    public static Button.ButtonStyle buttonStyle;
    public static Skin buttonSkin;

    public buttonsHelper (){
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons.pack"), false);
        buttonStyle = new Button.ButtonStyle();
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
    }

    public static Button.ButtonStyle createButtonStyle(String Off, String On, boolean inverse){
        if (inverse) {
            buttonStyle.up = buttonSkin.getDrawable(On);
            buttonStyle.down = buttonSkin.getDrawable(Off);
        }
        else {
            buttonStyle.pressedOffsetY = -4;
            buttonStyle.pressedOffsetX = -4;
            buttonStyle.up = buttonSkin.getDrawable(Off);
            buttonStyle.down = buttonSkin.getDrawable(On);
        }
        return buttonStyle;
    }

    public static Button createButton ( String Off, String On, boolean inverse) {
        createButtonStyle(Off, On, inverse);
        return new Button(buttonStyle);
    }

    /*
    public static void addClickListener ( Button button ) {
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("BUTTON PRESSED!");
                playerScore += 1;
                // super.clicked(event, x, y);
            }
        });
    }
    */

    public TextureAtlas getButtonAtlas() {
        return buttonAtlas;
    }

    // Disposes stuff
    public static void buttonDispose() {
        buttonAtlas.dispose();
        buttonSkin.dispose();
        buttonSkin.dispose();
    }

    public static Button.ButtonStyle getButtonStyle() {
        return buttonStyle;
    }



}
