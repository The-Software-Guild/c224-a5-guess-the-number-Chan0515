package guessthenumber.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuessTheNumberView {

    private UserIO io;
    @Autowired
    public GuessTheNumberView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("---- Bulls and Cows! ----");
        io.print("1. Start new game");
        io.print("2. Resume a previous game");
        io.print("3. View all games");
        io.print("4. View rounds");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!");
    }


    public void displayBeginGame(int gameID) {
        io.print("--New Game--");
        io.print("Game ID: " + gameID);
    }


    public int getGuess() {
        io.print("--BULLS AND COWS--");
        return io.readInt("Guess the 4-digit number: ", 0,9999);
    }

    public void displayGameFinished() {
        io.print("Game is already finished!");
        io.print("");
        io.print("");
    }

    public int displayResumeGame() {
        return io.readInt("Resuming previous game. Please enter your Game ID:");
    }

    public void displayExitBanner() {
        io.print("-----Exiting-----");
    }

    public int viewRoundsPrompt() {
        return io.readInt("Viewing previous rounds. Please enter your Game ID:");
    }

    public void displayRound(int i, int guessNumber, String guessScore, String guessTime) {
        io.print(String.format(i+ ".  " +guessNumber+ "  " +guessScore+ "  " +guessTime));
    }

    public void displayWin() {
        io.print("Congrats, you won!");
        io.print("");
        io.print("");
    }

    public void displayScore(String guessScore) {
        io.print("----Round Results----");
        io.print("You have " +guessScore.charAt(2)+ " exact matches and " +
                guessScore.charAt(6)+ " partial matches.");
        io.print("");
    }

    public void displayGames(String gameID, int i){
        io.print(i + ".  " + gameID);
    }
}