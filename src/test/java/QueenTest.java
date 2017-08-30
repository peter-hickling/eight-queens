package uk.co.codera;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueenTest {

	@Test
	public void creationOfQueenWithBoard() {
		Chessboard board = new Chessboard();
		Queen queen = new Queen(board);
		assertThat(queen.getChessboard(), is(equalTo(board)));
	}

	@Test
	public void queenPlacedOnBoard() {
		Chessboard board = new Chessboard();
		Queen queen = new Queen(board);
		assertTrue(queen.placeQueen(1, 1));

		assertTrue(!queen.getChessboard().placedQueens().isEmpty());
		
	}
	
	@Test
	public void queenNotPlacedDueToHorizontal() {
		Chessboard board = new Chessboard();
		Queen queen = new Queen(board);
		queen.placeQueen(1, 1);
		assertFalse(queen.placeQueen(1, 2));
		
		assertThat(queen.getChessboard().placedQueens().size(), is(equalTo(1)));
	}
	
	@Test
	public void queenNotPlacedDueToVertical() {
		Chessboard board = new Chessboard();
		Queen queen = new Queen(board);
		queen.placeQueen(1, 1);
		queen.placeQueen(2, 1);
		queen.placeQueen(2, 5);
		queen.placeQueen(1, 6);
		queen.placeQueen(2, 8);
		queen.placeQueen(3, 3);
		queen.placeQueen(6, 2);
		queen.placeQueen(8, 3);
		queen.placeQueen(6, 4);
		
		assertThat(queen.getChessboard().placedQueens().size(), is(equalTo(4)));
	}
	
	@Test
	public void queenNotPlacedDueToLeftDiagonal() {
		Chessboard board = new Chessboard();
		Queen queen = new Queen(board);
		queen.placeQueen(1, 1);
		queen.placeQueen(2, 2);
		
		assertThat(queen.getChessboard().placedQueens().size(), is(equalTo(1)));
	}
	
	@Test
	public void queenNotPlacedDueToRightDiagonal() {
		Chessboard board = new Chessboard();
		Queen queen = new Queen(board);
		queen.placeQueen(5, 5);
		queen.placeQueen(6, 4);
		queen.placeQueen(4, 6);
		
		assertThat(queen.getChessboard().placedQueens().size(), is(equalTo(1)));
	}
	
	@Test
	public void queenRemoved() {
		Chessboard board = new Chessboard();
		Queen queen = new Queen(board);
		queen.placeQueen(5, 5);
		queen.removeRandomQueen(1);
		
		assertThat(queen.getChessboard().placedQueens().size(), is(equalTo(0)));
	}

}
