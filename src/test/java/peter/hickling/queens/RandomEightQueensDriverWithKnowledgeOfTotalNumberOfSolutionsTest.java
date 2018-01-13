package peter.hickling.queens;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class RandomEightQueensDriverWithKnowledgeOfTotalNumberOfSolutionsTest {

    @Test
    public void canFindAllSolutions() {
        RandomEightQueensDriverWithKnowledgeOfTotalNumberOfSolutions driver = new RandomEightQueensDriverWithKnowledgeOfTotalNumberOfSolutions();
        Set<Set<Queen>> allSolutions = driver.getAllSolutions();
        assertThat(allSolutions.size(), is(92));
    }
}
