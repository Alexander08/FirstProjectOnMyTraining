package com.ischeduler.gui.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ischeduler.domain.EventKeeper;

public class Year implements TableManager {

    private JPanel            yearTable;

    private JLabel            yearHeaderLabel;
    private JPanel            monthsGrid;

    private Calendar          currentDate;
    private Locale            localZone;

    private final EventKeeper eventsList;

    public Year() {
        
        this(new Date(), new EventKeeper());
    }

    public Year(Date date, EventKeeper ek) {
        super();
        this.yearTable = new JPanel(new BorderLayout());

        this.eventsList = new EventKeeper();
        this.eventsList.setEventList(ek.getEventList());

        setDateForYear(date);

        setHeaderForYear();
        this.yearTable.add(this.yearHeaderLabel, BorderLayout.NORTH);

        setMonthGridForYear();
        this.yearTable.add(this.monthsGrid, BorderLayout.CENTER);

    }


    /**
     * 
     */
    private void setMonthGridForYear() {

        this.monthsGrid = new JPanel(new GridLayout(4, 3, 5, 5));

        this.monthsGrid.setBorder(new EmptyBorder(5, 10, 10, 10));

        Month monthTable = new Month(this.currentDate.getTime());

        for (int i = 1; i <= 12; i++) {
            
            monthTable = new Month(this.currentDate.getTime(), monthTable.getGroup());
            monthTable.setEventsList(this.eventsList);
            
            JPanel month = ((JPanel) monthTable.getComponent());
            month.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
            
            this.monthsGrid.add(month);
            this.currentDate.set(Calendar.MONTH, i);
        }
    }


    /**
     * 
     */
    private void setHeaderForYear() {

        DateFormat df = new SimpleDateFormat("yyyy");

        this.yearHeaderLabel = new JLabel("-- " + df.format(this.currentDate.getTime()) + " --");

        this.yearHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Font fontForHeader = new Font(this.yearHeaderLabel.getName(), Font.BOLD,
                this.yearHeaderLabel.getFont().getSize() * 2);

        this.yearHeaderLabel.setFont(fontForHeader);
    }


    /**
     * @param date
     */
    private void setDateForYear(Date date) {

        this.localZone = Locale.getDefault();
        this.currentDate = Calendar.getInstance(this.localZone);
        this.currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        this.currentDate.setTime(date);

        // Set First month of the year = January
        this.currentDate.set(Calendar.MONTH, 0);
    }


    @Override
    public JComponent getComponent() {

        return this.yearTable;
    }

    @Override
    public void setEventsList(EventKeeper ek) {
        this.eventsList.setEventList(ek.getEventList());

    }

    @Override
    public EventKeeper getEventsList() {
        // TODO Auto-generated method stub
        return this.eventsList;
    }

}
