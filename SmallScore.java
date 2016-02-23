/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

/**
 *
 * @author JNeidert
 */
public final class SmallScore extends CompositeDrawable {
    int score;
    boolean highscore;
    public SmallScore(int value, boolean hs)
    {
        highscore = hs;
        setValue(value);
    }
    
    public void addDigitsJustifyLeft()
    {
        children.clear();
        int value = score;
        int firstDigit = value - (value/10)*10;
        children.add(new Number(firstDigit, false));
        value /= 10;
        while(value > 0)
        {
            int digit = value - (value/10)*10;
            Number nextDigit = new Number(digit, false);
            for(Drawable previousDigit : children)
            {
                previousDigit.x += nextDigit.width/screen.widthratio;
            }
            children.add(nextDigit);
            value /= 10;
        }
    }
    
    public void setValue(int value)
    {
        score = value;
        addDigitsJustifyLeft();
    }
}
