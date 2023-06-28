/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.ProductDAO;

/**
 *
 * @author DucAnh
 */
@WebServlet(name = "DeleteProduct", urlPatterns = {"/DeleteProduct"})
public class DeleteProduct extends HttpServlet {
private final String HOME_PAGE_STAFF = "staff.jsp";
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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("productID");
        //String searchValue = request.getParameter("SearchValue");
        String url = HOME_PAGE_STAFF;
        try {
            int productID = Integer.parseInt(productId);
            ProductDAO dao = new ProductDAO();
            boolean result = dao.deleteAcc(productID);
            if (result) {
                // Nếu xóa thành công, chuyển hướng đến trang danh sách sản phẩm
                response.sendRedirect("MainController?btAction=StaffHome");
            } else {
                // Nếu không thành công, hiển thị thông báo lỗi cho người dùng
                response.setContentType("text/plain");
                response.getWriter().println("Failed to delete product with ID " + productID);
            }
        } catch (SQLException ex) {
            log("SearchLastnameServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchLastnameServlet_Naming: " + ex.getMessage());
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
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DeleteProduct.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DeleteProduct.class.getName()).log(Level.SEVERE, null, ex);
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
