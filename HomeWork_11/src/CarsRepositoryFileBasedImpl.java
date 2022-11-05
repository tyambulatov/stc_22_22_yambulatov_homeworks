import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarsRepositoryFileBasedImpl implements CarsRepository {

    final private String fileName;

    public CarsRepositoryFileBasedImpl(String fileName) {
        this.fileName = fileName;
    }

    private static final Function<String, Car> stringToCarMapper = currenCar -> {
        String[] carData = currenCar.split("\\|");
        final String licensePlate = carData[0];
        final String model = carData[1];
        final String colour = carData[2];
        final Integer mileage = Integer.parseInt(carData[3]);
        final Integer price = Integer.parseInt(carData[4]);
        return new Car(licensePlate, model, colour, mileage, price);
    };

    @Override
    public List<String> findLicensePlatesOfBlackOrZeroMileage() {
        try {
            return new BufferedReader(new FileReader(fileName))
                    .lines()
                    .map(stringToCarMapper)
                    .filter(car -> Objects.equals(car.getColour(), "Black") || car.getMileage().equals(0))
                    .map(Car::getLicensePlate)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }

    @Override
    public int findNumUniqueCarsPricedFrom700To800Thousands() {
        try {
            return (int) new BufferedReader(new FileReader(fileName))
                    .lines()
                    .map(stringToCarMapper)
                    .filter(car -> 700_000 <= car.getPrice() && car.getPrice() <= 800_000)
                    .map(Car::getModel)
                    .distinct()
                    .count();
        } catch (FileNotFoundException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }

    @Override
    public String findColourOfMinPricedCar() {
        try {
            return new BufferedReader(new FileReader(fileName))
                    .lines()
                    .map(stringToCarMapper)
                    .min(Comparator.comparingInt(Car::getPrice))
                    .map(Car::getColour)
                    .orElseThrow();
        } catch (FileNotFoundException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }

    @Override
    public double findAveragePriceOfModelCamry() {
        try {
            return new BufferedReader(new FileReader(fileName))
                    .lines()
                    .map(stringToCarMapper)
                    .filter(car -> car.getModel().equals("Camry"))
                    .mapToInt(Car::getPrice)
                    .average()
                    .orElseThrow();
        } catch (FileNotFoundException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }
}
