package com.j2o.sentinel.service;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class PaymentService {
    @Value("${razorpay.keyId}")
    private String keyId;

    @Value("${razorpay.keySecret}")
    private String secret;

    public String makeOrder(String amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(keyId, secret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_receipt_11");

            Order order = razorpayClient.orders.create(orderRequest);
            return order.get("id");
        } catch (Exception e) {
            return "";
        }
    }
}
