/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.OrderDAO;
import sample.dao.OrderGuestDAO;
import sample.dao.ProductDAO;
import sample.dto.OrderDTO;
import sample.dto.OrderGuestDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;

/**
 *
 * @author haong
 */
@WebServlet(name = "GetOrdersListServlet", urlPatterns = {"/GetOrdersListServlet"})
public class GetOrdersListServlet extends HttpServlet {

    private String ERROR_PAGE = "errorRegistration.html";
    private String ORDERS_LIST_PAGE = "staffOrdersList.jsp";

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
        String url = ERROR_PAGE;
        try {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (userDTO.getRole() == 1) {
                OrderDAO orderDAO = new OrderDAO();
                ProductDAO productDAO = new ProductDAO();
                ArrayList<OrderDTO> listOrder = orderDAO.loadOrder();
                for (OrderDTO order : listOrder) {
                    ArrayList<ProductDTO> productList = productDAO.getProductByOrderID(order.getOrderID());
                    order.setProductsList(productList);
                }
                session.setAttribute("ORDERS_LIST_CUSTOMER", listOrder);
                
//                OrderGuestDAO orderGuestDAO = new OrderGuestDAO();
//                List<OrderGuestDTO> listOrderGuest = orderGuestDAO.loadOrderGuest();
//                for(OrderGuestDTO order : listOrderGuest) {
//                    List<ProductDTO> productList = productDAO.getProductByOrderID(order.getOrderID());
//                    order.setProductsList(productList);
//                }
//                session.setAttribute("ORDERS_LIST_GUEST", listOrderGuest);
                
                url = ORDERS_LIST_PAGE;
            }
        } catch (ClassNotFoundException ex) {
            log("GetOrdersListServlet_ClassNotFoundException" + ex.getMessage());
        } catch (NamingException ex) {
            log("GetOrdersListServlet_NamingException" + ex.getMessage());
        } catch (SQLException ex) {
            log("GetOrdersListServlet_SQLException" + ex.getMessage());
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
