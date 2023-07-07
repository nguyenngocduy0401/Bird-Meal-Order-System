/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private String ERROR_PAGE = "errorRegistration.html";
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
                
                UserDTO accountDTO = new UserDTO();
                if (orderDTO.getUserID() != 0) {
                    UserDAO userDAO = new UserDAO();
                    accountDTO = userDAO.getUserByOrderID(orderID);
                }
                boolean result = false;
                if (status == 4) {
                    result = orderDAO.updateSuccessOrder(orderID);
                    for (ProductDTO product : productList) {
                        boolean minusProductQuantity = ProductDAO.minusProductQuantity(product.getQuantity(), product.getProductID());
                    }
                } else if (status == 2) {
                    result = orderDAO.updateStatusOrder(orderID, 2);
                    if (accountDTO != null) {
                        sendEmail = mailservice.sendEmailConfirmOrder(orderDTO, accountDTO);
                    }
                } else {
                    result = orderDAO.updateStatusOrder(orderID, status);
                }
                if (result) {
                    url = GET_ORDERS_LIST_SERVLET;
                    request.setAttribute("ORDER_UPDATE_STATUS", result);
                }

            }
        } catch (ClassNotFoundException ex) {
            log("UpdateOrderStatusServlet_ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateOrderStatusServlett_SQLException" + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateOrderStatusServlett_NamingException" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
