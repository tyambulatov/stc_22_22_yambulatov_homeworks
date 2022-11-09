import java.io.FileWriter;
import java.io.IOException;

public class TestRunner {

    private static final String FILE_PATH = "resources/input.txt";

    public static void run() {
        testProductRepository();
        reformInputFileToOriginal();
    }

    private static void testProductRepository() {
        ProductsRepositoryFileBasedImpl productsRepository = new ProductsRepositoryFileBasedImpl(FILE_PATH);
        assertTrue("1|Молоко|39.8|5".equals(productsRepository.findById(1).toLine()));
        assertTrue("5|Грибы|39.8|5".equals(productsRepository.findById(5).toLine()));

        assertTrue("[[5|Грибы|39.8|5]]".equals(productsRepository.findAllByTitleLike("Гр").toString()));
        assertTrue("[[1|Молоко|39.8|5]]".equals(productsRepository.findAllByTitleLike("мОЛ").toString()));

        Product milk = productsRepository.findById(1);
        milk.setCount(10);
        milk.setPrice(40.1);
        productsRepository.update(milk);
        assertTrue("[1|Молоко|40.1|10]".equals(productsRepository.findById(1).toString()));

        Product mushrooms = productsRepository.findById(5);
        mushrooms.setCount(10);
        mushrooms.setPrice(40.1);
        productsRepository.update(mushrooms);
        assertTrue("[5|Грибы|40.1|10]".equals(productsRepository.findById(5).toString()));
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError();
        }
    }

    private static void reformInputFileToOriginal() {
        try (FileWriter writer = new FileWriter(FILE_PATH, false)) {
            final String linesToWrite = "1|Молоко|39.8|5\n5|Грибы|39.8|5";
            writer.write(linesToWrite);
        } catch (IOException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }
}
