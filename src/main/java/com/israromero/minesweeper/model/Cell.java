package com.israromero.minesweeper.model;

public class Cell {
    private int id;
    private int value;
    private boolean hidden;

    public Cell(int id){
        this.id = id;
        this.hidden = true;
        this.value = 0;
    }

    public void cellPlusPlus(){
        value++;
    }

    public boolean esMina(){
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

    public int getId() {
        return this.id;
    }
}
