package peter.hickling.queens;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChessboardTest {
    @Mock
    ChessboardChecker chessboardChecker;

    Chessboard board;

    @Before
    public void before() {
        board = new Chessboard(chessboardChecker);
        whenChessboardCheckerCalledReturnsTrue();
    }

    @Test
    public void canAddAQueen() {
        board.addQueen(TestQueens.randomQueen());
        assertThat(board.placedQueens().size(), is(equalTo(1)));
    }

    @Test
    public void addQueenWillCallChessboardChecker() {
        board.addQueen(TestQueens.randomQueen());
        verify(chessboardChecker).canPlaceQueen(any(Chessboard.class), any(Queen.class));
    }

    @Test
    public void canEmptyBoard() {
        board.addQueen(TestQueens.randomQueen());
        board.empty();

        assertThat(board.placedQueens().size(), is(equalTo(0)));
    }

    @Test
    public void shouldNotBeAbleToFreelyAlterQueenSet() {
        board.addQueen(TestQueens.randomQueen());
        board.placedQueens().clear();

        assertThat(board.placedQueens().size(), is(not(0)));
    }

    private void whenChessboardCheckerCalledReturnsTrue() {
        when(chessboardChecker.canPlaceQueen(any(Chessboard.class), any(Queen.class))).thenReturn(true);
    }
}
