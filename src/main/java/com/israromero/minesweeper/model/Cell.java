package com.israromero.minesweeper.model;

public class Cell {
    private int value;
    private boolean hidden;

    public Cell(){
        this.hidden = true;
        this.value = 0;
    }

    public void cellPlusPlus(){
        value++;
    }

    public boolean isMine(){
        return (value == -1);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
