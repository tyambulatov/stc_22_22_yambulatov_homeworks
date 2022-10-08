public class Main {
    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        testShape();
        testRectangle();
        testSquare();
        testEllipse();
        testCircle();
    }

    public static void testShape() {
        Rectangle rectangle = new Rectangle(1, 2, 10, 10);
        assert 1 == rectangle.getX();
        assert 2 == rectangle.getY();

        rectangle.move(2, 3);
        assert 2 == rectangle.getX();
        assert 3 == rectangle.getY();

        Shape shape = new Rectangle(1, 2, 10, 10);
        assert 1 == shape.getX();
        assert 2 == shape.getY();

        assert 40 == shape.computePerimeter();
        assert 100 == shape.computeArea();
    }

    public static void testRectangle() {
        Rectangle rectangle = new Rectangle(1, 2, 10, 20);
        assert 10 == rectangle.getHeight();
        assert 20 == rectangle.getWidth();

        assert 60 == rectangle.computePerimeter();
        assert 200 == rectangle.computeArea();
    }

    public static void testSquare() {
        Square square = new Square(1, 2, 10);
        assert 10 == square.getHeight();
        assert 10 == square.getWidth();
        assert 10 == square.getSide();

        assert 40 == square.computePerimeter();
        assert 100 == square.computeArea();


    }

    public static void testEllipse() {
        Ellipse ellipse = new Ellipse(1, 2, 5, 10);
        assert 5 == ellipse.getSmallR();
        assert 10 == ellipse.getBigR();

        assert 4 * (50 * Math.PI + 25) / 15 == ellipse.computePerimeter();
        assert 50 * Math.PI == ellipse.computeArea();
    }

    public static void testCircle() {
        Circle circle = new Circle(1, 2, 10);
        assert 10 == circle.getSmallR();
        assert 10 == circle.getBigR();
        assert 10 == circle.getRadius();

        assert 20 * Math.PI == circle.computePerimeter();
        assert 100 * Math.PI == circle.computeArea();
    }

}