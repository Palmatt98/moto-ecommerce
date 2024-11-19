package com._mx.dto;


import java.util.Set;

public class OrderRequest {
    private String orderNumber;
    private Double paymentCost;
    private Set<Long> productIds;

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPaymentCost(Double paymentCost) {
        this.paymentCost = paymentCost;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Double getPaymentCost() {
        return paymentCost;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

}
