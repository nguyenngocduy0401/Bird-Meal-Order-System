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
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.AddressDAO;
import sample.dao.OrderDAO;
import sample.dao.OrderDetailGuestDAO;
import sample.dao.OrderDetailsDAO;
import sample.dao.OrderGuestDAO;
import sample.dto.AddressDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class SubmitCheckOutController extends HttpServlet {

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
            String name = request.getParameter("txtName");
            String phone = request.getParameter("txtPhoneNumber");
            String address = request.getParameter("txtAddress");
            String notes = request.getParameter("txtNotes");
            HttpSession session = request.getSession(true);
            LinkedHashMap<String, Integer> cart = (LinkedHashMap<String, Integer>) session.getAttribute("cartCheckOutForGuest");
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            ArrayList<AddressDTO> addressList = (ArrayList<AddressDTO>) session.getAttribute("addressList");
            try {
                if (userDTO != null) {
                    if (addressList != null && !addressList.isEmpty()) {
                        int addressID = Integer.parseInt(request.getParameter("selectAddress"));
                        AddressDTO addressDTO = AddressDAO.getAddressByID(addressID);
                        int orderID = OrderDAO.createNewOrderForCustomer(userDTO.getUserID(), addressDTO.getFullName(), addressDTO.getPhoneNumber(), 1, addressDTO.getAddressDetail(), notes);
                        if (orderID != -1) {
                            OrderDetailsDAO.createOrderDetailsForCustomer(orderID, cart);
                            response.sendRedirect("MainController?btAction=Home");
                        }
                    } else {
                        AddressDAO.createNewAddress(userDTO.getUserID(), name, phone, address);
                        int orderID = OrderDAO.createNewOrderForCustomer(userDTO.getUserID(), name, phone, 1, address, notes);
                        if (orderID != -1) {
                            OrderDetailsDAO.createOrderDetailsForCustomer(orderID, cart);
                            response.sendRedirect("MainController?btAction=Home");
                        }
                    }

                } else {
                    int orderID = OrderGuestDAO.createNewOrderForGuest(name, phone, 1, address, notes);
                    if (orderID != -1) {
                        boolean finishCheckOut = OrderDetailGuestDAO.createOrderDetailsForGuest(orderID, cart);
                        if (finishCheckOut) {
                            Cookie[] cookies = request.getCookies();
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("cart")) {
                                        cookie.setMaxAge(0);
                                        response.addCookie(cookie);
                                        break;
                                    }
                                }
                            }
                            session.removeAttribute("cart");
                            session.removeAttribute("cartCheckOutForGuest");
                            response.sendRedirect("MainController?btAction=Home");
                        }
                    }
                }
            } catch (Exception e) {
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
