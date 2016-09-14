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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.ischeduler.listener.manipulatetable.ChangeOnClick;

/**
 * Class Month it is used to display a Month of a year in a JPanel Can be used to display any month
 * of the year
 * 
 * @param: monthHeaderLabel - used for displaying Name of each day
 * @param: daysTable - used to display all days in a month
 * @param: currentDate - used to keep some information about the time
 * 
 *         New in iScheduler2:
 * 
 * @param: localZone - used for adjusting name for each zone
 * @param: daysHeaderTable - used to display day name
 * 
 * 
 */


public class Month implements TableManager {

    private JPanel            monthTable;
    private JLabel            monthHeaderLabel;

    private JPanel            daysTable;
    private JPanel            daysGrid;
    private final ButtonGroup group;
    private JPanel            daysHeaderLabel;

    private Calendar          currentDate;
    private Locale            localZone;


    public Month(Date date, ButtonGroup... group) {

        super();

        if (group.length == 0 || group[0] == null) {
            this.group = new ButtonGroup();

        } else {
            this.group = group[0];
        }
        setDate(date);

        setMonthHeader();

        setDayHeader();
        setDaysGrid();
        this.monthTable = new JPanel(new BorderLayout());
        this.monthTable.add(this.monthHeaderLabel, BorderLayout.NORTH);
        this.monthTable.add(this.daysTable, BorderLayout.CENTER);
    }


    /**
     * 
     */
    private void setDaysGrid() {

        this.daysGrid = new JPanel(new GridLayout(0, 7, 1, 1));

        int dayOfWeek;
        DateFormat df = new SimpleDateFormat("dd");
        int maximNumberOfDays = this.currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);

        while (this.currentDate.get(Calendar.DAY_OF_MONTH) <= maximNumberOfDays) {

            dayOfWeek = (this.currentDate.get(Calendar.DAY_OF_WEEK) + 5) % 7;

            if (dayOfWeek > (this.daysGrid.getComponentCount())) {

                constructDay(df, true);

            } else if (this.currentDate.get(Calendar.DAY_OF_MONTH) == maximNumberOfDays) {

                constructDay(df, false);
                break;
            } else {

                constructDay(df, false);
                this.currentDate.roll(Calendar.DAY_OF_MONTH, 1);
            }
        }
        this.daysTable.add(this.daysGrid, BorderLayout.CENTER);
    }


    /**
     * @param df
     * @param isEmpty
     */
    private void constructDay(DateFormat df, boolean isEmpty) {

        if (!isEmpty) {

            JToggleButton day = new JToggleButton(df.format(this.currentDate.getTime()));
            day.setEnabled(true);
            day.setBackground(Color.WHITE);
            // here you add listeners for days ------------------------???>>>>>

            day.addMouseListener(new ChangeOnClick());

            this.group.add(day);
            this.daysGrid.add(day);
        } else {

            JButton day = new JButton();
            day.setEnabled(false);
            day.setBorder(BorderFactory.createEmptyBorder());
            this.daysGrid.add(day);
        }
    }

    /**
     * @param date
     */
    private void setDate(Date date) {

        this.localZone = Locale.getDefault();
        this.currentDate = Calendar.getInstance(this.localZone);
        this.currentDate.setFirstDayOfWeek(Calendar.MONDAY); // Good work Silviu! ^^
        this.currentDate.setTime(date);

        // Set First day of the month = 1
        this.currentDate.set(Calendar.DAY_OF_MONTH,
                this.currentDate.getActualMinimum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 
     */
    private void setMonthHeader() {

        DateFormat df = new SimpleDateFormat("MMM yyyy");
        String monthHeaderLabelText = df.format(this.currentDate.getTime());

        this.monthHeaderLabel = new JLabel(monthHeaderLabelText);
        this.monthHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.daysTable = new JPanel(new BorderLayout());
    }

    /**
     * Setting Day Header for Table (internal use method) Buttons are disabled, Background color is
     * Grey Text color is White
     */
    private void setDayHeader() {

        DateFormat df = new SimpleDateFormat("EEEE");
        this.daysHeaderLabel = new JPanel(new GridLayout(1, 7, 1, 1));

        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        for (int i = 0; i < 7; i++) {

            JButton day = new JButton(df.format(c.getTime()));
            day.setEnabled(false);
            day.setBackground(Color.GRAY);
            // ***************************** - - ->> this is the place where you can add Listeners
            this.daysHeaderLabel.add(day);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        this.daysTable.add(this.daysHeaderLabel, BorderLayout.NORTH);
    }

    @Override
    public JComponent getComponent() {

        return this.monthTable;
    }


    public ButtonGroup getGroup() {
        return group;
    }

    

}
