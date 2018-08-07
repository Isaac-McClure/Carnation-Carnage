package CarnationCarnage;

public class Player {
    // Keeps track of player info such as their boards,
    // name, and their score.
    
    private double score;
    private int hits;
    private int totalShots;
    private int shipsSunk;
    private Board board;
    private String name;
    private Player opponent;
    private Boolean doneSetup;
    
    Player(String name){
        this.name = name;
        this.score = 0;
        setBoard(new Board());
        opponent = null;
        doneSetup = false;
        setHits(0);
        setShipsSunk(0);
        totalShots = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public Player getOpponent() {
        return opponent;
    }
    
    public double getScore() {
        return score;
    }
    
    public void setScore(Double score) {
    	this.score=score;
    }
    
    public void setOpponent(Player inOpponent) {
        this.opponent = inOpponent;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    public void setDoneSetup(Boolean done) {
        doneSetup = done;
    }

    public boolean getDoneSetup() {
        return doneSetup;
    }

    public int getShipsSunk() {
        return shipsSunk;
    }

    public void setShipsSunk(int shipsSunk) {
        this.shipsSunk = shipsSunk;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getTotalShots() {
        return totalShots;
    }

    public void setTotalShots(int totalShots) {
        this.totalShots = totalShots;
    }
    
    
}
