package com.ischeduler.listener.manipulatetable;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddRightSideComponent implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {

        int state = e.getStateChange();
        
        if(state == ItemEvent.SELECTED){
            // get date and put the current date in right using gui method
        }

    }

}
