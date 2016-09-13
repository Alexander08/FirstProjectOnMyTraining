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

public class Year implements TableManager {

    private JPanel   yearTable;

    private JLabel   yearHeaderLabel;
    private JPanel   monthsGrid;

    private Calendar currentDate;
    private Locale   localZone;


    public Year() {

        this(new Date());
    }   
    
    public Year(Date date) {

        this.yearTable = new JPanel(new BorderLayout());

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

        for (int i = 1; i <= 12; i++) {

            JPanel month = ((JPanel) new Month(this.currentDate.getTime()).getComponent());
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
        
        Font fontForHeader = new Font(this.yearHeaderLabel.getName(), Font.PLAIN,
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

}
