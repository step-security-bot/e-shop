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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Iterator;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class ProductFunctionalTest {

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
        driver.get(baseUrl + "/product/listProduct");

        driver.findElement(By.id("createProduct")).click();
        driver.findElement(By.id("nameInput")).sendKeys("Product 1");
        driver.findElement(By.id("quantityInput")).sendKeys("100");
        driver.findElement(By.id("submit")).click();

        assertEquals("Product 1", driver.findElement(By.id("namaProduk")).getText());
    }

    @Test
    void updateProductFunctionalTest(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/listProduct");

        driver.findElement(By.id("updateProduct")).click();
        driver.findElement(By.id("productName")).clear();
        driver.findElement(By.id("productName")).sendKeys("Product 2");
        driver.findElement(By.id("productQuantity")).clear();
        driver.findElement(By.id("productQuantity")).sendKeys("200");
        driver.findElement(By.id("submit")).click();

        assertEquals("Product 2", driver.findElement(By.id("namaProduk")).getText());
    }

    @Test
    void deleteProductFunctionalTest(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/listProduct");

        driver.findElement(By.id("deleteProduct")).click();
        
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}