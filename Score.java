/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author JNeidert
 */
public class Score extends CompositeDrawable {
    int previousScore;
    
    public Score()
    {
        previousScore = -1;
        x = screen.widthbpx - 10;
        y = screen.heightbpx/2;
    }
    
    @Override
    public void draw(SpriteBatch batch)
    {
        updateScore();
        
        super.draw(batch);
    }
    
    public void updateScore()
    {
        if(previousScore != Game.score)
        {
            children.clear();
            addDigits();
            previousScore = Game.score;
        }
    }
    
    public void addDigits()
    {
        int score = Game.score;
        int digits = (int)Math.ceil(Math.log10(score)) + 1;
        int digit = score - (10*(score/10));
        Number digit0 = new Number(digit, true);
        digit0.x = digit0.width/screen.widthratio - 2;
        float widthSum = digit0.x;
        children.add(digit0);
        score /= 10;
        while(score > 0)
        {
            digit = score - (10*(score/10));
            Number digit1 = new Number(digit, true);
            children.add(digit1);
            widthSum += digit1.width/screen.widthratio - 2;
            for(Drawable number : children)
                number.x += digit1.width/screen.widthratio - 2;
            score /= 10;
        }
        for(Drawable number : children)
        {
            number.x -= widthSum + digit0.width/screen.widthratio - 2;
        }
    }
}
