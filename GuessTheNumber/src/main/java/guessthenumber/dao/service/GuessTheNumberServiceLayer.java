
package guessthenumber.dao.service;

import guessthenumber.dto.Game;
import guessthenumber.dto.Round;

import java.sql.SQLException;
import java.util.List;

public interface GuessTheNumberServiceLayer {


    void postGuess(int gameID, int guessNumber, String guessScore, String format);

    String getScore(int gameID, int guessNumber);

    int startGame() throws SQLException;


    boolean checkGameStatus(int gameID);

    List<Round> getRounds(int gameID);

    void updateGameStatus(int gameID);

    List<Game> getGames();

    int getAnswer(int gameID);
}
