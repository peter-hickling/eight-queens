package peter.hickling.queens;

public class Queen implements Comparable<Queen> {

    private Integer x;
    private Integer y;

    private Queen(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
    }

    public int getX() {
        return new Integer(x);
    }

    public int getY() {
        return new Integer(y);
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
        private Integer x;
        private Integer y;

        public Builder x(Integer x) {
            this.x = x;
            return this;
        }

        public Builder y(Integer y) {
            this.y = y;
            return this;
        }

        public Queen build() {
            return new Queen(this);
        }
    }
}
