package peter.hickling.queens;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class EightQueensDriver {
    private Chessboard chessboard;
    private int totalNumberOfAttempts;

    public EightQueensDriver(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public Chessboard placeEightQueens() {
        int totalAttempts = 0;
        int numberOfAttempts = 0;
        int numberOfQueensToRemove = 0;

        while (chessboard.placedQueens().size() < 8) {
            numberOfAttempts++;

            if (chessboard.addQueen(Queen.aQueen().x(ThreadLocalRandom.current().nextInt(0, 8)).y(ThreadLocalRandom.current().nextInt(0, 8)).build())) {
                totalAttempts = totalAttempts + numberOfAttempts;
                numberOfAttempts = 0;
            }

            if (numberOfAttempts > 50) {
                numberOfQueensToRemove++;

                if (numberOfQueensToRemove < chessboard.placedQueens().size()) {
                    removeRandomQueens(numberOfQueensToRemove);
                } else {
                    numberOfQueensToRemove = 0;
                }
                totalAttempts = totalAttempts + numberOfAttempts;
                numberOfAttempts = 0;
            }
        }
        totalNumberOfAttempts = totalAttempts + totalNumberOfAttempts;
        return chessboard;
    }

    public Set<String> getAllSolutions() {
        Set<String> allSolutions = new HashSet<>();
        while (allSolutions.size() < 92) {
            allSolutions.add(placeEightQueens().placedQueens().toString());
            chessboard.empty();
        }
        System.out.println(totalNumberOfAttempts);
        return allSolutions;
    }

    private void removeRandomQueens(int numberOfQueensToRemove) {
        for (int i = 0; i < numberOfQueensToRemove; i++) {
            Queen queenToRemove = chessboard.placedQueens().stream().collect(Collectors.toList()).get(ThreadLocalRandom.current().nextInt(0, chessboard.placedQueens().size()));
            chessboard.removeQueen(queenToRemove);
        }
    }
}
