/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author aaron
 */
public class Tile {
    int type;
    boolean salted;
    boolean submerged;
    double height;
    
    
    public Tile(int type, double height, boolean salted, boolean submerged){
        this.type = type;
        this.height = height;
        this.salted = salted;
        this.submerged = submerged;
    }
    
    public void changeType(int newType){
        this.type = newType;
    }
    
    public void changeSalt(boolean salted){
        this.salted = salted;
    }
    
}
