/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.TokenDAO;
import sample.dao.UserDAO;
import sample.dto.InformationCreateError;
import sample.dto.TokenDTO;
import sample.dto.UserDTO;
import sample.utils.MailService;

/**
 *
 * @author haong
 */
@WebServlet(name = "SendEmailForgetPasswordServlet", urlPatterns = {"/SendEmailForgetPasswordServlet"})
public class SendEmailForgetPasswordServlet extends HttpServlet {
    private final String FORGET_PASSWORD_PAGE = "forgetPassword.jsp";
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
        String url = FORGET_PASSWORD_PAGE;
        String email = request.getParameter("txtEmail").trim();
        InformationCreateError errors = new InformationCreateError();
        boolean foundError = false;
        String token = UUID.randomUUID().toString();
        String link = "<a href='http://localhost:8080/Bird_Meal_Order_System/ConfirmationEmailServlet?token=" + token + "'>Click here</a>";
        try {
            UserDAO dao = new UserDAO();
            UserDTO dto = dao.getUserByEmail(email);
            if (dto != null && dto.isStatus() == false) {
                errors.setAccountNotAvailable("This gmail has been locked please contact birdmealordersystem@gmail.com to unlock");
                foundError = true;
            } else if (dto == null) {
                errors.setEmailNotFound("Couldn't find your account");
                foundError = true;
            }
            if (InformationCreateError.validEmail(email) == false) {
                foundError = true;
                errors.setEmailFormatError("Please enter a valid email!");
            }
            if (foundError) {
                request.setAttribute("EMAIL_ERROR", errors);
            } else {
                TokenDAO tdao = new TokenDAO();
                TokenDTO tdto = new TokenDTO(
                        token,
                        Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)),
                        Timestamp.valueOf(LocalDateTime.now().plusMinutes(15).truncatedTo(ChronoUnit.SECONDS)),
                        email);
                tdao.saveConfirmationToken(tdto);
                MailService mailservice = new MailService();
                mailservice.sendResetPasswordEmail(email, link);
                request.setAttribute("SEND_EMAIL_SUCCESS", true);
            }
        } catch (ClassNotFoundException ex) {
            log("SendEmailForgetPasswordServlet_ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            log("SendEmailForgetPasswordServlet_SQLException" + ex.getMessage());
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
