/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "SearchProductAdmin", urlPatterns = {"/SearchProductAdmin"})
public class SearchProductAdmin extends HttpServlet {

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
String searchValue = request.getParameter("txtSearchValue");
        List<ProductDTO> listProduct = ProductDAO.loadProducts();
        List<ProductDTO> listSearchProduct = new ArrayList<ProductDTO>();
        if (searchValue.trim().equals("")) {
            listSearchProduct = listProduct;
        } else {
            listSearchProduct = ProductDAO.getProductInListByName(listProduct, searchValue);
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class=\"container-fluid\">\n"
                    + "\n"
                    + "                    <!-- Page Heading -->\n"
                    + "                    <h1 class=\"h3 mb-2 text-gray-800\">Account Manager</h1>\n"
                    + "\n"
                    + "                    <!-- DataTales Example -->\n"
                    + "                    <div class=\"card shadow mb-4\">\n"
                    + "                        <div class=\"card-header py-3\">\n"
                    + "                            <h6 class=\"m-0 font-weight-bold text-primary\">Account Table</h6>\n"
                    + "                        </div>\n"
                    + "<div class=\"card shadow mb-4\">\n"
                    + "                        <div class=\"card-header py-3\">\n"
                    + "                            <div class=\"d-flex justify-content-between align-items-center\">\n"
                    + "                                <div class=\"form-inline navbar-search\">\n"
                    + "                                    <div class=\"input-group\">\n"
                    + "                                        <input type=\"text\" id=\"search-box\" class=\"form-control bg-light border-0 small\" placeholder=\"Search for...\" name=\"txtSearchValue\"\n"
                    + "                                               aria-label=\"Search\" aria-describedby=\"basic-addon2\">\n"
                    + "                                        <div class=\"input-group-append\">\n"
                    + "                                            <button class=\"btn btn-primary\" name=\"btAction\" onclick=\"searchProduct()\">\n"
                    + "                                                <i class=\"fas fa-search fa-sm\"></i>\n"
                    + "                                            </button>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>"
                    + "                        <div class=\"card-body\">\n"
                    + "                            <div class=\"table-responsive\">\n"
                    + "                                <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">\n"
                    + "                                    <thead>\n"
                    + "                                        <tr>\n"
                    + "                                                    <th>Name</th>\n"
                    + "                                                    <th>Price</th>\n"
                    + "                                                    <th>Quantity</th>\n"
                    + "                                                    <th>Category</th>\n"
                    + "                                                    <th>Size</th>\n"
                    + "                                                    <th>Age Recommendation</th>\n"
                    + "                                                    <th>Date</th>\n"
                    + "                                                    <th>Status</th>\n"
                    + "                                                    <th>Country</th>"
                    + "                                        </tr>\n"
                    + "                                    </thead>\n"
                    + "                                    <tbody>\n");

            listSearchProduct.forEach((dto) -> {
                out.print("<tr>\n"
                        + "                                            <td>" + dto.getProductName() + "</td>\n"
                        + "                                            <td>" + dto.getPrice() + "</td>\n"
                        + "                                            <td>" + dto.getQuantity() + "</td>\n");
                switch (dto.getCategoryID()) {
                    case 1:
                        out.print("<td>Food</td>\n");
                        break;
                    case 2:
                        out.print("<td>Combo</td>\n");
                        break;
                }
                out.print("                                            <td>" + dto.getSize() + "</td>\n"
                        + "                                            <td>" + dto.getAgeRecommendation() + "</td>\n"
                        + "                                            <td>" + dto.getDate() + "</td>\n"
                );
                if (dto.getStatus() == 1) {
                    out.print("<td>Available</td>\n");
                } else {
                    out.print("<td>Unavailable</td>\n");
                }
                out.print("<td>" + dto.getCountry() + "</td>\n");
            });

            out.print("                                    </tbody>\n"
                    + "                                </table>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "\n"
                    + "                </div>");
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
            Logger.getLogger(SearchProductAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchProductAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchProductAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchProductAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchProductAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchProductAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
