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
public class MonkeyArm extends Drawable {
    public MonkeyHead head;
    
    public MonkeyArm()
    {
        texture = new Texture("arm.png");
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        x = - (((float)texture.getWidth())/2);
        y = 0;
    }
}
