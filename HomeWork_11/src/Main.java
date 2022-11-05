import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarsRepository carsRepository = new CarsRepositoryFileBasedImpl("input.txt");

        assert List.of("o001aa111", "o002aa111", "o001aa111").equals(carsRepository.findLicensePlatesOfBlackOrZeroMileage());
        assert Integer.valueOf(0).equals(carsRepository.findNumUniqueCarsPricedFrom700To800Thousands());
        assert "Green".equals(carsRepository.findColourOfMinPricedCar());
        assert Double.valueOf(82000 * 2 / 3d).equals(carsRepository.findAveragePriceOfModelCamry());
    }
}