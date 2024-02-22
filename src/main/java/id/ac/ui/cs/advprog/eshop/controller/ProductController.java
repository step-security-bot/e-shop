package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/createProduct")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/createProduct")
    public String createProductPost(@ModelAttribute Product product, Model model){
        productService.create(product);
        return "redirect:listProduct";
    }

    @GetMapping("/listProduct")
    public String productListPage(Model model) {
        List<Product> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        return "ListProduct";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") String productId) {
        productService.deleteObjectById(productId);
        return "redirect:listProduct";
    }

    @GetMapping("/editProduct/{productId}")
    public String editProductPage(@PathVariable("productId") String productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/editProduct")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        System.out.println(product.getProductId());
        productService.update(product.getProductId(), product);

        return "redirect:listProduct";
    }
}

@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model){
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable("carId") String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);

        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteObjectById(carId);
        return "redirect:listCar";
    }
}

@Controller
@RequestMapping("/")
class HomeController {
    @GetMapping
    public String homePage() {
        return "Homepage";
    }
}