package id.ac.ui.cs.advprog.eshop.functional;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class HomePageFunctionalTest {
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
        driver.get(baseUrl + "/create");

        driver.findElement(By.id("createProduct")).click();
        driver.findElement(By.id("name")).sendKeys("Product 1");
        driver.findElement(By.id("price")).sendKeys("10000");
        driver.findElement(By.id("description")).sendKeys("Description 1");
        driver.findElement(By.id("submit")).click();

        assertEquals("Product 1", driver.findElement(By.id("productName")).getText());
        assertEquals("10000", driver.findElement(By.id("productPrice")).getText());
        assertEquals("Description 1", driver.findElement(By.id("productDescription")).getText());
    }
}
