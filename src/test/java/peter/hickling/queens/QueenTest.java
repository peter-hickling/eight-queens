package peter.hickling.queens;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueenTest {
    private Integer randomInteger = ThreadLocalRandom.current().nextInt(0, 8);

    @Test
    public void shouldBeAbleToGetAndSetXCoordinate() {
        assertThat(Queen.aQueen().x(randomInteger).build().getX(), is(randomInteger));
    }

    @Test
    public void shouldBeAbleToGetAndSetYCoordinate() {
        assertThat(Queen.aQueen().y(randomInteger).build().getY(), is(randomInteger));
    }

    @Test
    public void twoQueensShouldBeEqualIfTheyHaveTheSameCoordinates() {
        assertThat(
                Queen.aQueen().x(randomInteger).y(randomInteger).build()
                        .equals(Queen.aQueen().x(randomInteger).y(randomInteger).build()), is(true));
    }

    @Test
    public void twoQueensShouldBeDifferentIfTheyHaveDifferentCoordinates() {
        Integer randomIntegerOne = ThreadLocalRandom.current().nextInt(0, 8);
        Integer randomIntegerTwo = ThreadLocalRandom.current().nextInt(0, 8);

        assertThat(
                Queen.aQueen().x(randomIntegerOne).y(randomIntegerTwo).build()
                        .equals(Queen.aQueen().x(randomIntegerTwo + 1).y(randomIntegerTwo + 1).build()), is(false));
    }
}
