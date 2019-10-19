/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author Kaarthick
 */
public class RenderClass {
    public static Tile [][][] tileMap = new Tile[17][5][17];
    
    public static void initialRender(){ 
        try {
            FileReader in = new FileReader ("Map.txt");
            BufferedReader readFile = new BufferedReader (in);
            
            for(int i = 0;i<5;i++){
                for(int j = 0;j<17;j++){
                    String s = readFile.readLine();
                    String [] mapText = s.split(" ");
                    for(int k = 0;k<17;k++){
                        int tileNum = Integer.parseInt(mapText[k]); 
                        
                        Spatial model = null;
                        Tile temp = null;
                        switch (tileNum){
                            case -1:
                                temp = new Tile (tileNum, i, false, false,"null");
                                break;
                            case 0:
                                temp = new Tile (tileNum, i, false, false,"Models/grassoutline.j3o");
                                break;
                            case 1:
                                temp = new Tile (tileNum, i, false, false,"Models/foresttile.j3o");
                                break;
                            case 2:
                                temp = new Tile (tileNum, i, false, false,"Models/oceantile.j3o");
                                break;
                            case 10:
                                temp = new Tile (tileNum, i, false, false,"Models/skyscraperstile.j3o");
                                break;
                            case 20:
                                temp = new Tile (tileNum, i, false, false,"Models/smallfactorytile.j3o");
                                break;
                            case 22:
                                temp = new Tile (tileNum, i, false, false,"Models/heavyindustrytile.j3o");
                                break;
                            case 30:
                                temp = new Tile (tileNum, i, false, false,"Models/farmtile.j3o");
                                break;
                            case 50:
                                temp = new Tile (tileNum, i, false, false,"Models/renewabletile.j3o");
                                break;
                            case 52:  
                                temp = new Tile (tileNum, i, false, false,"Models/coalplanttile.j3o");
                                break;
                        }
                        
                        tileMap[k][i][j] = temp;
                        
                    }
                }
                String k = readFile.readLine();
            }
            
            readFile.close();
            in.close();
            
        } catch (FileNotFoundException e){
            System.out.println("You are trash at coding");
        } catch (IOException e){
            System.out.println("N");
        }
        
    }
    
    public void changeTile(int k, int i, int j, Tile t){
        tileMap[k][i][k] = t;
        Main.render = true;
    }
    
}
