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
import javax.swing.SwingConstants;

import com.ischeduler.domain.EventKeeper;
import com.ischeduler.gui.gridbuttons.MyJToggleButton;
import com.ischeduler.listener.manipulatetable.AddOrEditEventOnClickDay;

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
/////////////////////////////////////// ------------------------------------?>> Dont forget to
/////////////////////////////////////// extend JToggledButton for adding date field and a list of
/////////////////////////////////////// events for that day it will be easy to implement all the
/////////////////////////////////////// functionality. The other thing you can do is to use
/////////////////////////////////////// ActionComand() on buttons, but as you saw last time, it is a
/////////////////////////////////////// little bit harder to use that method... and also limited to
/////////////////////////////////////// just a string. So if u use a specialized class to do that
/////////////////////////////////////// job will be much better. Lets see, if YourJbuttonClass will
/////////////////////////////////////// keep a list of events and a date (not really necessary) and
/////////////////////////////////////// eventually a checker (not necessary again - you can take it
/////////////////////////////////////// form list). oki, so what your button must do: - store an
/////////////////////////////////////// event; - change color when there are events for that day. -
/////////////////////////////////////// open a window to manage events - keep that on mind when you
/////////////////////////////////////// implement that



public class Month implements TableManager {

    private JPanel            monthTable;
    private JLabel            monthHeaderLabel;

    private JPanel            daysTable;
    private JPanel            daysGrid;
    private final ButtonGroup group;
    private JPanel            daysHeaderLabel;

    private Calendar          currentDate;
    private Locale            localZone;

    private final EventKeeper eventsList;


    public Month() {
        this(new Date());
    }

    public Month(Date date, ButtonGroup... group) {

        super();

        this.eventsList = new EventKeeper();

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

    public Month(Date date, EventKeeper ek) {
        
        this(date);
        this.eventsList.setEventList(ek.getEventList());
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

            MyJToggleButton day = new MyJToggleButton(df.format(this.currentDate.getTime()),
                    this.currentDate.getTime(), this.eventsList);
            day.setEnabled(true);
            day.setBackground(Color.WHITE);

            day.addMouseListener(new AddOrEditEventOnClickDay(this.eventsList));

            this.group.add(day);
            this.daysGrid.add(day);
            day.repaint();
            this.daysGrid.repaint();
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

    @Override
    public void setEventsList(EventKeeper ek) {
        this.eventsList.setEventList(ek.getEventList());;

    }

    @Override
    public EventKeeper getEventsList() {
        // TODO Auto-generated method stub
        return this.eventsList;
    }



}
