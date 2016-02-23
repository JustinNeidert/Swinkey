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
public class GameStateHighScores extends GameState {
    public GameStateHighScores(Game g)
    {
        player = g.currentState.player;
        for(Drawable d : g.currentState.entities)
            entities.add(d);
        
        PlayButton pb = new PlayButton();
        HighScoresButton hsb = new HighScoresButton();
        
        entities.add(new StatBoard());
        entities.add(new GameOver());
//        entities.add(new SmallScore(Game.highscore, true));
        entities.add(pb);
        entities.add(hsb);
        
        buttons.add(pb);
        buttons.add(hsb);
        
        pb.startSlideIn();
        hsb.startSlideIn();
        
        game = g;
    }
    
    @Override
    public void draw(SpriteBatch batch)
    {
        Game.camera = player.postRotatedX - 100;
        super.draw(batch);
    }
    
}
