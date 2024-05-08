package com.j2o.sentinel.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private int productId;
    private int amount;
}
