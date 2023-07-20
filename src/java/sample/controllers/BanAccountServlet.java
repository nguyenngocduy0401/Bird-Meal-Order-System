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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.UserDAO;
import sample.dto.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BanAccountServlet", urlPatterns = {"/BanAccountServlet"})
public class BanAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        String banUsername = request.getParameter("banUsername");
        String bool = request.getParameter("bool");
        boolean flag = Boolean.parseBoolean(bool);
        if (flag) {
            UserDAO.banUser(banUsername, false);
        } else if (!flag) {
            UserDAO.banUser(banUsername, true);
        }
        List<UserDTO> listUser = UserDAO.getAllUser();
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class=\"container-fluid\">\n"
                    + "\n"
                    + "                    <!-- Page Heading -->\n"
                    + "                    <h1 class=\"h3 mb-2 text-gray-800\">Account Manager</h1>\n"
                    + "\n"
                    + "                    <!-- DataTales Example -->\n"
                    + "                    <div class=\"card shadow mb-4\">\n"
                    + "                        <div class=\"card-header py-3\">\n"
                    + "                            <h6 class=\"m-0 font-weight-bold text-primary\">Account Table</h6>\n"
                    + "                        </div>\n"
                    + "<div class=\"card shadow mb-4\">\n"
                    + "                        <div class=\"card-header py-3\">\n"
                    + "                            <div class=\"d-flex justify-content-between align-items-center\">\n"
                    + "                                <form action=\"MainController\">\n"
                    + "                                    <button class=\"btn btn-outline-primary mr-auto\" name=\"btAction\" value=\"CreateStaff\">Add new staff</button>\n"
                    + "                                </form>\n"
                    + "                                <div class=\"form-inline navbar-search\">\n"
                    + "                                    <div class=\"input-group\">\n"
                    + "                                        <input type=\"text\" id=\"search-box\" class=\"form-control bg-light border-0 small\" placeholder=\"Search for...\" name=\"txtSearchValue\"\n"
                    + "                                               aria-label=\"Search\" aria-describedby=\"basic-addon2\">\n"
                    + "                                        <div class=\"input-group-append\">\n"
                    + "                                            <button class=\"btn btn-primary\" name=\"btAction\" onclick=\"searchAccount()\">\n"
                    + "                                                <i class=\"fas fa-search fa-sm\"></i>\n"
                    + "                                            </button>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>"
                    + "                        <div class=\"card-body\">\n"
                    + "                            <div class=\"table-responsive\">\n"
                    + "                                <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">\n"
                    + "                                    <thead>\n"
                    + "                                        <tr>\n"
                    + "                                            <th>Name</th>\n"
                    + "                                            <th>Username</th>\n"
                    + "                                            <th>Emails</th>\n"
                    + "                                            <th>Role</th>\n"
                    + "                                            <th>Gender</th>\n"
                    + "                                            <th>Status</th>\n"
                    + "                                            <th>Phone number</th>\n"
                    + "                                            <th>Ban</th>\n"
                    + "                                        </tr>\n"
                    + "                                    </thead>\n"
                    + "                                    <tbody>\n");

            listUser.forEach((dto) -> {
                out.print("<tr>\n"
                        + "                                            <td>" + dto.getFullName() + "</td>\n"
                        + "                                            <td>" + dto.getUserName() + "</td>\n"
                        + "                                            <td>" + dto.getEmail() + "</td>\n");
                switch (dto.getRole()) {
                    case 0:
                        out.print("<td>Admin</td>\n");
                        break;
                    case 1:
                        out.print("<td>Staff</td>\n");
                        break;
                    default:
                        out.print("<td>Customer</td>\n");
                        break;
                }
                if (dto.isGender()) {
                    out.print("<td>Female</td>\n");
                } else {
                    out.print("<td>Male</td>\n");
                }
                if (dto.isStatus()) {
                    out.print("<td>Available</td>\n");
                } else {
                    out.print("<td>Unavailable</td>\n");
                }
                out.print("                                            <td>" + dto.getPhoneNumber() + "</td>\n");
                if (dto.getRole() != 0) {
                    if (dto.isStatus()) {
                        out.print("<td><a class=\"btn btn-outline-primary mr-auto\" onclick=\"banAccount(\'" + dto.getUserName() + "\', true)\" value=\"CreateStaff\">Ban</a></td>\n");
                    } else {
                        out.print("<td><a class=\"btn btn-outline-primary mr-auto\" onclick=\"banAccount(\'" + dto.getUserName() + "\', false)\" value=\"CreateStaff\">Unban</a></td>\n");
                    }
                } else {
                    if (dto.isStatus()) {
                        out.print("<td></td>");
                    } else {
                        out.print("<td></td>");
                    }
                }
            });

            out.print("                                    </tbody>\n"
                    + "                                </table>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "\n"
                    + "                </div>");
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
        } catch (SQLException ex) {
            Logger.getLogger(BanAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BanAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(BanAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BanAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
