package com.ischeduler.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ischeduler.gui.table.TableManager;
import com.ischeduler.listener.ChangeToMonthTable;
import com.ischeduler.listener.ChangeToWeekTable;
import com.ischeduler.listener.ChangeToDayTable;
import com.ischeduler.listener.ChangeToYearTable;

public class TopButtons implements TableManager {

    private JPanel  buttonsTable;
    private JButton previous;
    private JButton next;
    private JButton today;
    private JButton day; // change with toggle button - give you two state ;)
    private JButton week;
    private JButton month;
    private JButton year;

    public TopButtons(GUIManager gui) {

        this.buttonsTable = new JPanel(new GridLayout(1, 16, 3, 3));

        this.previous = new JButton(" << Previous");
        this.next = new JButton("Next >> ");
        this.today = new JButton("Today");

        this.day = new JButton("Day");
        this.day.addActionListener(new ChangeToDayTable(gui));

        this.week = new JButton("Week");
        this.week.addActionListener(new ChangeToWeekTable(gui));

        this.month = new JButton("Month");
        this.month.addActionListener(new ChangeToMonthTable(gui));

        this.year = new JButton("Year");
        this.year.addActionListener(new ChangeToYearTable(gui));

        this.buttonsTable.add(previous);

        for (int i = 0; i < 4; i++) {

            this.buttonsTable.add(constructEmptyButton());
        }

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

}
