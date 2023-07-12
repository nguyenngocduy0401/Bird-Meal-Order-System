/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.CartDAO;
import sample.dao.CartDetailDAO;
import sample.dao.CategoriesBirdDAO;
import sample.dao.CategoryDAO;
import sample.dao.ProductDAO;
import sample.dto.BirdDTO;
import sample.dto.CartDTO;
import sample.dto.CategoryDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

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
     * @throws javax.naming.NamingException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String indexPage = request.getParameter("index");
        String url = HOME_PAGE;
        int itemCount = 0;
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);

        try {
            HttpSession session = request.getSession(true);
            LinkedHashMap<String, Integer> cart = new LinkedHashMap<>();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (userDTO != null) {
                if (userDTO.getRole() == 2) {
                    CartDTO cartDTO = CartDAO.getCartByUserID(userDTO.getUserID());
                    if (cartDTO != null) {
                        cart = CartDetailDAO.getCartDetail(cartDTO.getCartID());
                        itemCount = cart.size();
                    }

                }
            } else {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cart")) {
                            String cartValue = cookie.getValue();
                            if (cartValue.equals("")) {
                                
                            } else {
                                String[] items = cartValue.split(",");
                                itemCount = items.length;
                            }
                            break;
                        }
                    }
                }
            }
            
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> result = dao.pagingProductUser(page, ON_PAGE_PRODUCT, -1, "",-1,-1,"");
            List<ProductDTO> listTop5Sale = ProductDAO.listTop5Product();
            List<ProductDTO> listTop5New = ProductDAO.list5NewProduct();
            CategoryDAO cateDAO = new CategoryDAO();
            List<CategoryDTO> cateList = cateDAO.getCatetoryList();
            List<String> listSize = dao.getSizeList();
            List<BirdDTO> listBird = CategoriesBirdDAO.getBirdList();
            int amount = dao.getAmountProductUser(-1, "", -1, -1, "");
            int endPage = amount/ON_PAGE_PRODUCT;
            if(amount%ON_PAGE_PRODUCT!=0) endPage ++;
            request.setAttribute("PRODUCTS", result);
            request.setAttribute("PAGE", endPage);
            request.setAttribute("TAGS", page);
            request.setAttribute("CATEGORY_LIST", cateList);
            request.setAttribute("SIZE_LIST", listSize);
            request.setAttribute("BIRD_LIST", listBird);
            request.setAttribute("TOP5SALE", listTop5Sale);
            request.setAttribute("TOP5NEW", listTop5New);
            session.setAttribute("countItemsCart", itemCount);
        } catch (SQLException e) {
            log("AccountSearchServlet _ SQL _ " + e.getMessage());
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
        } catch (NamingException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NamingException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
