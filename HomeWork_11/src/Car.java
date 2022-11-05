public class Car {
    private final String licensePlate;

    private final String model;

    private final String colour;

    private final Integer mileage;

    private final Integer price;

    public Car(String licensePlate, String model, String colour, Integer mileage, Integer price) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.colour = colour;
        this.mileage = mileage;
        this.price = price;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public Integer getMileage() {
        return mileage;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                '}';
    }
}
