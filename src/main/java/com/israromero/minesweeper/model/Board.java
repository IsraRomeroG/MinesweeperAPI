package com.israromero.minesweeper.model;

public class Board {
    private int width;
    private int height;
    private int mines;
    private Cell[][] board;

    public Board(int width, int height, int mines){
        this.width = width;
        this.height = height;
        this.mines = mines;
        //Valida parámetros y si es correcto inicializa board
        initBoard();
        generateMines(mines);
    }

    public void initBoard(){
        board = new Cell[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                board[x][y] = new Cell();
            }
        }
    }

    public void printBoard(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                System.out.print(board[x][y].isMine() ? (" X") : (" "+board[x][y].getValue()) );
            }
            System.out.println();
        }
    }

    private void generateMines(int mines){
        int range = (width * height)-1;//Range of random numbers
        while(mines > 0){
            double r = Math.random();
            int random = (int)(r*range);
            int x = random%width;
            int y = random/width;
//            System.out.println("R:"+r+" RANGE:"+range+" RANDOM:"+random+" X:"+x+" Y:"+y);
            if(!board[x][y].isMine()){
                board[x][y].setValue(-1);//-1 represents a mine
                addOneAround(x, y);
                mines--;
            }
        }
    }

    private void addOneAround(int x, int y){
        int left = x-1;
        int right = x+1;
        int up = y-1;
        int down = y+1;
        if(isCell(left, up) && !board[left][up].isMine()){
            board[left][up].cellPlusPlus();
        }
        if(isCell(x, up) && !board[x][up].isMine()){
            board[x][up].cellPlusPlus();
        }
        if(isCell(right, up) && !board[right][up].isMine()){
            board[right][up].cellPlusPlus();
        }

        if(isCell(left, y) && !board[left][y].isMine()){
            board[left][y].cellPlusPlus();
        }
        if(isCell(right, y) && !board[right][y].isMine()){
            board[right][y].cellPlusPlus();
        }

        if(isCell(left, down) && !board[left][down].isMine()){
            board[left][down].cellPlusPlus();
        }
        if(isCell(x, down) && !board[x][down].isMine()){
            board[x][down].cellPlusPlus();
        }
        if(isCell(right, down) && !board[right][down].isMine()){
            board[right][down].cellPlusPlus();
        }
    }

    boolean isCell(int x, int y){ return (x>=0)&&(x<width)&&(y>=0)&&(y<height); }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }
}
