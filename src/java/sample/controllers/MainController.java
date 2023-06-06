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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    private final String INDEX = "index.html";
    private final String HOME_CONTROLLER = "HomeController";
    private final String SEARCH_CONTROLLER = "SearchController";
    private final String STAFF_HOME_CONTROLLER = "StaffHomeController";
    private final String STAFF_ORDER_CONTROLLER = "StaffOrderController";
    private final String ADD_ITEM_TO_CART = "AddItemToCartServlet";
    private final String CHECK_OUT_TROLLER = "CheckOutOrderServlet.jsp";
    private final String CONFIRM_CHECK_OUT = "ConfirmCheckOutServlet.jsp";
    private final String PURCHASE = "PurcharHistoryController";
    
    
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
        String url = INDEX;
        String btAction = request.getParameter("btAction");
        try{
            if(btAction == null){
                url = HOME_CONTROLLER;
            } else if (btAction.equals("Search")){
                url = SEARCH_CONTROLLER;
            } else if (btAction.equals("Home")){
                url = HOME_CONTROLLER;
            } else if (btAction.equals("StaffHome")){
                url = STAFF_HOME_CONTROLLER;
            } else if (btAction.equals("StaffOrderHome")){
                url = STAFF_ORDER_CONTROLLER;
            }else if(btAction.equals("Add")){
                url = ADD_ITEM_TO_CART;
            }else if(btAction.equals("Check Out Selected Books")){
                url = CHECK_OUT_TROLLER;
            }else if(btAction.equals("Check Out")){
                url=CONFIRM_CHECK_OUT;
            }else if(btAction.equals("Purchase")){
                url = PURCHASE;
            }
            
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
