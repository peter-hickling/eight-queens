package peter.hickling.queens;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class AllSolutionsLatch {
    private Set<String> allSolutions = new HashSet<>();
    private final CountDownLatch done = new CountDownLatch(92);

    public boolean isSet() {
        return done.getCount() == 0;
    }

    public synchronized void addValue(String solution) {
        if (!isSet() && !allSolutions.contains(solution)) {
            allSolutions.add(solution);
            done.countDown();
        }
    }

    public Set<String> getAllSolutions() throws InterruptedException {
        done.await();
        synchronized (this) {
            return allSolutions;
        }
    }
}
