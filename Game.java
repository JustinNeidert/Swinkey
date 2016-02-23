package com.neidert.swinkey;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends ApplicationAdapter implements InputProcessor {
    GameState currentState;
    ScreenData screen;
    public SpriteBatch batch;
    Texture[] images;
    boolean transitionOut;
    static boolean transitionIn;
    static Color transitionColor;
    Color white;
    Texture fadeOver;
    public static float camera;
    public static float ground;
    public static int score;
    public static int highscore;
    public static int revives;
    public static TextureCache textureCache;
    
    @Override
    public void create () {
    	batch = new SpriteBatch();
        images = new Texture[50];
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        fadeOver = new Texture("fadeover.png");
        camera = 0.0f;
        ground = 10.0f;
        score = 0;
        textureCache = new TextureCache();
        
        retreiveHighScoreAndRevives();
                /*images[0] = new Texture("0-1.png");
         
        images[1] = new Texture("1-1.png");
                images[2] = new Texture("2-1.png");
                images[3] = new Texture("3-1.png");
                images[4] = new Texture("4-1.png");
                images[5] = new Texture("5-1.png");
                images[6] = new Texture("6-1.png");
                images[7] = new Texture("7-1.png");
                images[8] = new Texture("8-1.png");
                images[9] = new Texture("9-1.png");
                images[10] = new Texture("0-2.png");
                images[11] = new Texture("1-2.png");
                images[12] = new Texture("2-2.png");
                images[13] = new Texture("3-2.png");
                images[14] = new Texture("4-2.png");
                images[15] = new Texture("5-2.png");
                images[16] = new Texture("6-2.png");
                images[17] = new Texture("7-2.png");
                images[18] = new Texture("8-2.png");
                images[19] = new Texture("9-2.png");
                images[20] = new Texture("monkey.png");
                images[21] = new Texture("arm.png");
                images[22] = new Texture("canopypiece1.png");
                images[23] = new Texture("canopyedge1.png");
                images[24] = new Texture("logo.png");
                images[25] = new Texture("vine.png");
                images[26] = new Texture("play.png");
                images[27] = new Texture("highscores.png");
                images[28] = new Texture("GetReady.png");
                images[29] = new Texture("GameOver.png");
                images[30] = new Texture("tutorial.png");
                images[31] = new Texture("facebook.png");
                images[32] = new Texture("twitter.png");
                images[33] = new Texture("end1.png");
                images[34] = new Texture("end2.png");
                */
                
        screen = ScreenData.instance();
        transitionOut = false;
        transitionIn = false;
            
        currentState = new GameStateMain(this);
    }
    
    @Override
    public void render () {
	Gdx.gl.glClearColor(1, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        try
        {
            currentState.draw(batch);
        }
        catch(Exception ex)
        {
            System.out.println("currentState.draw not working " + ex);
        }
        
        if(transitionOut)
        {
            transitionColor.a += .02f;
            if(transitionColor.a > 0.99f)
            {
                transitionColor = Color.BLACK;
                transitionOut = false;
                currentState = new GameStatePlay(this);
                transitionIn = true;
            }
            
            batch.setColor(transitionColor);
            batch.draw(fadeOver, -1, -1, screen.widthpx+2, screen.heightpx+2);
            batch.setColor(white);
            
        } 
        else if(transitionIn)
        {
            transitionColor.a -= .02f;
            if(transitionColor.a < .01f)
            {
                transitionColor.a = 0.0f;
                transitionIn = false;
            }
            
            batch.setColor(transitionColor);
            batch.draw(fadeOver, -1, -1, screen.widthpx+2, screen.heightpx+2);
            batch.setColor(white);
        }
                
	batch.end();
    }

    @Override
    public boolean keyDown(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyUp(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyTyped(char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) 
    {
        boolean touched = false;
        for(Button button : currentState.buttons)
        {
            boolean thisTouched = button.containsPoint(i, i1);
            if(thisTouched)
                button.onTouchDown(i, i1);
            touched |= thisTouched;
        }
        
        currentState.touchDown(i, i1);
        
        return touched;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) 
    {
        boolean touched = false;
        for(Button button : currentState.buttons)
        {
            if(button.pressed)
                button.onTouchUp(i, i1, this);
            touched |= button.pressed;
        }
        
        currentState.touchUp(i, i1);
        
        return touched;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        boolean touched = false;
        for(Button button : currentState.buttons)
        {
            boolean thisTouched = button.containsPoint(i, i1);
            if(thisTouched)
                button.onTouchMoved(i, i1);
            touched |= thisTouched;
        }
        
        return touched;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void transitionToPlay()
    {
        transitionColor = new Color(0, 0, 0, 0);
        transitionOut = true;
        white = new Color(Color.WHITE);
    }
    
    public void transitionToDead()
    {
        transitionColor = new Color(1, 1, 1, 1.0f);
        transitionIn = true;
        if(score > highscore)
            saveHighScore();
        currentState = new GameStateHighScores(this);
    }
    
    public static void saveHighScore()
    {
        try
            {
                FileWriter writer = new FileWriter("save.txt");
                BufferedWriter bWriter = new BufferedWriter(writer);
                bWriter.write(String.valueOf(score) + " " + String.valueOf(0/*revives*/));
                bWriter.close();
                writer.close();
            }
            catch(Exception e)
            {
                System.out.println("highscore update failed");
            }
    }
    
    public static void retreiveHighScoreAndRevives()
    {
        try {
            FileReader reader = new FileReader("save.txt");
            BufferedReader bReader = new BufferedReader(reader);
//            System.out.println("from file: " + bReader.readLine());/*
            String[] save = bReader.readLine().split(" ");
            highscore = Integer.parseInt(save[0]);
            revives = Integer.parseInt(save[1]);
            bReader.close();
            reader.close();
            //System.out.println("from file:" + save[0] + " " + save[1]);
        } catch (Exception ex) {
            highscore = 0;
            revives = 0;
            System.out.println(ex + " file problem");
        }
    }
}
