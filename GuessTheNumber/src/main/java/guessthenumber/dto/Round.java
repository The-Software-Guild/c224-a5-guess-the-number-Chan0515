package guessthenumber.dto;

public class Round {


    private int gameID;
    private int guessNumber;
    private String guessScore;
    private String guessTime;

    public int getGuessNumber(){
        return guessNumber;
    }
    public String getGuessScore(){
        return guessScore;
    }
    public String getGuessTime(){
        return guessTime;
    }
    public int getGameID() {return gameID;}

    public void setGameID(int gameID) {this.gameID = gameID;}
    public void setGuessNumber(int num){
        guessNumber = num;
    }
    public void setGuessScore(String score){
        guessScore = score;
    }
    public void setGuessTime(String time){
        guessTime =  time;
    }
}
