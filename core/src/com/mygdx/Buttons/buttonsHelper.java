package com.mygdx.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by KevinJohn on 2/12/2015.
 * Handles Resources
 */
public class buttonsHelper {

    // TODO: Possibly. Format using Json files?
    // TODO: Make button press down ezier to see

    public TextureAtlas buttonAtlas;
    public Skin buttonSkin;
    private static Preferences pref = Gdx.app.getPreferences("My Preferences");

    private static BitmapFont font = new BitmapFont(Gdx.files.internal("digital.fnt"));
    private static BitmapFont fontLarge = new BitmapFont(Gdx.files.internal("digital.fnt"));

    private static Button.ButtonStyle buttonStyleGreen = new Button.ButtonStyle();
    private static Button.ButtonStyle buttonStyleGreenInverse = new Button.ButtonStyle();
    private static Button.ButtonStyle buttonStyleRed = new Button.ButtonStyle();
    private static Button.ButtonStyle buttonStyleRedInverse = new Button.ButtonStyle();
    private static Button.ButtonStyle buttonStyleGray = new Button.ButtonStyle();
    private static Button.ButtonStyle buttonStyleMute = new Button.ButtonStyle();
    private static Button.ButtonStyle buttonStyleMuteInverse = new Button.ButtonStyle();
    private static TextButton.TextButtonStyle textButtonStyleGray = new TextButton.TextButtonStyle();

    final private static Sound soundClick = Gdx.audio.newSound(Gdx.files.internal("button16.mp3"));

    public buttonsHelper (){
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons.pack"));
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
        createStyles();
    }

    public void createStyles () {
        // Create buttonStyleGreen
        buttonStyleGreen.up = buttonSkin.getDrawable("GreenButtonOff");
        buttonStyleGreen.down = buttonSkin.getDrawable("GreenButtonOn");
        // buttonStyleGreen.pressedOffsetY = -4;

        // Create buttonStyleGreenInverse
        buttonStyleGreenInverse.up = buttonSkin.getDrawable("GreenButtonOn");
        buttonStyleGreenInverse.down = buttonSkin.getDrawable("GreenButtonOff");

        // Create buttonStyleRed
        buttonStyleRed.up = buttonSkin.getDrawable("RedButtonOff");
        buttonStyleRed.down = buttonSkin.getDrawable("RedButtonOn");

        // Create buttonStyleRedInverse
        buttonStyleRedInverse.up = buttonSkin.getDrawable("RedButtonOn");
        buttonStyleRedInverse.down = buttonSkin.getDrawable("RedButtonOff");

        // Create buttonStyleGray
        buttonStyleGray.up = buttonSkin.getDrawable("GrayButtonOff");
        buttonStyleGray.down = buttonSkin.getDrawable("GrayButtonOn");

        // Create buttonStyleMute
        buttonStyleMute.up = buttonSkin.getDrawable("MuteButtonOff");
        buttonStyleMute.down = buttonSkin.getDrawable("MuteButtonOn");

        // Create buttonStyleMuteInverse
        buttonStyleMuteInverse.up = buttonSkin.getDrawable("MuteButtonOn");
        buttonStyleMuteInverse.down = buttonSkin.getDrawable("MuteButtonOff");

        // Create textButtonStyleGray
        textButtonStyleGray.up = buttonSkin.getDrawable("GrayButtonOff");
        textButtonStyleGray.down = buttonSkin.getDrawable("GrayButtonOn");
        textButtonStyleGray.font = font;
        // textButtonStyleGray.downFontColor = Color.YELLOW;
        // textButtonStyleGray.overFontColor = Color.WHITE;
        textButtonStyleGray.pressedOffsetY = -10;

        // Scale fontLarge
        fontLarge.setScale(2);
    }

    public static Button.ButtonStyle getButtonStyleGreen() { return buttonStyleGreen; }

    public static Button.ButtonStyle getButtonStyleGreenInverse() { return buttonStyleGreenInverse; }

    public static Button.ButtonStyle getButtonStyleRed() { return buttonStyleRed; }

    public static Button.ButtonStyle getButtonStyleRedInverse() { return buttonStyleRedInverse; }

    public static Button.ButtonStyle getButtonStyleGray() { return buttonStyleGray; }

    public static Button.ButtonStyle getButtonStyleMute() { return buttonStyleMute; }

    public static Button.ButtonStyle getButtonStyleMuteInverse() { return buttonStyleMuteInverse; }

    public static TextButton.TextButtonStyle getTextButtonStyleGray() { return textButtonStyleGray; }

    public static BitmapFont getFont() { return font; }

    public static BitmapFont getFontLarge() { return fontLarge; }

    public static Sound getSoundClick() { return soundClick; }

    public static Preferences getPref() { return pref; }

    /*
    public static Button.ButtonStyle setButtonStyle(String Off, String On){
        buttonStyle.up = buttonSkin.getDrawable(Off);
        buttonStyle.down = buttonSkin.getDrawable(On);
        return buttonStyle;
    }
    */

    /*
    public static Button createButton ( String Off, String On) {

        return new Button(createButtonStyle(Off, On);
    }
    */

    /*
    public static Button.ButtonStyle createButtonStyle (String Off, String On) {
        buttonStyle = new Button.ButtonStyle();

        buttonStyle.pressedOffsetY = -4;
        buttonStyle.pressedOffsetX = -4;
        return setButtonStyle(Off, On);
    }
    */

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

    public void buttonDispose() {
        this.buttonAtlas.dispose();
    }
    }
