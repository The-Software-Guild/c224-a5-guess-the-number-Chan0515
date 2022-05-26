package guessthenumber.dao;

import guessthenumber.TestApplicationConfiguration;
import guessthenumber.dto.Game;
import guessthenumber.dto.Round;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@Repository
public class GuessTheNumberDaoImplStub implements GuessTheNumberDao {
    private final Random randomizer = new Random();
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public GuessTheNumberDaoImplStub(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getAnswer(int gameID){
        final String sql = "SELECT numAnswer FROM game WHERE gameId = ?;";
        return (int) jdbcTemplate.queryForObject(sql, new GuessAnswerMapper(), gameID);
    }

    @Override
    public String getScore(int gameID, int guessNumber) {
        List<String> numArray = List.of(String.format(Integer.toString(guessNumber)).split("", 0));
        int e = 0;
        int p = 0;

        final String sql = "SELECT numAnswer FROM game WHERE gameId = ?;";
        String ans = String.format("%03d",jdbcTemplate.queryForObject(sql, new GuessAnswerMapper(), gameID));
        List<String> ansArray = Arrays.asList(ans.split("",0));

        for(int i=0; i<4; i++){
            if (numArray.get(i).equals(ansArray.get(i))){
                e++;
            }
            else if(ansArray.contains(numArray.get(i))){
                p++;
            }
        }
        return String.format("e:"+e+":p:"+p);
    }


    @Override
    public int startGame() {
        int gameAnswer = randomizer.nextInt(9999);

        final String sql = "INSERT INTO Game(numAnswer) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, Integer.toString(gameAnswer));
            return statement;

        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void postGuess(int gameID, int guessNumber, String guessScore, String time) {
        final String sql = "INSERT INTO Round(GameId,GuessNumber,GuessScore,GuessTime) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, Integer.toString(gameID));
            statement.setString(2,Integer.toString(guessNumber));
            statement.setString(3,guessScore);
            statement.setString(4,time);
            return statement;

        }, keyHolder);
    }

    @Override
    public boolean checkGameStatus(int gameID) {
        final String sql = "SELECT GameStatus FROM game WHERE gameId = ?;";
        return (Boolean) jdbcTemplate.queryForObject(sql, new GameStateMapper(), gameID);
    }

    @Override
    public List<Round> getRounds(int gameID) {
        final String sql = "SELECT GuessNumber, GuessScore, GuessTime FROM round WHERE GameId = ?";
        List<Round> roundList =  jdbcTemplate.query(sql, new RoundMapper(), gameID);
        return roundList;
    }

    @Override
    public void updateGameStatus(int gameID) {
        final String sql = "UPDATE game SET GameStatus = false WHERE gameId = ?;";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, Integer.toString(gameID));
            return statement;
        }, keyHolder);
    }

    @Override
    public List<Game> getGames() {
        final String sql = "SELECT gameId,GameStatus FROM Game;";
        return jdbcTemplate.query(sql, new GamesMapper());
    }

    public static class GamesMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Game game = new Game();
            game.setGameID(resultSet.getInt("gameID"));
            game.setGameInProgress((resultSet.getBoolean("GameStatus")));
            return game;
        }
    }

    public static class GuessAnswerMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("numAnswer");
        }
    }

    private static class GameStateMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getBoolean("GameStatus");
        }
    }

    private static class RoundMapper implements RowMapper{
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Round round = new Round();
            System.out.println();
            round.setGuessNumber(resultSet.getInt("GuessNumber"));
            round.setGuessScore(resultSet.getString("GuessScore"));
            round.setGuessTime(resultSet.getString("GuessTime"));
            return round;
        }
    }
}
