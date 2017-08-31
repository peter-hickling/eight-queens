package peter.hickling.queens;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Chessboard {
    private Set<Queen> queens;
    private ChessboardChecker chessboardChecker;

    public Chessboard(ChessboardChecker chessboardChecker) {
        queens = new HashSet<Queen>();
        this.chessboardChecker = chessboardChecker;
    }

    public Set<Queen> placedQueens() {
        return new TreeSet<>(queens);
    }

    public void removeQueen(Queen queen) {
        queens.remove(queen);
    }

    public boolean addQueen(Queen queen) {
        if(chessboardChecker.canPlaceQueen(this, queen)) {
            queens.add(queen);
            return true;
        } else {
            return false;
        }
    }

    public void empty() {
        queens.clear();
    }
}
