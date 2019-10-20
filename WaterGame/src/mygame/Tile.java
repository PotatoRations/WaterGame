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
    public int type;
    public boolean salted;
    public boolean submerged;
    public double height;
    public String fileName;


    public Tile(int type, double height, boolean salted, boolean submerged, String fileName){
        this.type = type;
        this.height = height;
        this.salted = salted;
        this.submerged = submerged;
        this.fileName = fileName;
    }

    public void changeType(int newType){
        this.type = newType;
    }

    public void changeSalt(boolean salted){
        this.salted = salted;
    }

}