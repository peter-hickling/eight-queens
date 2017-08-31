package peter.hickling.queens;

public class ChessboardChecker {
    public boolean canPlaceQueen(Chessboard chessboard, Queen queen) {
        return checkHorizontal(chessboard, queen) && checkVertical(chessboard, queen) && checkDiagonal(chessboard, queen);
    }

    private boolean checkHorizontal(Chessboard chessboard, Queen queen) {
        return !chessboard.placedQueens().stream().anyMatch(checkQueen -> checkQueen.getX() == queen.getX());
    }

    private boolean checkVertical(Chessboard chessboard, Queen queen) {
        return !chessboard.placedQueens().stream().anyMatch(checkQueen -> checkQueen.getY() == queen.getY());
    }

    private boolean checkDiagonal(Chessboard chessboard, Queen queen) {
        return !chessboard.placedQueens().stream().anyMatch(checkQueen -> (((checkQueen.getY() - queen.getY()) - (checkQueen.getX() - queen.getX())) == 0) || (((checkQueen.getY() - queen.getY()) + (checkQueen.getX() - queen.getX())) == 0));
    }
}
