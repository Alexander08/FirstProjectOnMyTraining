package com.ischeduler.gui;

import com.ischeduler.gui.table.TableManager;

public class TableHandler<T extends TableManager> {

    private T component;

    public TableHandler(T panel) {
        
        this.component = panel;
    }

    public T getComponent() {
        return component;
    }

    public void setComponent(T component) {
        this.component = component;
    }
    
    
}
