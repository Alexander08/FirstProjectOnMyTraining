package com.ischeduler.gui;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JFrame;

import com.ischeduler.gui.table.Month;
import com.ischeduler.gui.table.TableManager;

/**
 * 
 * 
 * */

public class GUIManager<T extends TableManager> {

    private JFrame window;
    private T      component;

    public GUIManager(T panel) {

        this.window = new JFrame("iScheduler2");
        this.component = panel;
        
        this.window.setLayout(new BorderLayout());
        this.window.add(component.getComponent(), BorderLayout.CENTER); // Te pupa Jean :* merge!!!
    

        this.window.pack();
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setVisible(true);
    }
    
    public static void main(String[] args) {
        //some edit file
        GUIManager<Month> gui = new GUIManager<Month>(new Month(new Date()));
    }
}
