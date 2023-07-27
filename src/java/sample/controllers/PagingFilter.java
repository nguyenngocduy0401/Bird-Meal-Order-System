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
@WebServlet(name = "PagingFilter", urlPatterns = {"/PagingFilter"})
public class PagingFilter extends HttpServlet {

    private final int ON_PAGE_PRODUCT = 6;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO dao = new ProductDAO();
        String indexPage = request.getParameter("index");
        if (indexPage.trim().equals("") || indexPage.isEmpty()) {
            indexPage = "1";
        }
        String minPriceString = request.getParameter("minPrice");
        if (minPriceString.trim().equals("") || minPriceString.isEmpty()) {
            minPriceString = "-1";
        }
        String maxPriceString = request.getParameter("maxPrice");
        if (maxPriceString.trim().equals("") || maxPriceString.isEmpty()) {
            maxPriceString = "-1";
        }
        int endPage = 0;
        int page = Integer.parseInt(indexPage);
        String cateFilter = request.getParameter("cateFilter");
        int cateID = Integer.parseInt(cateFilter);
        String sizeFilter = request.getParameter("sizeFilter");
        String birdFilter = request.getParameter("birdFilter");
        int birdID;
        if (birdFilter.trim().isEmpty()) {
            birdID = 0;
        } else {
            birdID = Integer.parseInt(birdFilter);
        }
        double minPrice = Double.parseDouble(minPriceString);
        double maxPrice = Double.parseDouble(maxPriceString);
        String txtSearchValue = request.getParameter("txtSearchValue");
        List<ProductDTO> result = null;
        if (txtSearchValue.trim().equals("") || txtSearchValue.isEmpty()) {
            result = dao.pagingProductFilterUser(page, ON_PAGE_PRODUCT, cateID, sizeFilter, minPrice, maxPrice, birdID);
            int amount = dao.getAmountProductFilter(cateID, sizeFilter, minPrice, maxPrice, birdID);
            endPage = amount / ON_PAGE_PRODUCT;
            if (amount % ON_PAGE_PRODUCT != 0) {
                endPage++;
            }
        } else {
            result = dao.searchListProductUser(txtSearchValue, page, ON_PAGE_PRODUCT, cateID, sizeFilter, minPrice, maxPrice, birdID);
            int amount = dao.getAmountSearchProductUser(txtSearchValue, cateID, sizeFilter, minPrice, maxPrice, birdID);
            endPage = amount / ON_PAGE_PRODUCT;
            if (amount % ON_PAGE_PRODUCT != 0) {
                endPage++;
            }
        }

        PrintWriter out = response.getWriter();

        try {
            if (result.isEmpty()) {
                out.print("<p class=\"text-uppercase mb-1\">No similar products found!</p>");
            } else {
                result.forEach((dto) -> {
                    out.println("<div class=\"cards block col-md-4 mt-1\">\n"
                            + " <section class=\"panel\">\n"
                            + " <div class=\"card product-item position-relative bg-light d-flex flex-column text-center product\">\n"
                            + " <div class=\"clickable\" onclick=\"document.getElementById('formid_" + dto.getProductID() + "').submit()\">\n"
                            + "                                    <img class=\"img-fluid mb-4\" src=\"" + dto.getImgPath() + "\" alt=\"\">\n"
                            + "                                    <p class=\"name text-uppercase\">" + dto.getProductName() + "</p>\n"
                            + "                                    <h5 class=\"text-primary mb-0\">" + dto.getPrice() + " VND</h5>\n"
                            + "                              </div>\n"
                            + "                                    <div class=\"btn-action d-flex justify-content-center\">\n"
                            + "\n"
                            + "                                        <div class=\"d-flex\">\n");
                    if (dto.getQuantity() != 0) {
                        out.print("<button type=\"submit\" value=\"Add\" onclick=\"addToCart(" + dto.getProductID() + ")\" class=\"btn btn-cart btn-primary py-2 px-3\" type=\"button\">\n"
                                + "                                                        <i class=\"bi bi-cart-fill fa-2x \"  style=\"font-size: 25px;\"></i>\n"
                                + "                                                    </button>");
                    }

                    out.print("                                        </div>\n"
                            + "                                        <div class=\"d-flex\">\n"
                            + "\n"
                            + "                                                    <form action=\"ProductDetailController\" method=\"post\" style=\"display: none;\" id=\"formid_" + dto.getProductID() + "\">\n"
                            + "                                                        <input type=\"hidden\" name=\"productID\" value=\"" + dto.getProductID() + "\">\n"
                            + "                                                        <button type=\"submit\" class=\"btn btn-primary py-2 px-3\">\n"
                            + "                                                            <i class=\"bi bi-eye\"></i>\n"
                            + "                                                        </button>\n"
                            + "                                                    </form>\n"
                            + "                                     </div>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                            </section>\n"
                            + "                        </div>");
                });
                out.println("<div class=\"col-12 mt-5 paging\">\n"
                        + "                            <nav aria-label=\"Page navigation\">\n"
                        + "                                <ul class=\"pagination pagination-lg m-0\">");
                if (1 < page && page <= endPage) {
                    out.println("<li class=\"page-item\">\n"
                            + "                                            <a class=\"page-link rounded-0\" onclick=\"loadPage(" + (page - 1) + ")\" aria-label=\"Previous\">\n"
                            + "                                                <span aria-hidden=\"true\">Previous</span>\n"
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
                            + "                                                <span aria-hidden=\"true\">Next</span>\n"
                            + "                                            </a>\n"
                            + "                                        </li>");
                }
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagingFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PagingFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(PagingFilter.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PagingFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PagingFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(PagingFilter.class.getName()).log(Level.SEVERE, null, ex);
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
