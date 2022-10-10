public abstract class AbstractNumbersPrintTask implements Task {

    protected int from;
    protected int to;

    protected AbstractNumbersPrintTask(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("Invalid tasks range:'from' is bigger than 'to': 'from': " + from + " 'to': " + to);
        }
        if (from <= 0) {
            throw new IllegalArgumentException("Invalid task number: 'from' or 'to' parameter is lower than 1: 'from': " + from + " 'to': " + to);
        }
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return this.from;
    }

    public int getTo() {
        return this.to;
    }
}
