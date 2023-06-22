/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.AddressDAO;
import sample.dao.CartDAO;
import sample.dao.CartDetailDAO;
import sample.dto.AddressDTO;
import sample.dto.CartDTO;
import sample.dto.CartDetailDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class CheckOutProductController extends HttpServlet {

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
            String[] pid = request.getParameterValues("select[]");
            HttpSession session = request.getSession(true);
            LinkedHashMap<String, Integer> cart = (LinkedHashMap<String, Integer>) session.getAttribute("cart");
            LinkedHashMap<String, Integer> cartCheckOut = new LinkedHashMap<>();
            ArrayList<AddressDTO> list ;
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (cart != null) {
                for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                    String productId = entry.getKey();
                    int quantity = entry.getValue();

                    for (String pidValue : pid) {
                        if (Integer.parseInt(pidValue) == Integer.parseInt(productId)) {
                            cartCheckOut.put(productId, quantity);
                        }
                    }
                }
            }
            if (userDTO != null) {
                if (userDTO.getRole() == 2) {
                    list = AddressDAO.getAddress(userDTO.getUserID());
                    session.setAttribute("addressList", list);
                }
            }
            
                session.setAttribute("cartCheckOutForGuest", cartCheckOut);
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
