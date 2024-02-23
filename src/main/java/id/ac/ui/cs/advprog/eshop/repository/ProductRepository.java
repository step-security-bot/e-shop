package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    static int id = 0;

    List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product update (String id, Product updateProduct) {
        UpdateProduct update = new UpdateProduct(this);
        return update.updateProduct(id, updateProduct);
    }

    public void delete (String id) {
        DeleteProduct deleteProduct = new DeleteProduct(this);
        deleteProduct.deleteProduct(id);
    }

    public void clear() { productData.clear(); }
}