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
@WebServlet(name = "PagingProduct", urlPatterns = {"/PagingProduct"})
public class PagingProduct extends HttpServlet {

    private final int ON_PAGE_PRODUCT = 6;

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
        ProductDAO dao = new ProductDAO();
        String indexPage = request.getParameter("index");
        int page = Integer.parseInt(indexPage);
        String txtSearchValue = request.getParameter("txtSearchValue");
        List<ProductDTO> result = null;
        if(txtSearchValue == null || txtSearchValue.trim().equals("")){
            result=dao.pagingProduct(page, ON_PAGE_PRODUCT);
        } else {
            result = dao.searchListProduct(txtSearchValue, page, ON_PAGE_PRODUCT);
        }
        PrintWriter out = response.getWriter();
        
        try {

            result.forEach((dto) -> {
                out.print("<div class=\"col-md-4 mt-1\">\n"
                        + "                            <section class=\"panel\">\n"
                        + "                                <div class=\"product-item position-relative bg-light d-flex flex-column text-center\">\n"
                        + "                                    <img class=\"img-fluid mb-4\" src=\"" + dto.getImgPath() + "\" alt=\"\">\n"
                        + "                                    <h6 class=\"text-uppercase\">" + dto.getProductName() + "</h6>\n"
                        + "                                    <h5 class=\"text-primary mb-0\">" + dto.getPrice() + " VND</h5>\n"
                        + "                                    <div class=\"btn-action d-flex justify-content-center\">\n"
                        + "                                        <a class=\"btn btn-primary py-2 px-3\" href=\"\"><i class=\"bi bi-cart\"></i></a>\n"
                        + "                                        <a class=\"btn btn-primary py-2 px-3\" href=\"\"><i class=\"bi bi-eye\"></i></a>\n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                            </section>\n"
                        + "                        </div>");
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
            Logger.getLogger(PagingProduct.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PagingProduct.class.getName()).log(Level.SEVERE, null, ex);
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
