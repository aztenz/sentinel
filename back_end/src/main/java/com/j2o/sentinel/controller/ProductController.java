package com.j2o.sentinel.controller;

import com.j2o.sentinel.model.Product;
import com.j2o.sentinel.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    private ProductService prodServ;

    //To get all Products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> users = prodServ.getAllProducts();
        return ResponseEntity.ok(users);
    }

    //To get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = prodServ.getProductById(id);
        return ResponseEntity.ok(product);
    }

    //To create a new Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProd = prodServ.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProd);

    }

    //To update an existing Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = prodServ.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    //To delete an existing product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        prodServ.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
