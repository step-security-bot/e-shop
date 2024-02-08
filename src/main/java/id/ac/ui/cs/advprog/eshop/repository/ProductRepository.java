package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ProductRepository {
    private static List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public static Product findByName(String name) {
        for (Product product : productData) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public Product delete(String name) {
        Product product = findByName(name);
        if (product != null) {
            productData.remove(product);
        }
        return product;
    }

    public Product update(Product product, String name) {
        Product oldProduct = findByName(name);
        if (oldProduct != null) {
            oldProduct.setProductName(product.getProductName());
            oldProduct.setProductQuantity(product.getProductQuantity());
        }
        return oldProduct;
    }

    public  void clear() {
        productData.clear();
    }
}
