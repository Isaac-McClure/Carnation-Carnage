package CarnationCarnage;

public class Player {
    // Keeps track of player info such as their boards,
    // name, and their score.
    
    private double score;
    private Board board;
    private String name;
    private Player opponent;
    
    Player(String name){
        this.name = name;
        this.score = 0;
        setBoard(new Board());
        opponent = null;
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
    
    public void setOpponent(Player inOpponent) {
        this.opponent = inOpponent;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
