package guessthenumber.dao;

import guessthenumber.TestApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@Repository
public class GuessTheNumberDaoImplStubTest {
    @Autowired
    public GuessTheNumberDaoImplStub dao;
    public JdbcTemplate jdbcTemplate;



    @Before
    public void dbSetup(){
        int gameAnswer = 1234;
        final String sql = "INSERT INTO Game(numAnswer) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, Integer.toString(gameAnswer));
            return statement;

        }, keyHolder);
        System.out.println("Setup Completed.");
    }
    @Test
    public void getAnswerTest() {
        assertEquals(1234,dao.getAnswer(1));
    }

    @Test
    public void getScore() {
    }

    @Test
    public void startGame() {
    }

    @Test
    public void postGuess() {
    }

    @Test
    public void checkGameStatus() {
    }

    @Test
    public void getRounds() {
    }

    @Test
    public void updateGameStatus() {
    }

    @Test
    public void getGames() {
    }
}