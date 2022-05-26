package guessthenumber.dto;

public class Game {
    public int gameID;


    public int answer = -1;
    public boolean gameInProgress;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getGameID() {
        return gameID;
    }
    public boolean getGameInProgress() {
        return gameInProgress;
    }


    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    public void setGameInProgress(boolean gameStatus) {
        this.gameInProgress = gameStatus;
    }
}
