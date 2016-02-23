/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author JNeidert
 */
public class TextureCache {
    public static Texture[] smallNumbers = new Texture[10];
    public static Texture[] bigNumbers = new Texture[10];
    public static Texture canopyMid;
    public static Texture canopyEdge;
    public static Texture treeTrunk;
    
    public TextureCache()
    {
        for(int i = 0; i < 10; i++)
        {
            smallNumbers[i] = new Texture(Integer.toString(i) + "-1.png");
            bigNumbers[i] = new Texture(Integer.toString(i) + "-2.png");
            canopyMid = new Texture("canopypiece1.png");
            canopyEdge = new Texture("canopyedge1.png");
            treeTrunk = new Texture("tree.png");
        }
    }
}
