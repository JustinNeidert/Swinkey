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
public class PlayerStateFall extends PlayerState{
    Player player;
    float xVel;
    float yVel;
    ScreenData screen;
    float gravity;
    
    public PlayerStateFall(Player gamePlayer, float angleMax, boolean forward)
    {
        player = gamePlayer;
        
        screen = ScreenData.instance();
        player.x = player.postRotatedX/screen.widthratio;
        player.y = screen.heightbpx - player.postRotatedY/screen.heightratio;
        float totalPE = (float)(1.0f - Math.cos(Math.toRadians(angleMax)));
        float currentPE = (float)(1.0f - Math.cos(Math.toRadians(player.rotation)));
        float currentME = 1.0f - (currentPE/totalPE);
        float xME = currentME * (1.0f - currentPE);
        float yME = (float) (currentME * (Math.sin(Math.toRadians(player.rotation))));
        float speedConstant = 2.0f;
        gravity = .03f;
        xVel = xME*speedConstant;
        yVel = 0.5f+yME*speedConstant;
        if(!forward)
        {
            xVel *= -1;
            yVel *= -1;
        }
        player.rotation = 0;
        Drawable monkeyHead = player.children.remove(player.children.size()-2);
        player.children.clear();
        player.children.add(monkeyHead);
    }
    
    @Override
    public void update()
    {
        player.x += xVel*screen.widthratio;
        player.y += yVel*screen.heightratio;
        yVel -= gravity;
        player.postRotatedX = player.x*screen.widthratio;
        player.postRotatedY = screen.heightpx - player.y*screen.heightratio;
        if(player.y < Game.ground)
            player.playerState = new PlayerStateDead(player);
        
        float right = player.gamestate.canopies.get(player.gamestate.canopies.size() - 1).x;
        float left = player.gamestate.canopies.get(0).x;
        float pxPerCanopy = 2.0f*screen.widthpx/((CanopyComposite)player.gamestate.canopies.get(0)).canopiesPerScreen;
        while(left < player.x - 2*screen.widthbpx)
        {
            player.canopiesToRemove.add(player.gamestate.canopies.remove(0));
//            player.gamestate.entities.remove(player.gamestate.canopies.remove(0));
            left = player.gamestate.canopies.get(0).x;
            //remove unnecessary trees
        }
        while(left > player.x - screen.widthbpx)
        {
            CanopyComposite newCanopy = new CanopyComposite();
            newCanopy.x = player.gamestate.canopies.get(0).x - pxPerCanopy/screen.widthratio;
            player.gamestate.canopies.add(0, newCanopy);
            player.entitiesToAdd.add(newCanopy);
//            player.gamestate.entities.add(newCanopy);
            player.entitiesToAdd.add(new TreeTrunk(newCanopy));
            left = player.gamestate.canopies.get(0).x;
        }
        while(right > player.x + 2*screen.widthbpx)
        {
            player.canopiesToRemove.add(player.gamestate.canopies.remove(player.gamestate.canopies.size() - 1));
//            player.gamestate.entities.remove(player.gamestate.canopies.remove(player.gamestate.canopies.size() - 1));
            right = player.gamestate.canopies.get(player.gamestate.canopies.size() - 1).x;
        }
        while(right < player.x + screen.widthbpx)
        {
            CanopyComposite newCanopy = new CanopyComposite();
            newCanopy.x = player.gamestate.canopies.get(player.gamestate.canopies.size() - 1).x + pxPerCanopy/screen.widthratio;
            player.entitiesToAdd.add(newCanopy);
//            player.gamestate.entities.add(newCanopy);
            player.gamestate.canopies.add(newCanopy);
            player.entitiesToAdd.add(new TreeTrunk(newCanopy));
            right = player.gamestate.canopies.get(player.gamestate.canopies.size() - 1).x;
        }
        
        int distance = (int) (player.postRotatedX/pxPerCanopy);
        if(distance > Game.score)
            Game.score = distance;
        //playerstate change re-adds canopies (causes flashing)
    }
}
