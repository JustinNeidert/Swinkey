/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neidert.swinkey;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Neidert
 */
public class TreeTrunk extends Drawable {
    public TreeTrunk(CanopyComposite canopy)
    {
        texture = TextureCache.treeTrunk;//new Texture("tree.png");
        width = texture.getWidth()*screen.widthratio;
        height = texture.getHeight()*screen.heightratio;
        x = canopy.x - texture.getWidth()/2.0f + canopy.children.get(0).width*(canopy.children.size() + 2)/(4.0f*screen.widthratio);
        y = 10;
    }
}
