package com.ischeduler.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * Different type of comparators will be implemented
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
    
}
