package peter.hickling.queens;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class AllSolutionsLatchTest {
    private final static Integer BIG_LATCH_MAX = 100000;
    private final AllSolutionsLatch<Integer> allSolutionsSmallLatch = new AllSolutionsLatch(3);
    private final AllSolutionsLatch<Integer> allSolutionsBigLatch = new AllSolutionsLatch(BIG_LATCH_MAX);

    @Test
    public void theLatchShouldNotCloseIfTheNumberOfSolutionsIsBelowTheTotal() {
        for (int i = 0; i < 2; i++) {
            allSolutionsSmallLatch.addValue(Collections.singleton(i));
        }

        assertThat(allSolutionsSmallLatch.isClosed(), CoreMatchers.is(false));
    }

    @Test
    public void theLatchShouldOnlyCloseWhenTheTotalNumberOfSolutionsHasBeenMet() {
        for (int i = 0; i < 3; i++) {
            allSolutionsSmallLatch.addValue(Collections.singleton(i));
        }

        assertThat(allSolutionsSmallLatch.isClosed(), CoreMatchers.is(true));
    }

    @Test
    public void shouldNotStoreDuplicates() {
        for (int i = 0; i < 4; i++) {
            allSolutionsSmallLatch.addValue(Collections.singleton(1));
        }

        assertThat(allSolutionsSmallLatch.isClosed(), CoreMatchers.is(false));
    }

    @Test(timeout=1000)
    public void safetyTest() throws InterruptedException, BrokenBarrierException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(21);
        for (int i = 0; i < 20; i++) {
            executor.execute(new TestTask(BIG_LATCH_MAX*i, allSolutionsBigLatch, cyclicBarrier, BIG_LATCH_MAX));
        }
        cyclicBarrier.await();
        cyclicBarrier.await();
        assertThat(allSolutionsBigLatch.getAllSolutions().size(), CoreMatchers.is(BIG_LATCH_MAX));
        executor.shutdownNow();
    }

    private class TestTask implements Runnable {
        private final Integer startValue;
        private final Integer numberOfValuesToAdd;
        private final AllSolutionsLatch<Integer> latch;
        private final CyclicBarrier barrier;

        public TestTask(Integer startValue, AllSolutionsLatch<Integer> latch, CyclicBarrier barrier, Integer numberOfValuesToAdd) {
            this.startValue = startValue;
            this.latch = latch;
            this.barrier = barrier;
            this.numberOfValuesToAdd = numberOfValuesToAdd;
        }

        @Override
        public void run() {
            try {
                barrier.await();
                for (int i = 0; i < numberOfValuesToAdd; i++) {
                    latch.addValue(Collections.singleton(startValue + i));
                }
                barrier.await();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}