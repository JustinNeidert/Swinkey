/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Neidert
 */
public abstract class Button extends Drawable {
    boolean pressed;
    public void onScreenTouch(float x, float y)
    {
        if(containsPoint(x, y))
        {
        }
    }
    
    public void onTouchDown(int x, int y)
    {
        System.out.println(x + " " + y + " down");
        pressed = true;
        lower();
    }
    
    public void onTouchUp(int x, int y, Game game)
    {
        pressed = false;
        System.out.println(x + " " + y + " up");
        raise();
    }
    
    public void onTouchMoved(int x, int y)
    {
        System.out.println(x + " " + y + " move");
    }
    
    public boolean containsPoint(float x, float y)
    {
        return (x > this.xAugmented*screen.widthratio && x < (this.xAugmented*screen.widthratio+this.width) && y > (screen.heightpx - (this.yAugmented*screen.heightratio+this.height)) && y < (screen.heightpx-this.yAugmented*screen.heightratio));
    }
    
    public void lower()
    {
        y--;
    }
    
    public void raise()
    {
        y++;
    }
}
