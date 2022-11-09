import java.util.List;

public interface ProductsRepository {
    Product findById(Integer id);

    List<Product> findAllByTitleLike(String title);

    void update(Product product);
}
