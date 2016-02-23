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
public class CanopyPiece extends Drawable{
    
    static float canopyPieceWidth;
    static float canopyPieceHeight;
    static float canopyPositionY;
    
    public CanopyPiece(boolean end)
    {
        if(!end)
            texture = TextureCache.canopyMid;//new Texture("canopypiece1.png");
        else
            texture = TextureCache.canopyEdge;//new Texture("canopyedge1.png");
        canopyPieceWidth = texture.getWidth();
        canopyPieceHeight = texture.getHeight();
        canopyPositionY = (float) (screen.heightbpx-0.5*canopyPieceHeight);
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        XrotationAnchor = 0;
        YrotationAnchor = 0;
        rotation = 0;
        xAugmented = 0;
        yAugmented = 0;
    }
    
}
