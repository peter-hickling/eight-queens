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

    @Test
    public void isSolutionShouldReturnsTrueIfItIsASolution() {
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(1).y(6).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(4).y(7).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(2).y(2).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(5).y(4).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(6).y(0).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(0).y(1).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(3).y(5).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(7).y(3).build());

        assertThat(chessboardChecker.isSolution(chessboard), is(true));
    }

    @Test
    public void isSolutionShouldReturnsFalseIfItIsNotASolution() {
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(2).y(6).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(4).y(7).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(2).y(2).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(5).y(4).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(6).y(0).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(0).y(1).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(3).y(5).build());
        chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(7).y(3).build());

        assertThat(chessboardChecker.isSolution(chessboard), is(false));
    }
}