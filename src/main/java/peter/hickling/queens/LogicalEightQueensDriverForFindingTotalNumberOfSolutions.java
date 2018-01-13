package peter.hickling.queens;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LogicalEightQueensDriverForFindingTotalNumberOfSolutions {
    private final AtomicInteger totalNumberOfAttempts = new AtomicInteger(0);
    private final Object lock = "lock for total number of attempts";
    private final ExecutorService executor = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    private final Map<Set<Queen>, Boolean> allSolutions = new ConcurrentHashMap<>();
    private final CountDownLatch countDownLatch = new CountDownLatch(8);

    private class SolutionTask implements Runnable {
        private final Chessboard startingPoint;
        private int numberOfAttempts = 0;

        public SolutionTask(Chessboard startingPoint) {
            this.startingPoint = startingPoint;
        }

        @Override
        public void run() {
            searchForSolutions(this.startingPoint, 1);
            synchronized (lock) {
                totalNumberOfAttempts.set(totalNumberOfAttempts.addAndGet(numberOfAttempts));
            }
            countDownLatch.countDown();
        }

        public void searchForSolutions(Chessboard startingPoint, int startingQueen) {
            for (int i = 0; i < 8; i++) {
                numberOfAttempts++;
                if (startingQueen < 7) {
                    searchForSolutions(startingPoint, startingQueen + 1);
                }
                if (startingPoint.moveQueenToNextPositionAndCheckIfSolution(startingQueen)) {
                    allSolutions.put(startingPoint.placedQueens(), true);
                }
            }
        }
    }

    public Set<Set<Queen>> findAllSolutions() throws InterruptedException {
        Chessboard chessboard = new Chessboard(new ChessboardChecker());
        for (int i = 0; i < 8; i++) {
            chessboard.putQueenOnBoardEvenIfIllegal(Queen.aQueen().x(i).y(0).build());
        }
        for (int i = 0; i < 8; i++) {
            executor.execute(new SolutionTask(new Chessboard(new ChessboardChecker(), chessboard.placedQueens())));
            chessboard.moveQueenToNextPositionAndCheckIfSolution(0);
        }
        countDownLatch.await();
        executor.shutdown();
        System.out.println(totalNumberOfAttempts);
        return allSolutions.keySet();
    }
}
