package com.mygdx.Buttons;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by KevinJohn on 2/18/2015.
 */
public class androidCamera extends OrthographicCamera{

    public androidCamera(int height, int width) {
        super (width, height);
        this.position.x = width/2;
        this.position.y = height/2;
    }

}
