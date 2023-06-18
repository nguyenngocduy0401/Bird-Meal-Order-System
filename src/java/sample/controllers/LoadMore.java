/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.OrderDetailsDAO;
import sample.dto.OrderDetailDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoadMore", urlPatterns = {"/LoadMore"})
public class LoadMore extends HttpServlet {

    private final int ON_PAGE_PRODUCT = 5;
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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String amountString = request.getParameter("tag");
        if(amountString == null) amountString = "0";
        int amount = Integer.parseInt(amountString);
        OrderDetailsDAO dao = new OrderDetailsDAO();
        List<OrderDetailDTO> listOrder = dao.getProductByUserID(3,amount,ON_PAGE_PRODUCT);
        int bool =  dao.getAmount(listOrder);
                
        try {
            PrintWriter out = response.getWriter();
            listOrder.forEach((dto) -> {
                out.print("<div class=\"blog-item product\">\n"
                        + "                                    <div class=\"row g-0 bg-light overflow-hidden\">\n"
                        + "                                        <div class=\"col-sm-3 img-fluid h-80\">\n"
                        + "                                            <img class=\"img-fluid h-100\" src=\"" + dto.getImgPath() + "\" style=\"object-fit: contain;\">\n"
                        + "                                        </div>\n"
                        + "                                        <div class=\"col-12 col-sm-9 h-100 d-flex flex-column justify-content-center\">\n"
                        + "                                            <div class=\"p-4\">\n"
                        + "                                                <div class=\"d-flex mb-3\">\n"
                        + "                                                    <small class=\"me-3\">" + dto.getStatus() + "</small>\n"
                        + "                                                    <small><i class=\"bi bi-calendar-date me-2\"></i>" + dto.getDate() + "</small>\n"
                        + "                                                </div>\n"
                        + "                                                <h5 class=\"text-uppercase mb-3\">" + dto.getProductName() + "</h5>\n"
                        + "                                                <i>Category " + dto.getCategoryID() + " -</i>\n"
                        + "                                                <span>Quantity x " + dto.getQuantity() + "</span>\n"
                        + "                                                <p>Price: " + dto.getPrice() + " $</p>\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </div>\n"
                        + "                                </div> ");
            });
            if(bool - amount >5){
            out.print("<button onclick=\"loadMore()\" class=\"btn btn-primary loadButton\">Load More</button>");
            }
        } finally {
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
            Logger.getLogger(LoadMore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadMore.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadMore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadMore.class.getName()).log(Level.SEVERE, null, ex);
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
