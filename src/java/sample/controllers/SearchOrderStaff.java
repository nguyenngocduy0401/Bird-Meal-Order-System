/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.OrderDAO;
import sample.dao.ProductDAO;
import sample.dto.OrderDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;

/**
 *
 * @author haong
 */
@WebServlet(name = "SearchOrderStaff", urlPatterns = {"/SearchOrderStaff"})
public class SearchOrderStaff extends HttpServlet {

    private String ERROR_PAGE = "errorRegistration.html";
    private String ORDERS_LIST_PAGE = "staffOrdersList.jsp";

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
        String searchValue = request.getParameter("txtSearchValue").trim().toLowerCase();
        String url = ERROR_PAGE;
        try {
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (userDTO.getRole() == 1) {
                OrderDAO dao = new OrderDAO();
                ProductDAO productDAO = new ProductDAO();
                ArrayList<OrderDTO> listOrder = dao.loadOrder();
                listOrder.sort(Comparator.comparing(OrderDTO::getOrderID).reversed());
                ArrayList<OrderDTO> listOrderSearch = new ArrayList<>();
                OrderDTO orderSearch = new OrderDTO();
                try {
                    int orderID = Integer.parseInt(searchValue);
                    orderSearch = OrderDAO.searchOrderByOrderID(orderID);
                    if (orderSearch != null) {
                        ArrayList<ProductDTO> productList = productDAO.getProductByOrderID(orderID);
                        orderSearch.setProductsList(productList);
                        request.setAttribute("ORDERS_SEARCH_BY_ORDERID", orderSearch); 
                    } else {
                        request.setAttribute("ORDERS_SEARCH_BY_ORDERID_NOT_FOUND", false); 
                    }
                } catch (NumberFormatException e) {
                    for (OrderDTO orderDTO : listOrder) {
                        if (orderDTO.getFullName().toLowerCase().contains(searchValue)) {
                            ArrayList<ProductDTO> productList = productDAO.getProductByOrderID(orderDTO.getOrderID());
                            orderDTO.setProductsList(productList);
                            listOrderSearch.add(orderDTO);
                        }
                    }
                    request.setAttribute("ORDERS_LIST_CUSTOMER", listOrderSearch);
                }
                url = ORDERS_LIST_PAGE;
            }
        } catch (ClassNotFoundException ex) {
            log("SearchOrderStaff_ClassNotFoundException" + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchOrderStaff_NamingException" + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchOrderStaff_SQLException" + ex.getMessage());
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
