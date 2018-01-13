package peter.hickling.queens;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertThat;

public class LogicalEightQueensDriverForFindingTotalNumberOfSolutionsTest {
    private final LogicalEightQueensDriverForFindingTotalNumberOfSolutions driver = new LogicalEightQueensDriverForFindingTotalNumberOfSolutions();

    @Test
    public void findAllSolutionsShouldReturn92Solutions() throws InterruptedException {
        Set<Set<Queen>> allSolutions = this.driver.findAllSolutions();
        assertThat(allSolutions.size(), CoreMatchers.is(92));
    }
}