package com.gaurang.microservice.order.service;

import com.gaurang.microservice.order.client.InventoryClient;
import com.gaurang.microservice.order.dto.OrderRequest;
import com.gaurang.microservice.order.model.Order;
import com.gaurang.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock){
            // map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            // save order to OrderRepository
            orderRepository.save(order);
        }else {
            throw new RuntimeException("Product with skucode "+orderRequest.skuCode()+" is not in stock");
        }
    }
}
