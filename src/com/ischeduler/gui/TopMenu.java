package com.ischeduler.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class TopMenu {
    
    private JMenuBar  menuBar;
   
    private JMenu     file;
    private JMenuItem save;
    private JMenuItem load;
    
    private JMenu     actions;
   
    private JMenu     help;

    public TopMenu() {
        
        this.menuBar = new JMenuBar();
        
        this.file = new JMenu("File");
        
        this.save = new JMenuItem("Save");       
        this.load = new JMenuItem("Load...");
  
        this.file.add(this.save);
        this.file.add(this.load);
        
        this.actions = new JMenu("Actions");
        
        this.help = new JMenu("Help");
        
        this.menuBar.add(this.file);
        this.menuBar.add(this.actions);
        this.menuBar.add(this.help);
    }

    public JMenuBar getMenuBar() {
        
        return this.menuBar;
    }
}
