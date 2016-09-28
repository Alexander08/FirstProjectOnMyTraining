package com.ischeduler.gui.gridbuttons;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JToggleButton;

import com.ischeduler.domain.Event;
import com.ischeduler.domain.EventKeeper;

public class MyJToggleButton extends JToggleButton {

    private final EventKeeper events;
    private Calendar currentDate;

    public MyJToggleButton(String title, Date date, EventKeeper ek) {

        super(title);
        this.events = ek;
        this.currentDate = Calendar.getInstance();
        this.currentDate.setTime(date);
    }

    public void changeBackgroundForEvents() {
        
        List<Event> eventsToday = this.events.getEventsForDay(this.currentDate.getTime());
        
        Iterator<Event> it = eventsToday.iterator();
        while (it.hasNext()) {

            Event e = it.next();
            if (!e.isDone()) {
                this.setBackground(Color.RED);
                return;
            }
        }
        EventKeeper ekk = new EventKeeper();
        ekk.setEventList(eventsToday);
        if (ekk.allDone()) {
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

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }
    
}
