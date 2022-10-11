public abstract class Shape {
    private int x;
    private int y;

    Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract double computePerimeter();

    public abstract double computeArea();

    public void move(int toX, int toY) {
        x = toX;
        y = toY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
