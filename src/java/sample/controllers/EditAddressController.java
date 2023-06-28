/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.AddressDAO;
import sample.dto.AddressDTO;
import sample.dto.InformationCreateError;

/**
 *
 * @author Duy
 */
public class EditAddressController extends HttpServlet {

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
            String province = request.getParameter("ddlProvince");
            String district = request.getParameter("ddlDistrict");
            String ward = request.getParameter("ddlWard");
            String addressDetails = request.getParameter("txtAddressDetails");
            String address = addressDetails + ", " + ward + ", " + district + ", " + province;
            HttpSession session = request.getSession(true);
            InformationCreateError errors = new InformationCreateError();
            AddressDTO addressDTO = (AddressDTO) session.getAttribute("addressEdit");
            boolean foundError = false;
            try {
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
                if (foundError) {
                    request.setAttribute("PLACE_ORDER_INFORMATION_ERROR", errors);
                    RequestDispatcher rd = request.getRequestDispatcher("editAddress.jsp");
                    rd.forward(request, response);
                } else {
                    if (addressDTO != null) {
                        boolean check = AddressDAO.updateAddress(addressDTO.getAddressID(), fullName, phoneNumber, address);
                        if (check) {
                            response.sendRedirect("addresses.jsp");
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
