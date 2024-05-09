package com.j2o.sentinel.dto.request.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersistOrderRQ {
    private int productId;
}
