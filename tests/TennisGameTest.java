import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
	// Here is the format of the scores: "player1Score - player2Score"
	// "love - love"
	// "15 - 15"
	// "30 - 30"
	// "deuce"
	// "15 - love", "love - 15"
	// "30 - love", "love - 30"
	// "40 - love", "love - 40"
	// "30 - 15", "15 - 30"
	// "40 - 15", "15 - 40"
	// "player1 has advantage"
	// "player2 has advantage"
	// "player1 wins"
	// "player2 wins"
	
	@Ignore
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testScoreDeuce() throws TennisGameException  {
		// "deuce" = 40 - 40
		// Arrange
		TennisGame game1 = new TennisGame();
		
		// Act
		game1.player1Scored();
		game1.player1Scored();
		game1.player1Scored();
		
		game1.player2Scored();
		game1.player2Scored();
		game1.player2Scored();
		// Assert
		assertEquals("deuce",game1.getScore());
	}
	
	@Test
	public void testScoreTies() throws TennisGameException  {
		// 15 - 15 & 30 - 30
		// Arrange
		TennisGame game1 = new TennisGame();
		TennisGame game2 = new TennisGame();
		
		// Act
		game1.player1Scored();
		game1.player2Scored();
		
		game2.player1Scored();
		game2.player1Scored();
		game2.player2Scored();
		game2.player2Scored();
		// Assert
		assertEquals("15 - 15",game1.getScore());
		assertEquals("30 - 30",game2.getScore());
	}
	
	@Test
	public void testScoreAdvantages() throws TennisGameException {
		// game1 "player1 has advantage"
		// game2 "player2 has advantage"
		// Arrange
		TennisGame game1 = new TennisGame();
		TennisGame game2 = new TennisGame();
		
		// Act
		game1.player1Scored();
		game1.player1Scored();
		game1.player1Scored();
		game1.player2Scored();
		game1.player2Scored();
		game1.player2Scored();
		game1.player1Scored();
		
		game2.player2Scored();
		game2.player2Scored();
		game2.player2Scored();
		game2.player1Scored();
		game2.player1Scored();
		game2.player1Scored();
		game2.player2Scored();		
		
		// Assert
		assertEquals("player1 has advantage",game1.getScore());
		assertEquals("player2 has advantage",game2.getScore());
	}
	
	@Test
	public void testScoreWinsLove() throws TennisGameException {
		// game1 "player1 wins"
		// game2 "player2 wins"
		// Arrange
		TennisGame game1 = new TennisGame();
		TennisGame game2 = new TennisGame();
		
		// Act
		game1.player1Scored();
		game1.player1Scored();
		game1.player1Scored();
		game1.player1Scored();
		
		game2.player2Scored();
		game2.player2Scored();
		game2.player2Scored();
		game2.player2Scored();
		
		// Assert
		assertEquals("player1 wins",game1.getScore());
		assertEquals("player2 wins",game2.getScore());
	}
	
	@Test
	public void testScoreWinsAdvantage() throws TennisGameException {
		// game1 "player1 wins"
		// game2 "player2 wins"
		// Arrange
		TennisGame game1 = new TennisGame();
		TennisGame game2 = new TennisGame();
		
		// Act
		game1.player1Scored();
		game1.player1Scored();
		game1.player1Scored();
		game1.player2Scored();
		game1.player2Scored();
		game1.player2Scored();
		game1.player1Scored();
		game1.player1Scored();
		
		game2.player2Scored();
		game2.player2Scored();
		game2.player2Scored();
		game2.player1Scored();
		game2.player1Scored();
		game2.player1Scored();
		game2.player2Scored();
		game2.player2Scored();	
		
		// Assert
		assertEquals("player1 wins",game1.getScore());
		assertEquals("player2 wins",game2.getScore());
	}
	
	@Test
	public void testScoreLoves() throws TennisGameException {
		// 1 "15 - love", 2 "love - 15"
		// 3 "30 - love", 4 "love - 30"
		// 5 "40 - love", 6 "love - 40"
		// Arrange
		TennisGame game1 = new TennisGame();
		TennisGame game2 = new TennisGame();
		TennisGame game3 = new TennisGame();
		TennisGame game4 = new TennisGame();
		TennisGame game5 = new TennisGame();
		TennisGame game6 = new TennisGame();
		
		// Act
		game1.player1Scored();
		
		game2.player2Scored();
		
		game3.player1Scored();
		game3.player1Scored();
		
		game4.player2Scored();
		game4.player2Scored();
		
		game5.player1Scored();
		game5.player1Scored();
		game5.player1Scored();
		
		game6.player2Scored();
		game6.player2Scored();
		game6.player2Scored();
		
		// Assert
		assertEquals("15 - love",game1.getScore());
		assertEquals("love - 15",game2.getScore());
		assertEquals("30 - love",game3.getScore());
		assertEquals("love - 30",game4.getScore());
		assertEquals("40 - love",game5.getScore());
		assertEquals("love - 40",game6.getScore());
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGameExceptionPlayer1() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		// Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGameExceptionPlayer2() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		// Act
		// This statement should cause an exception
		game.player2Scored();			
	}		
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
}
