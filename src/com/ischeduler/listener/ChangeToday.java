package com.ischeduler.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Day;

public class ChangeToday implements ActionListener {

    private GUIManager  gui;
    private ButtonGroup buttonsGroup;

    public ChangeToday(GUIManager gui, ButtonGroup... group) {
        
        this.gui = gui;
        if (group.length != 0) {
            this.buttonsGroup = group[0];
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        final Calendar date = gui.getCurrentDate();
        date.setTime(new Date());

        if (e.getSource() instanceof JButton) {
            this.buttonsGroup.getSelection().setSelected(false);
            this.buttonsGroup.getElements().nextElement().setSelected(true);
        }

        gui.changeTable(new Day(new Date()));
    }

}
