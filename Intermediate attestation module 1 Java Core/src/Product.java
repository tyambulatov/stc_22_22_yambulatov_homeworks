public class Product {
    private Integer id;

    private String title;

    private Double price;

    private Integer count;

    public Product(Integer id, String title, Double price, Integer count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "[" + id.toString() + '|' +
                title + '|' +
                price + '|' +
                count + "]";
    }

    public String toLine() {
        return id.toString() + '|' +
                title + '|' +
                price + '|' +
                count;
    }
}
