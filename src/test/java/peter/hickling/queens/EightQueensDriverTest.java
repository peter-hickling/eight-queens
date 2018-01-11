package peter.hickling.queens;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class EightQueensDriverTest {

    @Test
    public void canFindAllSolutions() {
        EightQueensDriver driver = new EightQueensDriver();
        Set<Set<Queen>> allSolutions = driver.getAllSolutions();
        System.out.println(allSolutions.stream().map(solution -> solution.toString()).collect(Collectors.toList()));
        assertThat(allSolutions.size(), is(92));
    }
}
