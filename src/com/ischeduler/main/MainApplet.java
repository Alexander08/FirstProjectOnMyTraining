package com.ischeduler.main;

import javax.swing.JApplet;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Day;
import com.ischeduler.gui.table.Year;

public class MainApplet extends JApplet {

    private static final long serialVersionUID = 1L;

    GUIManager gui = new GUIManager(new Day());

    public void init() {
        
        gui = new GUIManager(new Day());
        gui.changeTable(new Year(gui.getCurrentDate().getTime(), gui.getEventsList()));
        gui.setRightSide();

        setContentPane(gui.getWindow().getContentPane());
    }

}
