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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.OrderDAO;
import sample.dto.OrderDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "pagingOrder", urlPatterns = {"/pagingOrder"})
public class pagingOrder extends HttpServlet {
    
    private final int ON_PAGE_PRODUCT = 6;

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
        OrderDAO dao = new OrderDAO();
        String indexPage = request.getParameter("index");
        int page = Integer.parseInt(indexPage);
        String txtSearchValue = request.getParameter("txtSearchValue");
        List<OrderDTO> result = null;
        if(txtSearchValue == null || txtSearchValue.trim().equals("")){
            result=dao.loadPagingOrder(page, ON_PAGE_PRODUCT);
        } else {
            result = dao.loadPagingOrder(page, ON_PAGE_PRODUCT);
        }
        PrintWriter out = response.getWriter();
        
        try {

            result.forEach((dto) -> {
                out.print("<tr>\n" +
"                                <td>"+dto.getUserID()+"</td>\n" +
"                                <td>"+dto.getOrderDate()+"</td>\n" +
"                                <td>"+dto.getShippingDate()+"</td>\n" +
"                                <td>"+dto.getStatus()+"</td>\n" +
"                                <td>"+dto.getOrderAddress()+"</td>\n" +
"                                <td>\n" +
"                                    <button>Delete</button>\n" +
"                                    <button>Edit</button>\n" +
"                                </td>\n" +
"                            </tr>");
            });
            request.setAttribute("TAGS", indexPage);
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
        } catch (SQLException | NamingException | ClassNotFoundException ex) {
            Logger.getLogger(pagingOrder.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | NamingException | ClassNotFoundException ex) {
            Logger.getLogger(pagingOrder.class.getName()).log(Level.SEVERE, null, ex);
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
