package id.ac.ui.cs.advprog.eshop.functional;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @InjectMocks
    ProductRepository productRepository;

    @LocalServerPort
    private int serverPort;
    
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProductFunctionalTest(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/list");

        driver.findElement(By.id("createProduct")).click();
        driver.findElement(By.id("nameInput")).sendKeys("Product 1");
        driver.findElement(By.id("quantityInput")).sendKeys("100");
        driver.findElement(By.id("submit")).click();

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product product = productIterator.next();
        assertEquals("Product 1", product.getProductName());
        assertEquals(100, product.getProductQuantity());
    }
}
