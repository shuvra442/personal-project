package com.dustman.service;

import com.dustman.dao.OrderDAO;
import com.dustman.dto.order.OrderDTO;
import com.dustman.model.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public String addOrder(OrderDTO orderDTO) {
        if (orderDAO.addOrder(orderDTO)) {
            return "Order added successfully";
        } else {
            return "Failed to add order";
        }
    }

    public String cancelOrder(String id) {
        if (orderDAO.cancelOrder(id)) {
            return "Order cancelled successfully";
        }
        return "Something wrong \nplease try again";
    }

    public String completeOrder(String id) {
        if (orderDAO.completeOrder(id)) {
            return "Order completed successfully";
        }
        return "Something wrong \nplease try again";
    }

    public List<OrderDetails> showAllOrder(){
        return orderDAO.showAllOrder();
    }

    public List<OrderDetails> showUserOrder(String id){
        return  orderDAO.showUserOrder(id);
    }
    public OrderDetails checkOrder(String id){
        return  orderDAO.checkOrder(id);
    }
}
