package com.j2o.sentinel.controller;

import com.j2o.sentinel.dto.request.order.PostOrderRQ;
import com.j2o.sentinel.dto.request.order.PutOrderRQ;
import com.j2o.sentinel.dto.response.order.OrderDetails;
import com.j2o.sentinel.dto.response.order.OrderListItem;
import com.j2o.sentinel.dto.response.order.PostOrderRSP;
import com.j2o.sentinel.dto.response.order.PutOrderRSP;
import com.j2o.sentinel.exception.ItemNotFoundException;
import com.j2o.sentinel.model.Order;
import com.j2o.sentinel.model.Product;
import com.j2o.sentinel.model.User;
import com.j2o.sentinel.repository.ProductRepository;
import com.j2o.sentinel.service.OrderService;
import com.j2o.sentinel.service.ProductService;
import com.j2o.sentinel.utils.Util;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final String PRODUCT_NOT_FOUND = "Product not found";
    private final OrderService orderService;
    private final ProductRepository productRepository;

//    //To get all Products
//    @GetMapping
//    public ResponseEntity<List<OrderListItem>> getAllOrders(){
//        return ResponseEntity.ok(orderService.getAll());
//    }
//
//    //To get Product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<OrderDetails> getOrderById(@PathVariable int id) {
//        return ResponseEntity.ok(orderService.getItem(id));
//    }
//
//    //To create a new Product
//    @PostMapping
//    public ResponseEntity<PostOrderRSP> createOrder(@RequestBody PostOrderRQ order){
//        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(order));
//    }
//
//    //To update an existing Product
//    @PutMapping("/{id}")
//    public ResponseEntity<PutOrderRSP> updateOrder(@RequestBody PutOrderRQ order) {
//        return ResponseEntity.ok(orderService.update(order));
//    }
//
//    //To delete an existing product
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
//        orderService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    @PostMapping("/{productId}")
    public ResponseEntity<PostOrderRSP> create(@PathVariable int productId) {
        PostOrderRQ postOrderRQ = new PostOrderRQ();
        postOrderRQ.setProductId(productId);
        return ResponseEntity.ok(orderService.create(postOrderRQ));
    }

}
