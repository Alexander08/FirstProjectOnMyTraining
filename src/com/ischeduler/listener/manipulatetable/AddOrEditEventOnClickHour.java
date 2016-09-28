package com.ischeduler.listener.manipulatetable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.ischeduler.domain.Event;
import com.ischeduler.domain.EventKeeper;
import com.ischeduler.domain.EventType;
import com.ischeduler.domain.ToDoType;
import com.ischeduler.gui.CreateEventDialog;
import com.ischeduler.gui.gridbuttons.MyJToggleButtonHour;

public class AddOrEditEventOnClickHour extends MouseAdapter {

    private final EventKeeper events;

    public AddOrEditEventOnClickHour(EventKeeper ek) {
        super();
        this.events = ek;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        MyJToggleButtonHour button = (MyJToggleButtonHour) e.getSource();
        CreateEventDialog dialog;

        if (e.getClickCount() == 2) {

            dialog = new CreateEventDialog(button.getCurrentDate().getTime());

            int result = JOptionPane.showConfirmDialog(null, dialog.getDialog(),
                    "Create New Event ", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                fillEventWithData(e, dialog);
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {

            List<Event> eventsForDay = events.getEventsForHour(button.getCurrentDate().getTime());

            if (!eventsForDay.isEmpty()) {

                dialog = new CreateEventDialog(this.events, button.getCurrentDate().getTime(), 1);

                int result = JOptionPane.showConfirmDialog(null, dialog.getDialog(),
                        "Update Event ", JOptionPane.OK_CANCEL_OPTION);
            }
        }
    }

    /**
     * @param e
     * @param dialog
     */
    private void fillEventWithData(MouseEvent e, CreateEventDialog dialog) {
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

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            event.setStartDate(dateFormat.parse(dialog.getStartDate().getText()));
            event.setEndDate(dateFormat.parse(dialog.getEndDate().getText()));
            event.setReminderDate(dateFormat.parse(dialog.getReminderDate().getText()));

            this.events.addEvent(event);
            ((MyJToggleButtonHour) e.getSource()).repaint();

        } catch (ParseException e1) {

            JOptionPane.showMessageDialog(dialog.getDialog(),
                    "Please try again and enter a valid date", "Not a Date!",
                    JOptionPane.WARNING_MESSAGE);

            event = null;
        }
        System.out.println(event);
    }

}
