package com.ischeduler.domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Different type of comparators will be implemented as nested class of this class, they will be 
 * as nested class, and, because this class will be load by JVM by default when the program start
 * because is a very important class, all the comparators will have an getInstance() method dedicated
 * for every type of comparison, to have access to them everywhere 
 * */
public class EventKeeper {
    
    private List<Event> eventList;
    
    
    public EventKeeper(){
        
        super();
        eventList = new ArrayList<Event>();
    }


    public List<Event> getEventList() {
        return eventList;
    }


    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
    
    public boolean allDone(){
        
        if(this.eventList.isEmpty()){
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
    public void addEvent(Event e){
        this.eventList.add(e);
    }
}
