package com.j2o.sentinel.service;

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
import com.j2o.sentinel.repository.OrderRepository;
import com.j2o.sentinel.repository.ProductRepository;
import com.j2o.sentinel.utils.Util;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService
        extends GenericService< Order,
            Integer,
            OrderRepository,
            PostOrderRQ,
            PutOrderRQ,
            OrderListItem,
            OrderDetails,
            PostOrderRSP,
            PutOrderRSP> {
    private static final String CANNOT_MAKE_ORDER = "cannot make order";
    private static final String PRODUCT_NOT_FOUND = "product not found";
    public static final String ORDER_NOT_FOUND = "Order Not Found";
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Value("${razorpay.keyId}")
    private String razorPayKeyId;

    @Value("${razorpay.keySecret}")
    private String razorPaySecretKey;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        super(orderRepository,
                Order.class,
                PostOrderRSP.class,
                PutOrderRSP.class,
                OrderListItem.class,
                OrderDetails.class);

        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public PostOrderRSP  create(PostOrderRQ request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ItemNotFoundException(PRODUCT_NOT_FOUND));
            User currentUser = Util.getCurrentUser();
            Order order = new Order();
            order.setUser(currentUser);
            order.setProduct(product);
            order.setCreatedAt(new Date());
            order.setQuantity(1);
            order.setStatus("PENDING");

            order = orderRepository.save(order);
            RazorpayClient client = new RazorpayClient(razorPayKeyId, razorPaySecretKey);
            JSONObject options = new JSONObject();
            options.put("amount", product.getPrice()); // amount in price
            options.put("currency", "EGP");
            options.put("receipt", ""+order.getId());
            options.put("payment_capture", 1); // 1 - automatic capture, 0 - manual capture
            com.razorpay.Order initOrder = client.orders.create(options);
            PostOrderRSP postOrderRSP = new PostOrderRSP();
            postOrderRSP.setOrderDetails(initOrder);
            postOrderRSP.setPaymentStatus(processPaymentAndOrder(product.getPrice(), initOrder));
            return postOrderRSP;
        } catch (Exception e) {
            throw new RuntimeException(CANNOT_MAKE_ORDER);
        }
    }

    private String processPaymentAndOrder(double amount,
                                          com.razorpay.Order orderId
    ) {
        try {
            RazorpayClient client = new RazorpayClient(razorPayKeyId, razorPaySecretKey);
            // Initiate payment using Razorpay service
            JSONObject options = new JSONObject();
            options.put("amount", amount); // amount in paise
            options.put("currency", "EGP");
            options.put("receipt", orderId.get("id").toString());
            options.put("payment_capture", 1); // 1 - automatic capture, 0 - manual capture
            com.razorpay.Order orderAfterPay = client.orders.create(options);

            // Capture the payment
            JSONObject captureRequest = new JSONObject();
            captureRequest.put("amount", amount); // amount in paise
            Payment capture = client.payments.capture(orderAfterPay.get("id"), captureRequest);

//            updateOrderStatus();

            return "Payment captured and order status updated successfully.";
        } catch (Exception e) {
            throw new RuntimeException("Cannot pay for order");
        }
    }

    private void updateOrderStatus(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ItemNotFoundException(ORDER_NOT_FOUND));
        if (order != null) {
            order.setStatus("PAID");
            orderRepository.save(order);
        }
    }
}
