package peter.hickling.queens;

public class Queen implements Comparable<Queen> {

    private final Integer x;
    private final Integer y;

    private Queen(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Builder aQueen() {
        return new Builder();
    }

    public int hashCode() {

        return x.hashCode() ^ y.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof Queen && x.equals(((Queen) obj).getX()) && y.equals(((Queen) obj).getY());
    }

    public String toString() {
        return String.format("[%d, %d]", this.getX(), this.getY());
    }

    @Override
    public int compareTo(Queen queen) {
        return x - queen.getX();
    }

    public static class Builder {
        private int x;
        private int y;

        public Builder x(int x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Queen build() {
            return new Queen(this);
        }
    }
}
