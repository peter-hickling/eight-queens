package peter.hickling.queens;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class AllSolutionsLatch<T> {
    private Set<Set<T>> allSolutions = new HashSet<>();
    private final CountDownLatch done;

    public AllSolutionsLatch(Integer totalNumberOfSolutions) {
        done = new CountDownLatch(totalNumberOfSolutions);
    }

    public boolean isClosed() {
        return done.getCount() == 0;
    }

    public synchronized void addValue(Set<T> solution) {
        if (!isClosed() && !allSolutions.contains(solution)) {
            allSolutions.add(solution);
            done.countDown();
        }
    }

    public Set<Set<T>> getAllSolutions() throws InterruptedException {
        done.await();
        synchronized (this) {
            return allSolutions;
        }
    }
}
