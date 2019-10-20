/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;

/**
 *
 * @author youxf's inspiron
 */


public class Controls {
    public boolean blocked = false;
    private Camera cam;
    private Main main = Main.getApp();
    private InputManager inputManager = main.getInputManager();
    
    public Controls(Camera cam){
        this.cam = cam;
    }
    public final AnalogListener analogListener = new AnalogListener() {
    @Override
    public void onAnalog(String name, float value, float tpf) {
         Vector3f CameraLocation = cam.getLocation();
            if (name.equals("forward")) {
                if (CameraLocation.z>10) {                  
                CameraLocation.z-=0.6;
                cam.setLocation(CameraLocation);
                }
            }
            if (name.equals("back")) {
                if (CameraLocation.z<210) {
                CameraLocation.z+=0.6;
                cam.setLocation(CameraLocation);
                }
            }
            if (name.equals("left")) {
                if (CameraLocation.x>0) {
                CameraLocation.x-=0.6;
                cam.setLocation(CameraLocation);
                }
            }
             if (name.equals("right")) {
                if (CameraLocation.x<150) {                     
                CameraLocation.x+=0.6;
                cam.setLocation(CameraLocation);
                }
            }
            if(name.equals("nextTurn")){
                 Main.nextTurn = true;
                 System.out.println("Next turn");
            }
            if (name.equals("select")){
                System.out.println("Click");
                // Reset results list.
                CollisionResults results = new CollisionResults();
                // Convert screen click to 3d position
                Vector2f click2d = main.getInputManager().getCursorPosition();
                Vector3f click3d = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
                Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
                // Aim the ray from the clicked spot forwards.
                Ray ray = new Ray(click3d, dir);
                // Collect intersections between ray and all nodes in results list.
                Main.getApp().getRootNode().collideWith(ray, results);
                // (Print the results so we see what is going on:)
                for (int i = 0; i < results.size(); i++) {
                  // (For each “hit”, we know distance, impact point, geometry.)
                  float dist = results.getCollision(i).getDistance();
                  Vector3f pt = results.getCollision(i).getContactPoint();
                  String target = results.getCollision(i).getGeometry().getName();
                  System.out.println("Selection #" + i + ": " + target + " at " + pt + ", " + dist + " WU away.");
                }
                // Use the results -- we rotate the selected geometry.
                if (results.size() > 0) {

                    // The closest result is the target that the player picked:
                    Geometry target = results.getClosestCollision().getGeometry();
                    BoundingVolume volume = target.getModelBound();
                    
                    
                    
                    System.out.println(target.toString());
                    boolean found = false;
                    int x = 0,y = 0,z;
                    for (int i=0; i<RenderClass.tileMap.length; i++){
                        for (int j=0; j<RenderClass.tileMap[i].length; j++){
                            for (int k=RenderClass.tileMap[i][j].length-1; k>=0; k--){
                                if (Main.spatialMatrix[i][j][k].getWorldBound().intersects(volume)){
                                    x=i;
                                    y=j;
                                    z=k;
                                    System.out.println("print " + i + j + k);
                                    System.out.println("found");
                                    found = true;
                                        break;
                                }
                            }
                            if (found);
                                break;
                        }
                        if (found);
                                break;
                    }
                    
                    
                    
                    if (target.getName().equals("Oceanblock1") ){
                        System.out.print("Value is "+ x + y);
                        Main.guiManager.createUpgradeWindow(Main.getApp().screen, x, y);
                        
                    }
                    else if (target.getName().equals("renewabletime2")){
                        
                    }    
                }
            }
           
        }
    };
}
