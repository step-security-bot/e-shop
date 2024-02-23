package id.ac.ui.cs.advprog.eshop.functional;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(OrderAnnotation.class)
class CarFunctionalTest {

    @InjectMocks
    CarRepository carRepository;

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
    @Order(1)
    void createCarFunctionalTest(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/car/listCar");

        driver.findElement(By.id("createCar")).click();
        driver.findElement(By.id("nameInput")).sendKeys("Car 1");
        driver.findElement(By.id("colorInput")).sendKeys("Red");
        driver.findElement(By.id("quantityInput")).sendKeys("1");
        driver.findElement(By.id("submit")).click();

        assertEquals("Car 1", driver.findElement(By.id("namaCar")).getText());
    }

    @Test
    @Order(2)
    void updateCarFunctionalTest(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/car/listCar");

        driver.findElement(By.id("updateCar")).click();
        driver.findElement(By.id("carName")).clear();
        driver.findElement(By.id("carName")).sendKeys("Car 2");
        driver.findElement(By.id("carColor")).clear();
        driver.findElement(By.id("carColor")).sendKeys("Blue");
        driver.findElement(By.id("carQuantity")).clear();
        driver.findElement(By.id("carQuantity")).sendKeys("2");
        driver.findElement(By.id("submit")).click();

        assertEquals("Car 2", driver.findElement(By.id("namaCar")).getText());
    }

    @Test
    @Order(3)
    void deleteCarFunctionalTest(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/car/listCar");

        driver.findElement(By.id("deleteCar")).click();

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
}