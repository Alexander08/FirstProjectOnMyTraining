package com.ischeduler.gui.table;

import java.awt.Component;

import com.ischeduler.domain.EventKeeper;

public interface TableManager {

    Component getComponent();
    
    void setEventsList(EventKeeper ek);

    EventKeeper getEventsList();
}
