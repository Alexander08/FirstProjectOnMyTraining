package com.ischeduler.main;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Day;
import com.ischeduler.gui.table.Year;

public class Main {

    public static void main(String[] args) {

        // GUIManager<Month> gui = new GUIManager<Month>(new Month(new Date()));

        GUIManager gui = new GUIManager(new Day()); 
        // i will set Selected for year by default!
        gui.changeTable( new Year( gui.getCurrentDate().getTime(), gui.getEventsList() ));
        gui.launchFrame();
        gui.setRightSide();

    }
}
