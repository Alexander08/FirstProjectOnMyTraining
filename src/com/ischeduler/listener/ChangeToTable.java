package com.ischeduler.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.TableManager;

public abstract class ChangeToTable implements ActionListener {

    protected GUIManager   gui;
    protected TableManager table;

    public ChangeToTable(GUIManager gui, TableManager pane) {
        super();

        this.gui = gui;
        try {
            this.table = pane.getClass().newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final Calendar date = gui.getCurrentDate();
        date.setTime(new Date());
        this.gui.changeTable(this.table);
    }

}
