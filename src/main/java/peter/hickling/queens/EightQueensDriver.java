package peter.hickling.queens;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class EightQueensDriver {
    private final AtomicInteger totalNumberOfAttempts = new AtomicInteger(0);
    private final Object lock = "lock for total number of attempts";
    private final AllSolutionsLatch allSolutionsLatch = new AllSolutionsLatch();
    private final ExecutorService executor = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    private class SolutionTask implements Runnable {
        @Override
        public void run() {
            Chessboard chessboard = new Chessboard(new ChessboardChecker());
            int totalAttempts = 0;
            int numberOfAttempts = 0;
            int numberOfQueensToRemove = 0;

            while (chessboard.placedQueens().size() < 8) {
                numberOfAttempts++;

                if (chessboard.addQueen(Queen.aQueen().x(ThreadLocalRandom.current().nextInt(0, 8))
                        .y(ThreadLocalRandom.current().nextInt(0, 8)).build())) {
                    totalAttempts = totalAttempts + numberOfAttempts;
                    numberOfAttempts = 0;
                }

                if (numberOfAttempts > 50) {
                    numberOfQueensToRemove++;

                    if (numberOfQueensToRemove < chessboard.placedQueens().size()) {
                        removeRandomQueens(numberOfQueensToRemove, chessboard);
                    } else {
                        numberOfQueensToRemove = 0;
                    }
                    totalAttempts = totalAttempts + numberOfAttempts;
                    numberOfAttempts = 0;
                }
            }

            synchronized (lock) {
                totalNumberOfAttempts.set(totalNumberOfAttempts.addAndGet(totalAttempts));
            }

            allSolutionsLatch.addValue(chessboard.placedQueens().toString());
            executor.execute(new SolutionTask());
        }

        private void removeRandomQueens(int numberOfQueensToRemove, Chessboard chessboard) {
            for (int i = 0; i < numberOfQueensToRemove; i++) {
                Queen queenToRemove = chessboard.placedQueens().stream().collect(Collectors.toList())
                        .get(ThreadLocalRandom.current().nextInt(0, chessboard.placedQueens().size()));
                chessboard.removeQueen(queenToRemove);
            }
        }
    }

    public Set<String> getAllSolutions() {
        try {
            ((ThreadPoolExecutor) executor).setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            for (int i = 0; i < 20; i++) {
                executor.execute(new SolutionTask());
            }
            return allSolutionsLatch.getAllSolutions();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            System.out.println(totalNumberOfAttempts);
            executor.shutdownNow();
        }
    }
}