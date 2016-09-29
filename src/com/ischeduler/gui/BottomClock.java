package com.ischeduler.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class BottomClock implements ActionListener {

    private JLabel clock;

    private Timer  ticTac;

    public BottomClock() {
        
        super();
        
        this.clock = new JLabel(new Date().toString(), JLabel.RIGHT);
        this.clock.setBorder(new EmptyBorder(0, 0, 10, 10));
        this.ticTac = new Timer(500, this);
        this.ticTac.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        clock.setText(new Date().toString());
        
    }

    public JLabel getClock() {
        return clock;
    }

    public void setClock(JLabel clock) {
        this.clock = clock;
    }
}
