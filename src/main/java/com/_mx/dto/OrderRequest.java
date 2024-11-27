package com._mx.dto;

import java.util.List;

public class OrderRequest {

    private String orderNumber;

    private List<ProductOrderRequest> products;


    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setProducts(List<ProductOrderRequest> products) {
        this.products = products;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public List<ProductOrderRequest> getProducts() {
        return products;
    }

}
