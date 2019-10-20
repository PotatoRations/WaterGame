/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.font.BitmapFont;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import java.awt.Color;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;

/**
 *
 * @author aaron
 */

public class GUIManager {
    
    public GUIManager(){
        
    }
    
    public void nextYearButton(Screen screen){
        ButtonAdapter advance = new ButtonAdapter(screen){
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                Main.nextTurn = true;
                
            }
        };
        advance.setWidth(160);
        advance.setHeight(50);
        advance.setTextPosition(advance.getWidth()/2-80, advance.getHeight()/2);
        advance.setTextAlign(BitmapFont.Align.Center);
        advance.setText("ADVANCE");
        advance.setPosition(screen.getWidth()/2-80, 45);
        
        screen.addElement(advance);
    }
    
    //incomplete
    //includes index of tile to upgrade
    public void createUpgradeWindow(Screen screen, int x, int y) {
        final Window nWin = new Window(screen, new Vector2f( (screen.getWidth()/2)-175, (screen.getHeight()/2)-100 ));
        nWin.setWindowTitle("Upgrade?");
        
        nWin.setTextPosition(50, 50);
        nWin.setText("Upgrade this tile?"); 
        
        
        ButtonAdapter upgradeButton = new ButtonAdapter( screen, new Vector2f(30, 180) ) {
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                //if can afford, upgrade tile
                //else nWin.dropchildren, change text cannot afford, add button ok
                screen.removeElement(nWin);
            }
        };
        upgradeButton.setText("Yes");
        ButtonAdapter declineButton = new ButtonAdapter( screen, new Vector2f(nWin.getWidth()-30 - upgradeButton.getWidth(), 180) ) {
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                screen.removeElement(nWin);
            }
        };
        declineButton.setText("Yes");
        nWin.addChild(upgradeButton);
        nWin.addChild(declineButton);
        screen.addElement(nWin);
    }
    
    //need to finish
    public void buildButton(Screen screen, final int x, final int y){
        final Window nWin = new Window(screen, new Vector2f( (screen.getWidth()/2)-175, (screen.getHeight()/2)-100 ));
        nWin.setWindowTitle("Build?");
        
        nWin.setTextPosition(50, 50);
        nWin.setText("Build on this tile? \nNote: If land is salted, no farms can be built"); 
        
       
        if (!Data.tileMap[x][y].salted){
            ButtonAdapter farm = new ButtonAdapter( screen, new Vector2f(30, 180) ) {
                @Override
                public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                    Data.tileMap[x][y].changeType(31);
                    //else nWin.dropchildren, change text cannot afford, add button ok
                    screen.removeElement(nWin);
                }
            };
            farm.setText("Build farm");
            
        }
        
        
        
    }
    
    
}
