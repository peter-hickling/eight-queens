package peter.hickling.queens;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChessboardTest {
    @Mock
    private ChessboardChecker chessboardChecker;

    private Chessboard board;

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
    public void shouldNotBeAbleToFreelyAlterQueenSet() {
        board.addQueen(TestQueens.randomQueen());
        board.placedQueens().clear();

        assertThat(board.placedQueens().size(), is(not(0)));
    }

    @Test
    public void shouldMoveTheCorrectQueenToTheNextPosition() {
        board.addQueen(Queen.aQueen().y(1).x(0).build());
        board.addQueen(Queen.aQueen().y(3).x(1).build());
        board.moveQueenToNextPositionAndCheckIfSolution(0);

        assertThat(board.placedQueens().stream().filter(queen -> queen.getX() == 0).findFirst().get().getY(), is(2));
    }

    @Test
    public void shouldCallChessboardCheckerToFindOutIfNewStateIsASolution() {
        board.addQueen(Queen.aQueen().y(1).x(0).build());
        board.moveQueenToNextPositionAndCheckIfSolution(0);

        verify(chessboardChecker).isSolution(any(Chessboard.class));
    }

    @Test
    public void shouldBeAbleToPlaceAQueenInAnIllegalPosition() {
        board.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(0).y(0).build());
        board.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(0).y(1).build());

        assertThat(board.placedQueens().size(), is(2));
    }

    private void whenChessboardCheckerCalledReturnsTrue() {
        when(chessboardChecker.canPlaceQueen(any(Chessboard.class), any(Queen.class))).thenReturn(true);
    }
}
