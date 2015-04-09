package com.mygdx.Buttons;

import com.badlogic.gdx.Game;

public class Buttons extends Game {

    // Idk how to work git
    // TODO: Error thingys
    // TODO: Code clean up. Seriously. Its bothering me

    public static enum GAMESTATE {RUNNING, PAUSED, GAMEOVER}
    public static GAMESTATE gamestate;


    @Override
    public void create() {

        this.setScreen(new mainMenu(this));
        // System.out.println("Main Menu Created");

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        if (gamestate == GAMESTATE.RUNNING)
            gamestate = GAMESTATE.PAUSED;
            System.out.println("Gamestate: PAUZED");
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
