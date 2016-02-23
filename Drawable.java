 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Neidert
 */
public abstract class Drawable {
    float x = 0;
    float y = 0;
    float width = 10;
    float height = 10;
    float xAugmented = 0;
    float yAugmented = 0;
    float alpha = 1;
    float rotation = 0;
    float XrotationAnchor = 0;
    float YrotationAnchor = 0;
    boolean flipX = false;
    boolean flipY = false;
    String name;
    ScreenData screen = ScreenData.instance();
    Texture texture;// = new Texture(name);
    
    public void draw(SpriteBatch batch)
    {
        batch.draw(texture, xAugmented*screen.widthratio - Game.camera, yAugmented*screen.heightratio, XrotationAnchor, YrotationAnchor, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
    }
}
