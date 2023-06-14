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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.CategoriesBirdDAO;
import sample.dao.CategoryDAO;
import sample.dao.ProductDAO;
import sample.dto.BirdDTO;
import sample.dto.CategoryDTO;
import sample.dto.ProductDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private final String HOME_PAGE = "home.jsp";
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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String indexPage = request.getParameter("index");
        String url = HOME_PAGE;
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        String searchValue = request.getParameter("txtSearchValue");
        try {
            if (searchValue == null || searchValue.trim().isEmpty()) {
                request.setAttribute("PRODUCTS", null);
            } else {
                ProductDAO dao = new ProductDAO();
                CategoryDAO cateDAO = new CategoryDAO();
                List<CategoryDTO> cateList = cateDAO.getCatetoryList();
                List<String> listSize = dao.getSizeList();
                List<BirdDTO> listBird = CategoriesBirdDAO.getBirdList();
                List<ProductDTO> result = dao.searchListProductUser(searchValue, page, ON_PAGE_PRODUCT, -1, "", -1, -1, "");
                int amount = dao.getAmountSearchProductUser(searchValue, -1, "", -1, -1, "");
                int endPage = amount / ON_PAGE_PRODUCT;
                if (amount % ON_PAGE_PRODUCT != 0) {
                    endPage++;
                }
                request.setAttribute("PRODUCTS", result);
                request.setAttribute("PAGE", endPage);
                request.setAttribute("TAGS", page);
                request.setAttribute("txtSearchValue", searchValue);
                request.setAttribute("RESULT_AMOUNT", amount);
                request.setAttribute("CATEGORY_LIST", cateList);
                request.setAttribute("SIZE_LIST", listSize);
                request.setAttribute("BIRD_LIST", listBird);
            }
        } catch (SQLException e) {
            log("AccountSearchServlet _ SQL _ " + e.getMessage());
        } catch (NamingException e) {
            log("AccountSearchServlet _ Naming _ " + e.getMessage());
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
