package com.ischeduler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JToggleButton;

import com.ischeduler.domain.Event;
import com.ischeduler.domain.EventKeeper;

public class MyJToggleButton extends JToggleButton {

    private final EventKeeper events;
    private final Calendar    currentDate;

    public MyJToggleButton(String title, Date date) {

        super(title);
        this.currentDate = Calendar.getInstance();
        this.currentDate.setTime(date);
        this.events = new EventKeeper();
    }

    public void changeBackgroundForEvents() {

        Iterator<Event> it = this.events.getEventList().iterator();
        while (it.hasNext()) {

            Event e = it.next();
            if (!e.isDone()) {
                this.setBackground(Color.RED);
                return;
            }
        }
        if (events.allDone()) {
            this.setBackground(Color.GREEN);
            return;
        }
        this.setBackground(Color.WHITE);
    }
    @Override
    protected final void paintComponent(Graphics g){

        changeBackgroundForEvents();
        super.paintComponent(g);
    }

    public EventKeeper getEvents() {
        return events;
    }
    
}
