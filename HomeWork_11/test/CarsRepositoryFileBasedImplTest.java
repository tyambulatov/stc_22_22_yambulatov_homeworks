import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

class CarsRepositoryFileBasedImplTest {

    private final static String TEST_INPUT_FILES_PATH = "test/resources/";

    String getFilePath(String fileName) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        return TEST_INPUT_FILES_PATH + stackTrace[1].getMethodName() + "/" + fileName;
    }

    @Test
    void findLicensePlatesOfBlackOrZeroMileage() {
        Assertions.assertEquals(
                List.<String>of()
                        .toString(),
                new CarsRepositoryFileBasedImpl(getFilePath("inputNoBlackOrZeroMileage.txt"))
                        .findLicensePlatesOfBlackOrZeroMileage()
                        .toString()
        );

        Assertions.assertEquals(
                List.of("a", "c", "e")
                        .toString(),
                new CarsRepositoryFileBasedImpl(getFilePath("inputBlackAndNoZeroMileage.txt"))
                        .findLicensePlatesOfBlackOrZeroMileage()
                        .toString()
        );

        Assertions.assertEquals(
                List.of("a", "b", "d")
                        .toString(),
                new CarsRepositoryFileBasedImpl(getFilePath("inputNoBlackAndZeroMileage.txt"))
                        .findLicensePlatesOfBlackOrZeroMileage()
                        .toString()
        );

        Assertions.assertEquals(
                List.of("b", "c", "d", "f")
                        .toString(),
                new CarsRepositoryFileBasedImpl(getFilePath("inputBlackAndZeroMileage.txt"))
                        .findLicensePlatesOfBlackOrZeroMileage()
                        .toString()
        );
    }

    @Test
    void findNumUniqueCarsPricedFrom700To800Thousands() {
        Assertions.assertEquals(
                0,
                new CarsRepositoryFileBasedImpl(getFilePath("inputNoCarsPricedFrom700To800Thousands.txt"))
                        .findNumUniqueCarsPricedFrom700To800Thousands()
        );

        Assertions.assertEquals(
                1,
                new CarsRepositoryFileBasedImpl(getFilePath("inputOneCarModelPricedFrom700To800Thousands.txt"))
                        .findNumUniqueCarsPricedFrom700To800Thousands()
        );

        Assertions.assertEquals(
                1,
                new CarsRepositoryFileBasedImpl(getFilePath("inputEveryCarGotOneModelPricedFrom700To800Thousands.txt"))
                        .findNumUniqueCarsPricedFrom700To800Thousands()
        );

        Assertions.assertEquals(
                6,
                new CarsRepositoryFileBasedImpl(getFilePath("inputEveryCarGotDistinctModelPricedFrom700To800Thousands.txt"))
                        .findNumUniqueCarsPricedFrom700To800Thousands()
        );

        Assertions.assertEquals(
                3,
                new CarsRepositoryFileBasedImpl(getFilePath("inputDifferentCarModelsPricedFrom700To800Thousands.txt"))
                        .findNumUniqueCarsPricedFrom700To800Thousands()
        );
    }

    @Test
    void findColourOfMinPricedCar() {
        Assertions.assertEquals(
                "Black",
                new CarsRepositoryFileBasedImpl(getFilePath("inputCarPricedZero.txt"))
                        .findColourOfMinPricedCar()
        );

        Assertions.assertEquals(
                "Black",
                new CarsRepositoryFileBasedImpl(getFilePath("inputEveryCarGotSamePrice.txt"))
                        .findColourOfMinPricedCar()
        );

        Assertions.assertEquals(
                "Black",
                new CarsRepositoryFileBasedImpl(getFilePath("inputDifferentPrices.txt"))
                        .findColourOfMinPricedCar()
        );
    }

    @Test
    void findAveragePriceOfModelCamry() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> new CarsRepositoryFileBasedImpl("test/resources/findAveragePriceOfModelCamry/inputNoModelCamry.txt")
                        .findAveragePriceOfModelCamry()
        );

        Assertions.assertEquals(
                10d,
                new CarsRepositoryFileBasedImpl(getFilePath("inputOneCarModelCamry.txt"))
                        .findAveragePriceOfModelCamry()
        );

        Assertions.assertEquals(
                10d,
                new CarsRepositoryFileBasedImpl(getFilePath("inputCamryCarsGotSamePrice.txt"))
                        .findAveragePriceOfModelCamry()
        );

        Assertions.assertEquals(
                (100 + 1 + 10 + 10000) / 4d,
                new CarsRepositoryFileBasedImpl(getFilePath("inputDifferentCamryPrices.txt"))
                        .findAveragePriceOfModelCamry()
        );
    }
}
