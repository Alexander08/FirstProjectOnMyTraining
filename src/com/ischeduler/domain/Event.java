package com.ischeduler.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * This class is a POJO dedicated to keep Events or ToDos in memory and manipulate in all ways Here
 * are all the necessary data for an event. From this will tow particular type of events which will
 * implement their methods and specific business.
 * 
 * Another features of this class are serializable and comparator. For easy store, and no lose of
 * data serializable will be the best thing to use, Comparable will be used for an "natural" order
 * for store data using StartDate. hashCode() and equals() will be used to compare two events and
 * give easy access to every event.
 */
public abstract class Event implements Serializable, Comparable<Event> {

    private static final long serialVersionUID = 1L;

    private String            title;
    private Date              startDate;
    private Date              endDate;
    private Date              reminderDate;
    private String            description;
    private Boolean           reminder;
    private Boolean           done;

    public Event() {

        this(new String(), new Date(), new Date(), new Date(), new String());
    }

    public Event(String title) {

        this();
        this.title = title;
    }

    public Event(Date date) {

        this(new String(), date, date, date, new String());
    }

    public Event(String title, Date startDate, Date endDate, Date reminderDate,
            String description) {
        super();
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reminderDate = reminderDate;
        this.description = description;
        this.reminder = false;
        this.done = false;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate.setTime(startDate.getTime());
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public int compareTo(Event e) {

        return this.startDate.compareTo(e.getStartDate());
    }

    @Override
    public String toString() {
        return "Event [title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
                + ", reminderDate=" + reminderDate + ", description=" + description + ", reminder="
                + reminder + ", done=" + done + "]";
    }

}
