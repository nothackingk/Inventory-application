package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

/**
 *
 *
 *
 *
 */

@Controller
public class MainScreenControllerr {
   // private final PartRepository partRepository;
   // private final ProductRepository productRepository;'

    private PartService partService;
    private ProductService productService;

    private List<Part> theParts;
    private List<Product> theProducts;

 /*   public MainScreenControllerr(PartRepository partRepository, ProductRepository productRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
    }*/

    public MainScreenControllerr(PartService partService,ProductService productService){
        this.partService=partService;
        this.productService=productService;
    }
    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @Param("partkeyword") String partkeyword, @Param("productkeyword") String productkeyword){
        if (partService.findAll().isEmpty() && productService.findAll().isEmpty()) {

            InhousePart flour = new InhousePart();
            flour.setName("All-Purpose Flour");
            flour.setPrice(1.50);
            flour.setInv(50);
            flour.setPartId(101);
            flour.setMinInv(10);
            flour.setMaxInv(100);

            OutsourcedPart sugar = new OutsourcedPart();
            sugar.setName("Granulated Sugar");
            sugar.setPrice(1.20);
            sugar.setInv(40);
            sugar.setCompanyName("Sweet Supplies Co.");
            sugar.setMinInv(10);
            sugar.setMaxInv(100);

            InhousePart butter = new InhousePart();
            butter.setName("Unsalted Butter");
            butter.setPrice(2.75);
            butter.setInv(30);
            butter.setPartId(102);
            butter.setMinInv(10);
            butter.setMaxInv(100);

            OutsourcedPart eggs = new OutsourcedPart();
            eggs.setName("Fresh Eggs");
            eggs.setPrice(3.00);
            eggs.setInv(25);
            eggs.setCompanyName("Farm Fresh Foods");
            eggs.setMinInv(10);
            eggs.setMaxInv(100);

            InhousePart bakingPowder = new InhousePart();
            bakingPowder.setName("Baking Powder");
            bakingPowder.setPrice(0.99);
            bakingPowder.setInv(20);
            bakingPowder.setPartId(103);
            bakingPowder.setMinInv(10);
            bakingPowder.setMaxInv(100);

            partService.save(flour);
            partService.save(sugar);
            partService.save(butter);
            partService.save(eggs);
            partService.save(bakingPowder);

            Product cookies = new Product("Chocolate Chip Cookies", 4.99, 10);
            Product muffins = new Product("Blueberry Muffins", 3.99, 8);
            Product rolls = new Product("Cinnamon Rolls", 5.49, 6);
            Product bread = new Product("Sourdough Bread", 6.99, 5);
            Product cupcakes = new Product("Vanilla Cupcakes", 3.49, 12);

            productService.save(cookies);
            productService.save(muffins);
            productService.save(rolls);
            productService.save(bread);
            productService.save(cupcakes);
        }

        //add to the sprig model
        List<Part> partList=partService.listAll(partkeyword);
        theModel.addAttribute("parts",partList);
        theModel.addAttribute("partkeyword",partkeyword);
    //    theModel.addAttribute("products",productService.findAll());
        List<Product> productList=productService.listAll(productkeyword);
        theModel.addAttribute("products", productList);
        theModel.addAttribute("productkeyword",productkeyword);
        return "mainscreen";
    }
    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") int id, Model model) {

        Product product = productService.findById(id);

        if (product.getInv() > 0) {
            product.setInv(product.getInv() - 1);
            productService.save(product);
            model.addAttribute("message", "Purchase successful!");
        } else {
            model.addAttribute("message", "Purchase failed: product out of stock.");
        }

        return "confirmationbuy";
    }

}
