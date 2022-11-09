import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarsRepositoryFileBasedImpl implements CarsRepository {

    private final String fileName;

    public CarsRepositoryFileBasedImpl(String fileName) {
        this.fileName = fileName;
    }

    private Stream<Car> getAll() {
        List<Car> carList;
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            carList = lines
                    .map(CarsRepositoryFileBasedImpl::parseCar)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
        return carList.stream();
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

    @Override
    public List<String> findLicensePlatesCarsModel(String model) {
        return getAll()
                .filter(car -> car.getModel().equals(model))
                .map(Car::getLicensePlate)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findLicensePlatesCarsColour(String colour) {
        return getAll()
                .filter(car -> car.getColour().equals(colour))
                .map(Car::getLicensePlate)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findLicensePlatesCarsMileage(Integer mileage) {
        return getAll()
                .filter(car -> car.getMileage().equals(mileage))
                .map(Car::getLicensePlate)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findLicensePlatesCarsPrice(Integer price) {
        return getAll()
                .filter(car -> car.getPrice().equals(price))
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
