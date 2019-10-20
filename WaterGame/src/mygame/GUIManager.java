/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import java.awt.Color;
import static mygame.Main.getNode;
import static mygame.Main.temperature;
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
                Main.temperature +=0.5;
                Main.getNode.detachChildNamed("temp");
                BitmapText temp = new BitmapText(Main.getFont, true);
                temp.setName("temp");
                temp.setSize(Main.getFont.getCharSet().getRenderedSize());      // font size
                temp.setColor(ColorRGBA.Red);                             // font color
                temp.setText(temperature + "°C");             // the text
                temp.setLocalTranslation(380, Main.screenH-40, 0); // position
                getNode.attachChild(temp);
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
    public boolean done = false;
    //incomplete
    //includes index of tile to upgrade
    public void createUpgradeWindow(Screen screen, int x, int y) {
        if (!done){
            done = true;
            final Window nWin = new Window(screen, new Vector2f( (screen.getWidth()/2)-175, (screen.getHeight()/2)-100 ));
            nWin.setWindowTitle("Upgrade?");

            nWin.setTextPosition(50, 50);
            nWin.setText("Upgrade this tile?"); 


            ButtonAdapter upgradeButton = new ButtonAdapter( screen, new Vector2f(30, 180) ) {
                @Override
                public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                    RenderClass.changeTile(1, 3, 10, new Tile(10, 4, false, false, "Models/skyscraperstile.j3o"));
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
            declineButton.setText("No");
            nWin.addChild(upgradeButton);
            nWin.addChild(declineButton);
            screen.addElement(nWin);
        }
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
