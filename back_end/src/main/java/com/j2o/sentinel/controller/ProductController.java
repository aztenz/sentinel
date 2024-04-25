package com.j2o.sentinel.controller;

import com.j2o.sentinel.dto.request.product.PostProductRQ;
import com.j2o.sentinel.dto.request.product.PutProductRQ;
import com.j2o.sentinel.dto.response.product.ProductDetails;
import com.j2o.sentinel.dto.response.product.ProductListItem;
import com.j2o.sentinel.dto.response.product.PostProductRSP;
import com.j2o.sentinel.dto.response.product.PutProductRSP;
import com.j2o.sentinel.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //To get all Products
    @GetMapping
    public ResponseEntity<List<ProductListItem>> getAllProducts(){
        return ResponseEntity.ok(productService.getAll());
    }
//
//    //To get Product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDetails> getProductById(@PathVariable int id) {
//        return ResponseEntity.ok(productService.getItem(id));
//    }
//
//    //To create a new Product
//    @PostMapping
//    public ResponseEntity<PostProductRSP> createProduct(@RequestBody PostProductRQ Product){
//        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(Product));
//    }
//
//    //To update an existing Product
//    @PutMapping("/{id}")
//    public ResponseEntity<PutProductRSP> updateProduct(@RequestBody PutProductRQ Product) {
//        return ResponseEntity.ok(productService.update(Product));
//    }
//
//    //To delete an existing product
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
//        productService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}