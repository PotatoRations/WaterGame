package mygame;

import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.light.AmbientLight;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
        protected Geometry player;
        public static boolean render = false;
        public static boolean renderPop = false;


    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    public CameraControl ccontrol;
    public Controls moves;

    @Override
    public void simpleInitApp() {
     
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.1f));
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
        flyCam.setEnabled(false);

        
        //youcef start
        ccontrol = new CameraControl(cam);
        moves = new Controls(cam);
        initKeys(); // load my custom keybinding
        //youcef end
        
        
    }
    
    public void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("forward",  new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("back",   new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("left",  new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        // Add the names to the action listener.
        inputManager.addListener(moves.analogListener, "forward", "back", "left", "right");

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

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        
    }

   
    
    
}
