public class Circle extends Ellipse{

    private final double radius;
    Circle(int x, int y, double radius) {
        super(x, y, radius, radius);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
