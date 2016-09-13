package com.ischeduler.main;

import java.util.Date;

import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.Month;
import com.ischeduler.gui.table.Year;

public class Main {

    public static void main(String[] args) {

        // GUIManager<Month> gui = new GUIManager<Month>(new Month(new Date()));

        GUIManager gui = new GUIManager(new Year(new Date()));
        gui.launchFrame();
        gui.setRightSide();

    }
}
