package peter.hickling.queens;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Set;

public class EightQueensDriverTest {

    @Test
    public void canFindAllSolutions() {
        EightQueensDriver driver = new EightQueensDriver();
        Set<String> allSolutions = driver.getAllSolutions();
        System.out.println(allSolutions);
        assertThat(allSolutions.size(), is(92));
    }
}
