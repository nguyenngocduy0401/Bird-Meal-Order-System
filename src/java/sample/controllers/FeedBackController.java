/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.FeedbackDAO;
import sample.dao.OrderDAO;
import sample.dao.ProductDAO;
import sample.dto.FeedbackDTO;
import sample.dto.OrderDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FeedBackController", urlPatterns = {"/FeedBackController"})
public class FeedBackController extends HttpServlet {

    private final String FEEDBACK_PAGE = "feedback.jsp";

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
            throws ServletException, IOException, SQLException, NamingException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FEEDBACK_PAGE;
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int productID = Integer.parseInt(request.getParameter("productID"));
        HttpSession session = request.getSession();
        OrderDTO order = OrderDAO.loadOrderByOrderID(orderID);
        UserDTO user = (UserDTO) session.getAttribute("user");
        FeedbackDTO fb = FeedbackDAO.getFeedbackUserProductOrder(productID, user.getUserID(), orderID);
        if (fb != null) {
            request.setAttribute("FEEDBACK", fb);
        } else {
            request.setAttribute("FEEDBACK", null);
        }
        Date completeDate = Date.valueOf(order.getShippingDate());
        Date currentDate = Date.valueOf(LocalDate.now());
        try {
            ProductDTO product = ProductDAO.getProductByID(productID);
            request.setAttribute("PRODUCT", product);
            request.setAttribute("ORDERID", order.getOrderID());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
