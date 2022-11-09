import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsRepositoryFileBasedImpl implements ProductsRepository {
    private final String fileName;

    public ProductsRepositoryFileBasedImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Product findById(Integer id) {
        return getAll()
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File does not contain id: " + id));
    }

    private List<Product> getAll() {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            return lines
                    .map(ProductsRepositoryFileBasedImpl::parseProduct)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }

    private static Product parseProduct(String productLine) {
        String[] productData = productLine.split("\\|");

        final Integer productId = Integer.parseInt(productData[0]);
        final String productTitle = productData[1];
        final Double productPrice = Double.parseDouble(productData[2]);
        final Integer productCount = Integer.parseInt(productData[3]);

        return new Product(productId, productTitle, productPrice, productCount);
    }

    @Override
    public List<Product> findAllByTitleLike(String title) {
        return getAll()
                .stream()
                .filter(product -> product.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Product updatedProduct) {
        final List<String> updatedLines = generateUpdatedProductLines(updatedProduct);
        writeAll(updatedLines);
    }

    private List<String> generateUpdatedProductLines(Product updatedProduct) {
        final Integer idToBeFound = updatedProduct.getId();

        return getAll()
                .stream()
                .map(product -> product.getId().equals(idToBeFound) ? updatedProduct : product)
                .map(Product::toLine)
                .toList();
    }

    private void writeAll(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new UnsuccessfulWorkWithFileException(e);
        }
    }
}
