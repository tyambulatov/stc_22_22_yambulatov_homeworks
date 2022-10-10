public class Ellipse extends Shape{
    private final double smallR;
    private final double bigR;

    Ellipse(int x, int y, double smallR, double bigR) {
        super(x, y);
        if (smallR <= 0 || bigR <= 0) {
            throw  new IllegalArgumentException("smaller radius or bigger radius is lower then or equal to zero: smallR: "
                    + smallR + " bigR: " + bigR);
        }
        this.smallR = smallR;
        this.bigR = bigR;
    }

    @Override
    public double computePerimeter() {
        return 4 * (Math.PI * bigR * smallR + Math.pow((bigR - smallR), 2)) / (bigR + smallR);
    }

    @Override
    public double computeArea() {
        return Math.PI * bigR * smallR;
    }

    public final double getSmallR() {
        return this.smallR;
    }
    public final double getBigR() {
        return this.bigR;
    }


}
