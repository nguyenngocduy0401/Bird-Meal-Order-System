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
import sample.dao.FeedbackDAO;
import sample.dao.ReportDAO;
import sample.dao.UserDAO;
import sample.dto.FeedbackDTO;
import sample.dto.ReportDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AcceptReport", urlPatterns = {"/AcceptReport"})
public class AcceptReport extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        boolean flag = false;
        int reportID = Integer.parseInt(request.getParameter("reportID"));
        int status = Integer.parseInt(request.getParameter("status"));
        ReportDTO report = ReportDAO.getReportByReportID(reportID);
        FeedbackDTO fb = FeedbackDAO.getFeedbackByID(report.getFeedbackID());
        UserDTO user = UserDAO.getUserByID(fb.getUserID());
        try {
            if (user != null) {
                if (status == 1) {
                    int numberReport = user.getNumberReport() + report.getRate();
                    if (numberReport >= 10) {
                        flag = UserDAO.updateNumberOfReport(fb.getUserID(), 10);
                    } else {
                        flag = UserDAO.updateNumberOfReport(fb.getUserID(), numberReport);
                    }

                    if (flag) {
                        ReportDAO.updateReportFeedback(reportID, status);
                        if (numberReport >= 10) {
                            UserDAO.banUser(user.getUserName(), false);
                        }
                    }
                } else {
                    ReportDAO.updateReportFeedback(reportID, status);
                }

            }

        } finally {
            List<ReportDTO> result = ReportDAO.getAllReport();
            request.setAttribute("LIST_REPORT", result);
            request.getRequestDispatcher("viewReportAdmin.jsp").forward(request, response);
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
            Logger.getLogger(AcceptReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcceptReport.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AcceptReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcceptReport.class.getName()).log(Level.SEVERE, null, ex);
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
