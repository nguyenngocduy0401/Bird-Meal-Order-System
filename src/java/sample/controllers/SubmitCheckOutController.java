/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.AddressDAO;
import sample.dao.CartDAO;
import sample.dao.CartDetailDAO;
import sample.dao.OrderDAO;
import sample.dao.OrderDetailGuestDAO;
import sample.dao.OrderDetailsDAO;
import sample.dao.OrderGuestDAO;
import sample.dto.AddressDTO;
import sample.dto.CartDTO;
import sample.dto.InformationCreateError;
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
            String fullName = request.getParameter("txtFullName");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String notes = request.getParameter("txtNotes");
            double shippingFee;
            if (request.getParameter("shippingFeeValue") != null && !request.getParameter("shippingFeeValue").isEmpty()) {
                 shippingFee = Double.parseDouble(request.getParameter("shippingFeeValue"));
            }else{
             shippingFee = 0;
            }
            String province = request.getParameter("ddlProvince");
            String district = request.getParameter("ddlDistrict");
            String ward = request.getParameter("ddlWard");
            String addressDetails = request.getParameter("txtAddressDetails");
            String address = addressDetails + ", " + ward + ", " + district + ", " + province;
            HttpSession session = request.getSession(true);
            LinkedHashMap<String, Integer> cart = (LinkedHashMap<String, Integer>) session.getAttribute("cartCheckOutForGuest");
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            ArrayList<AddressDTO> addressList = (ArrayList<AddressDTO>) session.getAttribute("addressList");
            InformationCreateError errors = new InformationCreateError();
            boolean foundError = false;
            String selectAdd = request.getParameter("selectAddress");
            try {
                //check input
                if (selectAdd == null) {
                    if (fullName.trim().length() < 2
                            || fullName.trim().length() > 50) {
                        foundError = true;
                        errors.setFullnameLengthError("Full name is required input from 2 to 50 characters");
                    }
                    if (InformationCreateError.validPhoneNumber(phoneNumber) == false) {
                        foundError = true;
                        errors.setPhoneNumberFormatError("Please enter a valid mobile phone number. Enter 10 digits starting from 0");
                    }
                    if (province.isEmpty()) {
                        foundError = true;
                        errors.setProvinceNotSelect("Please select province");
                    }
                    if (district.isEmpty()) {
                        foundError = true;
                        errors.setProvinceNotSelect("Please select district");
                    }
                    if (ward.isEmpty()) {
                        foundError = true;
                        errors.setProvinceNotSelect("Please select ward");
                    }
                    if (addressDetails.trim().length() < 2
                            || addressDetails.trim().length() > 50) {
                        foundError = true;
                        errors.setFullnameLengthError("Address details is required input from 2 to 50 characters");
                    }
                } else  {
                    foundError = false;
                }
                if (foundError) {
                    request.setAttribute("PLACE_ORDER_INFORMATION_ERROR", errors);
                    RequestDispatcher rd = request.getRequestDispatcher("checkOutForGuest.jsp");
                    rd.forward(request, response);
                } else {
                    //create order
                    if (userDTO != null) {
                        if (addressList != null && !addressList.isEmpty()) {
                            int addressID = Integer.parseInt(request.getParameter("selectAddress"));
                            AddressDTO addressDTO = AddressDAO.getAddressByID(addressID);
                            int orderID = OrderDAO.createNewOrderForCustomer(userDTO.getUserID(), addressDTO.getFullName(), addressDTO.getPhoneNumber(), 1, addressDTO.getAddressDetail(), notes, shippingFee);
                            if (orderID != -1) {
                                OrderDetailsDAO.createOrderDetailsForCustomer(orderID, cart);
                                CartDTO cartDTO = CartDAO.getCartByUserID(userDTO.getUserID());
                                CartDetailDAO.cleanCart(cartDTO.getCartID());
                                response.sendRedirect("checkOutSuccess.jsp");
                            }
                        } else {

                            AddressDAO.createNewAddress(userDTO.getUserID(), fullName, phoneNumber, address);
                            int orderID = OrderDAO.createNewOrderForCustomer(userDTO.getUserID(), fullName, phoneNumber, 1, address, notes, shippingFee);
                            if (orderID != -1) {
                                OrderDetailsDAO.createOrderDetailsForCustomer(orderID, cart);
                                CartDTO cartDTO = CartDAO.getCartByUserID(userDTO.getUserID());
                                CartDetailDAO.cleanCart(cartDTO.getCartID());
                                response.sendRedirect("checkOutSuccess.jsp");
                            }
                        }

                    } else {

                        int orderID = OrderDAO.createNewOrderForGuest(fullName, phoneNumber, 1, address, notes, shippingFee);
                        if (orderID != -1) {
                            boolean finishCheckOut = OrderDetailsDAO.createOrderDetailsForCustomer(orderID, cart);
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
                                response.sendRedirect("checkOutSuccess.jsp");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log("SubmitCheckOutController_Exception" + e.getMessage());
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
