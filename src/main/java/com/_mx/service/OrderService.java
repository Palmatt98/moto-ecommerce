package com._mx.service;

import com._mx.dto.OrderRequest;
import com._mx.dto.ProductOrderRequest;
import com._mx.entity.Order;
import com._mx.entity.OrderStatus;
import com._mx.entity.Product;
import com._mx.enums.OrderStatusType;
import com._mx.repository.OrderRepository;
import com._mx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    OrderRequest orderRequest = new OrderRequest();

    public List<Order> getListOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElse(null);
        return order;
    }

    public Order saveOrder(OrderRequest request) {
        Order order = new Order();
        order.setTimestamp(LocalDateTime.now());
        order.setOrderNumber(request.getOrderNumber());

        //settiamo lo stato iniziale "PENDING" per tutti i nuovi ordini
        OrderStatus initialStatus = orderStatusService.findOrderStatusByCode(OrderStatusType.PENDING);
        order.setStatus(initialStatus);

        Set<Long> productIds = request.getProducts().stream()
                .map(orderProduct -> orderProduct.getId())
                .collect(Collectors.toSet());

        //prendere da db i prodotti attraverdo i loro id, poi popolare  order.setProducts con i prodotti ricavati dal db
        if (!CollectionUtils.isEmpty(productIds)) {
            List<Product> products = productRepository.findAllById(productIds);
            if (productIds.size() != products.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non puoi ordinare prodotti non esistenti");
            }
            //calcolo del costo totale dei prodotti una volta nel carrello prima di procere al pagamento
            order.setProducts(products);
            double paymentCost = products.stream()
                    .mapToDouble(product -> {
                        int qty = 0;
                        for (ProductOrderRequest poRequest : request.getProducts()) {
                            if (Objects.equals(product.getId(), poRequest.getId())) {
                                qty = poRequest.getQty();
                                break;
                            }
                        }
                        return product.getPrice() * qty;
                    })
                    //[42.32, 56.34, ...]
                    .sum();
            order.setPaymentCost(paymentCost);

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non puoi creare un ordine senza inserire i prodotti");
        }


        Order orderSaved = orderRepository.save(order);
        return orderSaved;
    }

    public List<Product> getProductsByOrderId(Long id) {
        List<Product> products = orderRepository.findProductsById(id);
        return products;
    }

    public Order deleteOrder(Long id) {
        //TODO logical delete dell'ordine
        //TODO fare eliminazione logica (colonna 'deleted_at' di tipo DATETIME sulla tabella orders, accettare l'eliminazione solo se in stato pending, settare status a canceled)
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato"));

        OrderStatusType pending = OrderStatusType.PENDING;

        // Verifica che lo stato sia PENDING
        if (order.getStatus().getCode() == pending) {
            // Setta lo stato a CANCELED una volta eliminato

            OrderStatus statusDeleted = orderStatusService.findOrderStatusByCode(OrderStatusType.CANCELED);
            order.setStatus(statusDeleted);

            // Imposta il timestamp della eliminazione

            order.setDeletedAt(LocalDateTime.now());
            // Salva l'ordine aggiornato nel database

            return orderRepository.save(order);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ordine può essere eliminato solo se è in stato PENDING");
        }


    }
}
