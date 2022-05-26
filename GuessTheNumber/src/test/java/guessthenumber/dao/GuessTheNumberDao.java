package guessthenumber.dao;

import guessthenumber.dto.Game;
import guessthenumber.dto.Round;

import java.util.List;

public interface GuessTheNumberDao {
    String getScore(int gameID, int guessNumber);
    int startGame();

    void postGuess(int gameID, int guessNumber, String guessScore, String time);

    boolean checkGameStatus(int gameID);

    List<Round> getRounds(int gameID);
    int getAnswer(int gameID);
    void updateGameStatus(int gameID);

    List<Game> getGames();
}