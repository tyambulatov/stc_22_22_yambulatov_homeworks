public class Square extends Rectangle{

    private final double side;

    Square(int x, int y, double side) {
        super(x, y, side, side);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

}
