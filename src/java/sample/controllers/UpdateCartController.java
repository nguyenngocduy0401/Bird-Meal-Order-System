/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.CartDAO;
import sample.dao.CartDetailDAO;
import sample.dao.ProductDAO;
import sample.dto.CartDTO;
import sample.dto.CartDetailDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class UpdateCartController extends HttpServlet {

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
            String pid = request.getParameter("pid");
            String qty = request.getParameter("qty");
            HttpSession session = request.getSession(true);
            LinkedHashMap<String, Integer> cart = new LinkedHashMap<>();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            //kiem tra co phai customer hay khong?
            if (userDTO != null) {
                if (userDTO.getRole() == 2) {
                    CartDTO cartDTO = CartDAO.getCartByUserID(userDTO.getUserID());
                    try {
                        CartDetailDTO cartDetailDTO = CartDetailDAO.getNumberProductInCart(cartDTO.getCartID(), Integer.parseInt(pid));
                        CartDetailDAO.updateProductCart(cartDetailDTO.getCartID(), Integer.parseInt(pid), Integer.parseInt(qty));
                        cart = CartDetailDAO.getCartDetail(cartDTO.getCartID());
                    } catch (Exception e) {
                    }

                }
            } else {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cart")) {
                            String[] items = cookie.getValue().split(",");
                            for (String item : items) {
                                String[] parts = item.split(":");
                                String productId = parts[0];
                                int quantity = Integer.parseInt(parts[1]);
                                if (productId.equals(pid)) {
                                    quantity = Integer.parseInt(qty);
                                }
                                if (quantity == 0) {
                                    cart.remove(productId, quantity);
                                } else {
                                    cart.put(productId, quantity);
                                }

                            }
                            break;
                        }
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                    String productId = entry.getKey();
                    int quantity = entry.getValue();
                    sb.append(productId).append(":").append(quantity).append(",");
                }
                // Remove the trailing comma
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }

                Cookie cartCookie = new Cookie("cart", sb.toString());
                cartCookie.setMaxAge(7 * 24 * 60 * 60); // Set the cookie expiration time (e.g., 7 days)
                response.addCookie(cartCookie);
            }
            session.setAttribute("cart", cart);
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
