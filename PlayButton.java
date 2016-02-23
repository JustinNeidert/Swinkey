/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Neidert
 */
public class PlayButton extends Button {
    private float slideTime;
    private float distance;
    private long currentTime;
    private float destination;
    
    public PlayButton()
    {
        texture = new Texture("play.png");
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        x = (float) ((((float)screen.widthbpx)*.25)-(((float)texture.getWidth())/2));
        y = (float) ((((float)screen.heightbpx)*.25)-(((float)texture.getHeight())/2));
    }
    
    @Override
    public void onTouchUp(int x, int y, Game game)
    {
        super.onTouchUp(x, y, game);
        
        game.transitionToPlay();
    }
    
    
    @Override
    public void draw(SpriteBatch batch)
    {
        batch.draw(texture, xAugmented*screen.widthratio, yAugmented*screen.heightratio, XrotationAnchor, YrotationAnchor, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
        update();
    }
    
    public void update()
    {
        if(slideTime > 0)
        {
            slideTime -= System.currentTimeMillis() - currentTime;
            currentTime = System.currentTimeMillis();
            x = (float) (destination + Math.pow(slideTime, 2)*distance/1000000);
        }
    }
    
    public void startSlideIn()
    {
        y = height/(2*screen.heightratio);
        x = 5*screen.widthbpx - width/(2*screen.widthratio);
        
        slideTime = 1000.0f;
        destination = screen.widthbpx/2 - width/screen.widthratio;
        distance = destination - x;
        currentTime = System.currentTimeMillis();
    }
}
