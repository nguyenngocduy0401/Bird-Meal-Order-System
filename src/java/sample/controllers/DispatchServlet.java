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
 * @author DucAnh
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
private final String HOME_PAGE = "homepage.jsp";
private final String LIST_PRODUCT_CONTROLLER = "ListProductServlet";
private final String ADD_ITEM_TO_CART = "AddItemToCartServlet";
private final String VIEW_YOUR_CART = "viewcart.jsp";
private final String SHOPPING_PRODUCT = "shopping.jsp";
private final String CHECK_OUT_TROLLER = "CheckOutOrderServlet.jsp";
private final String CONFIRM_CHECK_OUT = "ConfirmCheckOutServlet.jsp";
//private final String REMOVE_ITEM_FORM_CART = "RemoveItemServlet";

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
        String url = HOME_PAGE;
        //Which button did user clicked?
        String button = request.getParameter("btAction");

        try {
            if (button == null) {
                //transfer to Login Page
            }else if(button.equals("View Your Cart")){
                url = VIEW_YOUR_CART;
            }else if(button.equals("Add")){
                url = ADD_ITEM_TO_CART;
            }else if(button.equals("shopping")){
                url=SHOPPING_PRODUCT;
            }else if(button.equals("Check Out Selected Books")){
                url = CHECK_OUT_TROLLER;
            }else if(button.equals("Check Out")){
                url=CONFIRM_CHECK_OUT;
            }
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
