import java.util.List;

public interface CarsRepository {
    List<String> findLicensePlatesOfBlackOrZeroMileage();

    int findNumUniqueCarsPricedFrom700To800Thousands();

    String findColourOfMinPricedCar();

    double findAveragePriceOfModelCamry();
}
