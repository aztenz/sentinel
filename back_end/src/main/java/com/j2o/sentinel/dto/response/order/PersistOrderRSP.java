package com.j2o.sentinel.dto.response.order;

import com.razorpay.Order;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
public abstract class PersistOrderRSP {
    private Order orderDetails;
    private String paymentStatus;
}
