/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import static java.lang.System.out;
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
import javax.servlet.http.HttpSession;
import sample.dao.CartObj;
import sample.dao.OrderDAO;
import sample.dao.ProductDAO;
import sample.dto.OrderDTO;
import sample.dto.ProductDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BuyAgain", urlPatterns = {"/BuyAgain"})
public class BuyAgain extends HttpServlet {
    
    private final String SHOPPING_PAGE = "shopping.jsp";
private final String ERROR_PAGE = "errorpage.html";
private final String LIST_PRODUCT = "HomeController";

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
        String url = ERROR_PAGE;
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession();
            session.getAttribute("user");
            //2. Cust takes their --> attributes
            CartObj cart = (CartObj)session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }//cart is not existed --> create cart
            //3. Cust takes book item --> parameter
            String orderIDString = request.getParameter("orderID");
            int orderID = Integer.parseInt(orderIDString);
            ProductDAO productDAO = new ProductDAO();
            //4. Cust drops item down
            List<ProductDTO> productList = productDAO.getProductInOrder(orderID);
                for(ProductDTO product: productList){
                    cart.addItemToCart(product.getProductID(), product.getQuantity());
                }
                    
            
            //5. Update to cart place
            session.setAttribute("CART", cart);
            //6. Cust goes to shopping
//            url = "DispatchServlet"
//                    + "?btAction=Buy";
            url = LIST_PRODUCT;
        } catch (SQLException ex) {
            log("AddBookToCartServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddBookToCartServlet_Naming: " + ex.getMessage());
        } finally {
            out.close();
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
            Logger.getLogger(BuyAgain.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BuyAgain.class.getName()).log(Level.SEVERE, null, ex);
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
