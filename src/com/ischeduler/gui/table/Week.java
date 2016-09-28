package com.ischeduler.gui.table;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.ischeduler.domain.EventKeeper;


public class Week implements TableManager {

    private JPanel            weekTable;
    private JLabel            weekHeader;
    private JPanel            weekGrid;

    private Calendar          currentDate;
    private Locale            localZone;
    private final EventKeeper eventsList;



    public Week() {

        this(new Date(), new EventKeeper());
    }

    public Week(Date date, EventKeeper ek) {

        super();

        this.weekTable = new JPanel(new BorderLayout());

        this.eventsList = new EventKeeper();
        this.eventsList.setEventList(ek.getEventList());

        this.setDate(date);
        this.setHeaderOfWeek();
        this.setGridOfWeek();
    }


    /**
     * 
     */
    private void setGridOfWeek() {

        this.weekGrid = new JPanel(new GridLayout(1, 7, 1, 1));

        Day day = new Day(this.currentDate.getTime());
        for (int i = 0; i < 7; i++) {

           day = new Day(this.currentDate.getTime(), day.getGroup());
           day.setEventsList(this.eventsList);

            // this.group.add(day);
            this.weekGrid.add(day.getComponent());

            this.currentDate.add(Calendar.DAY_OF_WEEK, 1);
        }

        this.weekTable.add(this.weekGrid, BorderLayout.CENTER);
    }


    /**
     * 
     */
    private void setHeaderOfWeek() {

        DateFormat df = new SimpleDateFormat("w --- MMM yyyy");
        String nameWeek = df.format(currentDate.getTime());

        this.weekHeader = new JLabel("Week number: " + nameWeek);
        this.weekHeader.setHorizontalAlignment(SwingConstants.CENTER);

        Font fontForHeader = new Font(this.weekHeader.getName(), Font.BOLD,
                this.weekHeader.getFont().getSize() * 2);

        this.weekHeader.setFont(fontForHeader);
        this.weekHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        this.weekTable.add(this.weekHeader, BorderLayout.NORTH);
    }


    /**
     * @param date
     */
    private void setDate(Date date) {

        this.localZone = Locale.getDefault();
        this.currentDate = Calendar.getInstance(this.localZone);
        this.currentDate.setTime(date);

        this.currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    }


    @Override
    public JComponent getComponent() {

        return this.weekTable;
    }

    @Override
    public void setEventsList(EventKeeper ek) {
        this.eventsList.setEventList(ek.getEventList());

    }

    @Override
    public EventKeeper getEventsList() {
        return this.eventsList;
    }

}
