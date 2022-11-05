import java.util.List;

public interface CarsRepository {
    List<String> findLicensePlatesOfBlackOrZeroMileage();

    Integer findNumUniqueCarsPricedFrom700To800Thousands();

    String findColourOfMinPricedCar();

    Double findAveragePriceOfModelCamry();
}
