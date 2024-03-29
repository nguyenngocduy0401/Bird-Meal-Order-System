/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.OrderDAO;
import sample.dao.ProductDAO;
import sample.dao.UserDAO;
import sample.dto.OrderDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;
import sample.utils.MailService;

/**
 *
 * @author haong
 */
@WebServlet(name = "UpdateOrderStatusServlet", urlPatterns = {"/UpdateOrderStatusServlet"})
public class UpdateOrderStatusServlet extends HttpServlet {

    private String ERROR_PAGE = "error.html";
    private String GET_ORDERS_LIST_SERVLET = "GetOrdersListServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int orderID = Integer.parseInt(request.getParameter("txtOrderID"));
        int status = Integer.parseInt(request.getParameter("ddlStatus"));
        boolean sendEmail = false;
        String url = ERROR_PAGE;
        MailService mailservice = new MailService();
        try {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (userDTO.getRole() == 1) {
                OrderDAO orderDAO = new OrderDAO();
                ProductDAO productDAO = new ProductDAO();
                OrderDTO orderDTO = orderDAO.loadOrderByOrderID(orderID);
                ArrayList<ProductDTO> productList = productDAO.getProductByOrderID(orderID);
                orderDTO.setProductsList(productList);

                UserDTO accountDTO = null;
                if (orderDTO.getUserID() != 0) {
                    UserDAO userDAO = new UserDAO();
                    accountDTO = userDAO.getUserByOrderID(orderID);
                }
                boolean result = false;

                switch (status) {
                    case 0:
                        result = orderDAO.updateStatusOrder(orderID, 0);
                        if (accountDTO != null) {
                            sendEmail = mailservice.sendEmailOrderIsCancelledToCustomer(orderDTO, accountDTO);
                        } else if (orderDTO.getEmail() != null) {
                            sendEmail = mailservice.sendEmailOrderIsCancelledToGuest(orderDTO);
                        }
                        break;
                    case 2:
                        result = orderDAO.updateStatusOrder(orderID, 2);
                        if (accountDTO != null) {
                            sendEmail = mailservice.sendEmailConfirmOrderToCustomer(orderDTO, accountDTO);
                        } else if (orderDTO.getEmail() != null) {
                            sendEmail = mailservice.sendEmailConfirmOrderToGuest(orderDTO);
                        }
                        break;
                    case 3:
                        result = orderDAO.updateStatusOrder(orderID, 3);
                        if (accountDTO != null) {
                            sendEmail = mailservice.sendEmailOrderIsShippedToCustomer(orderDTO, accountDTO);
                        } else if (orderDTO.getEmail() != null) {
                            sendEmail = mailservice.sendEmailOrderIsShippedToGuest(orderDTO);
                        }
                        break;
                    case 4:
                        result = orderDAO.updateSuccessOrder(orderID);
                        for (ProductDTO product : productList) {
                            boolean minusProductQuantity = ProductDAO.minusProductQuantity(product.getQuantity(), product.getProductID());
                        }
                        if (accountDTO != null) {
                            sendEmail = mailservice.sendEmailOrderIsShippedSuccessfullyToCustomer(orderDTO, accountDTO);
                        } else if (orderDTO.getEmail() != null) {
                            sendEmail = mailservice.sendEmailOrderIsShippedSuccessfullyToGuest(orderDTO);
                        }
                        break;
                    case 5:
                        result = orderDAO.updateStatusOrder(orderID, 5);
                        if (accountDTO != null) {
                            sendEmail = mailservice.sendEmailOrderIsShippedNotSuccessfullyToCustomer(orderDTO, accountDTO);
                        } else if (orderDTO.getEmail() != null) {
                            sendEmail = mailservice.sendEmailOrderIsShippedNotSuccessfullyToGuest(orderDTO);
                        }
                        break;
                }
                if (result) {
                    request.setAttribute("ORDER_UPDATE_STATUS", result);
                    orderDAO = new OrderDAO();
                    productDAO = new ProductDAO();
                    ArrayList<OrderDTO> listOrder = orderDAO.loadOrder();
                    listOrder.sort(Comparator.comparing(OrderDTO::getOrderID).reversed());
                    for (OrderDTO order : listOrder) {
                        productList = productDAO.getProductByOrderID(order.getOrderID());
                        order.setProductsList(productList);
                    }
                    session.setAttribute("ORDERS_LIST_CUSTOMER", listOrder);
                }
            }
        } catch (ClassNotFoundException ex) {
            log("UpdateOrderStatusServlet_ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateOrderStatusServlett_SQLException" + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateOrderStatusServlett_NamingException" + ex.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
