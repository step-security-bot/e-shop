package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
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