/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 *
 * @author Neidert
 */
public class Player extends CompositeDrawable {
    float postRotatedX;
    float postRotatedY;
    GameState gamestate;
    PlayerState playerState;
    public ArrayList<Drawable> entitiesToAdd = new ArrayList<Drawable>();
    public ArrayList<CompositeDrawable> canopiesToRemove = new ArrayList<CompositeDrawable>();
    
    public Player(float xSwing, float angleDegrees, GameState gameState)
    {
        Game.camera = 0;
        gamestate = gameState;
        xAugmented = 0;
        yAugmented = 0;
        x = xSwing*(screen.widthpx/screen.widthratio);
        y = screen.heightpx/(2*screen.heightratio);
        XrotationAnchor = 0;
        YrotationAnchor = -y*screen.heightratio + (screen.heightpx - CanopyPiece.canopyPositionY*screen.heightratio);
        rotation = angleDegrees;
        float deltaX = ((float) (YrotationAnchor))*((float)Math.sin(Math.toRadians(rotation)));
        float deltaY = ((float) (YrotationAnchor))*(1.0f - (float)Math.cos(Math.toRadians(rotation)));
        postRotatedX = x*screen.widthratio + deltaX;
        postRotatedY = y*screen.heightratio + deltaY;
        
        children.add(new MonkeyHead());
        
        screenTouch(xSwing*screen.widthpx, screen.heightpx-CanopyPiece.canopyPositionY*screen.heightratio);
        
        
    }
    
    public boolean screenTouch(float xTouch, float yTouch)
    {
        if(yTouch < postRotatedY && !gamestate.getClass().equals(GameStateHighScores.class))
        {
            double distanceToTouch = Math.sqrt(Math.pow(xTouch - postRotatedX + Game.camera, 2) + Math.pow(yTouch - postRotatedY, 2));
            double radians = Math.asin((postRotatedX - Game.camera - xTouch)/distanceToTouch);
            float deltaY = screen.heightpx - CanopyPiece.canopyPositionY*screen.heightratio - postRotatedY;
            System.out.println(postRotatedY);
            float deltaX = (float) (deltaY*Math.tan(radians));
            float intersectionX = deltaX + postRotatedX - Game.camera;
            boolean contains = true;
            for(int i = 0; i < gamestate.canopies.size(); i++)
            {
                if(((CanopyComposite)gamestate.canopies.get(i)).containsX(intersectionX))
                {
                    contains = true;
                    break;
                }
                else
                {
                    contains = false;
                }
            }
            if(contains)
            {
                YrotationAnchor = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                XrotationAnchor = 0;
                x = (intersectionX + Game.camera)/screen.widthratio;
                y = (CanopyPiece.canopyPositionY*screen.heightratio - YrotationAnchor)/screen.heightratio;
                xAugmented = x;
                yAugmented = y;
                rotation = (float) Math.toDegrees(radians);
                playerState = new PlayerStateSwing(this, true, (int)xTouch, (int)yTouch);
                gamestate.startSlideOut();
            }
            return true;
        }
        else
            return false;
    }
    
    public void release()
    {
        if(playerState.getClass().equals(PlayerStateSwing.class))
            playerState = new PlayerStateFall(this, ((PlayerStateSwing)playerState).angleMax, ((PlayerStateSwing)playerState).forward);
    }
    
    @Override 
    public void draw(SpriteBatch batch)
    {
        playerState.update();
        super.draw(batch);
    }
}
