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
public class CanopyMain extends CompositeDrawable{
    
    public CanopyMain()
    {
        CanopyPiece canopy1 = new CanopyPiece(false);//needed to initialize static variables before accessing
        x = 0;
        y = CanopyPiece.canopyPositionY;
        for(int i = 0; i < screen.widthbpx/CanopyPiece.canopyPieceWidth + 1; i++)
        {
            children.add(new CanopyPiece(false));
            children.get(i).x = i*CanopyPiece.canopyPieceWidth;
            if(i%2 == 1)
                children.get(i).flipX = true;
        }
    }
}
