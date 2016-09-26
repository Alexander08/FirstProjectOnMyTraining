package com.ischeduler.main;

import javax.swing.JApplet;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Year;

public class MainApplet extends JApplet{
    
    GUIManager gui = new GUIManager(new Year());
    
    public void init(){
        gui = new GUIManager(new Year());
        gui.changeTable(new Year(gui.getCurrentDate().getTime()));
        gui.setRightSide();
        
        setContentPane(gui.getWindow().getContentPane());
    }

}
