/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Neidert
 */
public class MonkeyHead extends Drawable {
    
    public MonkeyHead()
    {
        texture = new Texture("monkey.png");
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        x = -(((float)texture.getWidth())/2);
        y = -(((float)texture.getHeight())/2);
    }
    
}
