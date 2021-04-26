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
    public void printHiddenBoard(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                System.out.print(board[x][y].isHidden() ? (" -") : (" "+board[x][y].getValue()) );
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

    public void reveal(int x, int y){
        //Validar que sea una celda válida
        //Si la célda es válida entonces descubre
        board[x][y].setHidden(false);

        if(board[x][y].getValue() == 0){//Explore
            revealAround(x, y);

//            System.out.println("---- BOARD UPDATE ----");
//            printHiddenBoard();
//            try {
//                Thread.sleep(1*500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            int way;
            do{
                way = findWayAround(x, y);
                if(way > -1){
                    reveal(way%width, way/width);
                }
            }while(way != -1);
        }else{
            if(board[x][y].isMine()){
                System.out.println("Ya perdiste mi shavo");
            }
            //Si es número, valida que no sea el último y continua jugando
            //0 -> Continue, 1 -> Win, -1 -> Loose
        }
        //Reveal around
        //Find a Way
        //if there's a way go that way reveal(way)
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

    private void revealAround(int x, int y){
        int left = x-1;
        int right = x+1;
        int up = y-1;
        int down = y+1;

        if(isCell(left, up)){
            board[left][up].setHidden(false);
        }
        if(isCell(x, up)){
            board[x][up].setHidden(false);
        }
        if(isCell(right, up)){
            board[right][up].setHidden(false);
        }

        if(isCell(left, y)){
            board[left][y].setHidden(false);
        }
        if(isCell(right, y)){
            board[right][y].setHidden(false);
        }

        if(isCell(left, down)){
            board[left][down].setHidden(false);
        }
        if(isCell(x, down)){
            board[x][down].setHidden(false);
        }
        if(isCell(right, down)){
            board[right][down].setHidden(false);
        }
    }

    private int findWayAround(int x, int y){//If there's a way return te "sequential number" of that way, if not return -1
        int left = x-1;
        int right = x+1;
        int up = y-1;
        int down = y+1;

        if(isWay(left, up)){
            return getSequentialNumber(left, up);
        }
        if(isWay(x, up)){
            return getSequentialNumber(x, up);
        }
        if(isWay(right, up)){
            return getSequentialNumber(right, up);
        }

        if(isWay(left, y)){
            return getSequentialNumber(left, y);
        }
        if(isWay(right, y)){
            return getSequentialNumber(right, y);
        }

        if(isWay(left, down)){
            return getSequentialNumber(left, down);
        }
        if(isWay(x, down)){
            return getSequentialNumber(x, down);
        }
        if(isWay(right, down)){
            return getSequentialNumber(right, down);
        }
        return -1;
    }

    //"Sequential Number" is the consecutive number from a cell into a rectangular matrix, numbered from 0 to n-1 elementos in the matrix
    private int getSequentialNumber(int x, int y){
        return (y*width)+x;
    }

    private boolean isWay(int x, int y){
        if(isCell(x, y)){
            if(board[x][y].getValue() == 0){
                if(isNextToHiddenCell(x, y)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNextToHiddenCell(int x, int y){
        int left = x-1;
        int right = x+1;
        int up = y-1;
        int down = y+1;

        if(isCell(left, up) && board[left][up].isHidden()){
            return true;
        }
        if(isCell(x, up) && board[x][up].isHidden()){
            return true;
        }
        if(isCell(right, up) && board[right][up].isHidden()){
            return true;
        }

        if(isCell(left, y) && board[left][y].isHidden()){
            return true;
        }
        if(isCell(right, y) && board[right][y].isHidden()){
            return true;
        }

        if(isCell(left, down) && board[left][down].isHidden()){
            return true;
        }
        if(isCell(x, down) && board[x][down].isHidden()){
            return true;
        }
        if(isCell(right, down) && board[right][down].isHidden()){
            return true;
        }

        return false;
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
