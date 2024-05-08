package com.j2o.sentinel.service;

import com.j2o.sentinel.dto.request.order.PostOrderRQ;
import com.j2o.sentinel.dto.request.order.PutOrderRQ;
import com.j2o.sentinel.dto.response.order.OrderDetails;
import com.j2o.sentinel.dto.response.order.OrderListItem;
import com.j2o.sentinel.dto.response.order.PostOrderRSP;
import com.j2o.sentinel.dto.response.order.PutOrderRSP;
import com.j2o.sentinel.model.Order;
import com.j2o.sentinel.repository.OrderRepository;
import org.springframework.stereotype.Service;

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
    public OrderService(OrderRepository orderRepository) {
        super(orderRepository,
                Order.class,
                PostOrderRSP.class,
                PutOrderRSP.class,
                OrderListItem.class,
                OrderDetails.class);
    }

    @Override
    public PostOrderRSP create(PostOrderRQ request) {


        return super.create(request);
    }
}
