package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.light.*;
import com.jme3.math.FastMath;
import com.jme3.math.Plane;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.water.SimpleWaterProcessor;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.ui.Picture;
import com.jme3.font.BitmapFont;


import java.io.*;
import tonegod.gui.core.Screen;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    public Screen screen;
    
    protected Geometry player;
    public static boolean render = false;
    public static boolean renderPop = false;
    public static boolean nextTurn = true;
    public static double temperature = 30;
    Data data;
    public static Main myapp;
    public static Node getNode;

    public CameraControl ccontrol;
    public Controls moves;
    public static int screenH;
    public static BitmapFont getFont;
    
    public static void main(String[] args) {
        Main app = new Main();
        myapp = app;
        app.start();
    }
    
    public static Main getApp(){
        return myapp;
    }
    
    public static GUIManager guiManager;

    @Override
    public void simpleInitApp() {  
        
        getNode = guiNode;
        getFont = guiFont;
        RenderClass.initialRender();
        data = new Data (17,200000,200000);
        screenH = settings.getHeight();
        
        Picture pic = new Picture("HUD Picture");
        pic.setImage(assetManager, "Top.png", true);
        pic.setWidth(800);
        pic.setHeight(75);
        pic.setPosition(settings.getWidth()/2-400, settings.getHeight()-75);
        guiNode.attachChild(pic);
        
        BitmapText hudText = new BitmapText(guiFont, true);
        hudText.setSize(guiFont.getCharSet().getRenderedSize());      // font size
        hudText.setColor(ColorRGBA.Black);                             // font color
        hudText.setText("Money: $500 000");             // the text
        hudText.setLocalTranslation(575, settings.getHeight(), 0); // position
        guiNode.attachChild(hudText);
        
        BitmapText power = new BitmapText(guiFont, true);
        power.setSize(guiFont.getCharSet().getRenderedSize());      // font size
        power.setColor(ColorRGBA.Black);                             // font color
        power.setText("Energy: 100/100");             // the text
        power.setLocalTranslation(580, settings.getHeight()-20, 0); // position
        guiNode.attachChild(power);
        
        Picture thermo = new Picture("HUD Picture");
        thermo.setImage(assetManager, "Thermo.png", true);
        thermo.setWidth(40);
        thermo.setHeight(70);
        thermo.setPosition(350, settings.getHeight()-70);
        guiNode.attachChild(thermo);
        
        BitmapText temp = new BitmapText(guiFont, true);
        temp.setName("temp");
        temp.setSize(guiFont.getCharSet().getRenderedSize());      // font size
        temp.setColor(ColorRGBA.Red);                             // font color
        temp.setText(temperature + "°C");             // the text
        temp.setLocalTranslation(380, settings.getHeight()-40, 0); // position
        getNode.attachChild(temp);
        
        screen = new Screen(this, "tonegod/gui/style/def/style_map.gui.xml");
        guiNode.addControl(screen);
        
        GUIManager guiManager = new GUIManager();
        this.guiManager = guiManager;
        
        guiManager.nextYearButton(screen);
        
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.5f));
        rootNode.addLight(al);
        
        
        
        for(int i = 0;i<5;i++){
            for(int j = 0;j<17;j++){
                for(int k = 0;k<17;k++){
                    Tile t = RenderClass.tileMap[k][i][j];
                   
                    
                    Vector3f move;
                    if(j%2 == 0 && t.type != -1){
                        Spatial model = assetManager.loadModel(t.fileName);
                        move = new Vector3f(j*10,i*4,k*12-6);
                        model.move(move);
                        rootNode.attachChild(model);
                    }

                    else if (t.type != -1){
                        Spatial model = assetManager.loadModel(t.fileName);
                        move = new Vector3f(j*10,i*4,k*12);
                        model.move(move);
                        rootNode.attachChild(model);
                    }
                    
                }
            }
        }
        
        
        flyCam.setEnabled(false);
        ccontrol = new CameraControl(cam);
        moves = new Controls(cam);
        initKeys(); // load my custom keybinding
        
        DirectionalLight sun = new DirectionalLight();
        ColorRGBA rays = new ColorRGBA(1,1,1,1);
        sun.setColor(rays);
        sun.setDirection(new Vector3f(-.2f,-.2f,-.8f).normalizeLocal());
        rootNode.addLight(sun);
                        
    }

    @Override
    public void simpleUpdate(float tpf) {     
        if(nextTurn){
            nextTurn = false;
            data.endLoop();
            data.startLoop();
        }
    }
    
    public void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("forward",  new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("back",   new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("left",  new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("nextTurn", new KeyTrigger(KeyInput.KEY_N));
        inputManager.addMapping("select", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        // Add the names to the action listener.
        inputManager.addListener(moves.analogListener, "forward", "back", "left", "right","nextTurn","select");

    }
    
    public static Spatial[][][] spatialMatrix = new Spatial[5][17][17];
    
    
    @Override
    public void simpleRender(RenderManager rm) {
        if(render){
            rootNode.detachAllChildren();
            for(int i = 0;i<5;i++){
                for(int j = 0;j<17;j++){
                    for(int k = 0;k<17;k++){
                        Tile t = RenderClass.tileMap[k][i][j];


                        Vector3f move;
                        if(j%2 == 0 && t.type != -1){
                            
                            Spatial model = assetManager.loadModel(t.fileName);
                            move = new Vector3f(j*10,i*4,k*12-6);
                            model.move(move);
                            spatialMatrix[i][j][k] = model;
                            rootNode.attachChild(model);
                        }

                        else if (t.type != -1){
                            Spatial model = assetManager.loadModel(t.fileName);
                            move = new Vector3f(j*10,i*4,k*12);
                            model.move(move);
                            spatialMatrix[i][j][k] = model;
                            rootNode.attachChild(model);
                        }

                    }
                }
             } 
          
        }
        /*else {
            for(int i = 0;i<5;i++){
                for(int j = 0;j<17;j++){
                    for(int k = 0;k<17;k++){
                        Tile t = RenderClass.tileMap[k][i][j];
                        if(t.type == 2){
                            int d = (int)(Math.random()*1-1);
                            if(d == 0){
                                Spatial model = assetManager.loadModel(t.fileName);
                                Vector3f move = new Vector3f(j*10,i*4+d,k*12);
                                model.move(move);
                                rootNode.attachChild(model);
                            }
                        }
                    }
                }
            }
        }
        */
        render = false;
    }
    
    
}