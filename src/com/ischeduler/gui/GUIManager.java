package com.ischeduler.gui;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ischeduler.gui.table.Day;
import com.ischeduler.gui.table.TableManager;

/**
 * GUIManager is a cross point class where main window load all components in a frame and make them
 * visible on screen
 * 
 * @param window - are declared final to have access on the same object for change properties
 * 
 * 
 */

public class GUIManager {

    private final JFrame   window;
    private TopMenu        menu;
    private TopButtons     buttonsArea;
    private TableManager   table;
    private JPanel         rightSide;
    final private Calendar currentDate;


    public GUIManager() {

        super();

        this.currentDate = Calendar.getInstance();
        this.currentDate.setTime(new Date());

        this.window = new JFrame("iScheduler2");
        this.window.setLayout(new BorderLayout());

        this.menu = new TopMenu();
        this.window.setJMenuBar(this.menu.getMenuBar());

        this.buttonsArea = new TopButtons(this);
        this.window.add(this.buttonsArea.getComponent(), BorderLayout.NORTH);

    }



    public GUIManager(TableManager table) {

        this();

        this.table = table;
        this.window.add(this.table.getComponent(), BorderLayout.CENTER);
    }

    /**
     * 
     */
    public void setRightSide() {

        this.rightSide = new JPanel(new BorderLayout());

        JPanel right = (JPanel) new Day().getComponent();
        right.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));

        this.rightSide.add(right, BorderLayout.CENTER);

        this.window.add(this.rightSide, BorderLayout.EAST);
    }

    /**
     * 
     */
    public void launchFrame() {

        this.window.pack();
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(1600, 900);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
    }

    public JFrame getWindow() {

        return this.window;
    }

    public void changeTable(TableManager table) {

        this.window.remove(this.table.getComponent());
        this.table = table;
        this.window.add(this.table.getComponent());

        this.window.revalidate();
    }

    public Calendar getCurrentDate() {
        return this.currentDate;
    }


}
