/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.OrderDAO;
import sample.dao.ProductDAO;
import sample.dao.UserDAO;
import sample.dto.ProductDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminPageController", urlPatterns = {"/AdminPageController"})
public class AdminPageController extends HttpServlet {
    private final String ADMIN_PAGE = "admin.jsp";
    private final String ERROR_PAGE = "error.jsp";
    
 
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
        int month = LocalDateTime.now().getMonthValue();
        int year = LocalDateTime.now().getYear();
        String url = ADMIN_PAGE;
        try {
            double thisMonthEarn = OrderDAO.getMonthEarning(month, year);
            double thisYearEarn = OrderDAO.getYearEarning(year);
            double earning1 = OrderDAO.getMonthEarning(1, year);
            double earning2 = OrderDAO.getMonthEarning(2, year);
            double earning3 = OrderDAO.getMonthEarning(3, year);
            double earning4 = OrderDAO.getMonthEarning(4, year);
            double earning5 = OrderDAO.getMonthEarning(5, year);
            double earning6 = OrderDAO.getMonthEarning(6, year);
            double earning7 = OrderDAO.getMonthEarning(7, year);
            double earning8 = OrderDAO.getMonthEarning(8, year);
            double earning9 = OrderDAO.getMonthEarning(9, year);
            double earning10 = OrderDAO.getMonthEarning(10, year);
            double earning11 = OrderDAO.getMonthEarning(11, year);
            double earning12 = OrderDAO.getMonthEarning(12, year);
            int amountUser = UserDAO.getAmountUser();
            List<ProductDTO> listProduct = ProductDAO.listTop5Product();
            request.setAttribute("YEAREARN", thisYearEarn);
            request.setAttribute("MONTHEARN", thisMonthEarn);
            request.setAttribute("AMOUNTUSER", amountUser);
            request.setAttribute("LISTTOP5", listProduct);
            request.setAttribute("MONTH1", earning1);
            request.setAttribute("MONTH2", earning2);
            request.setAttribute("MONTH3", earning3);
            request.setAttribute("MONTH4", earning4);
            request.setAttribute("MONTH5", earning5);
            request.setAttribute("MONTH6", earning6);
            request.setAttribute("MONTH7", earning7);
            request.setAttribute("MONTH8", earning8);
            request.setAttribute("MONTH9", earning9);
            request.setAttribute("MONTH10", earning10);
            request.setAttribute("MONTH11", earning11);
            request.setAttribute("MONTH12", earning12);
        }finally{
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
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
