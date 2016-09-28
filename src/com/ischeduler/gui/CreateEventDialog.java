package com.ischeduler.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ischeduler.domain.Event;
import com.ischeduler.domain.EventKeeper;
import com.ischeduler.domain.EventType;
import com.ischeduler.domain.ToDoType;
import com.ischeduler.gui.gridbuttons.MyJToggleButton;

public class CreateEventDialog implements ActionListener {

    private JPanel         dialogPane;
    private JScrollPane    eventList;
    private JPanel         eventsListPane;
    private JPanel         eventPane;

    private final Calendar currDate;

    private JRadioButton   typeEvent;
    private JRadioButton   typeTODO;
    private ButtonGroup    type;

    private JTextField     title;

    private JTextField     startDate;
    private JTextField     endDate;

    private JTextArea      description;
    private JRadioButton   priority1;
    private JRadioButton   priority2;
    private JRadioButton   priority3;
    private ButtonGroup    priorityType;


    private JRadioButton   reminder1;
    private JRadioButton   reminder2;
    private ButtonGroup    reminderType;

    private JTextField     reminderDate;

    private EventKeeper    eventsListForToday;
    private EventKeeper    eventsList;
    private ButtonGroup    eventGroup;

    public CreateEventDialog(Date date) {

        this.currDate = Calendar.getInstance(Locale.getDefault());
        this.currDate.setTime(date);

        fillAndPaint();
        fillFields(new EventType(this.currDate.getTime()));
    }

    // public CreateEventDialog(List<Event> events, Date date) {
    public CreateEventDialog(EventKeeper events, Date date) {

        this(date);

        this.eventsList = events;
        this.eventsListForToday = new EventKeeper();
        this.eventsListForToday.setEventList(this.eventsList.getEventsForDay(date));
        createListPane();

        this.dialogPane.add(southButtons(), BorderLayout.SOUTH);

        Event event = this.eventsListForToday.getEvent(0);
        fillFields(event);
    }

    public CreateEventDialog(EventKeeper events, Date date, int i) {

        this(date);

        this.eventsList = events;
        this.eventsListForToday = new EventKeeper();
        this.eventsListForToday.setEventList(this.eventsList.getEventsForHour(date));
        createListPane();

        this.dialogPane.add(southButtons(), BorderLayout.SOUTH);

        Event event = this.eventsListForToday.getEvent(0);
        fillFields(event);
    }
    
    private void createListPane() {
        int nrOfEvents = this.eventsListForToday.size();
        this.eventsListPane = new JPanel(new GridLayout(nrOfEvents, 1));

        this.eventGroup = new ButtonGroup();

        Iterator<Event> it = this.eventsListForToday.getEventList().iterator();
        int i = 1;
        while (it.hasNext()) {

            Event e = it.next();

            JToggleButton buttonEvent =
                    new JToggleButton(i + ". " + e.getTitle() + " " + e.getStartDate());

            buttonEvent.setActionCommand(Integer.toString(i));
            buttonEvent.addActionListener(this);

            this.eventsListPane.add(buttonEvent);
            this.eventGroup.add(buttonEvent);

            if (i == 1) {

                buttonEvent.setSelected(true);
            }

            i++;
        }
        this.eventList = new JScrollPane(this.eventsListPane);
        this.eventList.setPreferredSize(new Dimension(300, 100));
        this.eventList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.dialogPane.add(this.eventList, BorderLayout.NORTH);
    }


    public JPanel southButtons() {

        JPanel area = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton modiffyButton = new JButton("Modiffy Event");
        modiffyButton.setActionCommand("modiffy");
        modiffyButton.addActionListener(this);

        JButton isDoneButton = new JButton("Is Done!");
        isDoneButton.setActionCommand("done");
        isDoneButton.addActionListener(this);

        JButton deleteButton = new JButton("Delete Event");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(this);


        area.add(modiffyButton);
        area.add(isDoneButton);
        area.add(deleteButton);

        return area;
    }

    public void repaintList(int nrOfEvents) {
        this.dialogPane.remove(this.eventList);
        this.dialogPane.revalidate();
        createListPane();
        this.dialogPane.repaint();
        this.dialogPane.revalidate();
    }

    /**
     * 
     */
    private void fillAndPaint() {


        initializeComponents();

        setRadioTypeOption();
        setRadioReminderOption();
        fillPanelWithLabels();
        addComponentOnPanel();

        this.dialogPane.setLayout(new BorderLayout());
        this.dialogPane.add(this.eventPane, BorderLayout.CENTER);
    }

    private void initializeComponents() {

        this.dialogPane = new JPanel();
        this.eventPane = new JPanel();

        this.title = new JTextField(30);
        this.description = new JTextArea(2, 30);
        this.startDate = new JTextField(30);
        this.endDate = new JTextField(30);
        this.reminderDate = new JTextField(30);

        this.typeEvent = new JRadioButton("Event");
        this.typeTODO = new JRadioButton("To Do");

        this.reminder1 = new JRadioButton("ON");
        this.reminder2 = new JRadioButton("OFF");

        this.type = new ButtonGroup();
        this.reminderType = new ButtonGroup();
    }

    /**
     * @param eventsListPane
     * @param event
     */
    private void fillFields(Event event) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        this.title.setText(event.getTitle());
        this.description.setText(event.getDescription());
        this.startDate.setText(dateFormat.format(event.getStartDate()));
        this.endDate.setText(dateFormat.format(event.getEndDate()));
        this.reminderDate.setText(dateFormat.format(event.getReminderDate()));

        if (event.isReminder()) {

            this.reminder1.setSelected(true);
        } else {

            this.reminder2.setSelected(true);
        }
    }

    private void setRadioTypeOption() {

        this.typeEvent.setActionCommand("Event");
        this.typeEvent.setSelected(true);
        this.typeTODO.setActionCommand("TODO");

        this.type.add(this.typeEvent);
        this.type.add(this.typeTODO);
    }

    private JPanel setRadioReminderOption() {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        JPanel table = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.reminder1.setActionCommand("ON");
        this.reminder2.setActionCommand("OFF");

        this.reminderType.add(reminder1);
        this.reminderType.add(reminder2);

        table.add(this.reminder1);
        table.add(this.reminder2);

        return table;
    }

    private JPanel setRadioPriorityOption() {

        JPanel table = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.priority1 = new JRadioButton("Low");
        this.priority1.setActionCommand("LOW");

        this.priority2 = new JRadioButton("Medium");
        this.priority2.setActionCommand("MEDIUM");

        this.priority3 = new JRadioButton("High");
        this.priority3.setActionCommand("HIGH");

        this.priorityType = new ButtonGroup();

        this.priorityType.add(priority1);
        this.priorityType.add(priority2);
        this.priorityType.add(priority3);

        table.add(this.priority1);
        table.add(this.priority2);
        table.add(this.priority3);

        return table;
    }

    public void fillPanelWithLabels() {

        this.eventPane.setLayout(new BorderLayout());

        JPanel north = new JPanel(new GridLayout(1, 3));

        JLabel t1 = new JLabel("Choose type of the event: ", JLabel.CENTER);
        north.add(t1);

        north.add(this.typeEvent);
        north.add(this.typeTODO);

        north.setBorder(new EmptyBorder(0, 0, 10, 0));

        this.eventPane.add(north, BorderLayout.NORTH);

        JPanel left = new JPanel(new GridLayout(8, 1));

        JLabel t2 = new JLabel("Title: ", JLabel.RIGHT);
        JLabel t21 = new JLabel("Start Date: ", JLabel.RIGHT);
        JLabel t22 = new JLabel("End Date: ", JLabel.RIGHT);
        JLabel t3 = new JLabel("Description: ", JLabel.RIGHT);
        JLabel t4 = new JLabel("Set Priority: ", JLabel.RIGHT);
        JLabel t5 = new JLabel("Active Reminder: ", JLabel.RIGHT);
        JLabel t6 = new JLabel("Set Reminder Date: ", JLabel.RIGHT);

        left.add(t2);
        left.add(t21);
        left.add(t22);
        left.add(new JLabel(" "));
        left.add(t3);
        left.add(t4);
        left.add(t5);
        left.add(t6);
        left.setBorder(new EmptyBorder(5, 10, 10, 0));

        this.eventPane.add(left, BorderLayout.WEST);
    }

    private void addComponentOnPanel() {

        JPanel center = new JPanel(new GridLayout(8, 1));

        center.add(this.title);
        center.add(this.startDate);
        center.add(this.endDate);
        center.add(new JLabel(" "));
        center.add(this.description);
        center.add(setRadioPriorityOption());
        center.add(setRadioReminderOption());
        center.add(this.reminderDate);

        center.setBorder(new EmptyBorder(5, 10, 10, 10));

        this.eventPane.add(center, BorderLayout.CENTER);
    }

    public JPanel getDialog() {

        return this.dialogPane;
    }

    public Calendar getCurrDate() {
        return currDate;
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

    @Override
    public void actionPerformed(ActionEvent e) {

        // int index = Integer.parseInt(e.getActionCommand()) - 1;
        int index = Integer.parseInt(this.eventGroup.getSelection().getActionCommand()) - 1;

        if (e.getActionCommand().equals("done")) {
            
            Event ev = this.eventsListForToday.getEvent(index);
            this.eventsListForToday.getEventList().remove(ev);
            this.eventsList.getEventList().remove(ev);
            
            ev.setDone(true);
            this.eventsList.addEvent(ev);
            
        } else if (e.getActionCommand().equals("delete")) {

            this.eventsList.getEventList().remove(this.eventsListForToday.deleteEvent(index));
            repaintList(this.eventsListForToday.size());
        } else if (e.getActionCommand().equals("modiffy")) {
            
            this.eventsList.getEventList().remove(this.eventsListForToday.deleteEvent(index));
            createAndSaveAnEvent();
            repaintList(this.eventsListForToday.size());
        }else{
            
            fillFields(this.eventsListForToday.getEvent(index)); 
        }
    }

    public void createAndSaveAnEvent() {

        Event event;

        ButtonModel type = (ButtonModel) this.type.getSelection();
        if (type.getActionCommand().equals("Event")) {

            event = new EventType();
        } else {
            event = new ToDoType();
        }
        this.title.revalidate();
        this.title.repaint();
        event.setTitle(this.title.getText());
        event.setDescription(this.description.getText());

        type = (ButtonModel) this.reminderType.getSelection();

        if (type.getActionCommand().equals("ON")) {

            event.setReminder(true);
        } else {

            event.setReminder(false);
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            event.setStartDate(dateFormat.parse(this.startDate.getText()));
            event.setEndDate(dateFormat.parse(this.endDate.getText()));
            event.setReminderDate(dateFormat.parse(this.reminderDate.getText()));

            this.eventsList.addEvent(event);
            this.eventsListForToday.addEvent(event);

        } catch (ParseException e1) {

            JOptionPane.showMessageDialog(this.getDialog(),
                    "Please try again and enter a valid date", "Not a Date!",
                    JOptionPane.WARNING_MESSAGE);

            event = null;
        }
System.out.println(event);
    }
}
