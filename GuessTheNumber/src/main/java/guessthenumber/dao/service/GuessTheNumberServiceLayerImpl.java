
package guessthenumber.dao.service;

import guessthenumber.dao.GuessTheNumberDao;
import guessthenumber.dto.Game;
import guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GuessTheNumberServiceLayerImpl implements GuessTheNumberServiceLayer {
    private final GuessTheNumberDao dao;

    @Autowired
    public  GuessTheNumberServiceLayerImpl(GuessTheNumberDao dao){
        this.dao = dao;
    }

    @Override
    public void postGuess(int gameID, int guessNumber, String guessScore, String time) {
        dao.postGuess(gameID,guessNumber,guessScore,time);
    }

    @Override
    public String getScore(int gameID, int guessNumber) {
        return dao.getScore(gameID,guessNumber);
    }

    @Override
    public int startGame() {return dao.startGame();}

    @Override
    public boolean checkGameStatus(int gameID) {
        return dao.checkGameStatus(gameID);
    }

    @Override
    public List<Round> getRounds(int gameID) {
        return dao.getRounds(gameID);
    }

    @Override
    public void updateGameStatus(int gameID) {
        dao.updateGameStatus(gameID);
    }

    @Override
    public List<Game> getGames() {
        return dao.getGames();
    }

    public int getAnswer(int gameID){
        return dao.getAnswer(gameID);
    }
}
