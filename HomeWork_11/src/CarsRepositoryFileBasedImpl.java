import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarsRepositoryFileBasedImpl implements CarsRepository {

    private final String fileName;

    public CarsRepositoryFileBasedImpl(String fileName) {
        this.fileName = fileName;
    }

    private static Car parseCar(String carLine) {
        String[] carData = carLine.split("\\|");
        final String licensePlate = carData[0];

        final String model = carData[1];
        final String colour = carData[2];
        final Integer mileage = Integer.parseInt(carData[3]);
        final Integer price = Integer.parseInt(carData[4]);

        return new Car(licensePlate, model, colour, mileage, price);
    }

    private Stream<Car> getAll() {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            return lines.map(CarsRepositoryFileBasedImpl::parseCar);
        } catch (IOException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }

    @Override
    public List<String> findLicensePlatesOfBlackOrZeroMileage() {
        return getAll()
                .filter(car -> Objects.equals(car.getColour(), "Black") || car.getMileage().equals(0))
                .map(Car::getLicensePlate)
                .collect(Collectors.toList());
    }

    @Override
    public Integer findNumUniqueCarsPricedFrom700To800Thousands() {
        return (int) getAll()
                .filter(car -> 700_000 <= car.getPrice() && car.getPrice() <= 800_000)
                .map(Car::getModel)
                .distinct()
                .count();
    }

    @Override
    public String findColourOfMinPricedCar() {
        return getAll()
                .min(Comparator.comparingInt(Car::getPrice))
                .map(Car::getColour)
                .orElseThrow();
    }

    @Override
    public Double findAveragePriceOfModelCamry() {
        return getAll()
                .filter(car -> car.getModel().equals("Camry"))
                .mapToInt(Car::getPrice)
                .average()
                .orElseThrow();
    }
}
