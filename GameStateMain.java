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
public class GameStateMain extends GameState {
    
    public GameStateMain(Game g)
    {
        buttons.add(new PlayButton());
        buttons.add(new HighScoresButton());
        
        entities.add(new CanopyMain());
        entities.add(new Logo());
        
        
        player = new Player(0.5f, 30.0f, this);
        entities.add(0, player);
        
        for(Button button : buttons)
            entities.add(button);
        
        game = g;
    }
    
}
