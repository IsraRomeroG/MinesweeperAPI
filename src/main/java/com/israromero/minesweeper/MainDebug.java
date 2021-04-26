package com.israromero.minesweeper;

import com.israromero.minesweeper.model.Board;

public class MainDebug {
    public static void main(String[] args) {
        Board b = new Board(20, 20, 40);
        b.printBoard();

        b.reveal(9, 9);
        System.out.println("---- BOARD UPDATE ----");
        b.printHiddenBoard();
    }
}
