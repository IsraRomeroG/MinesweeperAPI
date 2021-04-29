package com.israromero.minesweeper.model;

public class Board {
    private int width;
    private int height;
    private int mines;
    private int openCells;
    private int status;
    private Cell[][] cells;

    public Board(int width, int height, int mines){
        this.width = height;
        this.height = width;
        this.mines = mines;
        this.openCells = 0;
        this.status = 0;
        //Valida parámetros y si es correcto inicializa board
        initCells();
        generateMines(mines);
    }

    public void initCells(){
        cells = new Cell[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                cells[x][y] = new Cell(getSequentialNumber(x,y));
            }
        }
    }

    public void printBoard(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                System.out.print(cells[x][y].esMina() ? (" X") : (" "+ cells[x][y].getValue()) );
            }
            System.out.println();
        }
    }
    public void printHiddenBoard(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                System.out.print(cells[x][y].isHidden() ? (" -") : (" "+ cells[x][y].getValue()) );
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
            if(!cells[x][y].esMina()){
                cells[x][y].setValue(-1);//-1 represents a mine
                addOneAround(x, y);
                mines--;
            }
        }
    }

    public void reveal(int id){
        int y = id/width;
        int x = id%width;
        reveal(x, y);
    }

    public void reveal(int x, int y){
        //Validar que sea una celda válida
        //Si la célda es válida entonces descubre
        openCell(x, y);

        if(cells[x][y].getValue() == 0){//Explore
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
            if(cells[x][y].esMina()){
                this.status = -1;
                revealAll();
                System.out.println("Ya perdiste mi shavo :(");
            }
            System.out.println();
            if((height*width) == (openCells+mines)){
                this.status = 1;
                revealAll();
                System.out.println("You are the winner madafaca");
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
        if(isCell(left, up) && !cells[left][up].esMina()){
            cells[left][up].cellPlusPlus();
        }
        if(isCell(x, up) && !cells[x][up].esMina()){
            cells[x][up].cellPlusPlus();
        }
        if(isCell(right, up) && !cells[right][up].esMina()){
            cells[right][up].cellPlusPlus();
        }

        if(isCell(left, y) && !cells[left][y].esMina()){
            cells[left][y].cellPlusPlus();
        }
        if(isCell(right, y) && !cells[right][y].esMina()){
            cells[right][y].cellPlusPlus();
        }

        if(isCell(left, down) && !cells[left][down].esMina()){
            cells[left][down].cellPlusPlus();
        }
        if(isCell(x, down) && !cells[x][down].esMina()){
            cells[x][down].cellPlusPlus();
        }
        if(isCell(right, down) && !cells[right][down].esMina()){
            cells[right][down].cellPlusPlus();
        }
    }

    private void revealAll(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                cells[x][y].setHidden(false);
            }
            System.out.println();
        }
    }

    private void revealAround(int x, int y){
        int left = x-1;
        int right = x+1;
        int up = y-1;
        int down = y+1;

        openCell(left,up);
        openCell(x,up);
        openCell(right,up);

        openCell(left,y);
        openCell(right,y);

        openCell(left,down);
        openCell(x,down);
        openCell(right,down);
    }

    private void openCell(int x, int y){
        if(isCell(x,y)) {
            if (cells[x][y].isHidden()) {
                cells[x][y].setHidden(false);
                openCells++;
            }
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
            if(cells[x][y].getValue() == 0){
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

        if(isCell(left, up) && cells[left][up].isHidden()){
            return true;
        }
        if(isCell(x, up) && cells[x][up].isHidden()){
            return true;
        }
        if(isCell(right, up) && cells[right][up].isHidden()){
            return true;
        }

        if(isCell(left, y) && cells[left][y].isHidden()){
            return true;
        }
        if(isCell(right, y) && cells[right][y].isHidden()){
            return true;
        }

        if(isCell(left, down) && cells[left][down].isHidden()){
            return true;
        }
        if(isCell(x, down) && cells[x][down].isHidden()){
            return true;
        }
        if(isCell(right, down) && cells[right][down].isHidden()){
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

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
