/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author JNeidert
 */
public class StatBoard extends CompositeDrawable {
    private int scoreValue;
    private SmallScore score;
    private SmallScore highScore;
    private float slideTime;
    private final float distance;
    private long currentTime;
    private Texture[] textures;
    private boolean newHS;
    public final float destination;
    
    public StatBoard()
    {
        textures = new Texture[2];
        textures[0] = new Texture("end1.png");
        textures[1] = new Texture("end2.png");
        texture = textures[0];
        
        newHS = false;
        
        height = texture.getHeight()*screen.heightratio;
        width = texture.getWidth()*screen.widthratio;
        y = -5*height/screen.heightratio;
        x = screen.widthbpx/2 - width/(2*screen.widthratio);
        
        scoreValue = 0;
        score = new SmallScore(scoreValue, false);
        highScore = new SmallScore (Game.highscore, false);
        if(Game.highscore < Game.score)
        {
            Game.highscore = Game.score;
            Game.saveHighScore();
        }
        score.y = height/(1.5f*screen.heightratio) + 3;
        score.x = 5;
        highScore.x = 5;
        highScore.y = highScore.children.get(0).height/screen.heightratio;
        children.add(score);
        children.add(highScore);
        slideTime = 1000.0f;
        destination = screen.heightbpx - 1.5f*height/screen.widthratio;
        distance = destination - y;
        currentTime = System.currentTimeMillis();
    }
    
    @Override
    public void draw(SpriteBatch batch)
    {
        update();
        batch.draw(texture, xAugmented*screen.widthratio, yAugmented*screen.heightratio, XrotationAnchor, YrotationAnchor, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flipX, flipY);
        super.draw(batch);
    }
    
    public void update()
    {
        if(slideTime > 0)
        {
            slideTime -= System.currentTimeMillis() - currentTime;
            currentTime = System.currentTimeMillis();
            y = (float) (destination - Math.pow(slideTime, 2)*distance/1000000);
        }
        else if(scoreValue < Game.score)
        {
            countUp();
        }
        else if (newHS)
        {
            highScoreFlash();
        }
    }
    
    private void highScoreFlash()
    {
        long deltams = System.currentTimeMillis() - currentTime;
        int textureIndex = ((int)(deltams / 100)) % 2;
        texture = textures[textureIndex];
    }
    
    private void countUp()
    {
        long deltams = System.currentTimeMillis() - currentTime;
        scoreValue = (int) (deltams / 100);
        if(scoreValue > Game.score)
            scoreValue = Game.score;
        score.setValue(scoreValue);
        if(scoreValue > Game.highscore)
        {
            Game.highscore = scoreValue;
            highScore.setValue(scoreValue);
            if(scoreValue == Game.score)
            {
                currentTime = System.currentTimeMillis();
                newHS = true;
            }
        }
    }
}
