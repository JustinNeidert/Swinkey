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
public class VinePiece extends Drawable {
    public VinePiece(int index)
    {
        texture = new Texture("vine.png");
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        x = -(((float)texture.getWidth())/2);
        y = index*texture.getHeight();
    }
}
