package peter.hickling.queens;

import java.util.HashSet;

public class Chessboard {
    private final HashSet<Queen> queens;
    private final ChessboardChecker chessboardChecker;

    public Chessboard(ChessboardChecker chessboardChecker) {
        queens = new HashSet<>();
        this.chessboardChecker = chessboardChecker;
    }

    public Chessboard(ChessboardChecker chessboardChecker, HashSet<Queen> placedQueens) {
        queens = placedQueens;
        this.chessboardChecker = chessboardChecker;
    }

    public HashSet<Queen> placedQueens() {
        return new HashSet<>(queens);
    }

    public void removeQueen(Queen queen) {
        queens.remove(queen);
    }

    public boolean addQueen(Queen queen) {
        if (chessboardChecker.canPlaceQueen(this, queen)) {
            queens.add(queen);
            return true;
        } else {
            return false;
        }
    }

    public void putQueenOnBoardEvenIfIllegal(Queen queen) {
        queens.add(queen);
    }

    public boolean moveQueenToNextPositionAndCheckIfSolution(int x) {
        Queen queenToMove = queens.stream().filter(queen -> queen.getX() == x).findFirst().get();
        queens.remove(queenToMove);
        queens.add(queenToMove.nextPosition());

        return chessboardChecker.isSolution(this);
    }
}
