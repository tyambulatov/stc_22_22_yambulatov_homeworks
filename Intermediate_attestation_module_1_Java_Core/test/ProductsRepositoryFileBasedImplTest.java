import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ProductsRepositoryFileBasedImplTest {

    String getFilePath(String fileName) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        final String testInputsFilesPath = "test/testResources/";
        return testInputsFilesPath + stackTrace[1].getMethodName() + "/" + fileName;
    }

    @Test
    void findById() {
        final String emptyFilePath = getFilePath("inputEmpty.txt");
        final String oneProductFilePath = getFilePath("inputOneProduct.txt");
        final String multipleProductsFilePath = getFilePath("inputMultipleProducts.txt");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ProductsRepositoryFileBasedImpl(
                        emptyFilePath)
                        .findById(1));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ProductsRepositoryFileBasedImpl(
                        multipleProductsFilePath)
                        .findById(null));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ProductsRepositoryFileBasedImpl(
                        multipleProductsFilePath)
                        .findById(10));

        Assertions.assertEquals("[3|Айва|159.6|32]",
                new ProductsRepositoryFileBasedImpl(
                        oneProductFilePath)
                        .findById(3)
                        .toString());

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ProductsRepositoryFileBasedImpl(
                        multipleProductsFilePath)
                        .findById(10));

        Assertions.assertEquals("[1|Абрикос|214.9|43]",
                new ProductsRepositoryFileBasedImpl(
                        multipleProductsFilePath)
                        .findById(1)
                        .toString());

        Assertions.assertEquals("[4|Апельсины|198.8|31]",
                new ProductsRepositoryFileBasedImpl(
                        multipleProductsFilePath)
                        .findById(4)
                        .toString());

        Assertions.assertEquals("[8|Грейпфрут|79.6|10]",
                new ProductsRepositoryFileBasedImpl(
                        multipleProductsFilePath)
                        .findById(8)
                        .toString());
    }

    @Test
    void findAllByTitleLike() {
        final String emptyFilePath = getFilePath("inputEmpty.txt");
        final String multipleProductsFilePath = getFilePath("inputMultipleProducts.txt");

        Assertions.assertEquals(
                "[]",
                new ProductsRepositoryFileBasedImpl(emptyFilePath)
                        .findAllByTitleLike("сыр")
                        .toString()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ProductsRepositoryFileBasedImpl(multipleProductsFilePath)
                        .findAllByTitleLike(null)
        );

        Assertions.assertEquals(
                "[]",
                new ProductsRepositoryFileBasedImpl(emptyFilePath)
                        .findAllByTitleLike("")
                        .toString()
        );

        Assertions.assertEquals(
                "[]",
                new ProductsRepositoryFileBasedImpl(multipleProductsFilePath)
                        .findAllByTitleLike("сыр")
                        .toString()
        );

        Assertions.assertEquals(
                "[[8|Грейпфрут|79.6|10]]",
                new ProductsRepositoryFileBasedImpl(multipleProductsFilePath)
                        .findAllByTitleLike("Грейпфрут")
                        .toString()
        );

        Assertions.assertEquals(
                "[[1|Абрикос|214.9|43], " +
                        "[2|Абрикосы|197.0|39]]",
                new ProductsRepositoryFileBasedImpl(multipleProductsFilePath)
                        .findAllByTitleLike("абрикос")
                        .toString()
        );

        Assertions.assertEquals(
                "[[1|Абрикос|214.9|43], " +
                        "[2|Абрикосы|197.0|39], " +
                        "[3|Айва|159.6|32], " +
                        "[4|Апельсины|198.8|31], " +
                        "[5|Арбуз|233.8|46], " +
                        "[6|Бананы|88.3|47], " +
                        "[7|Гранат|174.9|48]]",
                new ProductsRepositoryFileBasedImpl(multipleProductsFilePath)
                        .findAllByTitleLike("А")
                        .toString()
        );
    }

    @Test
    void update() {
        final String multipleProductsFilePath = getFilePath("inputMultipleProducts.txt");
        final String originalFileLines = "1|Абрикос|214.9|43\n" +
                "2|Абрикосы|197.0|39\n" +
                "3|Айва|159.6|32\n" +
                "4|Апельсины|198.8|31";
        reformInputFileToOriginal(multipleProductsFilePath);
        ProductsRepository productsRepository = new ProductsRepositoryFileBasedImpl(multipleProductsFilePath);

        Assertions.assertThrows(
                NullPointerException.class,
                () -> productsRepository.update(null));

        Product productOutOfBoundId = new Product(10, "Бананы", 88.3d, 47);
        productsRepository.update(productOutOfBoundId);
        Assertions.assertEquals(originalFileLines, getAll(multipleProductsFilePath));
        reformInputFileToOriginal(multipleProductsFilePath);

        Product productGotById = productsRepository.findById(1);
        productsRepository.update(productGotById);
        Assertions.assertEquals(originalFileLines, getAll(multipleProductsFilePath));
        reformInputFileToOriginal(multipleProductsFilePath);

        Product productUpdatedPrice = productsRepository.findById(1);
        productUpdatedPrice.setPrice(10d);
        productsRepository.update(productUpdatedPrice);
        String expectedLines = "1|Абрикос|10.0|43\n" +
                "2|Абрикосы|197.0|39\n" +
                "3|Айва|159.6|32\n" +
                "4|Апельсины|198.8|31";
        Assertions.assertEquals(expectedLines, getAll(multipleProductsFilePath));
        reformInputFileToOriginal(multipleProductsFilePath);

        Product productUpdatedCount = productsRepository.findById(1);
        productUpdatedCount.setCount(10);
        productsRepository.update(productUpdatedCount);
        expectedLines = "1|Абрикос|214.9|10\n" +
                "2|Абрикосы|197.0|39\n" +
                "3|Айва|159.6|32\n" +
                "4|Апельсины|198.8|31";
        Assertions.assertEquals(expectedLines, getAll(multipleProductsFilePath));
        reformInputFileToOriginal(multipleProductsFilePath);

        Product productUpdatedPriceAndCount = productsRepository.findById(1);
        productUpdatedPriceAndCount.setPrice(10d);
        productUpdatedPriceAndCount.setCount(100);
        productsRepository.update(productUpdatedPriceAndCount);
        expectedLines = "1|Абрикос|10.0|100\n" +
                "2|Абрикосы|197.0|39\n" +
                "3|Айва|159.6|32\n" +
                "4|Апельсины|198.8|31";
        Assertions.assertEquals(expectedLines, getAll(multipleProductsFilePath));
        reformInputFileToOriginal(multipleProductsFilePath);
    }

    private static String getAll(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }

    private static void reformInputFileToOriginal(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("1|Абрикос|214.9|43\n2|Абрикосы|197.0|39\n3|Айва|159.6|32\n4|Апельсины|198.8|31");
        } catch (IOException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }
}