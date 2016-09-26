package com.ischeduler.listener.manipulatetable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

import com.ischeduler.domain.Event;
import com.ischeduler.domain.EventKeeper;
import com.ischeduler.domain.EventType;
import com.ischeduler.domain.ToDoType;
import com.ischeduler.gui.CreateEventDialog;
import com.ischeduler.gui.MyJToggleButton;

public class ChangeOnClick extends MouseAdapter {

    private final EventKeeper events;

    public ChangeOnClick(EventKeeper ek) {
        super();
        this.events = ek;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {

            CreateEventDialog dialog = new CreateEventDialog();

            int result = JOptionPane.showConfirmDialog(null, dialog.getDialog(),
                    "Create New Event ", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                Event event;

                ButtonModel type = (ButtonModel) dialog.getType().getSelection();
                if (type.getActionCommand().equals("Event")) {

                    event = new EventType();
                } else {
                    event = new ToDoType();
                }
                event.setTitle(dialog.getTitle().getText());
                event.setDescription(dialog.getDescription().getText());

                type = (ButtonModel) dialog.getReminderType().getSelection();
                
                if (type.getActionCommand().equals("ON")) {
                
                    event.setReminder(true);
                } else {
                    
                    event.setReminder(false);
                }

                DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.LONG);
                try {
                    event.setStartDate(dateFormat.parse(dialog.getStartDate().getText()));
                    event.setStartDate(dateFormat.parse(dialog.getEndDate().getText()));
                    event.setStartDate(dateFormat.parse(dialog.getReminderDate().getText()));
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                this.events.addEvent(event);
                ((MyJToggleButton)e.getSource()).repaint();
                System.out.println(event);
            }
        }
        
    }

}
