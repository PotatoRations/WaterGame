package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.light.AmbientLight;
import com.jme3.math.FastMath;
import com.jme3.math.Plane;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.water.SimpleWaterProcessor;

import java.io.*;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    public static boolean render = false;
    public static boolean renderPop = false;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {  
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.6f));
        rootNode.addLight(al);
        
        RenderClass.initialRender();
        
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
        
        flyCam.setMoveSpeed(50);
                        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
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
    }
}
