package com.ischeduler.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;

import com.ischeduler.domain.EventKeeper;
import com.ischeduler.gui.GUIManager;
import com.ischeduler.gui.table.TableManager;

// doesnt work because save the object
// so, the same instance of the class is called again when the button is pressed
// i think that the reference of table is removed from the pane,
// in documentation, didnt say anything in delete the object
// anyway, an object can be deleted permanently, if it has no reference anymore,
// and when gc is called, can be removed
// but i keep a reference right here


public /* abstract */ class ChangeToTable<T extends TableManager> implements ActionListener {

    protected final GUIManager gui;
    protected T                table;

    public ChangeToTable(GUIManager gui, T pane) {
        super();

        this.gui = gui;
        this.table = pane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final Calendar date = gui.getCurrentDate();
        date.setTime(new Date());

        try {

            Class[] argType = new Class[] {Date.class, EventKeeper.class};

            TableManager newTable = this.table.getClass().getDeclaredConstructor(argType)
                    .newInstance(date.getTime(), gui.getEventsList());

            this.gui.changeTable(newTable);
            this.gui.getWindow().getContentPane().revalidate();// necessary for applet
            this.gui.getWindow().getContentPane().repaint(); // necessary for applet

        } catch (InstantiationException e1) {

            e1.printStackTrace();
        } catch (Exception e1) {

            e1.printStackTrace();
        }
    }

}
