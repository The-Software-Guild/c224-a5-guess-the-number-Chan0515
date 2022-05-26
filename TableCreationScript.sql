DROP DATABASE IF EXISTS BullsAndCows;
CREATE DATABASE BullsAndCows;

-- Make sure we're in the correct database before we add schema.
USE BullsAndCows;

CREATE TABLE Game (
    GameId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    numAnswer INT NOT NULL,
    GameStatus BOOLEAN DEFAULT true
);

CREATE TABLE Round ( 
    GameId INT,
    FOREIGN KEY fk_GameId (GameId)
        REFERENCES Game(GameId),
	GuessNumber INT,
    GuessScore VARCHAR(7) NOT NULL,
    GuessTime datetime NOT NULL
);

DROP DATABASE IF EXISTS BullsAndCowsTest;
CREATE DATABASE BullsAndCowsTest;

-- Make sure we're in the correct database before we add schema.
USE BullsAndCowsTest;

CREATE TABLE Game (
    GameId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    numAnswer INT NOT NULL,
    GameStatus BOOLEAN DEFAULT true
);

CREATE TABLE Round ( 
    GameId INT,
    FOREIGN KEY fk_GameId (GameId)
        REFERENCES Game(GameId),
	GuessNumber INT,
    GuessScore VARCHAR(7) NOT NULL,
    GuessTime datetime NOT NULL
);
