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

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.6f));
        rootNode.addLight(al);
        
        for (int i = 0;i<16;i++){
            for (int j = 0;j<16;j++){
                Spatial model = assetManager.loadModel("Models/grassoutline.j3o");
                Vector3f move;
                if(i%2 == 0){
                     move = new Vector3f(i*3,0,j*3-1);
                     model.move(move);
                }
                else {
                    move = new Vector3f(i*3,0,j*3);
                    model.move(move);
                }
               
                
                rootNode.attachChild(model);
         }
         
        } 

        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
