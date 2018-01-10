package peter.hickling.queens;

import java.util.concurrent.ThreadLocalRandom;

public class TestQueens {
    public static Queen randomQueen() {
        return Queen.aQueen().x(ThreadLocalRandom.current().nextInt(0, 8)).y(ThreadLocalRandom.current().nextInt(0, 8))
                .build();
    }
}
