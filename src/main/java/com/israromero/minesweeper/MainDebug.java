package com.israromero.minesweeper;

import com.israromero.minesweeper.model.Board;

public class MainDebug {
    public static void main(String[] args) {
        Board b = new Board(20, 10, 20);
        b.printBoard();
    }
}
