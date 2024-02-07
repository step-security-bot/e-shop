package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ListProduct";
    }

    @GetMapping("/delete/{name}")
    public String deleteProduct(@PathVariable("name") String name, Model model) {
        service.delete(name);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{name}")
    public String editProductPage(@PathVariable("name") String name, Model model) {
        Product product = service.findByName(name);
        model.addAttribute("product", product);
        System.out.println(product.getProductName());
        System.out.println(product.getProductQuantity());
        return "EditProduct";
    }

    @PostMapping("/edit/{name}")
    public String editProductPost(@ModelAttribute Product product, @PathVariable("name") String name, Model model) {
        service.update(product, name);
        return "redirect:/product/list";
    }
}
