package com.ischeduler.gui.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Day implements TableManager {

    private JPanel   dayTable;
    private JLabel   dayHeader;
    private JPanel   hoursGrid;
    private Calendar currentDate;
    private Locale   localeZone;


    public Day() {

        this(new Date());
    }

    public Day(Date date) {
        
        super();

        this.dayTable = new JPanel(new BorderLayout());

        this.setDate(date);
        this.setDayHeader();
        this.constructDayGrid();

    }

    /**
     * 
     */
    private void setDayHeader() {

        DateFormat dateFormat = new SimpleDateFormat("EEEE d MMM yyyy");
        String nameOfDay = dateFormat.format(currentDate.getTime());

        dayHeader = new JLabel(nameOfDay.toUpperCase());
        dayHeader.setHorizontalAlignment(SwingConstants.CENTER);
       
        this.dayTable.add(this.dayHeader, BorderLayout.NORTH);
    }

    /**
     * @param date
     */
    private void setDate(Date date) {

        this.localeZone = Locale.getDefault();
        this.currentDate = Calendar.getInstance(this.localeZone);
        this.currentDate.setTime(date);
        this.currentDate.set(Calendar.MINUTE, 0);
        this.currentDate.set(Calendar.HOUR_OF_DAY, 0);
    }

    /**
     * 
     */
    private void constructDayGrid() {

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");

        this.hoursGrid = new JPanel(new GridLayout(24, 1, 2, 2));
        this.hoursGrid.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        for (int i = 0; i < 24; i++) {

            JButton hour = new JButton(hourFormat.format(this.currentDate.getTime()));

            hour.setBackground(Color.WHITE);
            hour.setActionCommand(dateFormat.format(this.currentDate.getTime()));
            // --------------------->>>> here is where you can add Listeners for day
            this.hoursGrid.add(hour);

            this.currentDate.roll(Calendar.HOUR_OF_DAY, 1); // possibly need i instead of 1
        }

        this.dayTable.add(this.hoursGrid, BorderLayout.CENTER);
    }

    @Override
    public JComponent getComponent() {

        return this.dayTable;
    }

}
