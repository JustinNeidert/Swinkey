/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author JNeidert
 */
public class Number extends Drawable{
    public Number(int digit, boolean big)
    {
        if(big)
        {
            texture = TextureCache.bigNumbers[digit];
        }
        else
        {
            texture = TextureCache.smallNumbers[digit];
        }
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        y = -texture.getHeight()/2;
    }
    
    @Override
    public void draw(SpriteBatch batch)
    {
        batch.draw(texture, xAugmented*screen.widthratio, yAugmented*screen.heightratio, XrotationAnchor, YrotationAnchor, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
    }
}
