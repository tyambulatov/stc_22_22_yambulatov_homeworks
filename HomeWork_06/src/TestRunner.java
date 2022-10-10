public class TestRunner {
    public static void runTests() {
        testShape();
        testRectangle();
        testSquare();
        testEllipse();
        testCircle();
    }

    public static void testShape() {
        assert 1 == new Rectangle(1, 2, 10, 10).getX();
        assert 2 == new Rectangle(1, 2, 10, 10).getY();

        Rectangle rectangle = new Rectangle(1, 2, 10, 10);
        rectangle.move(3, 4);
        assert 3 == rectangle.getX();
        assert 4 == rectangle.getY();
    }

    public static void testRectangle() {
        assert 10 == new Rectangle(1, 2, 10, 20).getHeight();
        assert 20 == new Rectangle(1, 2, 10, 20).getWidth();

        assert 60 == new Rectangle(1, 2, 10, 20).computePerimeter();
        assert 200 == new Rectangle(1, 2, 10, 20).computeArea();
    }

    public static void testSquare() {
        assert 10 == new Square(1, 2, 10).getHeight();
        assert 10 == new Square(1, 2, 10).getWidth();
        assert 10 == new Square(1, 2, 10).getSide();

        assert 40 == new Square(1, 2, 10).computePerimeter();
        assert 100 == new Square(1, 2, 10).computeArea();


    }

    public static void testEllipse() {
        assert 5 == new Ellipse(1, 2, 5, 10).getSmallR();
        assert 10 == new Ellipse(1, 2, 5, 10).getBigR();

        assert 4 * (50 * Math.PI + 25) / 15 == new Ellipse(1, 2, 5, 10).computePerimeter();
        assert 50 * Math.PI == new Ellipse(1, 2, 5, 10).computeArea();
    }

    public static void testCircle() {
        assert 10 == new Circle(1, 2, 10).getSmallR();
        assert 10 == new Circle(1, 2, 10).getBigR();
        assert 10 == new Circle(1, 2, 10).getRadius();

        assert 20 * Math.PI == new Circle(1, 2, 10).computePerimeter();
        assert 100 * Math.PI == new Circle(1, 2, 10).computeArea();
    }
}
