package com.ischeduler.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Different type of comparators will be implemented as nested class of this class, they will be as
 * nested class, and, because this class will be load by JVM by default when the program start
 * because is a very important class, all the comparators will have an getInstance() method
 * dedicated for every type of comparison, to have access to them everywhere
 */

/**
 * You know that ArrayList for a collection af events it is a very bad idea!!!? You think was good
 * for quick access for insert and delete a new event; Another thing is access via index to the
 * elements Let's see what you miss: !Duplicate events - you need to write code for verification
 * !Delete an event will change the order of the other events in the list (index access) !Different
 * method for sorting (you need to implement them because of changing index of the element) !Sublist
 * don't have access at them without knowing the index (With set, you can cast them to priority
 * queue)
 * 
 */
public class EventKeeper implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Event>       eventList;

    public EventKeeper() {

        super();
        eventList = new ArrayList<Event>();
    }

    public int size() {
        return this.eventList.size();
    }

    public Event getEvent(int i) {
        return this.eventList.get(i);
    }

    public List<Event> getEventList() {
        return eventList;
    }


    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public boolean allDone() {

        if (this.eventList.isEmpty()) {
            return false;
        }

        Iterator<Event> it = this.eventList.iterator();
        while (it.hasNext()) {

            Event e = it.next();
            if (!e.isDone()) {
                return false;
            }
        }
        return true;
    }

    public void addEvent(Event e) {
        this.eventList.add(e);
    }

    public Event deleteEvent(int i) {

        return this.eventList.remove(i);
    }

    public void updateEventList(List<Event> list) {}


    public List<Event> getEventsForDay(Date date) {

        Iterator<Event> it = this.eventList.iterator();
        Calendar dateToCompare = prepareToCompare(date);

        List<Event> result = new ArrayList<Event>();

        while (it.hasNext()) {

            Event e = it.next();

            Calendar eventDate = prepareToCompare(e.getStartDate());
            if (dateToCompare.equals(eventDate)) {
                result.add(e);
            }
        }

        return result;
    }

    public List<Event> getEventsForHour(Date date) {

        Iterator<Event> it = this.eventList.iterator();
        Calendar dateToCompare = prepareToCompareHour(date);

        List<Event> result = new ArrayList<Event>();

        while (it.hasNext()) {

            Event e = it.next();

            Calendar eventDate = prepareToCompareHour(e.getStartDate());
            if (dateToCompare.equals(eventDate)) {
                result.add(e);
            }
        }

        return result;
    }

    /**
     * @param date
     */
    private Calendar prepareToCompare(Date date) {

        Calendar dateToCompare = Calendar.getInstance();
        dateToCompare.setTime(date);
        dateToCompare.set(Calendar.HOUR_OF_DAY, 0);
        dateToCompare.set(Calendar.MINUTE, 0);
        dateToCompare.set(Calendar.SECOND, 0);
        dateToCompare.set(Calendar.MILLISECOND, 0);

        return dateToCompare;
    }

    private Calendar prepareToCompareHour(Date date) {

        Calendar dateToCompare = Calendar.getInstance();
        dateToCompare.setTime(date);
        dateToCompare.set(Calendar.MINUTE, 0);
        dateToCompare.set(Calendar.SECOND, 0);
        dateToCompare.set(Calendar.MILLISECOND, 0);

        return dateToCompare;
    }

    public void sortEvents() {
        Collections.sort(this.eventList);
    }

    /**
     * Works nice, but on 1.8 - keep to 1.6
     */

    /*
     * public List<? super Event> getEventsForDay(Date date) {
     * 
     * final Date test = date;
     * 
     * List<Object> result = this.eventList .stream() .filter((Event e) ->
     * e.getStartDate().getTime() == test.getTime()) .collect(Collectors.toList());
     * 
     * return result; }
     */
}
