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
public class PlayerStateDead extends PlayerState{
    Player player;
    
    public PlayerStateDead(Player gamePlayer)
    {
        player = gamePlayer;
        player.gamestate.game.transitionToDead();
        player.gamestate = player.gamestate.game.currentState;
    }
}
