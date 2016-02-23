/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author Neidert
 */
public class ScreenData {
    int heightbpx = 144;
    int widthbpx;
    int heightpx;
    int widthpx;
    float heightratio;
    float widthratio;
    int cameraShiftX;
    int cameraShiftY;
    private static final ScreenData sd = new ScreenData();
    
    private ScreenData()
    {
        heightpx = Gdx.graphics.getHeight();
        widthpx = Gdx.graphics.getWidth();
        heightratio = ((float)heightpx)/((float)heightbpx);
        widthbpx = (int)(((float)widthpx)/heightratio);
        widthratio = heightratio;//((float)widthpx)/((float)widthbpx);
        cameraShiftX = 0;
        cameraShiftY = 0;
    }
    
    public static ScreenData instance()
    {
        return sd;
    }
}
