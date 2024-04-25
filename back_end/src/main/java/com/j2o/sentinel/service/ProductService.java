package com.j2o.sentinel.service;

import com.j2o.sentinel.dto.request.product.PostProductRQ;
import com.j2o.sentinel.dto.request.product.PutProductRQ;
import com.j2o.sentinel.dto.response.product.ProductDetails;
import com.j2o.sentinel.dto.response.product.ProductListItem;
import com.j2o.sentinel.dto.response.product.PostProductRSP;
import com.j2o.sentinel.dto.response.product.PutProductRSP;
import com.j2o.sentinel.model.Product;
import com.j2o.sentinel.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService
        extends GenericService< Product,
        Integer,
        ProductRepository,
        PostProductRQ,
        PutProductRQ,
        ProductListItem,
        ProductDetails,
        PostProductRSP,
        PutProductRSP> {
    public ProductService(ProductRepository ProductRepository) {
        super(ProductRepository,
                Product.class,
                PostProductRSP.class,
                PutProductRSP.class,
                ProductListItem.class,
                ProductDetails.class);
    }
}
