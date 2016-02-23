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
public class PlayerStateSwing extends PlayerState{
    Player player;
    long millis;
    public float angleMax;
    public int touchX;
    public int touchY;
    public int intersectX;
    public int intersectY;
    public boolean reversable;
    public boolean forward;
    
    public PlayerStateSwing(Player gamePlayer, boolean rev, int xTouch, int yTouch)
    {
        player = gamePlayer;
        millis = System.currentTimeMillis();
        reversable = rev;
        
        touchX = xTouch;
        touchY = yTouch;
        
        //player.touchX = touchX;
        //player.touchY = touchY;
        
        intersectY = (int) (CanopyPiece.canopyPositionY * player.screen.heightratio);
        intersectX = (int) (player.xAugmented*player.screen.widthratio);
        angleMax = player.rotation;
        
        int monkeyHeadIndex = Math.max(player.children.size()-2, 0);
        MonkeyHead monkeyhead = (MonkeyHead)player.children.get(monkeyHeadIndex);
        player.children.clear();
        player.children.add(monkeyhead);
        player.children.add(new MonkeyArm());
        VinePiece tempVP = new VinePiece(0);
        for(int i = 0; i < player.YrotationAnchor/tempVP.height + 1; i++)
        {
            player.children.add(0, new VinePiece(i));
        }
        
    }
    
    @Override
    public void update()
    {
        ScreenData screen = ScreenData.instance();
        if((screen.heightpx - player.postRotatedY)/screen.heightratio < Game.ground)
            player.playerState = new PlayerStateDead(player);
        
        long delta = System.currentTimeMillis() - millis;
        float prevAngle = player.rotation;
        player.rotation = (float) (angleMax*Math.cos(2*Math.PI*delta/3000.0));
        forward = prevAngle < player.rotation;
        
        /*float deltaX = (float) (player.YrotationAnchor*Math.sin(Math.toRadians(player.rotation)));
        float deltaY = (float) (player.YrotationAnchor*(1.0f-Math.cos(Math.toRadians(player.rotation))));
        player.postRotatedX = (player.x*player.screen.widthratio + deltaX);//postrotatedY becomes much larger than y and y is incorrect
        player.postRotatedY = (player.y*player.screen.heightratio + deltaY);*/
        
        float deltaX = (float) (player.YrotationAnchor*Math.sin(Math.toRadians(player.rotation)));
        float deltaY = (float) (player.YrotationAnchor*Math.cos(Math.toRadians(player.rotation)));
        player.postRotatedX = (player.x*player.screen.widthratio + deltaX);//postrotatedY becomes much larger than y and y is incorrect
        player.postRotatedY = (player.screen.heightpx-CanopyPiece.canopyPositionY*player.screen.heightratio + deltaY);
        
        if(!player.gamestate.getClass().equals(GameStateMain.class))
        {
            float pxPerCanopy = 2.0f*screen.widthpx/((CanopyComposite)player.gamestate.canopies.get(0)).canopiesPerScreen;
            int distance = (int) (player.postRotatedX/pxPerCanopy);
            if(distance > Game.score)
               Game.score = distance;
        }
        /*
        float deltaX = ((float) (YrotationAnchor))*((float)Math.sin(Math.toRadians(rotation)));
        float deltaY = ((float) (YrotationAnchor))*(1.0f - (float)Math.cos(Math.toRadians(rotation)));
        postRotatedX = x*screen.widthratio + deltaX;
        postRotatedY = y*screen.heightratio + deltaY;*/
    }
    
    
}
