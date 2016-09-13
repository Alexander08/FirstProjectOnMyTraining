package com.ischeduler.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Week;

public class ChangeToWeekTable implements ActionListener{
    private GUIManager gui;

    public ChangeToWeekTable(GUIManager gui) {
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        final Calendar date = gui.getCurrentDate();
        date.setTime(new Date());
        gui.changeTable(new Week(new Date()));
    }    
}
