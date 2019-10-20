/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author youxf's inspiron
 */
public class Controls {
    private Camera cam;
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
           
        }
    };
}
