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
public class Logo extends Drawable {
    
    static float logoWidth;
    static float logoHeight;
    
    public Logo()
    {
        texture = new Texture("logo.png");
        logoWidth = 72.0f;
        logoHeight = 26.0f;
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        XrotationAnchor = 0;
        YrotationAnchor = 0;
        rotation = 0;
        xAugmented = 0;
        yAugmented = 0;
        x = (((float)screen.widthbpx)-logoWidth)/2;
        y = (((float)screen.heightbpx)-logoHeight)*0.75f;
    }
    
}
