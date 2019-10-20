package mygame;

import com.jme3.app.SimpleApplication;
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
import com.jme3.input.controls.KeyTrigger;
import com.jme3.ui.Picture;


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
    
    Data data;
    

    public CameraControl ccontrol;
    public Controls moves;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    

    @Override
    public void simpleInitApp() {  
        
        Picture pic = new Picture("HUD Picture");
        pic.setImage(assetManager, "Top.png", true);
        pic.setWidth(800);
        pic.setHeight(75);
        pic.setPosition(settings.getWidth()/2-400, settings.getHeight()-75);
        guiNode.attachChild(pic);
        
        screen = new Screen(this, "tonegod/gui/style/def/style_map.gui.xml");
        guiNode.addControl(screen);
        
        GUIManager guiManager = new GUIManager();
        
        guiManager.nextYearButton(screen);
        
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.5f));
        rootNode.addLight(al);
        
        RenderClass.initialRender();
        data = new Data (17,200000,200000);
        
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
        // Add the names to the action listener.
        inputManager.addListener(moves.analogListener, "forward", "back", "left", "right","nextTurn");

    }

    @Override
    public void simpleRender(RenderManager rm) {
        if(render){
            for(int i = 0;i<5;i++){
                for(int j = 0;j<17;j++){
                    for(int k = 0;k<17;k++){
                        Tile t = RenderClass.tileMap[k][i][j];


                        Vector3f move;
                        if(j%2 == 0 && t.type != -1){
                            System.out.println(t.type);
                            System.out.println(t.fileName);
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