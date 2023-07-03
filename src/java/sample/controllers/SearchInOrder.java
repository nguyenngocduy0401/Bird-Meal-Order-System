/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
 * @author Admin
 */
@WebServlet(name = "SearchInOrder", urlPatterns = {"/SearchInOrder"})
public class SearchInOrder extends HttpServlet {

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
     * @throws javax.naming.NamingException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        String searchValue = request.getParameter("searchValue");
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        String username = user.getUserName();
        try {
            PrintWriter out = response.getWriter();
            OrderDAO dao = new OrderDAO();
            ProductDAO productDAO = new ProductDAO();
            List<OrderDTO> listOrder = dao.loadOrderByUsername(username);
            for (OrderDTO order : listOrder) {
                ArrayList<ProductDTO> productsList = productDAO.getProductInOrder(order.getOrderID());
                order.setProductsList(productsList);
            }
            List<OrderDTO> listOrderSearch = new ArrayList<>();
            listOrder.forEach((order) -> {
                boolean contains = false;
                for (ProductDTO pro : order.getProductsList()) {
                    if (pro.getProductName().contains(searchValue)) {
                        contains = true;
                    }
                }
                if (contains) {
                    listOrderSearch.add(order);
                }
            });
            if (listOrderSearch.isEmpty()) {
                out.print("<h5 class=\"text-uppercase mb-3\">Không có đơn hàng nào được tìm thấy!!</h5>");
            } else {
                listOrderSearch.forEach((dto) -> {
                    out.print("<div class=\"blog-item product\">\n"
                            + "                                    <div class=\"row g-0 bg-light overflow-hidden\">\n"
                            + "                                        <div class=\"col-12 h-50 d-flex flex-column justify-content-center\">\n"
                            + "                                            <div class=\"p-4\">\n"
                            + "                                                <div class=\"d-flex mb-3 \">\n");

                    switch (dto.getStatus()) {
                        case 0:
                            out.print("<small class=\"me-3 col-4\">Canceled</small>\n");
                            break;
                        case 1:
                            out.print("<small class=\"me-3 col-4\">Waiting</small>\n");
                            break;
                        case 2:
                            out.print("<small class=\"me-3 col-4\">Confirmed</small>\n");
                            break;
                        case 3:
                            out.print("<small class=\"me-3 col-4\">Shipping</small>\n");
                            break;
                        case 4:
                            out.print("<small class=\"me-3 col-4\">Complete</small>\n");
                            break;
                    }
                    out.print("<small><i class=\"col-4 bi bi-calendar-date me-2\"></i>" + dto.getDate() + "</small>\n"
                            + "                                                    <small class=\"add\"><p >" + dto.getOrderAddress() + "</p></small>\n"
                            + "                                                </div>\n"
                            + "                                                <div>");
                    dto.getProductsList().forEach((product) -> {
                        out.print("<div class=\"row border product-inrow\">\n"
                                + "                                                            <div class=\"col-md-2 img-fluid\">\n"
                                + "                                                                <img src=" + product.getImgPath() + " alt=\"\" class=\"img-fluid d-none d-md-block rounded mb-2 shadow \">\n"
                                + "                                                            </div>\n"
                                + "                                                            <div class=\"col-md-8 text-left mt-sm-2\">\n"
                                + "                                                                <h4>" + product.getProductName() + "</h4>\n"
                                + "                                                                <p class=\"font-weight-light\">" + product.getProductDetail() + "</p>\n"
                                + "                                                            </div>\n"
                                + "                                                            <div class=\"col-md-2 text-right mt-sm-2\">\n"
                                + "                                                                <p > Price: " + product.getPrice() + " $</p>\n"
                                + "                                                                <p class=\"font-weight-light text-right\">x " + product.getQuantity() + "</p>\n"
                                + "                                                            </div>\n"
                                + "                                                        </div>\n");
                    });
                    out.print(" </div>\n"
                            + "                                            </div>\n"
                            + "                                        </div>\n"
                            + "<button type=\"submit\" value=\"Add\" onclick=\"addToCart(${dto.orderID})\" class=\"btn btn-primary col-3 btn-buy\" type=\"button\">\n"
                            + "                                                Buy again\n"
                            + "                                            </button>"
                            + "                                    </div>\n"
                            + "                                </div> ");
                });
            }
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
            Logger.getLogger(SearchInOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchInOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchInOrder.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchInOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchInOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchInOrder.class.getName()).log(Level.SEVERE, null, ex);
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
