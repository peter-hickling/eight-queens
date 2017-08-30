package peter.hickling.queens;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class ChessboardTest {
    Map<Integer, Integer> testBoard = new TreeMap<Integer, Integer>();

    @Test
    public void chessboardContainsMap() {
        Chessboard board = new Chessboard();
        assertThat(board.placedQueens().getClass(), is(equalTo(testBoard.getClass())));
    }

    @Test
    public void chessboardAddsAQueen() {
        testBoard.put(1, 1);

        Chessboard board = new Chessboard();
        board.addQueen(1, 1);
        assertThat(board.placedQueens().size(), is(equalTo(testBoard.size())));
    }

    @Test
    public void emptyBoard() {
        Chessboard board = new Chessboard();
        board.addQueen(1, 1);
        board.empty();

        assertThat(board.placedQueens().size(), is(equalTo(0)));
    }
}
