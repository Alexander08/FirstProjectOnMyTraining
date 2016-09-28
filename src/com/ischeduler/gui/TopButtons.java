package com.ischeduler.gui;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import com.ischeduler.domain.EventKeeper;
import com.ischeduler.gui.table.Day;
import com.ischeduler.gui.table.Month;
import com.ischeduler.gui.table.TableManager;
import com.ischeduler.gui.table.Week;
import com.ischeduler.gui.table.Year;
import com.ischeduler.listener.ChangeToTable;
import com.ischeduler.listener.ChangeToday;
import com.ischeduler.listener.GoNextPrev;

public class TopButtons implements TableManager {

    private JPanel        buttonsTable;
    private JButton       previous;
    private JButton       next;
    private JButton       today;

    private ButtonGroup   selectTable;
    private JToggleButton day;         // change with toggle button - give you two state ;)
    private JToggleButton week;
    private JToggleButton month;
    private JToggleButton year;

    public TopButtons(GUIManager gui) {

        this.buttonsTable = new JPanel(new GridLayout(1, 16, 3, 3));

        this.previous = new JButton(" << Previous");
        this.next = new JButton("Next >> ");
        this.next.setActionCommand("Next");

        this.today = new JButton("Today");


        this.day = new JToggleButton("Day");
        this.day.setActionCommand("Day");
        this.day.addActionListener(new ChangeToTable<Day>(gui, new Day()));

        this.week = new JToggleButton("Week");
        this.week.setActionCommand("Week");
        this.week.addActionListener(
                new ChangeToTable<Week>(gui, new Week(new Date(), gui.getEventsList())));

        this.month = new JToggleButton("Month");
        this.month.setActionCommand("Month");
        this.month.addActionListener(new ChangeToTable<Month>(gui, new Month()));

        this.year = new JToggleButton("Year");
        this.year.setActionCommand("Year");
        this.year.setSelected(true);
        this.year.addActionListener(new ChangeToTable<Year>(gui,
                new Year(gui.getCurrentDate().getTime(), gui.getEventsList())));

        this.buttonsTable.add(previous);

        for (int i = 0; i < 4; i++) {

            this.buttonsTable.add(constructEmptyButton());
        }

        this.selectTable = new ButtonGroup();

        this.selectTable.add(this.day);
        this.selectTable.add(this.week);
        this.selectTable.add(this.month);
        this.selectTable.add(this.year);

        this.next.addActionListener(new GoNextPrev(gui, this.selectTable));
        this.previous.addActionListener(new GoNextPrev(gui, this.selectTable));
        this.today.addActionListener(new ChangeToday(gui, this.selectTable));


        this.buttonsTable.add(this.day);
        this.buttonsTable.add(this.week);
        this.buttonsTable.add(this.month);
        this.buttonsTable.add(this.year);

        this.buttonsTable.add(constructEmptyButton());

        this.buttonsTable.add(this.today);

        this.buttonsTable.add(constructEmptyButton());

        this.buttonsTable.add(this.next);

        this.buttonsTable.setBorder(new EmptyBorder(15, 20, 5, 20));
    }

    /**
     * 
     */
    private JButton constructEmptyButton() {

        JButton emptyButton = new JButton();
        emptyButton.setEnabled(false);
        emptyButton.setBorder(BorderFactory.createEmptyBorder());

        return emptyButton;
    }

    @Override
    public JComponent getComponent() {
        return this.buttonsTable;
    }

    @Override
    public void setEventsList(EventKeeper ek) {
        // TODO Auto-generated method stub

    }

    @Override
    public EventKeeper getEventsList() {
        // TODO Auto-generated method stub
        return null;
    }

}
