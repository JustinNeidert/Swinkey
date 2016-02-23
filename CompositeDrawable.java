/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 *
 * @author Neidert
 */
public abstract class CompositeDrawable extends Drawable {
    public ArrayList<Drawable> children = new ArrayList<Drawable>();
    
    @Override
    public void draw(SpriteBatch batch)
    {
        for(Drawable child : children)
        {
            augmentPosition(child);
            child.draw(batch);
        }
    }
    
    public void augmentPosition(Drawable child)
    {
        child.xAugmented = child.x+xAugmented;
        child.yAugmented = child.y+yAugmented;
        
        child.rotation = rotation;
        child.XrotationAnchor = XrotationAnchor - child.x*screen.widthratio;
        child.YrotationAnchor = YrotationAnchor - child.y*screen.heightratio;
    }
}
