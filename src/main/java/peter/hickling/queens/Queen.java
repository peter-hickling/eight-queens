package peter.hickling.queens;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Queen {

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
        return new HashCodeBuilder().append(x).append(y).toHashCode();
    }

    public boolean equals(Object obj) {
        Queen other = (Queen) obj;
        return new EqualsBuilder().append(this.getX(), other.getX()).append(this.getY(), other.getY()).isEquals();
    }

    public String toString() {
        return String.format("[%d, %d]", this.getX(), this.getY());
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
