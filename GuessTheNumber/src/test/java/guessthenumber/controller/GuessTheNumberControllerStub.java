package guessthenumber.controller;

import com.mysql.cj.jdbc.MysqlDataSource;
import guessthenumber.TestApplicationConfiguration;
import guessthenumber.dao.service.GuessTheNumberServiceLayerImpl;
import guessthenumber.dto.Game;
import guessthenumber.dto.Round;
import guessthenumber.ui.GuessTheNumberView;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@RestController
@RequestMapping("/api")
public class GuessTheNumberControllerStub {


    private static DataSource ds;
    @Bean
    private static DataSource getDataSource() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("BullsAndCowsTest");
        ds.setUser("newuser");
        ds.setPassword("Testing123!");
        ds.setServerTimezone("America/Chicago");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);
        return ds;
    }
    private GuessTheNumberView view;
    private GuessTheNumberServiceLayerImpl service;
    @Autowired
    public GuessTheNumberControllerStub(GuessTheNumberServiceLayerImpl service, GuessTheNumberView view) {
        this.service = service;
        this.view = view;
    }

    @PostMapping(value="/begin")
    private ResponseEntity<String> newGame() throws SQLException {
        Game newGame = new Game();
        newGame.setGameID(service.startGame());
        return ResponseEntity.ok(String.format("Your game ID is " + newGame.getGameID()));
    }

    @PostMapping(value="/guess")
    public Round Guess(@RequestBody Round round) throws SQLException {
        int id = round.getGameID();
        int num = round.getGuessNumber();
        String guessScore = service.getScore(id, num);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime guessTime = LocalDateTime.now();
        service.postGuess(round.getGameID(), round.getGuessNumber(), guessScore, dtf.format(guessTime));
        round.setGuessNumber(num);
        round.setGuessTime(dtf.format(guessTime));
        round.setGuessScore(guessScore);
        if (guessScore.equals("e:4:p:0")) {
            service.updateGameStatus(id);
        }
        return round;
    }


    @GetMapping(value="/game")
    private List<Game> viewGames() {
        return service.getGames();
    }

    @GetMapping(value="/game/{id}")
    private Game getGame(@PathVariable int id){
        Game game = new Game();
        game.setGameID(id);
        game.setGameInProgress(service.checkGameStatus(id));
        if(!game.getGameInProgress()){
            game.setAnswer(service.getAnswer(id));
        }
        return game;
    }

    @GetMapping(value="/rounds/{id}")
    public List<Round> viewRounds(@PathVariable int id) {
        return service.getRounds(id);
    }

}
