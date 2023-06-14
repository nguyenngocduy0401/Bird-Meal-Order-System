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
import sample.dao.ProductDAO;
import sample.dto.ProductDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PagingStaffProduct", urlPatterns = {"/PagingStaffProduct"})
public class PagingStaffProduct extends HttpServlet {
    
    private final int ON_PAGE_PRODUCT = 10;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO dao = new ProductDAO();
        String indexPage = request.getParameter("index");
        int endPage = 0;
        int page = Integer.parseInt(indexPage);
        String txtSearchValue = request.getParameter("txtSearchValue");
        List<ProductDTO> result = null;
        if (txtSearchValue == null || txtSearchValue.trim().equals("")) {
            result = dao.pagingProduct(page, ON_PAGE_PRODUCT);
            int amount = dao.getAmountProduct();
            endPage = amount / ON_PAGE_PRODUCT;
            if (amount % ON_PAGE_PRODUCT != 0) {
                endPage++;
            }
        } else {
            result = dao.searchListProduct(txtSearchValue, page, ON_PAGE_PRODUCT);
            int amount = dao.getAmountSearchProduct(txtSearchValue);
            endPage = amount / ON_PAGE_PRODUCT;
            if (amount % ON_PAGE_PRODUCT != 0) {
                endPage++;
            }
        }
        PrintWriter out = response.getWriter();
        
        try {
            out.print(" <table border=\"1\">\n"
                    + "                    <thead style=\"background: #7AB730; color: white\">\n"
                    + "                        <tr>\n"
                    + "                            <th>Name</th>\n"
                    + "                            <th>Price</th>\n"
                    + "                            <th>Quantity</th>\n"
                    + "                            <th>Category</th>\n"
                    + "                            <th>Details</th>\n"
                    + "                            <th>Size</th>\n"
                    + "                            <th>Age Recommendation</th>\n"
                    + "                            <th>Date</th>\n"
                    + "                            <th>Status</th>\n"
                    + "                            <th>Country</th>\n"
                    + "                            <th>Action</th> \n"
                    + "                        </tr>\n"
                    + "                    </thead>\n"
                    + "                    <tbody>");
            result.forEach((dto) -> {
                out.print("<tr>\n"
                        + "                                <td>" + dto.getProductName() + "</td>\n"
                        + "                                <td>" + dto.getPrice() + "</td>\n"
                        + "                                <td>" + dto.getQuantity() + "</td>\n"
                        + "                                <td>" + dto.getCategoryID() + "</td>\n"
                        + "                                <td>" + dto.getProductDetail() + "</td>\n"
                        + "                                <td>" + dto.getSize() + "</td>\n"
                        + "                                <td>" + dto.getAgeRecommendation() + "</td>\n"
                        + "                                <td>" + dto.getDate() + "</td>\n"
                        + "                                <td>" + dto.getStatus() + "</td>\n"
                        + "                                <td>" + dto.getCountry() + "</td>\n"
                        + "                                <td>\n"
                        + "                                    <button>Delete</button>\n"
                        + "                                    <button>Edit</button>\n"
                        + "                                </td>\n"
                        + "                            </tr>");
            });
            out.print("</tbody>\n"
                    + "                </table>"
                    + "<div class=\" col-centered col-md-9 mt-5 mx-auto \">");
            out.println("<div class=\"col-12 mt-5\">\n"
                    + "                            <nav aria-label=\"Page navigation\">\n"
                    + "                                <ul class=\"pagination pagination-lg m-0\">");
            if (1 < page && page <= endPage) {
                out.println("<li class=\"page-item\">\n"
                        + "                                            <a class=\"page-link rounded-0\" onclick=\"loadPage(" + (page - 1) + ")\" aria-label=\"Previous\">\n"
                        + "                                                <span aria-hidden=\"true\"><i class=\"bi bi-arrow-left\"></i></span>\n"
                        + "                                            </a>\n"
                        + "                                        </li>");
            }
            for (int i = 1; i <= endPage; i++) {
                if (page == i) {
                    out.println("<li class=\"page-item active\">\n"
                            + "                                            <a class=\"page-link\" onclick=\"loadPage(" + i + ")\">" + i + "</a>\n"
                            + "                                        </li>");
                } else {
                    out.println("<li class=\"page-item\">\n"
                            + "                                            <a class=\"page-link\" onclick=\"loadPage(" + i + ")\">" + i + "</a>\n"
                            + "                                        </li>");
                }
                
            }
            if (1 <= page && page < endPage) {
                out.println("<li class=\"page-item\">\n"
                        + "                                            <a onclick=\"loadPage(" + (page + 1) + ")\" class=\"page-link rounded-0\" aria-label=\"Next\">\n"
                        + "                                                <span aria-hidden=\"true\"><i class=\"bi bi-arrow-right\"></i></span>\n"
                        + "                                            </a>\n"
                        + "                                        </li>");
            }
            out.print("</div>");
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
            Logger.getLogger(PagingStaffProduct.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PagingStaffProduct.class.getName()).log(Level.SEVERE, null, ex);
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
