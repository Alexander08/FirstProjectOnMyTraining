package com.ischeduler.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.TableManager;
import com.ischeduler.gui.table.Year;

public class ChangeToYearTable implements ActionListener{

    private GUIManager   gui;
    private TableManager table;

    public ChangeToYearTable(GUIManager gui) {
        this.gui = gui;
        this.table = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Calendar date = gui.getCurrentDate();
        date.setTime(new Date());
        this.table = new Year(new Date());
        gui.changeTable(this.table);
    }
}
