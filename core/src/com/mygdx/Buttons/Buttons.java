package com.mygdx.Buttons;

import com.badlogic.gdx.Game;

public class Buttons extends Game {

    // Idk how to work git
    // TODO: Error thingys

    // TODO: Code clean up. Seriously. Its bothering me


    @Override
    public void create() {

        this.setScreen(new mainMenu(this));
        System.out.println("Main Menu Created");

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

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
