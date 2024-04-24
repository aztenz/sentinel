package Controllers;

import Entity.Order;
import Entity.Product;
import Service.OrderService;
import Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {

    @Autowired
    private OrderService orderServ;

    //To get all Products
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderServ.getAllOrders();
        ResponseEntity.ok(orders);
    }

    //To get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderServ.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    //To create a new Product
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order newOrder = orderServ.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);

    }

    //To update an existing Product
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderServ.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    //To delete an existing product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderServ.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
