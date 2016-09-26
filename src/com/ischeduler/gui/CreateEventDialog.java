package com.ischeduler.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreateEventDialog {

    private JPanel       dialogPane;

    Calendar             currDate;

    private JRadioButton typeEvent;
    private JRadioButton typeTODO;
    private ButtonGroup  type;

    private JTextField   title;

    private JTextField   startDate;
    private JTextField   endDate;

    private JTextArea    description;
    private JRadioButton priority1;
    private JRadioButton priority2;
    private JRadioButton priority3;
    private ButtonGroup  priorityType;


    private JRadioButton reminder1;
    private JRadioButton reminder2;
    private ButtonGroup  reminderType;

    private JTextField   reminderDate;

    public CreateEventDialog() {

        this.currDate = Calendar.getInstance(Locale.getDefault());
        this.currDate.setTime(new Date());

        this.dialogPane = new JPanel();

        setTypeOfEventOnPanel();

        this.title = new JTextField(30);

        // Simple date format and make this date to be more nicer
        this.startDate = new JTextField(30);
        this.startDate.setText(currDate.getTime().toString());

        this.endDate = new JTextField(30);
        this.endDate.setText(currDate.getTime().toString());

        this.description = new JTextArea(2, 30);

        setReminderOnPanel();

        fillPanel();
    }

    /**
     * 
     */
    private void setTypeOfEventOnPanel() {

        this.typeEvent = new JRadioButton("Event");
        this.typeEvent.setActionCommand("Event");
        this.typeTODO = new JRadioButton("To Do");

        this.type = new ButtonGroup();
        this.type.add(this.typeEvent);
        this.type.add(this.typeTODO);
    }

    /**
     * 
     */
    private JPanel setReminderOnPanel() {

        JPanel table = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.reminder1 = new JRadioButton("ON");
        this.reminder1.setActionCommand("ON");
        this.reminder2 = new JRadioButton("OFF");
        this.reminder2.setSelected(true);

        this.reminderType = new ButtonGroup();
        this.reminderType.add(reminder1);
        this.reminderType.add(reminder2);

        table.add(reminder1);
        table.add(reminder2);

        this.reminderDate = new JTextField(30);
        this.reminderDate.setEnabled(false);
        this.reminderDate.setText(currDate.getTime().toString());

        return table;
    }

    /**
     * 
     */
    private JPanel setPriorityOnPanel() {

        JPanel table = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.priority1 = new JRadioButton("Low");
        this.priority2 = new JRadioButton("Medium");
        this.priority3 = new JRadioButton("High");

        this.priority2.setSelected(true);
        this.priorityType = new ButtonGroup();
        this.priorityType.add(priority1);
        this.priorityType.add(priority2);
        this.priorityType.add(priority3);

        // table.add(new JLabel("Set Priority: "));
        table.add(this.priority1);
        table.add(this.priority2);
        table.add(this.priority3);

        return table;
    }

    public void fillPanel() {

        dialogPane.setLayout(new BorderLayout());

        JPanel north = new JPanel(new GridLayout(1, 3));

        JLabel t1 = new JLabel("Choose type of the event: ");
        t1.setHorizontalAlignment(SwingConstants.CENTER);
        north.add(t1);

        typeEvent.setSelected(true);

        north.add(typeEvent);
        north.add(typeTODO);
        north.setBorder(new EmptyBorder(0, 0, 10, 0));

        dialogPane.add(north, BorderLayout.NORTH);

        JPanel left = new JPanel(new GridLayout(8, 1));
        JLabel t2 = new JLabel("Title: ");
        t2.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(t2);

        JLabel t21 = new JLabel("Start Date: ");
        t21.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(t21);

        JLabel t22 = new JLabel("End Date: ");
        t22.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(t22);

        left.add(new JLabel(" "));

        JLabel t3 = new JLabel("Description: ");
        t3.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(t3);

        JLabel t4 = new JLabel("Set Priority: ");
        t4.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(t4);

        JLabel t5 = new JLabel("Active Reminder: ");
        t5.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(t5);

        JLabel t6 = new JLabel("Set Reminder Date: ");
        t6.setHorizontalAlignment(SwingConstants.RIGHT);
        left.add(t6);

        left.setBorder(new EmptyBorder(5, 10, 10, 0));

        dialogPane.add(left, BorderLayout.WEST);

        addComponentOnPanel();
    }

    /**
     * 
     */
    private void addComponentOnPanel() {
        
        JPanel center = new JPanel(new GridLayout(8, 1));

        center.add(title);

        center.add(startDate);
        center.add(endDate);
        center.add(new JLabel(" "));

        center.add(description);

        center.add(setPriorityOnPanel());

        center.add(setReminderOnPanel());
        center.add(reminderDate);

         center.setBorder(new EmptyBorder(5, 10, 10, 10));

        dialogPane.add(center, BorderLayout.CENTER);
    }

    public JPanel getDialog() {

        return this.dialogPane;
    }

    public Calendar getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Calendar currDate) {
        this.currDate = currDate;
    }

    public ButtonGroup getType() {
        return type;
    }

    public void setType(ButtonGroup type) {
        this.type = type;
    }

    public JTextField getTitle() {
        return title;
    }

    public void setTitle(JTextField title) {
        this.title = title;
    }

    public JTextField getStartDate() {
        return startDate;
    }

    public void setStartDate(JTextField startDate) {
        this.startDate = startDate;
    }

    public JTextField getEndDate() {
        return endDate;
    }

    public void setEndDate(JTextField endDate) {
        this.endDate = endDate;
    }

    public JTextArea getDescription() {
        return description;
    }

    public void setDescription(JTextArea description) {
        this.description = description;
    }

    public ButtonGroup getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(ButtonGroup priorityType) {
        this.priorityType = priorityType;
    }

    public ButtonGroup getReminderType() {
        return reminderType;
    }

    public void setReminderType(ButtonGroup reminderType) {
        this.reminderType = reminderType;
    }

    public JTextField getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(JTextField reminderDate) {
        this.reminderDate = reminderDate;
    }
    
    
}
