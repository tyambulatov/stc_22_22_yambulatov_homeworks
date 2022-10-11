public class Rectangle extends Shape {

    private final double height;
    private final double width;

    Rectangle(int x, int y, double height, double width) {
        super(x, y);

        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("height or width is lower than or equal to zero: height: "
                        + height + " width: " + width);
        }
        this.height = height;
        this.width = width;
    }

    @Override
    public double computePerimeter() {
        return 2 * (height + width);
    }

    @Override
    public double computeArea() {
        return height * width;
    }

    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }

}
