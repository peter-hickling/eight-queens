package uk.co.codera;

import java.util.Random;

public class Queen {

	private Chessboard board;

	public Queen(Chessboard board) {
		this.board = board;
	}

	public boolean placeQueen(int horizontal, int vertical) {
		boolean canPlace = true;
		if (checkHorizontal(horizontal))
			canPlace = false;
		if (checkVertical(vertical))
			canPlace = false;
		if (checkDiagonal(horizontal, vertical))
			canPlace = false;
		if (canPlace) {
			board.addQueen(horizontal, vertical);
			return true;
		}
		return false;
	}

	public void removeRandomQueen(int numberOfQueensToRemove) {

		for (int i = 0; i < numberOfQueensToRemove; i++) {
			Random random = new Random();
			int randomNumber = random.nextInt(board.placedQueens().size());
			board.removeQueen(randomNumber);
		}

	}

	public Chessboard getChessboard() {
		return board;
	}

	private boolean checkHorizontal(int horizontal) {
		return board.placedQueens().containsKey(horizontal);
	}

	private boolean checkVertical(int vertical) {
		return board.placedQueens().containsValue(vertical);
	}

	private boolean checkDiagonal(int horizontal, int vertical) {

		for (int i = -8; i < 9; i++) {
			if (board.placedQueens().containsKey(horizontal + i) && 
					board.placedQueens().get(horizontal + i).equals(vertical + i)) {
				return true;
			}
			if (board.placedQueens().containsKey(horizontal - i) && 
					board.placedQueens().get(horizontal - i).equals(vertical + i)) {
				return true;
			}
		}

		return false;
	}

	public void removeAllQueens() {
		board.empty();
	}
}
