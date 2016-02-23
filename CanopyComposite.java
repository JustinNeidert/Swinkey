/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

/**
 *
 * @author Neidert
 */
public class CanopyComposite extends CompositeDrawable {
    float canopiesPerScreen;
    
    public CanopyComposite()
    {
        canopiesPerScreen = 2.0f;
        CanopyPiece canopy1 = new CanopyPiece(false);//needed to initialize static variables before accessing
        x = 2*screen.widthbpx;
        y = CanopyPiece.canopyPositionY;
        CanopyPiece canopyStart = new CanopyPiece(true);
        children.add(canopyStart);//edge
        for(int i = 1; i < screen.widthbpx/(canopiesPerScreen*CanopyPiece.canopyPieceWidth) - 1; i++)
        {
            children.add(new CanopyPiece(false));
            children.get(i).x = (i+1)*CanopyPiece.canopyPieceWidth;
            if(i%2 == 0)
                children.get(i).flipX = true;
        }
        CanopyPiece canopyEnd = new CanopyPiece(true);
        canopyEnd.x = (float) (((float)screen.widthbpx)/canopiesPerScreen);
        canopyEnd.flipX = true;
        children.add(canopyEnd);//edge
        
    }
    
    public boolean containsX(float X)
    {
        float buff = 2.5f * screen.widthratio;
        if((x*screen.widthratio + buff - Game.camera) <= X && X <= (x*screen.widthratio + (children.size() + 2) * children.get(1).width - buff - Game.camera))
        {
            return true;
        }
        return false;
    }
}
