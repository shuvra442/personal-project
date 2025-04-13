package com.dustman.service;

import com.dustman.dao.OrderDAO;
import com.dustman.dao.ShopDAO;
import com.dustman.dao.UserDAO;
import com.dustman.dto.OrderDTO;
import com.dustman.dto.Status;
import com.dustman.model.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderDAO orderDAO;
    private final EmailService emailService;
    private final UserDAO userDAO;
    private final ShopDAO shopDAO;

    public OrderService(OrderDAO orderDAO, EmailService emailService, UserDAO userDAO, ShopDAO shopDAO) {
        this.orderDAO = orderDAO;
        this.emailService = emailService;
        this.userDAO = userDAO;
        this.shopDAO = shopDAO;
    }

    public Status addOrder(OrderDTO orderDTO) {
        if (orderDAO.addOrder(orderDTO)) {
            String userName = userDAO.getUserName(orderDTO.orderFrom());
            String shopName = userDAO.getUserName(orderDTO.orderTo());
            String mailTextTO = userName + " has placed an order";
            String mailTextFrom = "Your order place to "+shopName ;
            emailService.sendEmail(userDAO.getEmailID(orderDTO.orderTo()), "Order", mailTextTO);
            emailService.sendEmail(userDAO.getEmailID(orderDTO.orderFrom()), "Order", mailTextFrom);
            System.out.println("Hello");
            return new Status(200, mailTextFrom);
        } else {
            return new Status(500);
        }
    }

    // todo:only user and shop keeper can cancel Order
    public Status cancelOrder(String orderID,String userID) {
        if (orderDAO.cancelOrder(orderID)) {
            emailService.sendEmail(userDAO.getEmailID(userID), "Order", "Order has been cancelled");
            return new Status(200, "Order cancelled successfully");
        }
        return new Status(500);
    }
    // todo:only  shop keeper can complete Order status
    public Status completeOrder(String id) {
        if (orderDAO.completeOrder(id)) {
            String userEmail = orderDAO.fromUserEmailUsingOrderID(id);
            emailService.sendEmail(userEmail, "Order", "Order has been completed");
            return new Status(200, "Order completed successfully");
        }
        return new Status(500);
    }

    //todo: admin can see all order
    public Status showAllOrder() {
        List<OrderDetails> orderDetailsList = orderDAO.showAllOrder();
        if (!orderDetailsList.isEmpty()) {
            return new Status(200, orderDetailsList);
        }
        return new Status(204);
    }

    //todo: only user can see there order
    public Status showUserOrder(String id) {
        List<OrderDetails> orderDetailsList = orderDAO.showUserOrder(id);
        if (!orderDetailsList.isEmpty()) {
            return new Status(200, orderDetailsList);
        }
        return new Status(204);
    }

    public Status checkOrder(String id) {
        OrderDetails orderDetails = orderDAO.checkOrder(id);
        if (orderDetails != null) {
            return new Status(200, orderDetails);
        }
        return new Status(204);
    }
}
