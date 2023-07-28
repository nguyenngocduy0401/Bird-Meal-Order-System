/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.CartDAO;
import sample.dao.CartDetailDAO;
import sample.dao.OrderDetailsDAO;
import sample.dao.ProductDAO;
import sample.dto.CartDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class BuyAgainController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String orderID = request.getParameter("orderID");
            HttpSession session = request.getSession(true);
            LinkedHashMap<String, Integer> listProductFromOrder = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> listProductFromCart = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> cart = new LinkedHashMap<>();
            int itemCount = 0;
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            ProductDTO productDTO;
            try {
                listProductFromOrder = OrderDetailsDAO.getProductFromOrderDetail(Integer.parseInt(orderID));
                CartDTO cartDTO = CartDAO.getCartByUserID(userDTO.getUserID());
                if (cartDTO != null) {
                    listProductFromCart = CartDetailDAO.getCartDetail(cartDTO.getCartID());
                    LinkedHashMap<String, Integer> mergedList = new LinkedHashMap<>();

                    // Merge the lists
                    mergedList.putAll(listProductFromCart);

                    for (String key : listProductFromOrder.keySet()) {
                        int quantity = listProductFromOrder.get(key);

                        if (mergedList.containsKey(key)) {
                            int existingQuantity = mergedList.get(key);
                            int mergeQuantity = existingQuantity + quantity;
                            productDTO = ProductDAO.getProductByID(Integer.parseInt(key));
                            if (mergeQuantity > productDTO.getQuantity()) {
                                mergeQuantity = productDTO.getQuantity();
                            } else {
                                mergeQuantity = existingQuantity + quantity;
                            }
                            mergedList.put(key, mergeQuantity);
                        } else {
                            mergedList.put(key, quantity);
                        }
                    }
                    CartDetailDAO.createCartDetailsForCustomer(cartDTO.getCartID(), mergedList);
                    if (userDTO != null) {
                        if (userDTO.getRole() == 2) {
                            try {

                                cart = CartDetailDAO.getCartDetail(cartDTO.getCartID());
                                itemCount = cart.size();
                                session.setAttribute("countItemsCart", itemCount);
                            } catch (Exception e) {
                            }

                        }
                    }
                }
            } catch (ClassNotFoundException | NumberFormatException | SQLException | NamingException e) {
            }

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
