package com.ischeduler.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ischeduler.gui.table.Month;
import com.ischeduler.gui.table.TableManager;
import com.ischeduler.gui.table.Year;

/**
 * 
 * 
 * */

public class GUIManager {

    private JFrame       window;

    private TopMenu      menu;

    private TopButtons   buttonsArea;

    private TableManager table;

    public GUIManager(TableManager table) {

        this.window = new JFrame("iScheduler2");
        this.menu = new TopMenu();

        this.buttonsArea = new TopButtons();
        this.table = table;

        this.window.setLayout(new BorderLayout());

        this.window.setJMenuBar(this.menu.getMenuBar());

        this.window.add(this.buttonsArea.getComponent(), BorderLayout.NORTH);
        this.window.add(this.table.getComponent(), BorderLayout.CENTER); // Te pupa Jean :* merge!!!

        this.window.pack();
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setVisible(true);
    }

    public void changeTable(TableManager table) {

        this.window.remove(this.table.getComponent());
        this.table = table;
        this.window.add(this.table.getComponent());

        this.window.revalidate();
    }

    public static void main(String[] args) {

        // GUIManager<Month> gui = new GUIManager<Month>(new Month(new Date()));

        GUIManager gui = new GUIManager(new Year(new Date()));

        try {
            
            Thread.sleep(10000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gui.changeTable(new Month(new Date()));

    }
}
