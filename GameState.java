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
public abstract class GameState {
    
    public ArrayList<Drawable> entities = new ArrayList<Drawable>();
    public ArrayList<Button> buttons = new ArrayList<Button>();
    public ArrayList<CompositeDrawable> canopies = new ArrayList<CompositeDrawable>();
    public Player player;
    public Game game;
    
    public void draw(SpriteBatch batch)
    {
        for(Drawable entity : entities)
        {
            entity.draw(batch);
            augmentPosition(entity);
        }
    }
    
    public void touchDown(int x, int y)
    {
        
    }
    
    public void touchUp(int x, int y)
    {
        
    }
    
    public void touchMoved(int x, int y)
    {
        
    }
    
    public void augmentPosition(Drawable child)
    {
        child.xAugmented = child.x;
        child.yAugmented = child.y;
    }
    
    public void startSlideOut()
    {
        
    }
}
