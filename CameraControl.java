/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author youxf's inspiron
 */
public class CameraControl {
    
    public Vector3f camleft;
    public Camera cam;
    public Vector3f up;
    public CameraControl(Camera cam){
        //moves camera up
        this.cam = cam;
        Vector3f CameraLocation = this.cam.getLocation();
        CameraLocation.y = 50;
        CameraLocation.z = 102;
        CameraLocation.x = 102;


        
        this.cam.setLocation(CameraLocation);
        camleft = this.cam.getDirection();
        up = this.cam.getUp();
        up.y = (float) -1;
        up.z = -1;
        //camleft.setZ((float) -0.5);
        this.cam.lookAtDirection(up, camleft);
        //(0.64196736, -0.58390445, 0.4969241)
       
        
        
    }
    
    public void repeat() throws InterruptedException{
        camleft.setX((float) 0.00001);
        //camleft.y = (float) 0;
        camleft.setZ((float) 0.0000001);
        float angle = 3.14f;
        TimeUnit.SECONDS.sleep(1);

        
        this.cam.setRotation((new Quaternion().fromAngleAxis(angle, camleft)));
        camleft = cam.getDirection();
        System.out.println(cam.getDirection());
        
    }
}
