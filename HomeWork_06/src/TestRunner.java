public class TestRunner {
    public static void runTests() {
        testShape();
        testRectangle();
        testSquare();
        testEllipse();
        testCircle();
    }

    public static void testShape() {
        assert 0 == new Rectangle(0, 0, 10, 10).getX();
        assert 0 == new Rectangle(0, 0, 10, 10).getY();

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

        double[][] tests = {{0, 0}, {0, 1}, {1, 0}, {-1, -1}, {1, -1}, {-1, 1}};
        for (var heightWidth : tests) {
            try {
                new Rectangle(1, 2, heightWidth[0], heightWidth[1]);
                throw new Exception("rectangle was created with incorrect height or width");

            } catch (Exception exception) {

                String expectedMessage = "height or width is lower than or equal to zero: height: "
                        + heightWidth[0] + " width: " + heightWidth[1];
                String gotMessage = exception.getMessage();
                assert expectedMessage.equals(gotMessage);
            }
        }
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

        double[][] tests = {{0, 0}, {0, 1}, {1, 0}, {-1, -1}, {1, -1}, {-1, 1}};
        for (var smallRbigR : tests) {
            try {
                new Ellipse(1, 2, smallRbigR[0], smallRbigR[1]);
                throw new Exception("rectangle was created with incorrect height or width");

            } catch (Exception exception) {

                String expectedMessage = "smaller radius or bigger radius is lower then or equal to zero: smallR: "
                        + smallRbigR[0] + " bigR: " + smallRbigR[1];
                String gotMessage = exception.getMessage();
                assert expectedMessage.equals(gotMessage);
            }
        }
    }

    public static void testCircle() {
        assert 10 == new Circle(1, 2, 10).getSmallR();
        assert 10 == new Circle(1, 2, 10).getBigR();
        assert 10 == new Circle(1, 2, 10).getRadius();

        assert 20 * Math.PI == new Circle(1, 2, 10).computePerimeter();
        assert 100 * Math.PI == new Circle(1, 2, 10).computeArea();
    }
}
