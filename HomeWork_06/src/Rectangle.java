public class Rectangle extends Shape {

    private final double height;
    private final double width;

    Rectangle(int x, int y, double height, double width) {
        super(x, y);
        this.height = height;
        this.width = width;
    }

    public double computePerimeter() {
        return 2 * (height + width);
    }

    public double computeArea() {
        return height * width;
    }

    public double getHeight() {
        return this.height;
    }
    public double getWidth() {
        return this.width;
    }

}
