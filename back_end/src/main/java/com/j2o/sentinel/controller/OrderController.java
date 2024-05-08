package com.j2o.sentinel.controller;

import com.j2o.sentinel.dto.request.order.PostOrderRQ;
import com.j2o.sentinel.dto.request.order.PutOrderRQ;
import com.j2o.sentinel.dto.response.order.OrderDetails;
import com.j2o.sentinel.dto.response.order.OrderListItem;
import com.j2o.sentinel.dto.response.order.PostOrderRSP;
import com.j2o.sentinel.dto.response.order.PutOrderRSP;
import com.j2o.sentinel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

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


}
