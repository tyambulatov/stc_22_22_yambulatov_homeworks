public class Ellipse extends Shape{
    private final double smallR;
    private final double bigR;

    Ellipse(int x, int y, double smallR, double bigR) {
        super(x, y);
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
