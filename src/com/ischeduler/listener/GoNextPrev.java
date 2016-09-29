package com.ischeduler.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Day;
import com.ischeduler.gui.table.Month;
import com.ischeduler.gui.table.Week;
import com.ischeduler.gui.table.Year;

public class GoNextPrev implements ActionListener {

    private final ButtonGroup selection;
    private final GUIManager  gui;
    private Calendar          date;

    public GoNextPrev(GUIManager gui, ButtonGroup selection) {

        this.gui = gui;
        this.selection = selection;
        this.date = gui.getCurrentDate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton direction = (JButton) e.getSource();
        ButtonModel button = (ButtonModel) this.selection.getSelection();

        if (direction.getActionCommand() == "Next") {

            if (button.getActionCommand() == "Year") {

                date.add(Calendar.YEAR, 1);
                gui.changeTable(new Year(date.getTime(), gui.getEventsList()));

            } else if (button.getActionCommand() == "Month") {

                date.add(Calendar.MONTH, 1);
                gui.changeTable(new Month(date.getTime()));

            } else if (button.getActionCommand() == "Week") {

                date.add(Calendar.WEEK_OF_YEAR, 1);
                gui.changeTable(new Week(date.getTime(), gui.getEventsList()));

            } else if (button.getActionCommand() == "Day") {

                date.add(Calendar.DAY_OF_YEAR, 1);
                gui.changeTable(new Day(date.getTime()));
            }
        } else {
            if (button.getActionCommand() == "Year") {

                date.add(Calendar.YEAR, -1);
                gui.changeTable(new Year(date.getTime(), gui.getEventsList()));

            } else if (button.getActionCommand() == "Month") {

                date.add(Calendar.MONTH, -1);
                gui.changeTable(new Month(date.getTime()));

            } else if (button.getActionCommand() == "Week") {

                date.add(Calendar.WEEK_OF_YEAR, -1);
                gui.changeTable(new Week(date.getTime(), gui.getEventsList()));

            } else if (button.getActionCommand() == "Day") {

                date.add(Calendar.DAY_OF_YEAR, -1);
                gui.changeTable(new Day(date.getTime()));
            }
        }
    }
// this file was modified
}
