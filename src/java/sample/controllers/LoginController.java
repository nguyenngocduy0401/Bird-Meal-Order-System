/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.UserDAO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class LoginController extends HttpServlet {

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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String save = request.getParameter("savelogin");
            UserDTO user = null;
            try {
                if (username == null || username.equals("") || password == null || password.equals("")) {
                    Cookie[] c = request.getCookies();
                    String token = "";
                    if (c != null) {
                        for (Cookie aCookie : c) {
                            if (aCookie.getName().equals("selector")) {
                                token = aCookie.getValue();
                            }
                        }
                    }
                    if (!token.equals("")) {
                        response.sendRedirect("personalPage.jsp");
                    } else {
                        response.sendRedirect("errorpage.html");
                    }
                } else {
                    user = UserDAO.getUser(username, password);
                    System.out.println(user.getUserName());
                    if (user != null) {
                        //admin
                        if (user.getRole() == 0) {
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("name", user.getFullName());
                                session.setAttribute("userName", username);
                                //create a cookie and attach it to response object
                                if (save != null) {
                                    String token = "ABC123";
                                    UserDAO.updateToken(token, username);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 2);// this is a sample
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("admin.jsp");
                            }

                        } else if (user.getRole() == 1) {//satff
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("name", user.getFullName());
                                session.setAttribute("userName", username);
                                //create a cookie and attach it to reponse object
                                if (save != null) {
                                    String token = "ABC123";
                                    UserDAO.updateToken(token, username);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 2);// this is a sample
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("staff.jsp");
                            }
                        } else if (user.getRole() == 2) {//customer
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("name", user.getFullName());
                                session.setAttribute("userName", username);
                                //create a cookie and attach it to reponse object
                                if (save != null) {
                                    String token = "ABC123";
                                    UserDAO.updateToken(token, username);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 2);// this is a sample
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("home.jsp");
                            } else {
                                response.sendRedirect("invalid.html");
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
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
