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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Week implements TableManager {

    private JPanel   weekTable;
    private JLabel   weekHeader;
    private JPanel   weekGrid;
    private Calendar currentDate;
    private Locale   localZone;


    public Week() {

        this(new Date());
    }

    public Week(Date date) {

        super();
        
        this.weekTable = new JPanel(new BorderLayout());

        this.setDate(date);
        this.setHeaderOfWeek();
        this.setGridOfWeek();
    }


    /**
     * 
     */
    private void setGridOfWeek() {

        this.weekGrid = new JPanel(new GridLayout(1, 7, 1, 1));

        for (int i = 0; i < 7; i++) {

            Day day = new Day(this.currentDate.getTime());
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
        
        Font fontForHeader = new Font(this.weekHeader.getName(), Font.PLAIN,
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

}
