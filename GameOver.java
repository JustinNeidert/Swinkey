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
 * @author JNeidert
 */
public class GameOver extends Drawable {
    private float slideTime;
    private float distance;
    private long currentTime;
    private float destination;
    
    public GameOver()
    {
        texture = new Texture("gameover.png");
        height = texture.getHeight()*screen.heightratio;
        width = texture.getWidth()*screen.widthratio;
        y = 5*height/screen.heightratio + screen.heightbpx;
        x = screen.widthbpx/2 - width/(2*screen.widthratio);
        slideTime = 1000.0f;
        destination = screen.heightbpx - 1.0f*height/screen.widthratio;
        distance = destination - y;
        currentTime = System.currentTimeMillis();
    }
    
    @Override
    public void draw(SpriteBatch batch)
    {
        update();
        batch.draw(texture, xAugmented*screen.widthratio, yAugmented*screen.heightratio, XrotationAnchor, YrotationAnchor, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
    }
    
    public void update()
    {
        if(slideTime > 0)
        {
            slideTime -= System.currentTimeMillis() - currentTime;
            currentTime = System.currentTimeMillis();
            y = (float) (destination - Math.pow(slideTime, 2)*distance/1000000);
        }
    }
    
}
