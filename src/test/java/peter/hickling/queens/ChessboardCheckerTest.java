package peter.hickling.queens;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChessboardCheckerTest {
    private ChessboardChecker chessboardChecker = new ChessboardChecker();
    private Chessboard chessboard = new Chessboard(new ChessboardChecker());

    @Test
    public void ifQueenCanBePlacedShouldReturnTrue() {
        assertThat(chessboardChecker.canPlaceQueen(chessboard, TestQueens.randomQueen()), is(true));
    }

    @Test
    public void ifQueenCannotBePlacedDueToHorizontalReturnFalse() {
        Queen queen = Queen.aQueen().x(0).y(1).build();
        chessboard.addQueen(Queen.aQueen().x(queen.getX()).y(3).build());
        assertThat(chessboardChecker.canPlaceQueen(chessboard, queen), is(false));
    }

    @Test
    public void ifQueenCannotBePlacedDueToVerticalReturnFalse() {
        Queen queen = Queen.aQueen().x(0).y(1).build();
        chessboard.addQueen(Queen.aQueen().x(3).y(queen.getY()).build());
        assertThat(chessboardChecker.canPlaceQueen(chessboard, queen), is(false));
    }

    @Test
    public void ifQueenCannotBePlacedDueToRightDiagonalReturnFalse() {
        Queen queen = Queen.aQueen().x(0).y(1).build();
        chessboard.addQueen(Queen.aQueen().x(queen.getX() + 1).y(queen.getY() + 1).build());
        assertThat(chessboardChecker.canPlaceQueen(chessboard, queen), is(false));
    }

    @Test
    public void ifQueenCannotBePlacedDueToLeftDiagonalReturnFalse() {
        Queen queen = Queen.aQueen().x(2).y(1).build();
        chessboard.addQueen(Queen.aQueen().x(queen.getX() - 1).y(queen.getY() - 1).build());
        assertThat(chessboardChecker.canPlaceQueen(chessboard, queen), is(false));
    }
}