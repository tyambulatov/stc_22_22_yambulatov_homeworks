import java.util.List;

public interface CarsRepository {
    List<String> findLicensePlatesCarsModel(String model);

    List<String> findLicensePlatesCarsColour(String colour);

    List<String> findLicensePlatesCarsMileage(Integer mileage);

    List<String> findLicensePlatesCarsPrice(Integer price);

    Integer findNumUniqueCarsPricedFrom700To800Thousands();

    String findColourOfMinPricedCar();

    Double findAveragePriceOfModelCamry();
}
