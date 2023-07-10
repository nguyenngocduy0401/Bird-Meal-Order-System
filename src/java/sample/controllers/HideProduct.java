/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "HideProduct", urlPatterns = {"/HideProduct"})
public class HideProduct extends HttpServlet {

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
        int productId = Integer.parseInt(request.getParameter("productID"));
        int status = Integer.parseInt(request.getParameter("status"));

        ProductDAO dao = new ProductDAO();
        boolean result = dao.HideProduct(productId, 0); // Cập nhật trạng thái sản phẩm thành giá trị đánh dấu cho sản phẩm bị ẩn (trong đây là 0)
        //response.sendRedirect("productList.jsp"); // Chuyển hướng về trang danh sách sản phẩm sau khi ẩn sản phẩm thành công
        if (result) {
            // Nếu xóa thành công, chuyển hướng đến trang danh sách sản phẩm
            response.sendRedirect("MainController?btAction=StaffHome");
        } else {
            // Nếu không thành công, hiển thị thông báo lỗi cho người dùng
            response.setContentType("text/plain");
            response.getWriter().println("Failed to delete product with ID " + productId);
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
