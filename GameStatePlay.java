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
public class GameStatePlay extends GameState {
    ArrayList<Drawable> digits = new ArrayList<Drawable>();
    Score sc;
    Hold hold;
    GetReady getReady;
    boolean slidIn;
    
    public GameStatePlay(Game g)
    {
        CanopyComposite canopy1 = new CanopyComposite();
        canopy1.x = 0;
        player = new Player(0.1f, 30.0f, this);
        CanopyComposite canopy2 = new CanopyComposite();
        canopy2.x = (1.75f)*player.screen.widthbpx/canopy1.canopiesPerScreen;
        TreeTrunk tree = new TreeTrunk(canopy1);
        Game.score = 0;
        sc = new Score();
        
        entities.add(player);
        entities.add(canopy1);
        entities.add(0, tree);
        entities.add(sc);
        //entities.add(canopy2);
        
        canopies.add(canopy1);
        //canopies.add(canopy2);
        
        slidIn = true;
        hold = new Hold();
        entities.add(hold);
        getReady = new GetReady();
        entities.add(getReady);
        
        game = g;
    }
    
    @Override 
    public void touchDown(int x, int y)
    {
        player.screenTouch(x, y);
    }
    
    @Override
    public void touchUp(int x, int y)
    {
        player.release();
    }
    
    @Override
    public void draw(SpriteBatch batch)
    {
        for(CompositeDrawable cd : player.canopiesToRemove)
        {
            entities.remove(cd);
        }
        for(Drawable d : player.entitiesToAdd)
        {
            if(d.getClass().equals(TreeTrunk.class))
                entities.add(0, d);
            else
                entities.add(d);
        }
        player.entitiesToAdd.clear();
        player.canopiesToRemove.clear();
        Game.camera = player.postRotatedX /**ScreenData.instance().widthratio*/ - 100;
        
        super.draw(batch);
    }
    
    @Override
    public void startSlideOut()
    {
        if(hold != null)
        {
            hold.startSlideOut();
            getReady.startSlideOut();
        }
    }
}
