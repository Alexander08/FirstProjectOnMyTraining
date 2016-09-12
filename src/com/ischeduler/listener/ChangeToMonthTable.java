package com.ischeduler.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Month;

public class ChangeToMonthTable implements ActionListener {

    private final GUIManager gui;
    
    public ChangeToMonthTable(GUIManager gui) {
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        gui.changeTable(new Month(new Date()));
    }

}
